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

## Explain how object was created and saved in background
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

## IoC (Inversion of Control)
> IoC is an idea of inversion of control, and DI is a specific implementation of IoC.
- **Inversion** means: 
  - Give the creation responsibilities of objects to the third-party container.
  - Give the maintenance responsibilities of relationship of objects to the third-party container.
### IoC Container
Spring 通过 **IoC container** 来管理所有 
- **Java 对象的实例化和初始化**， 
- **控制对象与对象之间的依赖关系**。

我们将由 IoC 容器管理的 Java 对象称为 Spring **Bean**，它与使用关键字 new 创建的 Java 对象没有任何区别。

### DI (Dependency Injection)
- **指Spring创建对象的过程中，将对象依赖属性通过配置进行注入**
#### Approach 1: set injection
#### Approach 2: constructor injection

### How IoC is implemented in Spring
- Spring 的 IoC 容器就是 IoC思想的一个落地的产品实现。
- IoC容器中管理的组件也叫做 bean。
- **在创建 bean 之前，首先需要创建IoC 容器。**
- Spring 提供了IoC 容器的两种实现方式：

**①BeanFactory**

这是 IoC 容器的基本实现，是 Spring 内部使用的接口。面向 Spring 本身，不提供给开发人员使用。

**②ApplicationContext**

BeanFactory 的子接口，提供了更多高级特性。面向 Spring 的使用者，几乎所有场合都使用 ApplicationContext 而不是底层的 BeanFactory。

**ApplicationContext的主要实现类**

| 类型名                          | 简介                                                         |
| ------------------------------- | ------------------------------------------------------------ |
| ClassPathXmlApplicationContext  | 通过读取类路径下的 XML 格式的配置文件创建 IOC 容器对象       |
| FileSystemXmlApplicationContext | 通过文件系统路径读取 XML 格式的配置文件创建 IOC 容器对象     |
| ConfigurableApplicationContext  | ApplicationContext 的子接口，包含一些扩展方法 refresh() 和 close() ，让 ApplicationContext 具有启动、关闭和刷新上下文的能力。 |
| WebApplicationContext           | 专门为 Web 应用准备，基于 Web 环境创建 IOC 容器对象，并将对象引入存入 ServletContext 域中。 |

### manage bean by xml
#### get bean
```java {filename = "package/TestUser"}
ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
// get bean by id
User user1 = (User) context.getBean("user");
// get bean by class
User user2 = context.getBean(User.class);
// get bean by id && class
User user3 = context.getBean("user", User.class);
```
#### DI by setter
1. create class, define properties and having corresponding setter methods
2. setup `bean-di.xml`
```xml {filename = "resources/bean-di.xml"
<bean id="book" class="com.mrtutu.spring6.iocxml.di.Book">
    <property name="bname" value="SCI publish"></property>
    <property name="author" value="Yixian"></property>
</bean>
```

#### DI by constructor
1. create class, define properties and having corresponding constructor with params
2. setup `bean-di.xml`
```xml {filename = "resources/bean-di.xml"
<bean id="bookCon" class="com.mrtutu.spring6.iocxml.di.Book">
    <constructor-arg name="bname" value="IEEE publish"></constructor-arg>
    <constructor-arg name="author" value="Yixian"></constructor-arg>
</bean>
```

#### special value
1. value
```xml
<!-- 使用value属性给bean的属性赋值时，Spring会把value属性的值看做字面量 -->
<property name="name" value="张三"/>
```
2. null value
```xml {filename = "resources/bean-di.xml"}
<property name="others">
    <null/>
</property>
```
3. xml symbol
```xml {filename = "resources/bean-di.xml"}
<property name="others" value="&lt;&gt;"></property>
```
4. CDATA tag
```xml {filename = "resources/bean-di.xml"}
<property name="others">
    <value><![CDATA[a < b]]></value>
</property>
```

#### speical type injection
##### 1. object injection
1. outside bean: use `ref` instead of `value`
2. inner bean
3. sequential bean: `getter` is required
##### 2. array injection
```xml {filename = "resources/bean-diarray.xml"}
<property name="loves">
    <array>
        <value>coding</value>
        <value>learning</value>
        <value>eating</value>
    </array>
</property>
```
##### 3. set injection
```xml {filename = "resources/bean-dilist.xml"}
<property name="empList">
    <list>
        <ref bean="empone"></ref>
        <ref bean="emptwo"></ref>
    </list>
</property>
```
##### 4. map injection
```xml {filename = "resources/bean-dilist.xml"}
<property name="s2t">
    <map>
        <entry>
            <key>
                <value>t1t1t1</value>
            </key>
            <ref bean="teacherone"></ref>
        </entry>
        <entry>
            <key>
                <value>t2t2t2</value>
            </key>
            <ref bean="teachertwo"></ref>
        </entry>
    </map>
</property>
```
#### p namespace injection
```xml {filename = "resources/bean-diref.xml"}
xmlns:p="http://www.springframework.org/schema/p"

<bean id="studentp" class="com.mrtutu.spring6.iocxml.dimap.Student"
p:sid="psid111" p:sname="yw" p:lessonList-ref="lessonList" p:s2t-ref="s2t">
</bean>
```

#### outside file injection
```xml{filename = "spring6-ioc-xml/pom.xml"}
<!-- MySQL驱动 -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>

<!-- 数据源 -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.2.16</version>
</dependency>
```

```{filename = "resources/jdbc.properties"}
jdbc.user=root
jdbc.password=root
jdbc.url=jdbc:mysql://localhost:3306/spring?serverTimezone=UTC
jdbc.driver=com.mysql.cj.jdbc.Driver
```

```xml {filename="resources/bean-jdbc.xml"}
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

  <!--引入外部文件-->
  <context:property-placeholder location="classpath:jdbc.properties"></context:property-placeholder>

  <!--数据库数据注入-->
  <bean id="druidDataSource" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="url" value="${jdbc.url}"/>
    <property name="driverClassName" value="${jdbc.driver}"/>
    <property name="username" value="${jdbc.user}"/>
    <property name="password" value="${jdbc.password}"/>
  </bean>
</beans>
```

```java {filename="spring6-ioc-xml/src/main/java/com.mrtutu.spring6.iocxml.jdbc/TestJdbc"}
package com.mrtutu.spring6.iocxml.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestJdbc {
    @Test
    public void demo1() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    }

    @Test
    public void demo2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-jdbc.xml");
        DruidDataSource druidDataSource = context.getBean("druidDataSource", DruidDataSource.class);
        System.out.println(druidDataSource.getUrl());
    }
}
```

#### bean scope
```xml {filename="resources/bean-scope.xml"}
<bean id="orders" class="com.mrtutu.spring6.iocxml.scope.Orders" scope="prototype"></bean>
```

|                    | Meaning                                                  | When to create object         |
|--------------------|----------------------------------------------------------|-------------------------------|
| singleton(default) | In IoC container, the object of bean is always singleton | when initialize IoC container |
| prototype          | In IoC container, this bean has many objects             | when get bean                 | 

#### bean lifetime
1. bean对象创建（调用无参构造器）
2. 给bean对象设置属性
3. bean的后置处理器（初始化之前）
4. bean对象初始化（需在配置bean时指定初始化方法）
5. bean的后置处理器（初始化之后）
6. bean对象就绪可以使用
7. bean对象销毁（需在配置bean时指定销毁方法）
8. IOC容器关闭

```xml {filename="resources/bean-lifecycle.xml"}
<!-- bean的后置处理器要放入IOC容器才能生效 -->
<bean id="myBeanPost" class="com.mrtutu.spring6.iocxml.lifecycle.MyBeanPost"/>
```

#### FactoryBean
- it's always used for integrating third party framework.

#### auto match and load based on xml
- autowire by type: must be unique
- autowire by name: bean **id** must be euqal to property name

### manage bean by annotation
1. 引入依赖
2. 开启组件扫描
3. 使用注解定义Bean
4. DI

#### open component-scan
```xml {filename="resources/bean.xml"}
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <context:component-scan base-package="com.mrtutu.spring6"></context:component-scan>
</beans>
```

#### define bean
Spring 提供了以下多个注解，这些注解可以直接标注在 Java 类上，将它们定义成 Spring Bean。

| 注解        | 说明                                                         |
| ----------- | ------------------------------------------------------------ |
| @Component  | 该注解用于描述 Spring 中的 Bean，它是一个泛化的概念，仅仅表示容器中的一个组件（Bean），并且可以作用在应用的任何层次，例如 Service 层、Dao 层等。  使用时只需将该注解标注在相应类上即可。 |
| @Repository | 该注解用于将数据访问层（Dao 层）的类标识为 Spring 中的 Bean，其功能与 @Component 相同。 |
| @Service    | 该注解通常作用在业务层（Service 层），用于将业务层的类标识为 Spring 中的 Bean，其功能与 @Component 相同。 |
| @Controller | 该注解通常作用在控制层（如SpringMVC 的 Controller），用于将控制层的类标识为 Spring 中的 Bean，其功能与 @Component 相同。 |

#### @Autowired injection
> `byType` is default
1. on property

#### @Resource injection
