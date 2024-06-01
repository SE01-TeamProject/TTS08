let beforeAssignee;//수정 전의 Assignee값
//html이 load되는 즉시 실행되는 함수
document.addEventListener('DOMContentLoaded', function() {
    getAssigneeCandidates();
    initializeComments();
});

function clearAssigneeCandidates() {
    document.getElementById('assignee-input').replaceChildren();
}

 function getAssigneeCandidates(){
    clearAssigneeCandidates();
    fetch('http://localhost:8080/project/' + localStorage.getItem("projectId"))
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            addOption('assignee-input', data.developer1);
            addOption('assignee-input', data.developer2);
            addOption('assignee-input', data.developer3);
            console.log("모든 개발자를 목록에 추가하였습니다.");
            initializeIssueData();
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
 }

async function initializeIssueData() {
    const issuenumContainer = document.getElementById('issue-num');
    const reporterContainer = document.getElementById('reporter-name');
    const assigneeContainer = document.getElementById('assignee-name');
    const titleContainer = document.getElementById('title-input');
    const priorityContainer = document.getElementById('priority-input');
    const statusContainer = document.getElementById('status-input');
    const typeContainer = document.getElementById('type-input');
    const assigneeSelectContainer = document.getElementById('assignee-input');
    const descriptionContainer = document.getElementById('description-input');

    const currentIssueTitle = localStorage.getItem('currentIssueTitle');
    try {
        const response = await fetch('http://localhost:8080/issueTitle/' + currentIssueTitle);

        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }

        const data = await response.json();

        issuenumContainer.textContent = data.issuenum;
        localStorage.setItem('issuenum', data.issuenum);
        assigneeContainer.textContent = data.assignee;
        beforeAssignee = data.assignee;
        reporterContainer.textContent = data.reporter;
        titleContainer.value = data.title;
        priorityContainer.value = getPriority(data.priority);
        statusContainer.value = getStatus(data.status);
        localStorage.setItem('status', getStatus(data.status));
        typeContainer.value = getType(data.type);
        assigneeSelectContainer.value = data.assignee;
        console.log("assignee select의 목록을 [" + data.assignee + "] 로 변경하였습니다.");
        descriptionContainer.value = data.description;
        getRecommendedAssignee();
        disableInputs();
    } catch (error) {
        console.error('There was a problem with the fetch operation:', error);
    }
}

async function setIssue() {
    const id = parseInt(localStorage.getItem('issuenum'));
    const priority = getPriorityNum(document.getElementById('priority-input').value);
    const assignee = document.getElementById('assignee-input').value;
    let status = getStatusNum(document.getElementById('status-input').value);
    const level = getLevel(parseInt(localStorage.getItem('level')));
    if(level !== 'Admin' && level !== 'PL' && getStatus(status) === 'New') {
        alert("Only Admin or PL can update \"New\" Issue");
        return;
    }
    if(beforeAssignee === 'N/A' && assignee !== 'N/A') {
        status = getStatusNum("Assigned");
    }
    if(assignee !== 'N/A' && getStatus(status) === 'New') {
        alert("You cannot go back to \"New\" status.");
        return;
    }
    const description = document.getElementById('description-input').value;
    const url = 'http://localhost:8080/setIssue';

    const response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({id, priority, status, assignee, description}),
    });

    if (response.ok) {
        getAssigneeCandidates();
        alert('Issue updated.');
    } else {
        alert('Failed to update Issue');
    }
}

 function disableInputs() {
     document.getElementById('title-input').disabled = true;
     document.getElementById('description-input').setAttribute('readonly', 'true');
     document.getElementById('type-input').disabled = true;
     const assigneeSelectContainer = document.getElementById('assignee-input');
     const priorityContainer = document.getElementById('priority-input');
     const statusContainer = document.getElementById('status-input');
     const level = getLevel(parseInt(localStorage.getItem('level')));
     if (statusContainer.value === 'New') {
         statusContainer.disabled = true;
     } else if (statusContainer.value === 'Closed') {
         assigneeSelectContainer.disabled = true;
         priorityContainer.disabled = true;
     } else {
         statusContainer.disabled = false;
         priorityContainer.disabled = false;
         assigneeSelectContainer.disabled = false;
     }
     if (level !== 'Admin' && level !== 'PL') {
         assigneeSelectContainer.disabled = true;
     }
 }
//여기서부터 Comment관련함수 ------------------------------------------------------------------------------------
 async function addComment() {
             const issue = localStorage.getItem('currentIssueTitle');
             const writer = localStorage.getItem('loginId');
             const note = document.getElementById('comment-input').value;

             if(note === "") {
                 alert("내용을 작성하신 후 다시 시도해주세요.");
                 return;
             }

            fetch('http://localhost:8080/addComment', {
              method: 'POST',
              headers: {
                  'Content-Type': 'application/json'
              },
              body: JSON.stringify({issue, writer, note})
            })
              .then(response => response.text())
              .then(data => {
                  if (data === 'true') {
                      alert("Comment add Success");
                  } else {
                      alert("Comment add failed");
                  }
              });
              initializeComments();
              closeCommentModal();
         }

         async function initializeComments() {
               await clearAllComments();
               showAllComments();
         }

         function showAllComments() {
             const level = getLevel(parseInt(localStorage.getItem('level')));
             fetch('http://localhost:8080/listComment/' + localStorage.getItem('issuenum'))
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok ' + response.statusText);
                    }
                    return response.json();
                })
                  .then(data => {
                      if (Array.isArray(data)) {
                          data.forEach(comment => {// writer note date
                              showComment(comment.writer, comment.note, comment.date, level);
                          });
                      } else {
                          console.error('Expected an array but received:', data);
                      }
                  })
                  .catch(error => {
                      console.error('There was a problem with the fetch operation:', error);
                  });
        }

         function showComment(writer, note, date, level) {
            // 댓글을 추가할 컨테이너 요소 선택
            const commentsContainer = document.getElementById('comments-container');

            // 새로운 댓글 요소 생성
            const commentContainer = document.createElement('div');
            commentContainer.className = 'comment-container';

            // 댓글 헤더 생성
            const commentHead = document.createElement('span');
            commentHead.className = 'comment-head';
            commentHead.innerHTML = `Comment&nbsp;&nbsp;&nbsp;
                                     Writer: <span>${writer}</span>&nbsp;&nbsp;&nbsp;
                                     Date: <span>${date}</span>`;

            // 댓글 내용 생성
            const commentTextarea = document.createElement('textarea');
            commentTextarea.className = 'comment-history';
            commentTextarea.rows = 7;
            commentTextarea.cols = 40;
            commentTextarea.readOnly = true;
            commentTextarea.style.resize = 'none';
            commentTextarea.textContent = note;

            // 요소들을 컨테이너에 추가
            commentContainer.appendChild(commentHead);
            commentContainer.appendChild(commentTextarea);

            // 댓글 컨테이너를 댓글 목록에 추가
            commentsContainer.appendChild(commentContainer);
         }
         async function clearAllComments() {
                document.getElementById('comments-container').replaceChildren();
                await new Promise(resolve => setTimeout(resolve, 1000));
         }

         function openCommentModal() {
             if( localStorage.getItem('status') === 'Closed') {
                 alert("You cannot add a comment to Issue with status \"Closed\"");
             }
             else {
                 openPopUp('comment-popup');
             }
         }

         function closeCommentModal() {
             closePopUp('comment-popup');
             document.getElementById('comment-input').value = '';
         }
         function backToIssuePage() {
             location.href = "http://localhost:8080/issue.html";
         }
         //----------------------------------------여기까지가 comment -------------------------------------------

         function getRecommendedAssignee() {
             const description = document.getElementById('description-input').value;
             console.log("description : [" + description + "]");
             const recommendContainer = document.getElementById('recommended-assignee');
             fetch('http://localhost:8080/suggestAssignee/' + description)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok ' + response.statusText);
                    }
                    return response.json();
                })
                  .then(data => {
                      console.log("recommend Assignee : [" + data.assignee + "]" );
                      recommendContainer.textContent = data.assignee;
                  })
                  .catch(error => {
                      console.error('There was a problem with the get recommend assignee:', error);
                      recommendContainer.textContent = 'N/A';
                  });
        }

        function applyRecommendedAssignee() {
                const statusContainer = document.getElementById('status-input');
                if(statusContainer.value === 'Closed') {
                    alert("You cannot change \"Closed\" issue's assignee");
                    return;
                }
                const assigneeSelectContainer = document.getElementById('assignee-input');
                assigneeSelectContainer.value = document.getElementById('recommended-assignee').textContent;
        }