-- :name find-by-id-query :? :*
-- :doc select all bills
SELECT *
FROM bills;

-- :name find-by-id-query :? :*
-- :doc select bill by id
SELECT *
FROM bills
WHERE id = :id;