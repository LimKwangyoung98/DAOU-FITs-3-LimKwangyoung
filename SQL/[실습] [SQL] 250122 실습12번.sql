--[SQL] 250122 실습 12번

--사원 테이블에서 부서별로 급여의 함계를 계산하고 전체 합계를 함께 출력하시오.
--(단, 부서 데이터가 NULL인 사원은 제외)
SELECT NVL(TO_CHAR(DEPARTMENT_ID), 'TOTAL') DEPARTMENT_ID, SUM(SALARY) TOTAL_SALARY
FROM EMPLOYEES E
WHERE DEPARTMENT_ID IS NOT NULL
GROUP BY ROLLUP(DEPARTMENT_ID)
ORDER BY E.DEPARTMENT_ID;

--같은 날짜에 입사한 사원이 2명 이상인 날짜와 입사한 사원수, 마지막 행은 총 직원 수를 함께 출력하시오.
SELECT NVL(TO_CHAR(HIRE_DATE), 'TOTAL') HIRE_DATE, COUNT(*) CNT
FROM EMPLOYEES
GROUP BY ROLLUP(HIRE_DATE)
HAVING COUNT(*) >= 2
ORDER BY HIRE_DATE;

--각 지점별 대출 금액 합계와 모든 지점의 총 합계를 출력하시오.
--출력 컬럼은 지점명, 대출 금액 합계이며,
--지정 총합계는 'All Branch'로 출력하고 지점명으로 정렬하되 총합계는 맨 아랫줄에 나타내시오.
SELECT NVL((SELECT NAME FROM BRANCHES WHERE BRANCH_ID = L.BRANCH_ID), 'All Branch') NAME,
  SUM(L.AMOUNT) TOTAL_AMOUNT
FROM LOANS L
GROUP BY ROLLUP(L.BRANCH_ID)
ORDER BY L.BRANCH_ID;