-- ADDRESS
INSERT INTO address (city, state, address, district, house_number, cep, address_2)
VALUES ('Sao Paulo', 'SP', 'Rua Abura', 'Imirim', '641 A', 02542110, 'Casa 2')

-- CLIENT
INSERT INTO client (name, birthdate, phone, email, document, address_id)
VALUES ('Abner Amós', '1993-08-10', 11973851774, 'abner.fsouza@hotmail.com', 42104482810, 2)

-- ACCOUNT
INSERT INTO bank_account (account_number, client_id, balance, registration_dt, account_tp)
VALUES (303, 4, 165.100, '2021-07-03 11:24:45', 'INVESTIMENTO')

-- ACCESS
INSERT INTO access (client_id, account_id, password_acess)
VALUES (2, 1, '654321')

-- TRANSACTION
INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (1.99, 'ENTRADA', '2022-08-15 12:01:34', 1, 'DEPOSITO')
