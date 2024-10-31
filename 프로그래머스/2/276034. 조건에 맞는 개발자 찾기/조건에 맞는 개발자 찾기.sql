-- 코드를 작성해주세요
select id,email,first_name,last_name
from developers 
where id in
(
    select d.id 
    from developers d
    join skillcodes s
    where d.skill_code & s.code = s.code
    and (s.name = 'C#' or s.name='Python')
)
order by id asc
;