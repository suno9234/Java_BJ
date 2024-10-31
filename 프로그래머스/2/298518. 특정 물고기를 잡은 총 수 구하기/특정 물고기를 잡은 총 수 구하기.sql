-- 코드를 작성해주세요
select count(*) fish_count
from fish_info i
join fish_name_info n on i.fish_type = n.fish_type 
where fish_name = 'BASS' or fish_name='SNAPPER';