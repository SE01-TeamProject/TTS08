function addUser() {
              const fullName = document.getElementById('name-input').value;
              const name = document.getElementById('id-input').value;
              const password = document.getElementById('password-input').value;
              const level = document.getElementById('level-input').value;

              if (fullName === "" || name === "" || password === "") {
                  alert("내용을 모두 기입 후 다시 시도해주세요.");
                  return;
              }

              fetch('http://localhost:8080/addUser', {
                  method: 'POST',
                  headers: {
                      'Content-Type': 'application/json'
                  },
                  body: JSON.stringify({fullName, name, password, level})
              })
                  .then(response => response.text())
                  .then(data => {
                      if (data === 'true') {
                          alert("유저 추가 완료");
                          gotoAdminPage();
                      } else if (data === 'false') {
                          alert("중복된 아이디입니다.");
                      }
                  });
          }

          function displayUsersInProjectAdd() {
              fetch('http://localhost:8080/listUser')
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok ' + response.statusText);
                    }
                    return response.json();
                })
                  .then(data => {
                      if (Array.isArray(data)) {
                          data.forEach(user => {// id name fullName password level
                              switch(user.level) {
                                  case 1://PL
                                      addOption('PL-select', user.name);
                                      break;
                                  case 2://Developer
                                      addOption('developer-select', user.name);
                                      break;
                                  case 3://Tester
                                      addOption('tester-select', user.name);
                                      break;
                                  default:
                                      console.log("undefined level detected");
                              }
                          });
                      } else {
                          console.error('Expected an array but received:', data);
                      }
                  })
                  .catch(error => {
                      console.error('There was a problem with the fetch operation:', error);
                  });
          }
          function displayUsersInUserManage() {
              fetch('http://localhost:8080/listUser')
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok ' + response.statusText);
                    }
                    return response.json();
                })
                  .then(data => {
                      if (Array.isArray(data)) {
                          data.forEach(user => {// id name fullName password level
                              switch(user.level) {
                                  case 1://PL
                                      break;
                                  case 2://Developer

                                      break;
                                  case 3://Tester

                                      break;
                                  default:
                                      console.log("undefined level detected");
                              }
                          });
                      } else {
                          console.error('Expected an array but received:', data);
                      }
                  })
                  .catch(error => {
                      console.error('There was a problem with the fetch operation:', error);
                  });
          }

          function addTable(parentId, userId, userLevel) {

          }

		  function addProject() {
  			  const title = document.getElementById('title-input').value;
  	          const description = document.getElementById('description-input').value;
              const pl = document.getElementById('PL-select').value;
              const developerContainer = document.getElementById('developer-select');
              const developers = Array.from(developerContainer.selectedOptions).map(option => option.value);
              if(developers.length !== 3) {
                  alert("Select 3 developers. (Ctrl + Click)");
                  return;
              }
              const developer1 = developers[0];
              const developer2 = developers[1];
              const developer3 = developers[2];

              const testerContainer = document.getElementById('tester-select');
              const testers = Array.from(testerContainer.selectedOptions).map(option => option.value);
              if(testers.length !== 3) {
                  alert("Select 3 testers. (Ctrl + Click)");
                  return;
              }
              const tester1 = testers[0];
              const tester2 = testers[1];
              const tester3 = testers[2];

              const tester = document.getElementById('tester-select').value;
              if(title === "" || description === "내용을 입력하세요.") {
                    alert("제목이 비었거나 내용을 적지 않았습니다. 모두 기입 후 시도해주세요.");
                    return;
              }

  	          fetch('http://localhost:8080/addProject', {
  	              method: 'POST',
  	              headers: {
  	                  'Content-Type': 'application/json'
  	              },
  	              body: JSON.stringify({title, description, pl, developer1, developer2, developer3, tester1, tester2, tester3})
  	          })
  	              .then(response => response.text())
  	              .then(data => {
  	                  if (data === 'true') {
                          alert("프로젝트 추가 완료");
  	                      gotoAdminPage();
  	                  } else if (data === 'false') {
  	                      alert("중복된 프로젝트입니다.");
  	                  }
  	              });
  	      }