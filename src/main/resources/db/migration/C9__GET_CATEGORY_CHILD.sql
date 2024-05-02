CREATE OR REPLACE FUNCTION function_by_category(categoryId BIGINT)
    RETURNS BIGINT
    LANGUAGE SQL AS
$$
SELECT *
FROM (
         WITH RECURSIVE category_parentId AS (
             SELECT
                 tsxr_parent.id,
                 tsxr_parent.parent_id,
                 0 AS level
             FROM tsx_category tsxr_parent
             WHERE tsxr_parent.id = categoryId -- Use the function parameter here
             UNION ALL
             SELECT
                 tsx_child.id,
                 tsx_child.parent_id,
                 level + 1
             FROM tsx_category tsx_child
                      INNER JOIN category_parentId rn ON rn.id = tsx_child.parent_id
         )
         SELECT category_parentId.id
         FROM category_parentId
         ORDER BY category_parentId.level ASC
     ) AS user_address;

$$;