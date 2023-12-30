+++
title = 'Spring6'
date = 2023-12-27T10:03:16-05:00
+++

## New Project
1. new project, and then delete `src` folder
2. new module, and then update `pom.xml` and creating new package and `package/JavaClass.java`
3. new `resources/bean.xml`
4. new `package/TestJavaClass.java`

```xml {filename="module/pom.xml"}
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>6.1.2</version>
</dependency>
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.10.1</version>
</dependency>
```

```xml {filename = "resources/bean.xml"}
<bean id="user" class="com.mrtutu.spring6.User"></bean>
```

```java {filename = "package/TestJavaClass.java"}
@Test
public void testUserObject() {
    // load spring conf file, creating object
    ApplicationContext context =
            new ClassPathXmlApplicationContext("bean.xml");

    // get object
    User user = (User)context.getBean("user");
    System.out.println(user);

    // call method of object
    user.add();
  }
```

## Explain how object was created and saved
1. load bean.xml
2. parse bean.xml
3. get `id` and `class` from xml
4. create object from full path
```java
// get class name
Class clazz = Class.forName("com.mrtutu.spring6.User");
// create object from the class name
// legacy: Object o = clazz.newInstance();
User user = (User) clazz.getDeclaredConstructor().newInstance();
```
### objects storage
```java {filename="DefaultListableBeanFactory.java"}
Map<String, BeanDefinition> beanDefinitionMap
// key: unique id
// value: all class descriptions
```

## Log4j2
```xml {filename="module/pom.xml"}
<!--log4j2-->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.20.0</version>
</dependency>
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-slf4j2-impl</artifactId>
    <version>2.20.0</version>
</dependency>
```

```xml {filename = "resources/log4j2.xml"}
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <loggers>
        <!--
            level指定日志级别，从低到高的优先级：
                TRACE < DEBUG < INFO < WARN < ERROR < FATAL
                trace：追踪，是最低的日志级别，相当于追踪程序的执行
                debug：调试，一般在开发中，都将其设置为最低的日志级别
                info：信息，输出重要的信息，使用较多
                warn：警告，输出警告的信息
                error：错误，输出错误信息
                fatal：严重错误
        -->
        <root level="DEBUG">
            <appender-ref ref="spring6log"/>
            <appender-ref ref="RollingFile"/>
            <appender-ref ref="log"/>
        </root>
    </loggers>

    <appenders>
        <!--输出日志信息到控制台-->
        <console name="spring6log" target="SYSTEM_OUT">
            <!--控制日志输出的格式-->
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss SSS} [%t] %-3level %logger{1024} - %msg%n"/>
        </console>

        <!--文件会打印出所有信息，这个log每次运行程序会自动清空，由append属性决定，适合临时测试用-->
        <File name="log" fileName="/Users/yixianwang/projects/spring6/spring6_log/test.log" append="false">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} %-5level %class{36} %L %M - %msg%xEx%n"/>
        </File>

        <!-- 这个会打印出所有的信息，
            每次大小超过size，
            则这size大小的日志会自动存入按年份-月份建立的文件夹下面并进行压缩，
            作为存档-->
        <RollingFile name="RollingFile" fileName="/Users/yixianwang/projects/spring6/spring6_log/app.log"
                     filePattern="log/$${date:yyyy-MM}/app-%d{MM-dd-yyyy}-%i.log.gz">
            <PatternLayout pattern="%d{yyyy-MM-dd 'at' HH:mm:ss z} %-5level %class{36} %L %M - %msg%xEx%n"/>
            <SizeBasedTriggeringPolicy size="50MB"/>
            <!-- DefaultRolloverStrategy属性如不设置，
            则默认为最多同一文件夹下7个文件，这里设置了20 -->
            <DefaultRolloverStrategy max="20"/>
        </RollingFile>
    </appenders>
</configuration>
```

```java {filename = "package/TestJavaClass.java"}
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassName {
  private Logger logger = LoggerFactory.getLogger(ClassName.class);

  @Test
  public void testFunc() {
    logger.info("this is info from logger");
  }
}
```
