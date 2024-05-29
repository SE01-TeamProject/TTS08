            function gotoHomePage() {
                location.href = "http://localhost:8080/home.html";
            }
            function gotoAdminPage() {
                //이거 admin인지 권한 확인하는것 필요함
                const currentUserId = localStorage.getItem('loginId');
                if (currentUserId === 'admin') {
                    location.href = "http://localhost:8080/admin.html";
                }
                else {
                    alert("Only admin can access admin page!");
                }
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

        function closePopUp(pageId) {
              document.getElementById(pageId).classList.add('hide');
              document.getElementById('bg-page').classList.add('hide');
        }

        function openPopUp(pageId) {
              document.getElementById(pageId).classList.remove('hide');
              document.getElementById('bg-page').classList.remove('hide');
        }

        function openSecondPopUp(pageId) {
             document.getElementById(pageId).classList.remove('hide');
             document.getElementById('bg-secondpage').classList.remove('hide');
        }
        function closeSecondPopUp(pageId) {
             document.getElementById(pageId).classList.add('hide');
             document.getElementById('bg-secondpage').classList.add('hide');
        }
        function addOption(parentId, value) {
            const parent = document.getElementById(parentId);
            const newOption = document.createElement('option');
            newOption.text = value;
            parent.appendChild(newOption);
        }
        function delay(ms) {
            return new Promise(resolve => setTimeout(resolve, ms));
        }
