--[SQL] 250115 실습 5번

--고객 테이블에서 이름을 오름차순으로, 이메일을 내림차순으로 정렬하여 출력하시오.
SELECT *
FROM CUSTOMERS
ORDER BY NAME, EMAIL DESC;

--계좌 테이블에서 잔액을 내림차순으로 정렬하되 동일 잔액일 경우 계좌ID를 오름차순으로 정렬하여 출력하시오.
SELECT *
FROM ACCOUNTS
ORDER BY BALANCE DESC, ACCOUNT_ID;

--거래 테이블에서 대출 상태를 기준으로 오름차순, 대출 금액을 기준으로 내림차순으로 정렬하여 출력하시오.
SELECT *
FROM TRANSACTIONS
ORDER BY TRANSACTION_TYPE, AMOUNT DESC;

--대출 테이블에서 대출 상태를 기준으로 오름차순, 대출 금액을 기준으로 내림차순으로 정렬하여 출력하시오.
SELECT *
FROM LOANS
ORDER BY STATUS, AMOUNT DESC;
