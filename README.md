# TTS08
소프트웨어 공학 01분반 8팀 이슈 관리 시스템 레포지토리입니다.

TTS08을 실행하기 위해서는 다음과 같은 프로그램이 필요합니다:
 - H2Database
 - JDK Development Kit 22
 - Spring Tool Suite

1. 링크에서 JDK 22 - Windows(exe)를 설치합니다. JDK는 아래의 디렉터리에 설치됩니다: https://www.oracle.com/java/technologies/downloads/

   ```sh
   c:\program files\java\jdk-22
   ```

2. Spring Tool Suite - Spring Tools 4 for Eclipse를 받고 압축을 풉니다: https://spring.io/tools

3. H2Database의 가장 최신 버전인 2.2.224의 Windows Installer를 받고 설치합니다: https://h2database.com/html/download.html

## 초기 설정

아래의 과정을 수행 중 컴퓨터가 구성하는데 시간이 걸리므로 천천히 해야 합니다.

1. 저장소를 복제합니다:

   ```sh
   git clone https://github.com/SE01-TeamProject/TTS08.git
   ```

2. Spring Tool Suite(STS) 또는 Intellij로 프로젝트를 엽니다.

3-1. STS로 프로젝트를 열면 Error와 Warning이 하나씩 발생합니다. 

- Error: Unbound class container 해결 방법

   - 좌측 Package Explorer에서 프로젝트 선택 -> File의 Properties -> Java Build Path -> Libraries -> JRE System Library 선택 후 Edit
 
   - Alternate JRE의 Installed JREs -> Add 선택
 
   - Standard VM 선택 다음 Directory에서 jdk-22가 존재하는 경로에서 jdk-22 폴더 선택 -> Finish하고 생성된 jdk(jdk-22) 선택 뒤 Apply and Close

   - Workspace default JRE(jdk-22) 선택 후 Finish -> Apply and Close: JRE 뒤에 jdk-22 표시가 존재해야 함

- Warnings: Project 'TTS08' has no explicit encoding set 해결 방법

   - Warnings의 세부사항 좌클릭 -> Quick Fix 선택

   - Set project encoding to 'UTF-8' 선택 후 Finish

3-2. Intellij로 프로젝트에서의 실행은 다음과 같습니다.    

- 현재 default로는 JDK22를 기준으로 프로젝트가 구동됩니다.

    - Module Setting > Module SDK가 22
      
    - Settings > Build Tools > gradle > gradeJVM이 22
      
    - build.gradle에서 sourceCompatibility = '22'
      
- 만약 JDK22가 없다면 오류가 발생할 수 있으며, JDK22 설치 후 다음과 같이 세팅을 한 뒤 다시 실행하십시오. 만약 이 세팅으로 실행했는데도 gradle 8.7과 JDK22의 호환 문제가 발생한다면   
```sh
   sourceCompatibility = '21'
   ```
로 설정한 후, Settings > Build Tools > gradle > gradeJVM을 21로 설정하고 다시 실행하세요.

그 이후에도 오류가 발생할 경우, Module Setting > Moduel SDK도 21로 설정한 후 다시 실행하세요.

4. 프로젝트를 실행하고 localhost:8080에 접속합니다.
