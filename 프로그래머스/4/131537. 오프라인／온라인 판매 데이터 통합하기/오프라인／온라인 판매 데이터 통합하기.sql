-- 코드를 입력하세요
select date_format(SALES_DATE,'%Y-%m-%d') as SALES_DATE, product_id, user_id, sales_amount from(
    select SALES_DATE ,USER_ID, PRODUCT_ID, SALES_AMOUNT from ONLINE_SALE
    union
    select SALES_DATE,NULL AS USER_ID, PRODUCT_ID, SALES_AMOUNT from OFFLINE_SALE
) as unioned
where date_format(SALES_DATE,'%m') = '03'
order by SALES_DATE ASC,
product_id asc,
user_id asc
;