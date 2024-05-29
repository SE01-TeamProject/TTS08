 //html이 load되는 즉시 실행되는 함수
         document.addEventListener('DOMContentLoaded', function() {
              showAllIssues();
        });

        async function addIssue() {
          const project = localStorage.getItem('projectId');
		  const title = document.getElementById('title-input').value;
          const description = document.getElementById('description-input').value;
		  const reporter = localStorage.getItem('loginId');
		  const priority = document.getElementById('priority-input').value;
		  const type = document.getElementById('type-input').value;
          if(title === "" || description === "") {
               alert("제목이 비었거나 내용을 적지 않았습니다. 모두 기입 후 시도해주세요.");
               return;
          }
          fetch('http://localhost:8080/addIssue', {
              method: 'POST',
              headers: {
                  'Content-Type': 'application/json'
              },
              body: JSON.stringify({project, title, description, reporter, priority, type})
          })
              .then(response => response.text())
              .then(data => {
                  if (data === 'true') {
                      alert("Issue add success.")
                      closePopUp('issue-popup');
                  } else if (data === 'false') {
                      alert("중복된 이슈입니다.");
                  }
              });
              await delay(200);//alert창 무시하고 페이지 리로드되는 것 방지
              location.href = "http://localhost:8080/issue.html";
        }

        async function showAllIssues() {
            const currentProjectId = localStorage.getItem('projectId');
            try {
                const response = await fetch('http://localhost:8080/listIssue/' + currentProjectId);
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                const issueLists = await response.json();
                issueLists.forEach(issue => {//issuenum title priority state type Date
                    const tableBody = document.getElementById('issue-table-body');
                    const newRow = document.createElement('tr');
                    newRow.className = 'tc';

                    const issueNumCell = document.createElement('td');
                    issueNumCell.textContent = issue.issuenum;
                    newRow.appendChild(issueNumCell);

                    const titleCell = document.createElement('td');
                    const titleButton = document.createElement('button');
                    titleButton.textContent = issue.title;
                    titleButton.onclick = function () {
                        gotoissueUpdatePage(issue.title);
                    };
                    titleCell.appendChild(titleButton);
                    newRow.appendChild(titleCell);

                    const priorityCell = document.createElement('td');
                    priorityCell.textContent = issue.priority;
                    newRow.appendChild(priorityCell);

                    const stateCell = document.createElement('td');
                    stateCell.textContent = issue.status;
                    newRow.appendChild(stateCell);

                    const typeCell = document.createElement('td');
                    typeCell.textContent = issue.type;
                    newRow.appendChild(typeCell);

                    const dateCell = document.createElement('td');
                    dateCell.textContent = issue.date;
                    newRow.appendChild(dateCell);

                    tableBody.appendChild(newRow);
                 });
            } catch (error) {
                console.error('Fetch error:', error);
            }
        }

        function openIssueAddModal() {
             fetch('http://localhost:8080/userName/' + localStorage.getItem('loginId'))
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok ' + response.statusText);
                    }
                    return response.json();
                })
                  .then(data => {
                      const userLevel = data.level;
                      if(data.level === 0 || data.level === 3) {
                           putCurrentUserId();
                           openPopUp('issue-popup');
                      }
                      else {
                          alert("Only admin or tester can add issue");
                      }
                  })
                  .catch(error => {
                      console.error('There was a problem with the fetch operation:', error);
                  });
        }

        function putCurrentUserId() {
            const id = localStorage.getItem('loginId');
            const reporterContainer = document.getElementById('reporter-name');
            reporterContainer.innerText = id;
        }

        function gotoissueUpdatePage(issueTitle) {
            localStorage.setItem('currentIssueTitle', issueTitle); //캐싱해뒀다가 issueupdate페이지로 가서 현재 접속한 issue에 대해 알려줄 것임
            location.href = "http://localhost:8080/issueupdate.html";
        }

       function disableInput() {
            //get처리로 권한 받아서 해줘야함.
             const level = 2; //get처리로 받은 권한레벨 순서대로 admin, PL, developer, tester
             //document.getElementById('type').value = 'Task';
             //document.getElementById('type').disabled = true;
             //document.getElementById('description-input').setAttribute('readonly', 'true');
       }