CREATE OR REPLACE FUNCTION get_region_address(regionId BIGINT)
    RETURNS TEXT
    LANGUAGE SQL AS
$$
SELECT CONCAT(
               STRING_AGG(name_en, ', ') , '|',
               STRING_AGG(name_ru, ', ') , '|',
               STRING_AGG(name_uz, ', ')
       ) AS region_naem
FROM (
         WITH RECURSIVE region_name AS (
             SELECT tsx_child.name_en AS name_en,
                    tsx_child.name_ru AS name_ru,
                    tsx_child.name_uz AS name_uz,
                    tsx_child.id,
                    tsx_child.parent_id,
                    0 AS level
             FROM tsx_region tsx_child
             WHERE tsx_child.id = regionId -- Use the function parameter here
             UNION ALL
             SELECT tsxr_parent.name_en AS name_en,
                    tsxr_parent.name_ru AS name_ru,
                    tsxr_parent.name_uz AS name_uz,
                    tsxr_parent.id,
                    tsxr_parent.parent_id,
                    level + 1
             FROM tsx_region tsxr_parent
                      INNER JOIN region_name rn ON rn.parent_id = tsxr_parent.id
         )
         SELECT region_name.name_en, region_name.name_ru, region_name.name_uz
         FROM region_name
         ORDER BY region_name.level DESC
     ) AS user_address;

$$;