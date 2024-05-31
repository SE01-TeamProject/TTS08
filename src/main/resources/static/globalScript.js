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
            function gotoStatisticPage() {
                const currentLocation = window.location.href;
                localStorage.setItem('statisticsPreviousPage', currentLocation);
                location.href = "http://localhost:8080/statistic.html";
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
        function getStatus(number) {
            let result;
            switch(number) {
                case 0 : result = "New";
                break;
                case 1 : result = "Assigned";
                break;
                case 2 : result = "Fixed";
                break;
                case 3 : result = "Resolved";
                break;
                case 4 : result = "Closed";
                break;
                case 5 : result = "Reopened";
                break;
                default : result = "Unknown Status";
                break;
            }
            return result;
        }
        function getStatusNum(string) {
            let result;
            switch(string) {
                case "New" : result = 0;
                break;
                case "Assigned" : result = 1;
                break;
                case "Fixed" : result = 2;
                break;
                case "Resolved" : result = 3;
                break;
                case "Closed" : result = 4;
                break;
                case "Reopened" : result = 5;
                break;
                default : result = -1;
                break;
            }
            return result;
        }
        function getPriority(number) {
            let result;
            switch(number) {
                case 0 : result = "Major";
                break;
                case 1 : result = "Minor";
                break;
                case 2 : result = "Blocker";
                break;
                case 3 : result = "Critical";
                break;
                case 4 : result = "Trivial";
                break;
                default : result = "Unknown Priority";
                break;
            }
            return result;
        }
        function getPriorityNum(string) {
            let result;
            switch(string) {
                case "Major" : result = 0;
                break;
                case "Minor" : result = 1;
                break;
                case "Blocker" : result = 2;
                break;
                case "Critical" : result = 3;
                break;
                case "Trivial" : result = 4;
                break;
                default : result = -1;
                break;
            }
            return result;
        }
        function getType(number) {
            let result;
            switch(number) {
                case 0 : result = "Bug";
                break;
                case 1 : result = "Task";
                break;
                default : result = "Unknown Type";
                break;
            }
            return result;
        }
        function getLevel(number) {
            let result;
             switch(number) {
                case 0 : result = "Admin";
                break;
                case 1 : result = "PL";
                break;
                case 2 : result = "Developer";
                break;
                case 3 : result = "Tester";
                break;
                default : result = "Unknown Type";
                break;
            }
            return result;
        }