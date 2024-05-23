# TTS08
소프트웨어 공학 01분반 8팀 이슈 관리 시스템 레포지토리입니다.

TTS08을 실행하기 위해서는 다음과 같은 프로그램이 필요합니다:
 - H2Database
 - JDK Development Kit 22
 - Spring Tool Suite

1. 링크에서 JDK 22 - Windows(MSI)를 설치합니다. JDK는 아래의 디렉터리에 설치됩니다: https://www.oracle.com/java/technologies/downloads/

   ```sh
   c:\program files\java\jdk-22
   ```

2. Spring Tool Suite - Spring Tools 4 for Eclipse를 받고 압축을 풉니다: https://spring.io/tools

3. H2Database의 가장 최신 버전인 2.2.224의 Platform-Independent Zip를 받고 압축을 풉니다: https://h2database.com/html/download.html

## 초기 설정

1. 저장소를 복제합니다:

   ```sh
   git clone https://github.com/SE01-TeamProject/TTS08.git
   ```

2. Spring Tool Suite로 프로젝트를 엽니다.

3. 프로젝트를 열면 Error와 Warning이 하나씩 발생합니다. 

- Error: Unbound class container 해결 방법

   - 좌측 Package Explorer에서 프로젝트 선택 -> File의 Properties -> Java Build Path -> Libraries -> JRE System Library 선택 후 Edit

   - Workspace default JRE 선택 후 Finish -> Apply and Close

- Warnings: Project 'TTS08' has no explicit encoding set 해결 방법

   - Warnings의 세부사항 좌클릭 -> Quick Fix 선택

   - Set project encoding to 'UTF-8' 선택 후 Finish

4. 프로젝트를 실행하고 localhost:8080/(페이지이름).html에 접속합니다. (html파일들은 main>resources>static 폴더 안에 있습니다.)
