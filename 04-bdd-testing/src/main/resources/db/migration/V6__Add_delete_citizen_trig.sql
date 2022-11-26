CREATE OR REPLACE FUNCTION delete_passports_when_trig() RETURNS TRIGGER AS
$delete_citizen_trig$
BEGIN
    DELETE
    FROM passport
    WHERE citizen_id = OLD.id;

    DELETE
    FROM foreign_passport
    WHERE citizen_id = OLD.id;

    RETURN OLD;
END;
$delete_citizen_trig$ LANGUAGE plpgsql;

CREATE TRIGGER delete_citizen_trig
    BEFORE DELETE
    ON citizen
    FOR EACH ROW
EXECUTE PROCEDURE delete_passports_when_trig();



