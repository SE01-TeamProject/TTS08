 //html이 load되는 즉시 실행되는 함수
         document.addEventListener('DOMContentLoaded', function() {
              //showAllComments();
        });

        function addIssue() {
		  const title = document.getElementById('title-input').value;
          const description = document.getElementById('description-input').value;
		  const reporter = localStorage.getItem('loginId');
		  const priority = document.getElementById('priority-input').value;
		  const type = document.getElementById('type-input').value;
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
        }

        function showAllIssues() {

        }

        function showIssue() {

        }

       function disableInput() {
            //get처리로 권한 받아서 해줘야함.
             const level = 2; //get처리로 받은 권한레벨 순서대로 admin, PL, developer, tester
             //document.getElementById('type').value = 'Task';
             //document.getElementById('type').disabled = true;
             //document.getElementById('description-input').setAttribute('readonly', 'true');
       }