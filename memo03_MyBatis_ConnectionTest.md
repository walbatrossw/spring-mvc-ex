본 포스팅은 [코드로 배우는스프링 웹프로젝트](http://www.yes24.com/24/goods/19720776?scode=032&OzSrank=1)를 참조하여 작성한 내용입니다. 개인적으로 학습한 내용을 복습하기 위한 내용이기 때문에 내용상 오류가 있을 수 있습니다. 기존의 Spring MVC 관련 포스팅들이 제대로 정리되지 않은 것 같아 처음부터 차분히 포스팅 진행할 예정입니다.

## 01) MyBtis의 장점

- 간결한 코드처리 : JDBC작업을 위한 반복적인 코드(`try~cathch~finally`, `PreparedStatement`, `ResultSet`)를 직접 작성하지 않아도 된다.
- SQL문 분리 운영 : XML 또는 애너테이션 방식으로 SQL문을 별도로 처리하는 작업이 가능하다.
- Spring과 연동으로 자동화된 처리 : `MyBatis-Spring`라이브러리를 이용하여 직접 SQL문 호출없이도 원하는 결과를 얻을 수 있다.
- 동적 SQL을 이용한 제어기능 : 제어문이나 반복문 등의 처리 기능을 통해 SQL과 관련된 처리를 Java코드에서 분리할 수 있다.

## 02) MyBatis 연동을 위한 설정

- `pom.xml` : JDBC, Mybatis관련 라이브러리 추가

  ```xml
  <!--Spring-Jdbc 라이브러리-->
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>${org.springframework-version}</version>
  </dependency>
  <!--Spring-Test 라이브러리-->
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-test</artifactId>
      <version>${org.springframework-version}</version>
  </dependency>
  <!--MyBatis 라이브러리-->
  <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.4.1</version>
  </dependency>
  <!--MyBatis-Spring 라이브러리-->
  <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis-spring</artifactId>
      <version>1.3.0</version>
  </dependency>
  ```

- `applicationContext.xml` : DataSource 설정
  ```xml
  <beans xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop" xmlns:context="http://www.springframework.org/schema/context" xmlns:jdbc="http://www.springframework.org/schema/jdbc" xmlns:mybatis-spring="http://mybatis.org/schema/mybatis-spring" xsi:schemalocation="http://www.springframework.org/schema/jdbc http://www.springframework.org/schema/jdbc/spring-jdbc-3.1.xsd
          http://mybatis.org/schema/mybatis-spring http://mybatis.org/schema/mybatis-spring-1.2.xsd
          http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
          http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.1.xsd
          http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.1.xsd">
  </beans>
  <!--DataSource 설정-->
  <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
      <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
      <property name="url" value="jdbc:mysql://127.0.0.1:3306/spring_ex?useSSL=false"/>
      <property name="username" value="아이디"/>
      <property name="password" value="비밀번호"/>
  </bean>
  ```

## 03) DataSource 테스트
- test/java/하위패키지 : 테스트 코드 작성
  ```java
  @RunWith(SpringJUnit4ClassRunner.class)
  @ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/applicationContext.xml"})
  public class DataSourceTest {

      @Inject
      private DataSource dataSource;

      @Test
      public void testConnection() throws Exception {
          try(Connection connection = dataSource.getConnection()) {
              System.out.println(connection);
          } catch (Exception e) {
              e.printStackTrace();
          }
      }

  }
  ```

- 정상적으로 테스트 완료 후 콘솔 화면
  ```
  INFO : org.springframework.beans.factory.xml.XmlBeanDefinitionReader - Loading XML bean definitions from URL [file:src/main/webapp/WEB-INF/spring/applicationContext.xml]
  INFO : org.springframework.context.support.GenericApplicationContext - Refreshing org.springframework.context.support.GenericApplicationContext@4b4523f8: startup date [Wed Nov 22 21:29:37 KST 2017]; root of context hierarchy
  INFO : org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor - JSR-330 'javax.inject.Inject' annotation found and supported for autowiring
  com.mysql.jdbc.JDBC4Connection@77847718
  INFO : org.springframework.context.support.GenericApplicationContext - Closing org.springframework.context.support.GenericApplicationContext@4b4523f8: startup date [Wed Nov 22 21:29:37 KST 2017]; root of context hierarchy
  ```

## 04) Mybatis 연결 설정

* `applicationContext.xml` : SqlSessionFactory 객체 설정
  ```xml
  <!--SqlSessionFactory 설정 : dataSource를 참조, mybatis-config.xml 경로설정-->
  <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
      <property name="dataSource" ref="dataSource"/>
      <property name="configLocation" value="classpath:/mybatis-config.xml"/>
  </bean>
  ```

* `mybatis-config.xml` : MyBatis 설정파일, src/main/resources 디렉토리에 xml파일 생성한 뒤 아래와 같이 작성
  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE configuration
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-config.dtd">
  <configuration>

  </configuration>
  ```

## 05) MyBatis 연결 테스트
- test/java/하위패키지 : 테스트 코드 작성
  ```java
  @RunWith(SpringJUnit4ClassRunner.class)
  @ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/applicationContext.xml"})
  public class MyBatisTest {

      @Inject
      private SqlSessionFactory sqlSessionFactory;

      @Test
      public void testFactory() {
          System.out.println(sqlSessionFactory);
      }

      @Test
      public void testSession() throws Exception {
          try(SqlSession session = sqlSessionFactory.openSession()) {
              System.out.println(session);
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
  }
  ```
- 정상적으로 테스트 완료 후 콘솔화면
  ```
  INFO : org.springframework.beans.factory.xml.XmlBeanDefinitionReader - Loading XML bean definitions from URL [file:src/main/webapp/WEB-INF/spring/applicationContext.xml]
  INFO : org.springframework.context.support.GenericApplicationContext - Refreshing org.springframework.context.support.GenericApplicationContext@6325a3ee: startup date [Wed Nov 22 22:57:50 KST 2017]; root of context hierarchy
  INFO : org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor - JSR-330 'javax.inject.Inject' annotation found and supported for autowiring
  org.apache.ibatis.session.defaults.DefaultSqlSessionFactory@26794848
  org.apache.ibatis.session.defaults.DefaultSqlSession@1ff4931d
  INFO : org.springframework.context.support.GenericApplicationContext - Closing org.springframework.context.support.GenericApplicationContext@6325a3ee: startup date [Wed Nov 22 22:57:50 KST 2017]; root of context hierarchy
  ```
