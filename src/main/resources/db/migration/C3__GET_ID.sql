CREATE FUNCTION function_getid(NAMES CHARACTER VARYING) RETURNS bigint
    LANGUAGE SQL
    AS
$$

SELECT  id FROM tsx_category WHERE name_en = names OR name_ru = names;



$$;
CREATE FUNCTION function_get_add_group_id(NAMES CHARACTER VARYING) RETURNS bigint
    LANGUAGE SQL
AS
$$

SELECT  id FROM tsx_add_group WHERE name_en = names OR name_ru = names;



$$;