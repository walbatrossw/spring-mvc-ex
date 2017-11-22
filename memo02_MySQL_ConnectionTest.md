# Spring MVC - MySQL 연결테스트

본 포스팅은 [코드로 배우는스프링 웹프로젝트](http://www.yes24.com/24/goods/19720776?scode=032&OzSrank=1)를 참조하여 작성한 내용입니다.
개인적으로 학습한 내용을 복습하기 위한 내용이기 때문에 내용상 오류가 있을 수 있습니다.
기존의 Spring MVC 관련 포스팅들이 제대로 정리되지 않은 것 같아 처음부터 차분히 포스팅 진행할 예정입니다.

## jUnit - 기본적으로 알아야할 사항들
* `@Test` : 테스트할 내용을 메서드 안에 작성하고 `@Test` 애너테이션을 추가하면 테스트용 코드로 간주하고 테스트를 진행
* `@Before` : 모든 테스트 작업에 앞서 준비되어야 하는 내용을 작성해서 메서드에 추가하는 애너테이션
* `@After` : 테스트 작업이 끝난 후 자동으로 실행되는 메서드에 추가하는 애너테이션
* `org.junit.Assert.assertxxx` : 테스트 중에 발생되는 값을 확신하는 용도로 테스트 중간엔 특정 값이나 상태를 예상하고, 체크하는 용도

## MySQL 라이브러리 추가
* pom.xml
  ```xml
  <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.41</version>
  </dependency>
  ```

## JDBC 연결 테스트 코드 작성
* MySQL schema 생성 : spring_ex
* test/java/하위패키지에 테스트 클래스를 생성하고 아래와 같이 작성
  ```java
  public class MySQLConnectionTest {

      private static final String DRIVER = "com.mysql.jdbc.Driver";
      private static final String URL = "jdbc:mysql://127.0.0.1:3306/spring_ex?useSSL=false&serverTimezone=Asia/Seoul";
      private static final String USER = "아이디";
      private static final String PASSWORD = "비밀번호";

      @Test
      public void testConnection() throws Exception {
          Class.forName(DRIVER);
          try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
              System.out.println(connection);
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
  }
  ```

* 정상적으로 테스트 완료 후 콘솔 화면
  ```
  com.mysql.jdbc.JDBC4Connection@f5f2bb7 // Connection 객체 생성
  ```
