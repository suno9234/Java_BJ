-- 코드를 입력하세요
# SELECT * from car_rental_company_rental_history
# group by car_id
# order by car_id;
SELECT CAR_ID, 
IF(CAR_ID IN(SELECT CAR_ID
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
WHERE START_DATE <= '2022-10-16'
AND END_DATE >='2022-10-16') ,'대여중' , '대여 가능') AVAILABILITY

FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
GROUP BY CAR_ID
ORDER BY CAR_ID DESC;

