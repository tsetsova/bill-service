-- :name add-query :! :n
-- :doc insert a bill

INSERT INTO bills
(id,
 amount,
 tip_percentage,
 total)
VALUES
  ((:id)::uuid,
   :amount,
   :tip_percentage,
   :total);