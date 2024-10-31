-- 코드를 작성해주세요
select count(*) 'COUNT'
from ecoli_data
where id in(
    select id
    from ecoli_data
    where genotype & 2 != 2 and ( genotype & 1 = 1 or genotype & 4 = 4) 
)

;