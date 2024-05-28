  //html이 load되는 즉시 실행되는 함수
    document.addEventListener('DOMContentLoaded', function() {
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
                    const tableBody = document.getElementById('user-table-body');
                    const newRow = document.createElement('tr');

                    const titleCell = document.createElement('td');
                    titleCell.textContent = project.title;
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