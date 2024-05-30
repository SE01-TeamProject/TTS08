 //html이 load되는 즉시 실행되는 함수
         document.addEventListener('DOMContentLoaded', function() {
              disableInputs();
              initializeIssueData();
        });

function initializeIssueData() {
    const id = localStorage.getItem('loginId');
    const currentIssueTItle = localStorage.getItem('currentIssueTitle');

    const issuenumContatiner = document.getElementById('issue-num');
    const reporterContainer = document.getElementById('reporter-name');
    const titleContainer = document.getElementById('title-input');
    const priorityContainer = document.getElementById('priority-input');
    const statusContainer = document.getElementById('status-input');
    const typeContainer = document.getElementById('type-input');
    const descriptionContainer = document.getElementById('description-input');

    reporterContainer.innerText = id;

      fetch('http://localhost:8080/issueTitle' + currentIssueTItle)
          .then(response => {
              if (!response.ok) {
                  throw new Error('Network response was not ok ' + response.statusText);
              }
              return response.json();
          })
          .then(data => {
              issuenumContatiner.textContent = data.issuenum;
              localStorage.setItem('issuenum', data.issuenum);
              titleContainer.value = data.title;
              priorityContainer.value = data.priority;
              statusContainer.value = data.status;
              typeContainer.value = data.type;
              descriptionContainer.value = data.description;
          })
          .catch(error => {
              console.error('There was a problem with the fetch operation:', error);
          });
}

function setIssue() {

}

//여기서부터 Comment관련함수 ------------------------------------------------------------------------------------
 function addComment() {
             const writer = localStorage.getItem('loginId');
             const note = document.getElementById('comment-input').value;
            fetch('http://localhost:8080/addComment', {
              method: 'POST',
              headers: {
                  'Content-Type': 'application/json'
              },
              body: JSON.stringify({writer, note})
            })
              .then(response => response.text())
              .then(data => {
                  if (data === 'true') {
                      alert("Comment add Success");
                  } else {
                      alert("Comment add failed");
                  }
              });
              showAllComments();
              closeCommentModal();
         }

         function showAllComments() {
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
                              showComment(comment.writer, comment.note, comment.date);
                          });
                      } else {
                          console.error('Expected an array but received:', data);
                      }
                  })
                  .catch(error => {
                      console.error('There was a problem with the fetch operation:', error);
                  });
        }

         function showComment(writer, note, date) {
            // 댓글을 추가할 컨테이너 요소 선택
            const commentsContainer = document.getElementById('comments-container');

            // 새로운 댓글 요소 생성
            const commentContainer = document.createElement('div');
            commentContainer.className = 'comment-container';

            // 댓글 헤더 생성
            const commentHead = document.createElement('span');
            commentHead.className = 'comment-head';
            commentHead.innerHTML = `Comment&nbsp;&nbsp;&nbsp;Writer: <span>${writer}</span>&nbsp;&nbsp;&nbsp;Date: <span>${date}</span>`;

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

        function putWriterOnHead() {
             const writerId = localStorage.getItem('loginId');
             const head = document.getElementById('comment-popup-head');
             const headInfo = document.createElement('p');
             headInfo.textContent = 'Writer : ' + writerId;
             const firstChild = head.firstChild;
             head.insertBefore(headInfo, firstChild);
        }
         function deleteWriterOnHead() {
             console.log("deleteWriterAccess");
             const head = document.getElementById('comment-popup-head');
             if (head) {
                const headInfo = head.firstElementChild;
                if (headInfo) {
                head.removeChild(headInfo);
                console.log('Element removed:', headInfo);
                }
                else {
                console.error('No child element found in #comment-popup-head');
                }
             } else {
                console.error('#comment-popup-head element not found');
             }
         }
         function openCommentModal() {
             openPopUp('comment-popup');
             putWriterOnHead();
         }
         function closeCommentModal() {
             closePopUp('comment-popup');
             deleteWriterOnHead();
             document.getElementById('comment-input').value = '';
         }
         function backToIssuePage() {
             location.href = "http://localhost:8080/issue.html";
         }

         //Admin이 누를 때, PL이 누를 때, Dev가 누를 떄, Tester가 누를 때 다 지정해줘야함. + State를 보고 분기별로 나눠서 생각
         function disableInputs() {
             document.getElementById('description-input').setAttribute('readonly', 'true');
         }