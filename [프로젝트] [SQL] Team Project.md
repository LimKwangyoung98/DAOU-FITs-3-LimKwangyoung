# SQL 기반 금융권 팀 프로젝트 보고서

## 1. 프로젝트 개요

- **조 이름 :** 오로나민C 조
- **프로젝트 이름 :** 중개형 ISA 계좌 서비스
- **진행 기간 :** 2025년 1월 31일(금) ~ 2025년 2월 3일(월)

## 2. 프로젝트 팀원

- 박선홍
- 이종우
- 임광영
- 최한얼

## 3. 프로젝트 배경 및 목적

최근 금융 시장에서는 자산 관리 및 절세 효과를 위한 ISA(개인종합자산관리계좌)가 주목받고 있지만, ISA 계좌는 가입 요건, 세금 혜택, 납입 한도 및 유형(일반형, 서민형) 등 복잡한 규정을 포함하고 있습니다. 이에 본 프로젝트는 투자자들이 ISA를 쉽게 활용할 수 있는데 목적이 있습니다.

본 프로젝트의 목적은 다음과 같습니다.
- 중개형 ISA 시스템 구축
- 세금 및 절세 시스템
- 거래 내역 및 입출금 관리

## 4. 프로젝트 구조 및 단계별 진행 내용

### 4.1. 중개형 ISA 시스템 사전 조사

- ISA(Individual Savings Account)
  - 예금과 주식, 펀드 등 다양한 금융상품에 투자할 수 있는 계좌입니다.
- ISA 계좌 유형
  - 일반형
    - 가입 요건 : 만 19세 이상 또는 전년도 근로소득이 있는 만 15세 ~ 19세 미만.
    - 비과세한도 : 200만원
  - 서민형
    - 가입 요건 : 전년도 총급여 5천만원 또는 종합소득 3천 8백만원 이하.
    - 비과세한도 : 400만원
  - 농어민
    - 가입요건 : 전년도 종합소득 3천 8백만원 이하 농어민.
    - 비과세한도 : 400만원
  - 비과세한도 초과시 9.9% 분리과세 적용
  - 의무가입기간 : 3년

### 4.2. 데이터 수집
- **데이터 출처 :** investing.com
- **데이터 유형 :** CSV 파일

### 4.2. 데이터베이스 설계

- **데이터베이스 시스템 :** Oracle
- **설계된 테이블 :**
  - `CUSTOMERS`(고객)
  - `CUSTOMER_GRADES`(고객 등급)
  - `ACCOUNT_TRANSFERS`(입출금내역)
  - `ACCOUNTS`(계좌)
  - `ACCOUNT_TYPES`(계좌유형)
  - `ASSET_HOLDINGS`(보유자산)
  - `TRANSACTIONS`(거래)
  - `AUTOMATIC_BUY_SETTINGS`(자동 거래 세팅)
  - `ASSET_TYPES`(자산 종류)
  - `ASSETS`(자산)
  - `ASSET_PRICE_HISTORIES`(자산가격)
- **키 테이블 관계 :**
  - `CUSTOMERS_GRADES` $\rightarrow$ `CUSTOMERS` : $1:N$
  - `CUSTOMERS` $\rightarrow$ `ACCOUNTS` : $1:N$
  - `ACCOUNTS_TYPES` $\rightarrow$ `ACCOUNTS` : $1:N$
  - `ACCOUNTS` $\rightarrow$ `ACCOUNT_TRANSFERS` : $1:N$
  - `ACCOUNTS` $\rightarrow$ `ASSET_HOLDINGS` : $1:N$
  - `ACCOUNTS` $\rightarrow$ `TRANSACTIONS` : $1:N$
  - `ACCOUNTS` $\rightarrow$ `AUTOMATIC_BUY_SETTINGS` : $1:N$
  - `ASSETS` $\rightarrow$ `ASSET_HOLDINGS` : $1:N$
  - `ASSETS` $\rightarrow$ `TRANSACTIONS` : $1:N$
  - `ASSETS` $\rightarrow$ `AUTOMATIC_BUY_SETTINGS` : $1:N$
  - `ASSET_TYPES` $\rightarrow$ `ASSETS` : $1:N$
  - `ASSETS` $\rightarrow$ `ASSET_PRICE_HISTORIES` : $1:N$

### 4.3. SQL 쿼리 설계 및 구현

IRP 시스템을 구현하기 위해 다음과 같은 SQL 쿼리를 설계했습니다.
    
### 4.4. ISA 시스템 가입 결과

## 5. 프로젝트 결과

## 6. 프로젝트의 의의 및 개선점

### 6.1. 의의

### 6.2. 개선점 및 향후 방향

## 7. 결론