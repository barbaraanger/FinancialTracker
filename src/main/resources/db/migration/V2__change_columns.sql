ALTER TABLE expense
    ADD title VARCHAR(255);

ALTER TABLE expense
    DROP COLUMN name;

ALTER TABLE expense
    ALTER COLUMN account_id SET NOT NULL;

ALTER TABLE expense
    ALTER COLUMN amount TYPE DECIMAL USING (amount::DECIMAL);