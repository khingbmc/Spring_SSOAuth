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

### 💁🏻‍♂️ When you use service you send user info to authenticate with IDP Server (Our Service)

# 👥Team Member

|<a href=""><img src="resource/kim.jpg" width="100px"></a>  |![](resource/fluke.jpg=100x100) |![](resource/khing.jpg=100x100) | ![](resource/tor.jpg=100x100)  |
| :-: | :-: | :-: | :-: |
| โฆษิต โฆษิตศรีคุณากร |พีรพล อ่อนแฮน |รฐนนท์ จันทนะสุคนธ์|รัชภัทร เพิ่มพูล
|60070008 |      60070067      |      60070079      | 60070083|
|    [@KimKosit](https://github.com/KimKosit)    |     [@pumy2517](https://github.com/pumy2517)     |     [@khingbmc](https://github.com/khingbmc)     | [@torasann](https://github.com/torasann) |



