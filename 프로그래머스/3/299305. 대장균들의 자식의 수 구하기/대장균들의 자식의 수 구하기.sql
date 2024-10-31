select ed.id, IFNULL(cnt.counts,0) child_count
from ecoli_data as ed
left join(
    select parent_id id, count(*) counts from ecoli_data
    group by parent_id
)as cnt
on ed.id = cnt.id
order by id
;
