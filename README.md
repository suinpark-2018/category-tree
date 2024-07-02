# 계층형 카테고리

## 필요한 기능
- 여러 형태의 카테고리 구현
- 이름은 동일하나, 각 카테고리별로 다른 공지사항 게시판 포함
- 동일한 게시판이나, 각각 다른 카테고리에 소속되는 익명 게시판 포함
<br>

## 필수 기능
- 카테고리 식별자 및 카테고리명으로 검색 가능
- 카테고리 검색 시, 카테고리의 하위 카테고리까지 조회 가능
- Json 구조로 변환 가능
<br>

## ERD

### DB table
<img width="200" alt="스크린샷 2024-07-02 오후 8 27 02" src="https://github.com/suinpark-2018/test-board/assets/147983164/93134c7a-cff0-4e01-a8a7-13596631b025">

<br> :bulb: parent_idx 컬럼은 상위 카테고리 식별번호를 의미하며, 재귀 호출을 통해 카테고리 계층을 조회함.<br>
<br>

## Project Structure
```
test-board
    ├── README.md
    ├── pom.xml
    └── src
        ├── main
        │   ├── java
        │   │   └── com
        │   │       └── test
        │   │           └── board
        │   │               ├── common
        │   │               │   └── config
        │   │               │       └── JsonConfig.java
        │   │               ├── controller
        │   │               │   ├── CategoryController.java
        │   │               │   └── HomeController.java
        │   │               ├── dao
        │   │               │   ├── CategoryDao.java
        │   │               │   └── CategoryDaoImpl.java
        │   │               ├── domain
        │   │               │   └── CategoryDto.java
        │   │               └── service
        │   │                   ├── CategoryService.java
        │   │                   └── CategoryServiceImpl.java
        │   ├── resources
        │   │   ├── application.properties
        │   │   ├── mapper
        │   │   │   └── categoryMapper.xml
        │   │   └── mybatis-config.xml
        │   └── webapp
        │       ├── WEB-INF
        │       │   ├── spring
        │       │   │   ├── appServlet
        │       │   │   │   └── servlet-context.xml
        │       │   │   └── root-context.xml
        │       │   ├── views
        │       │   │   └── index.jsp
        │       │   └── web.xml
        │       └── resources
        └── test
            └── java
                └── com
                    └── test
                        └── board
                            ├── dao
                            │   └── CategoryDaoImplTest.java
                            └── service
                                └── CategoryServiceImplTest.java

```
<br>

## 주요 기능
<img src="https://github.com/suinpark-2018/test-board/assets/147983164/1c818f26-ed9c-4e8b-96ae-f24988f2a6df" alt="다중 카테고리 기능" width="50%">


## 기술 스택

### :wrench: Enviroment
|IntelliJ|Git|Github|Maven|
|:---:|:---:|:---:|:---:|
|<img src="https://github.com/JJackSparrow/statuscode200/assets/147983164/6eeea4ae-8147-48e0-b575-d8b877018518" width="100" alter="Intelli_J">|<img src="https://github.com/JJackSparrow/statuscode200/assets/147983164/518df61b-b11c-40e9-a777-3f20e3e6cc71" width="150" alter="Git">|<img src="https://github.com/JJackSparrow/statuscode200/assets/147983164/e2edb3e4-f99c-4f14-98e5-24c1b951353d" width="100" alter="Git_Hub">|<img src="https://github.com/JJackSparrow/statuscode200/assets/147983164/fa9e2440-de88-4bdc-914c-8117f4b4e71e" width="100" alter="Maven">|

<br>

### :hammer: Tool

#### Backend
|Java|Spring Framework|MyBatis|JUnit|
|:---:|:---:|:---:|:---:|
|<img src="https://github.com/JJackSparrow/statuscode200/assets/147983164/60bf40c0-9618-4921-ada7-dbf99f8420f9" width="100" alter="Java">|<img src="https://github.com/JJackSparrow/statuscode200/assets/147983164/d6b56031-cad0-4603-ae2f-6019e0678d28" width="150" alter="Spring_Framework">|<img src="https://github.com/JJackSparrow/statuscode200/assets/147983164/0a173691-17d7-41ab-beaa-219bcec56c0e" width="100" alter="MyBatis">|<img src="https://github.com/JJackSparrow/statuscode200/assets/147983164/de7d26b1-0a02-46aa-8885-d5b338868166" width="100" alt="JUnit">|

#### Frontend
|HTML/CSS/JS|jQuery|Json|JSP|Ajax|
|:---:|:---:|:---:|:---:|:---:|
|<img src="https://github.com/JJackSparrow/statuscode200/assets/147983164/f582f661-4ad1-47e0-8f50-a2e1a5f701d3" width="100" alter="HTML_CSS_JS">|<img src="https://github.com/JJackSparrow/statuscode200/assets/147983164/f1ba0255-736b-4ec8-81d6-d6602033ee47" width="80" alter="jQuery">|<img src="https://github.com/JJackSparrow/statuscode200/assets/147983164/30685eac-63ff-4d38-8e40-83c6339cc6c6" width="100" alter="Jason">|<img src="https://github.com/JJackSparrow/statuscode200/assets/147983164/c81205bb-a537-4495-86cd-af7b3c16af22" width="80" alter="JSP">|<img src="https://github.com/JJackSparrow/statuscode200/assets/147983164/dbea28d9-cbae-44fe-ad7c-b1bc7d7358ad" width="100" alter="Ajax">|

#### Database
|MySQL|
|:---:|
|<img src="https://github.com/JJackSparrow/statuscode200/assets/147983164/d3c36f68-7cbd-47f3-9540-b3deab04bde3" width="150" alter="MySQL">|

#### Web Server
|Apache Tomcat|
|:---:|
|<img src="https://github.com/JJackSparrow/statuscode200/assets/147983164/620bad47-c2af-4864-9b0e-dc9382b3a6d9" width="150" alter="ApacheTomcat">|

<br>

## Commit Message Convention
### 규칙
```
- 제목 첫글자는 대문자 사용
- 제목 마침표 미포함
- 제목과 본문은 빈 행으로 구분
- 제목 명령문으로 작성
- 제목 50자 이내로 간결하게 작성
```
### 유형
```
[Feat]        :     새로운 기능 추가
[Test]        :     테스트 코드 완료
[Docs]        :     문서 추가 또는 수정 (ex. README 변경)
[Chore]       :     패키지 매니저(ex. gitignore 수정), 빌드 업무 수정 
[Refactor]    :     리팩토링, 코드 개선
[Fix]         :     버그 수정
[Style]       :     코드 스타일 변경 (포맷팅, 세미콜론 누락 등 코드 변경이 없는 경우)
[Comment]     :     주석 추가 및 수정
[Rename]      :     파일 또는 폴더명을 수정하거나 이동하는 작업만 수행한 경우
[Remove]      :     파일을 삭제하는 작업만 수행한 경우
[Conflict]    :     합병 시 발생한 충돌 수정
```
