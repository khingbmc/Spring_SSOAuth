# 🤛🏾 SSO Authentication🤜🏾

เป็น Service ที่ให้บริการในการยืนยันตัวตนกลางที่รองรับการให้ผู้ใช้งานลงชื่อเข้าใช้งานระบบเพียงแค่รอบเดียว แล้วสามารถใช้งานระบบได้หลายระบบ โดยทาง Service จะให้บริการในด้าน 
  - Authentication Service
    - with saml2.0 standard
  - Register User Service
  - Collect User info to Database
    - may be use sqlite
  - Generate Token
    - use jwt token

## SSO Flow Diagram
![SSoFlowDiagram](/resource/saml2.0.png)



## Dependencies ที่มีการใช้งาน

```xml
    <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-freemarker</artifactId>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt</artifactId>
        <version>0.9.1</version>
    </dependency>
```
- freemarker ใช้สำหรับเรียกใช้ template HTML
- jjwt ใช้สำหรับ json web token
## Example JWT Tokens

![SSoFlowDiagram](/resource/jwt.png)

### 💁🏻‍♂️ When you want to use service you focus on sp directory

```
sp
│   .mvn
│   .DS_Store   
│   .gitignore
|   mvnw
|   mvnw.cmd
|   pom.xml
|
└───src
│   │   .DS_Store
│   │
│   └───main
│       │   file111.txt
│       │   file112.txt
|       └───java
|       |   └───com/saml/sp
|       |       └───config
|       |       |   |   AppConfig.java
|       |       |   |   BeanConfig.java
|       |       |   |   SecurityConfiguration.java
|       |       └───web
|       |       |   |   ServiceProviderController.java
|       |       |   SPApplication.java
|       |   └───org/spring/framework/security/saml
|       └───resources
|       |   └───templates
|       |   |   └───spi
|       |   |       |   generic-error.vm
|       |   |       |   select-provider.vm
|       |   |   |   data.html
|       |   |   |   logged-in.html
|       |   |   |   test.html
|       |   |   application.properties
|       |   |   application.yml    
│       └───test/java/com/saml/sp

```

## in file SecurityConfiguration.java 
โดยการ Config Authorization ทำใน file นี้โดยสามารถอ่าน method ได้จาก Library ของ [Spring Security](https://www.baeldung.com/spring-security-expressions)
![SecurityConfig](/resource/security_config.png)




## and config application.yml in resources directory
![appConfig](/resource/application_config.png)

### in file application.yml you must modify entity-id 
  - entity-id คิือชื่อ package ของ service ที่จะนำไปใช้โดยห้ามซ้ำกับ service อื่นเลย

### เมื่ือทำการเปลี่ยน entity-id แล้วให้นำชื่อที่เปลี่ยนใน entity-id ส่งมายัง email ของผู้พัฒนาหรือแจ้งก่อนวันนำเสนอได้เลยครับ
# 👥Team Member

|<a href="https://github.com/KimKosit"><img src="resource/kim.jpg" width="100px"></a>  |<a href="https://github.com/pumy2517"><img src="resource/fluke.jpg" width="100px"></a>  |<a href="https://github.com/khingbmc"><img src="resource/khing.jpg" width="100px"></a>  | <a href="https://github.com/torasann"><img src="resource/tor.jpg" width="100px"></a>  |
| :-: | :-: | :-: | :-: |
| โฆษิต โฆษิตศรีคุณากร |พีรพล อ่อนแฮน |รฐนนท์ จันทนะสุคนธ์|รัชภัทร เพิ่มพูล
|60070008 |      60070067      |      60070079      | 60070083|
|    [@KimKosit](https://github.com/KimKosit)    |     [@pumy2517](https://github.com/pumy2517)     |     [@khingbmc](https://github.com/khingbmc)     | [@torasann](https://github.com/torasann) |


S