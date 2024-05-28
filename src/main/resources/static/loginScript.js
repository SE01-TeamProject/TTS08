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
                    gotoHomePage();
                } else if (data === 'false') {
                    alert("잘못된 아이디 또는 비밀번호입니다.");
                    gotoLoginPage();
                }
            });
    }