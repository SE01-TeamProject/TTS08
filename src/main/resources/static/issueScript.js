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

        function clearAllIssues() {
            document.getElementById('issue-table-body').replaceChildren();
        }

        async function showAllIssues() {
            clearAllIssues();
            const currentProjectId = localStorage.getItem('projectId');
            try {
                const response = await fetch('http://localhost:8080/listIssue/' + currentProjectId);
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                const issueLists = await response.json();
                issueLists.forEach(issue => {//issuenum title priority state type Date
                    showIssue(
                        issue.issuenum,
                        issue.title,
                        issue.priority,
                        issue.status,
                        issue.type,
                        issue.reporter,
                        issue.assignee,
                        issue.date
                    );
                 });
            } catch (error) {
                console.error('Fetch error:', error);
            }
        }

        async function showSearchedIssues() {
            clearAllIssues();
            const searchStr = document.getElementById('search-input').value;
            const searchTarget = document.getElementById('search-select').value;
            let baseStr;

            if(searchStr === '') {
                alert("Search input is empty. Fill the input and try again.");
                return;
            }

            const currentProjectId = localStorage.getItem('projectId');
            try {
                const response = await fetch('http://localhost:8080/listIssue/' + currentProjectId);
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                const issueLists = await response.json();
                issueLists.forEach(issue => {//issuenum title priority state type Date
                    switch (searchTarget) {
                        case 'title':
                            baseStr = issue.title;
                            break;
                        case 'priority':
                            baseStr = getPriority(issue.priority);
                            break;
                        case 'state':
                            baseStr = getStatus(issue.status);
                            break;
                        case 'type':
                            baseStr = getType(issue.type);
                            break;
                        case 'reporter' :
                            baseStr = issue.reporter;
                            break;
                        case 'assignee' :
                            baseStr = issue.assignee;
                            break;
                    }
                    if(baseStr.includes(searchStr)) {
                        showIssue(
                        issue.issuenum,
                        issue.title,
                        issue.priority,
                        issue.status,
                        issue.type,
                        issue.reporter,
                        issue.assignee,
                        issue.date
                    );
                    }
                 });
            } catch (error) {
                console.error('Fetch error:', error);
            }
        }

        function showIssue(issuenum, title, priority, status, type, reporter, assignee, date) {
            const tableBody = document.getElementById('issue-table-body');
            const newRow = document.createElement('tr');
            newRow.className = 'tc';

            const issueNumCell = document.createElement('td');
            issueNumCell.textContent = issuenum;
            newRow.appendChild(issueNumCell);

            const titleCell = document.createElement('td');
            const titleButton = document.createElement('button');
            titleButton.textContent = title;
            titleButton.onclick = function () {
                gotoissueUpdatePage(title);
            };
            titleCell.appendChild(titleButton);
            newRow.appendChild(titleCell);

            const priorityCell = document.createElement('td');
            priorityCell.textContent = getPriority(priority);
            newRow.appendChild(priorityCell);

            const stateCell = document.createElement('td');
            stateCell.textContent = getStatus(status);
            newRow.appendChild(stateCell);

            const typeCell = document.createElement('td');
            typeCell.textContent = getType(type);
            newRow.appendChild(typeCell);

            const reporterCell = document.createElement('td');
            reporterCell.textContent = reporter;
            newRow.appendChild(reporterCell);

            const assigneeCell = document.createElement('td');
            assigneeCell.textContent = assignee;
            newRow.appendChild(assigneeCell);

            const dateCell = document.createElement('td');
            dateCell.textContent = date;
            newRow.appendChild(dateCell);

            tableBody.appendChild(newRow);
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
                      if(data.level === 0 || data.level === 3) {
                           putCurrentUserId();
                           document.getElementById('title-input').value = '';
                           document.getElementById('description-input').value = '';
                           document.getElementById('priority-input').value = 'Major';
                           document.getElementById('type-input').value = 'Bug';
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