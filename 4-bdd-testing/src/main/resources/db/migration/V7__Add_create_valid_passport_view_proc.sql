-- получать паспорт нужно:
-- при достижении 14 лет
-- при достижении 20 лет
-- при достижении 45 лет

-- получить возраст гражданина
-- получить "возраст" получения паспорта (дата получения минус дата рождения)

-- текущая дата минус дата рождения
-- получим сколько лет гражданину

-- если гражданину от 14 до 20
-- и возраст паспорта от 14 до 20,
-- то паспорт дейсвтителен

-- если гражданину от 20 до 45
-- и возраст паспорта от 20 до 45,
-- то паспорт дейсвтителен

-- если гражданину больше 45
-- и паспорту больше 45,
-- то паспорт действителен

CREATE OR REPLACE FUNCTION create_valid_passport_view()
    RETURNS BOOLEAN
AS
$$
begin
    CREATE OR REPLACE VIEW valid_passport_view AS
    SELECT *
    FROM (SELECT p.serial_number,
                 c.surname,
                 c.name,
                 c.patronymic,
                 c.birth_date,
                 c.birth_fed_place,
                 c.birth_place,
                 p.issuing_division_code,
                 i.name                                             as issuing_division_name,
                 p.issue_date,
                 date_part('year', age(CURRENT_DATE, c.birth_date)) as years_old,
                 date_part('year', age(p.issue_date, c.birth_date)) as years_old_when_passported
          FROM passport as p
                   INNER JOIN citizen as c ON p.citizen_id = c.id
                   INNER JOIN issuing_division as i ON p.issuing_division_code = i.code) t
    WHERE ((t.years_old >= 14 and t.years_old < 20) and
           (t.years_old_when_passported >= 14 and t.years_old_when_passported < 20))
       OR ((t.years_old >= 20 and t.years_old < 45) and
           (t.years_old_when_passported >= 20 and t.years_old_when_passported < 45))
       OR (t.years_old >= 45 and t.years_old_when_passported >= 45);

    return true;
end;
$$ LANGUAGE plpgsql;