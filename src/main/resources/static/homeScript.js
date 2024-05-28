  //html이 load되는 즉시 실행되는 함수
    document.addEventListener('DOMContentLoaded', function() {
        localStorage.removeItem('projectId');
        loadAllProjects();
    });

    async function loadAllProjects() {
            try {
                const response = await fetch('http://localhost:8080/listProject');
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                const projectLists = await response.json();
                projectLists.forEach(project => {
                    const tableBody = document.getElementById('project-table-body');
                    const newRow = document.createElement('tr');
                    newRow.className = 'tc';

                    const titleCell = document.createElement('td');
                    const titleButton = document.createElement('button');
                    titleButton.textContent = project.title;
                    titleButton.onclick = function () {
                        gotoIssuePage(project.title);
                    };
                    titleCell.appendChild(titleButton);
                    newRow.appendChild(titleCell);

                    const descCell = document.createElement('td');
                    descCell.textContent = project.description;
                    newRow.appendChild(descCell);

                    const dateCell = document.createElement('td');
                    dateCell.textContent = project.date;
                    newRow.appendChild(dateCell);

                    tableBody.appendChild(newRow);
                 });
            } catch (error) {
                console.error('Fetch error:', error);
            }
    }

    function gotoIssuePage(projectTitle) { //이 함수는 반드시 프로젝트 클릭에 의해 실행되어야함.
         fetch('http://localhost:8080/project/id/' + projectTitle)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok ' + response.statusText);
                    }
                    return response.text();
                })
                  .then(data => {
                      localStorage.setItem('projectId', data);
                      location.href = "http://localhost:8080/issue.html";
                  })
                  .catch(error => {
                      console.error('There was a problem with the fetch operation:', error);
                  });
    }