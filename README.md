## 예약구매시스템  -   수정중

### 1. 프로젝트 개요
```plaintext
일정 시간에 오픈되는 상품을 판매하는 서비스의 서버개발
```

### 2. 요구사항 및 스펙
```plaintext
[요구사항]
 1. 사용자의 회원가입 시 외부 이메일 인증을 거쳐야한다.
 2. 상품의 오픈시간을 통해 조회를 제어하도록한다.
 3. 실제 결제는 진행하지 않으나 수량은 엄격하게 제어하도록 한다.(주문시 상품은 N개를 선택할 수 있으며 결제주문수량은 상품수량을 초과할 수 없다.) 
 4. 동시에 대량의 요청을 처리할 수 있도록 한다. 이때, 요청은 상품수량의 2배이며 3번 요구사항에 따라 에러를 제어할 수 있게 한다.
[프로젝트 스펙]
 * Back-end : Java17, SpringBoot 3.2.2
 * Database : MySql 8.0, Redis 7.2.4
 * Infra : Docker
 * Tools : Github, IntelliJ
```

### 3. ERD

### 4. Architecture

### 5. API Documentation

### 6. File Structure
```plaintext
Root
 -
 -
 -
 -
 - 
```

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
