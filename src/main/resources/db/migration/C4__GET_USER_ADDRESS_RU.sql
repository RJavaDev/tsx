CREATE OR REPLACE FUNCTION get_region_address_RU(regionId INTEGER)
    RETURNS TEXT
    LANGUAGE SQL AS
$$
SELECT STRING_AGG(name_ru, ', ')
FROM (
         WITH RECURSIVE region_name AS (
             SELECT bts_child.name_ru AS name_ru,
                    bts_child.id,
                    bts_child.parent_id,
                    0 AS level
             FROM tsx_region bts_child
             WHERE bts_child.id = regionId
             UNION ALL
             SELECT btsr_parent.name_ru AS name_ru,
                    btsr_parent.id,
                    btsr_parent.parent_id,
                    level + 1
             FROM tsx_region btsr_parent
                      INNER JOIN region_name rn ON rn.parent_id = btsr_parent.id
         )
         SELECT region_name.name_ru
         FROM region_name
         ORDER BY region_name.level DESC
     ) AS user_address_ru;
$$;