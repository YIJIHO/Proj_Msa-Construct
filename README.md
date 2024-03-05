## Lay-By-System

### 1. About The Project
```plaintext
 소셜 미디어 기반으로 예약구매 서비스의 서버개발 / 1인 4주
```
### 2. Requirements
```plaintext
 1. 사용자의 회원가입 시 외부 이메일 인증을 거쳐야한다.
 2. 상품의 오픈시간을 통해 조회를 제어하도록한다.
 3. 실제 결제는 진행하지 않으나 수량은 엄격하게 제어하도록 한다.(주문수량은 n개일 수 있으나 상품수량을 초과할 수 없다.) 
 4. 동시에 대량의 요청을 처리할 수 있도록 한다. 이때, 요청은 상품수량의 2배이며 3번 요구사항에 따라 에러를 제어할 수 있게 한다.
```
### 3. Specification
![Java](https://img.shields.io/badge/Java-9932cc?style=for-the-badge&logo=java&logoColor=white&labelColor=gray&label=17)
![Spring Boot](https://img.shields.io/badge/-SpringBoot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white&labelColor=gray&label=3.2.2)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white&labelColor=gray&label=8.0)
![Redis](https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white&labelColor=gray&label=7.2.4)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Git](https://img.shields.io/badge/-Git-F05032?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-e9967a?style=for-the-badge&logo=github&logoColor=white)
![IntelliJ](https://img.shields.io/badge/IntelliJ-6a5acd?style=for-the-badge&logo=intellij-idea&logoColor=white)
### 3. ERD
![image](https://github.com/YIJIHO/Proj_Msa-Construct/assets/127674150/ae33948c-8950-4174-875b-02e04b67b24f)

### 4. Architecture
![image](https://github.com/YIJIHO/Proj_Msa-Construct/assets/127674150/eaf468f9-a593-43db-985d-109241775949)
### 5. File Structure
```plaintext
Lay-By-System
 |-->ApiGateway
 |-->Docker
 |     └>docker-compose.yml
 |-->UserModule
 |-->ActiveModule
 |-->NewsfeedModule
 |-->ProductModule
 |-->OrderModule
 └-->Managing-StockModule
```
### 6. API Documentation


### 7. Command
```plaintext
//docker컨테이너 올리기    
docker-compose up

//docker컨테이너 내리기  
docker-compose down

//docker컨테이너 시작
docker-compose start

//docker컨테이너 정지  
docker-compose stop  
```
