  function login() {
        const id = document.getElementById('id').value;
        const password = document.getElementById('password').value;
        fetch('http://localhost:8080/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({id, password})
        })
            .then(response => response.text())
            .then(data => {
                if (data === 'true') {
                    localStorage.setItem('loginId', id);
                    saveUserLevel();
                } else if (data === 'false') {
                    alert("잘못된 아이디 또는 비밀번호입니다.");
                    gotoLoginPage();
                }
            });
    }

  function saveUserLevel() {
        fetch('http://localhost:8080/userName/' + localStorage.getItem('loginId'))
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok ' + response.statusText);
                    }
                    return response.json();
                })
                  .then(data => {
                      localStorage.setItem('level', data.level);
                      console.log(localStorage.getItem('level'));
                      gotoHomePage();
                  })
                  .catch(error => {
                      console.error('There was a problem with the fetch operation:', error);
                  });
  }