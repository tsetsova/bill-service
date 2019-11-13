CREATE TABLE bills (
  bill_id UUID    PRIMARY KEY,
  amount          FLOAT NOT NULL,
  tip_percentage  FLOAT NOT NULL,
  total           FLOAT NOT NULL
);