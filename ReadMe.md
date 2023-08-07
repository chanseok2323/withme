## Multi Module 설정 시 문제점
 1. rds-core 에서 엔티티 및 리포지토리 만들어서, api 에서 사용 시 'Cannot access org.springframework.data.jpa.repository.JpaRepository' 문제 발생
    - 이유는 api -> rds-core -> jpa 를 implementation 을 이용하여 gradle 에서 사용해서 참조
    - gradle 의 api, implementation 차이
        - implementation을 하위 의존에 대한 관계를 숨겨준다. 즉 하위 의존에 대한 접근을 제한한다. 그래서 api 에서 rds-core 의 하위 인 jpa 를 참조 할 수 없었다
    * 해결 방법 : rds-core -> jpa 를 참조하는 부분을 api 로 변경했다.
    * 그런데 변경 후, gradle 빌드 시, 'Could not find method api() for arguments jpa' 에러가 발생했다
        - apply plugin: java -> apply plugin: java-library 변경