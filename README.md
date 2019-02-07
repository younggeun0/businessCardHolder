# 📇명함첩 토이 프로젝트
* Jave SE Toy Project, 명첩
* 클라이언트가 명함 이미지와 메모를 서버에 전달하여 저장
  * 저장된 명함들 리스트와 상세정보틀 볼 수 있음

## 핵심 기능
* 클라이언트 서버 프로그래밍  
  * 서버에서 요청에 따른 모든 DAO 처리
* 클라이언트가 올린 새로운 이미지 저장
  * 클라이언트가 상세 명함 조회 시 이미지를 받지 않았다면 FileStream으로 다운로드

## UI 설계 - 서버

![01](https://github.com/younggeun0/younggeun0.github.io/blob/master/_posts/img/toyProjects/BCH/01.png?raw=true)


## UI 설계 - 클라이언트

![02](https://github.com/younggeun0/younggeun0.github.io/blob/master/_posts/img/toyProjects/BCH/02.png?raw=true)

![03](https://github.com/younggeun0/younggeun0.github.io/blob/master/_posts/img/toyProjects/BCH/03.png?raw=true)

![04](https://github.com/younggeun0/younggeun0.github.io/blob/master/_posts/img/toyProjects/BCH/01.png?raw=true)

![05](https://github.com/younggeun0/younggeun0.github.io/blob/master/_posts/img/toyProjects/BCH/05.png?raw=true)

## DB 설계

* 테이블, 시퀀스(함수에서 사용하기 위한) 생성
```sql
CREATE TABLE business_card(
    bc_num CHAR(9) CONSTRAINT pk_bc PRIMARY KEY,
    input_date DATE DEFAULT SYSDATE,
    memo VARCHAR2(400) NOT NULL,
    file_name VARCHAR2(100) NOT NULL
);

CREATE SEQUENCE seq_bc
  START WITH 1
  MAXVALUE 999999
  INCREMENT BY 1
  NOCYCLE
  CACHE 20;
```

* 새로운 데이터 입력 시 함수로 Primary Key인 bc_num값을 입력

```sql
CREATE OR REPLACE FUNCTION bc_code
RETURN CHAR
IS
BEGIN
  RETURN concat('bc_', LPAD(seq_bc.nextval, 6, 0));
END;
/
```

## Class Diagram - 서버

![12](https://github.com/younggeun0/younggeun0.github.io/blob/master/_posts/img/toyProjects/BCH/12.png?raw=true)

## Class Diagram - 클라이언트

![13](https://github.com/younggeun0/younggeun0.github.io/blob/master/_posts/img/toyProjects/BCH/13.png?raw=true)


## 구현 화면 - 서버

![06](https://github.com/younggeun0/younggeun0.github.io/blob/master/_posts/img/toyProjects/BCH/06.png?raw=true)

## 구현 화면 - 클라이언트

![07](https://github.com/younggeun0/younggeun0.github.io/blob/master/_posts/img/toyProjects/BCH/07.png?raw=true)

![08](https://github.com/younggeun0/younggeun0.github.io/blob/master/_posts/img/toyProjects/BCH/08.png?raw=true)

![09](https://github.com/younggeun0/younggeun0.github.io/blob/master/_posts/img/toyProjects/BCH/09.png?raw=true)

![10](https://github.com/younggeun0/younggeun0.github.io/blob/master/_posts/img/toyProjects/BCH/10.png?raw=true)

![11](https://github.com/younggeun0/younggeun0.github.io/blob/master/_posts/img/toyProjects/BCH/11.png?raw=true)
