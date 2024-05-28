 //html이 load되는 즉시 실행되는 함수
         document.addEventListener('DOMContentLoaded', function() {
              showAllIssues();
        });

        function addIssue() {
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
              body: JSON.stringify({title, description, reporter, priority, type})
          })
              .then(response => response.text())
              .then(data => {
                  if (data === 'true') {
                      alert("Issue add success.")
                      gotoIssuePage();
                  } else if (data === 'false') {
                      alert("중복된 이슈입니다.");
                  }
              });
            closePopUp('issue-popup');
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
                    const tableBody = document.getElementById('user-table-body');
                    const newRow = document.createElement('tr');
                    newRow.className = 'tc';

                    const issueNumCell = document.createElement('td');
                    issueNumCell.textContent = issue.issuenum;
                    newRow.appendChild(issueNumCell);

                    const titleCell = document.createElement('td');
                    const titleButton = document.createElement('button');
                    titleButton.textContent = issue.title;
                    titleButton.onclick = function () {
                        //issueupdate페이지로 이동하는 함수
                    };
                    titleCell.appendChild(titleButton);
                    newRow.appendChild(titleCell);

                    const priorityCell = document.createElement('td');
                    priorityCell.textContent = issue.priority;
                    newRow.appendChild(priorityCell);

                    const stateCell = document.createElement('td');
                    stateCell.textContent = issue.state;
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

       function disableInput() {
            //get처리로 권한 받아서 해줘야함.
             const level = 2; //get처리로 받은 권한레벨 순서대로 admin, PL, developer, tester
             //document.getElementById('type').value = 'Task';
             //document.getElementById('type').disabled = true;
             //document.getElementById('description-input').setAttribute('readonly', 'true');
       }