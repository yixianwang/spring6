+++
title = 'Spring6'
date = 2023-12-27T10:03:16-05:00
+++

## object creation
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

