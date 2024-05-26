            function gotoHomePage() {
                location.href = "http://localhost:8080/home.html";
            }
            function gotoAdminPage() {
                //이거 admin인지 권한 확인하는것 필요함
                location.href = "http://localhost:8080/admin.html";
            }
            function gotoIssuePage() {
                location.href = "http://localhost:8080/issue.html";
            }
            function gotoLoginPage() {
                location.href = "http://localhost:8080/login.html";
            }

             function hideElements() {
            const hideableElements = document.querySelectorAll('.hideable');
            hideableElements.forEach(function(element) {
                element.style.display = 'none';
            });
        }

        function showElements() {
            const hideableElements = document.querySelectorAll('.hideable');
            hideableElements.forEach(function(element) {
                element.style.display = 'block';
            });
            document.getElementById('title-input').value = '';
            document.getElementById('description-input').value = '내용을 입력하세요.';
        }