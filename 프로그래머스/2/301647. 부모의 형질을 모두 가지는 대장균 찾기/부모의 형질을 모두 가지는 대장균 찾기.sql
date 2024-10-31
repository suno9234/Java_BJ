select ed.id, ed.genotype, edc.genotype parent_genotype
from ecoli_data ed
join (
    select id,genotype 
    from ecoli_data
) as edc
on ed.parent_id = edc.id
where ed.genotype & edc.genotype = edc.genotype
order by id asc
;