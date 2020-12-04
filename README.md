# HTM Project - SERVER 
Git Flow 전략 설명

## 기본적인 Git Flow 전략
#### 티켓 생성
* Issue 티켓을 생성한다. Jira 를 사용하면 좋지만, 그럴 여유는 없으니 Github 에서 제공하는 issue 에서 임의로 생성.
* Auto-increment 가 안되니까 수동으로 숫자올려서 진행.
* 티켓 Prefix 는 HTMSVR 로 한다.
* 이슈 제목은 아래와 같이 딴다
- [티켓 prefix]-[티켓 번호] 제목
- ex) HTMSVR-1 기본 프로젝트 환경 구성
- 티켓 생성 후 티켓 내부 내용은 해당 티켓으로 작업할 내용을 상세히 기술한다.
- assignee 를 할당하여 해당 티켓을 작업할 대상자를 선택

#### 작업 브랜치 생성
* 작업 브랜치는 항상 develop 브랜치를 기준으로 해야만한다.
* 작업 브랜치명은 feature/[티켓 이름] 으로 생성해야한다.
* ex) feature/HTMSVR-1
* 정확한 git flow 전략을 위해선 develop 브랜치를 생성해야하지만 이번 프로젝트에선 제외!

#### Pull Request 생성
* 자신이 만든 feature 가 완성되어 develop와 merge를 해야하는경우, 단순하게 merge 하지않고 같이 협업하는 사람들과의 코드리뷰를 통해 머지를 진행하도록 한다.
* Pull Request 의 제목 규칙은 Issue 만들 때와 동일. ex) HTMSVR-1 기본 프로젝트 환경 구성
* 상세 내용도 변경 / 추가 / 참고 할점을 기준으로 상세하게 최대한 적는다.
* Reviewer 를 선정할 수 있으며, 기본 원칙은 모든 리뷰어가 Approve 를 해줘야만 Merge 하는 것이 원칙.
* 코드리뷰가 끝나고 Merge 를 하기전, develop 브랜치와 rebase 를 통해 브랜치 최신화를 진행한다.
* 또한 1 PR 1 commit 원칙을 지키며, 코드리뷰 도중 커밋이 여러개 생겼다면 squashing 을 통해 커밋을 줄여주도록 한다.
* squash command - git rebase -i HEAD~(스쿼시할 개수)


