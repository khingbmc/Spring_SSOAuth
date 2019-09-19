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

## API Structure overview Plan

<center>
  <image src="resource/saml2.0.png" alt="saml2.0 work flow"/>
</center>

## Dependencies ที่มีการใช้งาน
### pom.xml useful dependency
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

### 💁🏻‍♂️ When you use service you send user info to authenticate with IDP Server (Our Service)

# 👥Team Member

|![](resource/kim.jpg) |![](resource/fluke.jpg) |![](resource/khing.jpg) | ![](resource/tor.jpg)  |
| :-: | :-: | :-: | :-: |
| โฆษิต โฆษิตศรีคุณากร |พีรพล อ่อนแฮน |รฐนนท์ จันทนะสุคนธ์|รัชภัทร เพิ่มพูล
|60070008 |      60070067      |      60070079      | 60070083|
|    [@KimKosit](https://github.com/KimKosit)    |     [@pumy2517](https://github.com/pumy2517)     |     [@khingbmc](https://github.com/khingbmc)     | [@torasann](https://github.com/torasann) |



