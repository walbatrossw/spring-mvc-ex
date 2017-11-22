# IntelliJ에서 Spring MVC Project 생성하기

`IntelliJ`에서는 `STS(Spring Tool Suite)`처럼 `Spring MVC Project`를 생성하는 방법이 따로 존재하는 것 같지 않다. 구글링을 통해 참고한 내용들을 바탕으로 직접 `IntelliJ`에서 `Spring Mvc Project`를 생성하는 과정을 정리해보았다. `STS`에서 처럼 기본적인 `Spring MVC Project`를 생성하고, 웹페이지에 hello world를 출력하기까지는 아래와 같이 크게 5가지의 단계를 거친다.

1. Maven module을 생성한다.
2. Maven module을에 Spring Framework중에서 Spring MVC를 추가한다.
3. 스프링 관련 설정, 디렉토리 생성 및 설정을 한다.
4. Tomcat 서버 설정을 한다.
5. 기본/테스트 package와 views 디렉토리를 생성한다.

이렇게 설정을 하고나면 아래와 같은 구조가 된다. 이제 마지막으로 `HomeController`, `home.jsp`를 작성하고 나서 tomcat server를 구동하게 되면 루트 웹페이지에서 hello world라는 문구를 출력하게 된다.

```
src
 └── main
      ├── java : java code
      ├── resourecs : mapper, log 관련 설정 xml (log4j.xml)
      ├── webapp : web 디렉토리
      |     ├── resources : js, css 등의 정적 자원들
      |     └── WEB-INF : web information 디렉토리
      |           ├── spring : spring관련 설정 xml (dispatcher-servlet.xml, applicationContext.xml)
      |           └── views : jsp
      └── test : test 관련 디렉토리
            ├── java : java test code
            └── resources : test 관련 resources
pom.xml
```

`STS`에서 간단하고 편하게 프로젝트를 생성하고 쓰면되지 않을까? 라는 생각이 들 수 있다. 하지만 나의 경우 여러가지 편의기능을 제공하는 `IntelliJ`에 익숙해져 있기 때문이기도 하고, 세세한 설정을 직접 해보면서 공부를 해봐야겠는 생각에 이렇게 번거로운 작업을 하게 되었다.

그렇다면 이제 각각의 단계를 좀더 자세하게 정리해보자.


## 01) Maven 프로젝트 생성
* `File`>`New`>`Module`>`create from archetype` : `maven-archetype-webapp` 선택
* `groupId`, `artifact` 작성
* module 생성 뒤 maven BUILD SUCCESS 성공메시지 확인

## 02) Spring MVC 추가
* module 우클릭 후 `add framework support...` 선택
* `spring mvc` 선택

## 03) Spring 관련 설정 수정 및 추가, 디렉토리 생성 및 Role 설정
* `pom.xml`
  - properties : java, spring, aspectj, slf4j 버전 명시
    ```xml
    <properties>
        <java-version>1.8</java-version>
        <org.springframework-version>4.3.12.RELEASE</org.springframework-version>
        <org.aspectj-version>1.6.10</org.aspectj-version>
        <org.slf4j-version>1.6.6</org.slf4j-version>
    </properties>
    ```

  - dependencies : maven 라이브러리 추가
    ```xml
    <dependencies>
        <!--Spring-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${org.springframework-version}</version>
            <exclusions>
                <!-- Exclude Commons Logging in favor of SLF4j -->
                <exclusion>
                    <groupId>commons-logging</groupId>
                    <artifactId>commons-logging</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>${org.springframework-version}</version>
        </dependency>

        <!-- AspectJ -->
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjrt</artifactId>
            <version>${org.aspectj-version}</version>
        </dependency>

        <!-- Logging -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>${org.slf4j-version}</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>jcl-over-slf4j</artifactId>
            <version>${org.slf4j-version}</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>${org.slf4j-version}</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.15</version>
            <exclusions>
                <exclusion>
                    <groupId>javax.mail</groupId>
                    <artifactId>mail</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>javax.jms</groupId>
                    <artifactId>jms</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>com.sun.jdmk</groupId>
                    <artifactId>jmxtools</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>com.sun.jmx</groupId>
                    <artifactId>jmxri</artifactId>
                </exclusion>
            </exclusions>
            <scope>runtime</scope>
        </dependency>

        <!-- @Inject -->
        <dependency>
            <groupId>javax.inject</groupId>
            <artifactId>javax.inject</artifactId>
            <version>1</version>
        </dependency>

        <!-- Servlet -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>2.5</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.1</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>3.8.1</version>
            <scope>test</scope>
        </dependency>

    </dependencies>
    ```

  - plugins : 플러그인 추가
    ```xml
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>2.5.1</version>
            <configuration>
                <source>${java-version}</source>
                <target>${java-version}</target>
                <compilerArgument>-Xlint:all</compilerArgument>
                <showWarnings>true</showWarnings>
                <showDeprecation>true</showDeprecation>
            </configuration>
        </plugin>
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>exec-maven-plugin</artifactId>
            <version>1.2.1</version>
            <configuration>
                <mainClass>org.test.int1.Main</mainClass>
            </configuration>
        </plugin>
    </plugins>
    ```

* `web.xml` : `applicationContext.xml`, `dispatcher-servlet.xml` 경로 설정, dispatcher url 패턴 설정
  ```xml
  <!DOCTYPE web-app PUBLIC
        "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
        "http://java.sun.com/dtd/web-app_2_3.dtd" >
  <web-app version="2.5" xmlns="http://java.sun.com/xml/ns/javaee"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd">

      <display-name>Archetype Created Web Application</display-name>

      <context-param>
          <param-name>contextConfigLocation</param-name>
          <param-value>/WEB-INF/spring/applicationContext.xml</param-value>
      </context-param>

      <listener>
          <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
      </listener>

      <servlet>
          <servlet-name>dispatcher</servlet-name>
          <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
          <init-param>
              <param-name>contextConfigLocation</param-name>
              <param-value>/WEB-INF/spring/dispatcher-servlet.xml</param-value>
          </init-param>
          <load-on-startup>1</load-on-startup>
      </servlet>

      <servlet-mapping>
          <servlet-name>dispatcher</servlet-name>
          <url-pattern>/</url-pattern>
      </servlet-mapping>

  </web-app>
  ```
* `applicationContext.xml` : 수정사항 없음, 프로젝트 진행하면서 수정 및 추가

* `dispatcher-servlet.xml` : mvc관련 설정 추가
  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans:beans xmlns="http://www.springframework.org/schema/mvc"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xmlns:beans="http://www.springframework.org/schema/beans"
               xmlns:context="http://www.springframework.org/schema/context"
               xsi:schemaLocation="http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd
  		http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
  		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

      <!--애너테이션 인식-->
      <annotation-driven />

      <!--정적 자원-->
      <resources mapping="/resources/**" location="/resources/" />

      <!--viewResolver 설정-->
      <beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
          <beans:property name="prefix" value="/WEB-INF/views/" />
          <beans:property name="suffix" value=".jsp" />
      </beans:bean>

      <context:component-scan base-package="com.doubles.ex00" />

  </beans:beans>
  ```

* `log4j.xml` : 로그 관련 설정 추가
  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE log4j:configuration PUBLIC "-//APACHE//DTD LOG4J 1.2//EN" "log4j.dtd">
  <log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">

  	<!-- Appenders -->
  	<appender name="console" class="org.apache.log4j.ConsoleAppender">
  		<param name="Target" value="System.out" />
  		<layout class="org.apache.log4j.PatternLayout">
  			<param name="ConversionPattern" value="%-5p: %c - %m%n" />
  		</layout>
  	</appender>

  	<!-- Application Loggers -->
  	<logger name="com.doubles.demo">
  		<level value="info" />
  	</logger>

  	<!-- 3rdparty Loggers -->
  	<logger name="org.springframework.core">
  		<level value="info" />
  	</logger>

  	<logger name="org.springframework.beans">
  		<level value="info" />
  	</logger>

  	<logger name="org.springframework.context">
  		<level value="info" />
  	</logger>

  	<logger name="org.springframework.web">
  		<level value="info" />
  	</logger>

  	<!-- Root Logger -->
  	<root>
  		<priority value="warn" />
  		<appender-ref ref="console" />
  	</root>

  </log4j:configuration>
  ```

* 디렉토리설정 : 자바 코드, 설정파일, 테스트 코드 등이 위치할 디렉토리를 생성하고 각각의 role을 정의
  * 해당 디렉토리를 우클릭하고 `Mark Directory as`에서 각각에 해당하는 role을 선택
    * src/main/java : `sources Root`
    * src/main/resourecs : `resources Root`
    * src/test/java : `test sources Root`
    * src/test/resourecs : `test resources Root`

## 04) Tomcat Server 설정
* `Run`>`edit configurations`>`tomcat server`>`local` 선택
* `Name` : 사용자가 정의한 tomcat server 이름
* `Server`탭에서 설정할 것
  * `configure...` : tomcat 디렉토리 설정
  * `port` : 포트 설정, 기본설정은 8080
* `Deployment`탭에서 설정할 것
  * `artifact` : `war exploded` 선택

## 05) 그외의 설정
* src/main/java : 기본 package 생성
* src/test/java : 기본 테스트 package 생성
* WEB-INF/views : jsp 페이지가 위치할 디렉토리 생성

## 06) `HomeController`, `home.jsp` 작성
* `HomeController`
  ```java
  @Controller
  public class HomeController {

      @RequestMapping("/")
      public String home(Model model) {

          model.addAttribute("greeting", "hello world");

          return "home";
      }

  }
  ```

* `home.jsp`
  ```xml
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <html>
  <head>
      <title>Home</title>
  </head>
  <body>

      <h2>This is welcome page....</h2>

      <p>${greeting}</p>

  </body>
  </html>
  ```

### # 참고 출처
* http://javaengine.tistory.com/313
* http://multifrontgarden.tistory.com/108
* https://nesoy.github.io/articles/2017-02/SpringMVC
* http://jhleed.tistory.com/75