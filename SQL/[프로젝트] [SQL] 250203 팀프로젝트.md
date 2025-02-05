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

### 4.2. ERD 작성

![ERD](images/SQL-ERD.png)

### 4.3. 데이터 수집

- **데이터 출처 :** investing.com
- **데이터 유형 :** CSV 파일

### 4.4. 데이터베이스 설계

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

### 4.5. SQL 쿼리 설계 및 구현

ISA 시스템을 구현하기 위해 다음과 같은 SQL 쿼리를 설계했습니다.

`ROLLUP` 함수를 통해 국내 주식 및 해외 ETF 별 소계를 계산했습니다.
```sql
SELECT
    NVL(A.ASSET_TYPE_ID, 'Total') ASSET_TYPE_ID,
    ROUND(AVG(T.TRANSACTION_AMOUNT), 2) AVG_AMOUNT,
    AVG(T.TRANSACTION_PRICE) AVG_PRICE
FROM TRANSACTIONS T, ASSETS A
WHERE T.ASSET_ID = A.ASSET_ID
GROUP BY ROLLUP(A.ASSET_TYPE_ID);
```

`LAG` 함수를 통해 특정 종목의 일자별 전일 대비 가격변동을 계산했습니다.
```sql
SELECT 
    asset_id,
    creation_timestamp,
    PRICE,
    LAG(PRICE) OVER (
        PARTITION BY asset_id 
        ORDER BY creation_timestamp
    ) AS prev_price,
    PRICE - LAG(PRICE) OVER (
        PARTITION BY asset_id 
        ORDER BY creation_timestamp
    ) AS price_change
FROM ASSET_PRICE_HISTORIES
WHERE ASSET_ID = '1';
```

`ROWS BETWEEN`을 통해 5일 간의 이동평균을 계산했습니다.
```sql
SELECT 
    asset_id,
    creation_timestamp,
    PRICE,
    AVG(PRICE) OVER (
        PARTITION BY asset_id 
        ORDER BY creation_timestamp 
        ROWS BETWEEN 4 PRECEDING AND CURRENT ROW
    ) AS moving_avg_5d
FROM ASSET_PRICE_HISTORIES
WHERE ASSET_ID = '1';
```

윈도우 함수 `RANK()`를 통해 고객별 순위를 계산했습니다.
```sql
SELECT
    RANK() OVER (ORDER BY SUM(t.transaction_price * t.transaction_amount) DESC) AS customer_rank,
    c.customer_id,
    c.customer_name,
    COUNT(t.transaction_id) AS total_transactions,
    SUM(t.transaction_price * t.transaction_amount) AS total_transaction_amount
FROM CUSTOMERS c
JOIN ACCOUNTS a ON c.customer_id = a.customer_id
JOIN TRANSACTIONS t ON a.account_number = t.account_number
GROUP BY c.customer_id, c.customer_name
ORDER BY customer_rank;
```

`MERGE INTO` 키워드를 통해 자산의 최신 가격을 업데이트 했습니다.
```sql
MERGE INTO ASSETS a
USING (
    SELECT
        aph.asset_id,
        aph.price AS latest_price
    FROM ASSET_PRICE_HISTORIES aph
    JOIN (
        SELECT asset_id, MAX(creation_timestamp) AS latest_timestamp
        FROM ASSET_PRICE_HISTORIES
        GROUP BY asset_id
    ) latest ON aph.asset_id = latest.asset_id AND aph.creation_timestamp = latest.latest_timestamp
) latest_price_data
ON (a.asset_id = latest_price_data.asset_id)
WHEN MATCHED THEN
    UPDATE SET a.asset_latest_price = latest_price_data.latest_price;
```

효율적인 Query를 위한 인덱스를 다음과 같이 설정했습니다.
```sql
-- 자산 가격 변동 이력 조회 시 자산별로 select 하는 쿼리가 많음.
asset_price_histories table_index (asset_id)

-- 자동 매수 설정 조회 시 계좌별로 설정된 자동 매수를 select 하는 쿼리가 많음.
automatic_buy_setting table_index (account_number)

-- 입출금 내역 조회 시 계좌별 입출금 내역을 보여주기 위한 쿼리가 많음.
account_transfers table_index (account_number)

-- 거래 내역 조회시 계좌 별로 거래 내역을 불러오는 쿼리가 많음.
transactions table_index (account_number)

-- 자산 가격 변동 이력의 경우 대용량 데이터를 다루는 테이블
-- order by를 시간 순으로 하는 경우가 많기 때문에
-- creation_timestamp에 index 설정 시 효율적일 수 있음.
asset_price_histories table_index (creation_timestamp)
```

효율적인 Query를 위한 뷰를 다음과 같이 설정했습니다.
```sql
-- 고객별 거래 요약 뷰
CREATE OR REPLACE VIEW CUSTOMER_TRANSACTION_SUMMARY AS
SELECT
    c.customer_id,
    c.customer_name,
    COUNT(t.transaction_id) AS total_transactions,
    SUM(t.transaction_price * t.transaction_amount) AS total_transaction_amount,
    MAX(t.creation_timestamp) AS last_transaction_date
FROM CUSTOMERS c
JOIN ACCOUNTS a ON c.customer_id = a.customer_id
JOIN TRANSACTIONS t ON a.account_number = t.account_number
GROUP BY c.customer_id, c.customer_name;

-- ISA계좌 세액공제 효과 비교 뷰
CREATE OR REPLACE VIEW ISA_TAX_CREDIT_EFFECT AS
SELECT 
    CUSTOMER_ID AS "고객ID",
    ACCUMULATED_PROFIT AS "실현이익",
    TAXES AS "ISA 적용 세금",
    NO_ISA AS "일반계좌 세금",
    NO_ISA - TAXES AS "중도해지 시 추가 세금"
FROM
(SELECT C.CUSTOMER_ID, A.ACCUMULATED_PROFIT,
CASE
    WHEN A.ACCOUNT_TYPE_ID = 1 THEN GREATEST(0, FLOOR((A.ACCUMULATED_PROFIT - 2000000) * 0.099)) -- ISA 일반형
    WHEN A.ACCOUNT_TYPE_ID = 2 THEN GREATEST(0, FLOOR((A.ACCUMULATED_PROFIT - 4000000) * 0.099)) -- ISA 서민형
    ELSE GREATEST(0, FLOOR(A.ACCUMULATED_PROFIT * 0.154)) -- 일반계좌   
END AS TAXES,
GREATEST(0, FLOOR(A.ACCUMULATED_PROFIT * 0.154)) AS NO_ISA
FROM CUSTOMERS C, ACCOUNTS A
WHERE C.CUSTOMER_ID = A.CUSTOMER_ID) S;
```

## 5. 프로젝트 결과

SQL Query 문을 통해 ISA 계좌의 세액공제 효과 비교 결과는 다음과 같습니다.
```sql
SELECT 
    CUSTOMER_ID AS "고객ID",
    ACCUMULATED_PROFIT AS "실현이익",
    TAXES AS "ISA 적용 세금",
    NO_ISA AS "일반계좌 세금",
    NO_ISA - TAXES AS "중도해지 시 추가 세금"
    
FROM (
  	SELECT 
  		C.CUSTOMER_ID,
  		A.ACCUMULATED_PROFIT,
        CASE
            WHEN A.ACCOUNT_TYPE_ID = 1 THEN GREATEST(0, FLOOR((A.ACCUMULATED_PROFIT - 2000000) * 0.099)) -- ISA 일반형
            WHEN A.ACCOUNT_TYPE_ID = 2 THEN GREATEST(0, FLOOR((A.ACCUMULATED_PROFIT - 4000000) * 0.099)) -- ISA 서민형
            ELSE GREATEST(0, FLOOR(A.ACCUMULATED_PROFIT * 0.154)) -- 일반계좌
        END AS TAXES,
		GREATEST(0, FLOOR(A.ACCUMULATED_PROFIT * 0.154)) AS NO_ISA
	FROM CUSTOMERS C, ACCOUNTS A
	WHERE C.CUSTOMER_ID = A.CUSTOMER_ID
) S;
```
![TAX](images/SQL-TAX.png)

## 6. 프로젝트의 의의 및 개선점

### 6.1. 의의

증권사의 주력 상품인 ISA 계좌 등 금융도메인을 주제로 프로젝트 한 경험은 향후 키움증권에서 다양한 금융, 증권 도메인을 결합하여 실무에서 프로젝트를 하는 데 큰 도움이 될 것입니다.

### 6.2. 개선점 및 향후 방향

실제 고객 거래 데이터가 아니어서 국내 상장 해외 ETF로 ISA 활용 절세 및 세액공제 계산을 한 것이 한계점입니다. 향후에 고객 데이터의 다양한 변수(주식 거래, 국내 상품, 연봉 등)를 활용하여 맞춤형 ISA 세액공제 시스템을 개발하고 싶습니다.