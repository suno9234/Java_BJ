-- 코드를 작성해주세요
select YEAR(ed.DIFFERENTIATION_DATE) as YEAR,
ym.mc-size_of_colony as YEAR_DEV, ed.id as ID
from ecoli_data as ed
join(
select date_format(DIFFERENTIATION_DATE,"%Y") as year, max(size_of_colony) as mc
from ecoli_data
group by date_format(DIFFERENTIATION_DATE,"%Y")
) as ym
on date_format(ed.DIFFERENTIATION_DATE,"%Y") = ym.year
order by year asc, year_dev asc
 ;