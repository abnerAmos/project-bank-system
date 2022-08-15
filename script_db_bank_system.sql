DROP DATABASE IF EXISTS DB_BANK_SYSTEM;

CREATE DATABASE DB_BANK_SYSTEM;

USE DB_BANK_SYSTEM;

--

SELECT * FROM client;

CREATE TABLE client (
id				INT						NOT NULL	AUTO_INCREMENT	,
name			VARCHAR	(150)			NOT NULL					,
birthdate		DATE					NOT NULL					,
phone			BIGINT	(11)			NOT NULL					,
email			VARCHAR (50)			NOT NULL	UNIQUE			,
person_tp		ENUM ('PF', 'PJ')		NOT NULL	DEFAULT 'PF'	,
document_tp		ENUM ('CPF', 'CNPJ')	NOT NULL	DEFAULT	'CPF'	,
document		VARCHAR	(20)			NOT NULL					,
address_id		INT						NOT NULL					,
PRIMARY KEY	(id)													,
FOREIGN KEY (address_id) REFERENCES address (id)
);

--

SELECT * FROM bank_account;

CREATE TABLE bank_account(
id				INT				NOT NULL	AUTO_INCREMENT		,
account_number	INT 	(10)	NOT NULL						,
client_id		INT				NOT NULL						,
agency			INT 	(4)		NOT NULL	DEFAULT 1			,
balance			DECIMAL (13, 2)				DEFAULT 0			,
registration_dt	DATETIME		NOT NULL						,
account_tp		VARCHAR (30)	NOT NULL	DEFAULT 'CORRENTE'	,
deactivation_dt	DATETIME										,
PRIMARY KEY	(id)												,
FOREIGN KEY (client_id) REFERENCES client (id)
);

--

DROP TABLE address
SELECT * FROM address;

CREATE TABLE address (
id				INT				NOT NULL	AUTO_INCREMENT	,
city			VARCHAR (30)	NOT NULL					,
state			VARCHAR	(2)		NOT NULL					,
address 		VARCHAR (30)	NOT NULL					,
district		VARCHAR	(30)	NOT NULL					,
house_number	VARCHAR	(5)		NOT NULL					,
cep				INT		(8)		NOT NULL					,
address_2		VARCHAR	(30)								,
PRIMARY KEY	(id)											
);

INSERT INTO address (city, state, address, district, house_number, cep, address_2)
VALUES ('Sao Paulo', 'SP', 'Avenida Imirim', 'Imirim', 2383, 02465300, 'Loja')

--

CREATE TABLE transactions (
id						INT									NOT NULL	AUTO_INCREMENT	,
value					DECIMAL						(13, 2)	NOT NULL					,
operation				ENUM ('ENTRADA', 'SAIDA')			NOT NULL					,	-- saida ou entrada
operation_dt			DATETIME														,
account_id				INT							(10)	NOT NULL					,	-- pagante
transaction_account_id	INT							(10)	NOT NULL					,	-- recebedor
transaction_tp			VARCHAR 					(10)	NOT NULL					,	-- saque, deposito, transferencia
PRIMARY KEY (id)																		,
FOREIGN KEY (account_id) REFERENCES bank_account (id)
);

--

DROP TABLE access 
SELECT * FROM access

CREATE TABLE access (
client_id			INT			NOT NULL				,
account_id			INT			NOT NULL				,
password_acess		INT (6)		NOT NULL				,
client_block		DATETIME							,
PRIMARY KEY (client_id, account_id)						,
FOREIGN KEY (client_id) REFERENCES client (id)			,
FOREIGN KEY (account_id) REFERENCES bank_account (id)	
);

UPDATE access SET password_acess = '654321'
WHERE account_id = 3;

ALTER TABLE access CHANGE COLUMN account_id account BIGINT NOT NULL;

-- QUERIES:
-- Saque, Deposito e Transferência

UPDATE bank_account SET balance = :balance
WHERE account_number = :acc AND agency = :ag;

-- Consultar Saldo
SELECT balance FROM bank_account
WHERE account_number = :acc AND agency = :ag;

-- Cadastrar Conta
INSERT INTO bank_account (account_number, client_id, balance , registration_dt, account_tp)
VALUES (2, 3, 200000, '2022-08-03', 'POUPANCA' )

-- Cadastrar Cliente
INSERT INTO client (name, dt_birth, phone, email, document, adress)
VALUES ('Abner Amós', '1993-08-10', 11967851774, 'abneramos@email.com', 36998765432, 3)

-- Inativação Conta
UPDATE bank_account SET deactivation_dt = :ddt
WHERE account_number = :acc AND agency = :ag;

-- Cadastrar Operação
INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_account_id, transaction_tp)
VALUES (100, 'ENTRADA', '2022-08-10', 'PAGANTE_X', 'RECEBEDOR_Y', 'TRANSFERENCIA')

-- Buscar Cliente
SELECT name FROM client;

-------------------------------------------------

-- Inserts Tabela client

INSERT INTO client (name, birthdate, phone, email, person_tp, document_tp, document, address_id)
VALUES ('Erik Gonzaga MEI', '2000-03-05', 1122563138, 'erikgonzagamei@email.com', 'PJ', 'CNPJ', 35821066000127, 1)

INSERT INTO client (name, birthdate, phone, email, document, address_id)
VALUES ('Abner Amós', '1993-08-10', 11973851774, 'abner.fsouza@hotmail.com', 42104482810, 2)

INSERT INTO client (name, birthdate, phone, email, document, address_id)
VALUES ('Ronan Roldao', '1987-05-05', 11912345678, 'ronan.roldao@gmail.com', 19876543210, 3)

INSERT INTO client (name, birthdate, phone, email, document, address_id)
VALUES ('Nadine Roldao', '2001-12-25', 11987651234, 'nadine.roldao@yahoo.com.br', 13579135791, 4)

INSERT INTO client (name, birthdate, phone, email, document, address_id)
VALUES ('Ricardo Silva', '2001-07-02', 11981742639, 'ricardoslv@ig.br', 97531975323, 5)

-- Inserts Tabela bank_account
INSERT INTO bank_account (account_number, client_id, balance, registration_dt)
VALUES (0101, 4, 20000, '2019-02-01')

INSERT INTO bank_account (account_number, client_id, registration_dt)
VALUES (0202, 6, '2022-08-03')

INSERT INTO bank_account (account_number, client_id, balance, registration_dt, account_tp)
VALUES (0202, 7, 100000, '2022-08-03', 'INVESTIMENTO' )

INSERT INTO bank_account (account_number, client_id, balance, registration_dt, account_tp)
VALUES (0202, 9, 100, '2022-08-10', 'POUPANCA' )

INSERT INTO bank_account (account_number, client_id, balance, registration_dt)
VALUES (0202, 11, 3500, '2015-05-15')

UPDATE bank_account SET registration_dt = '2015-05-15 13:15:12' WHERE client_id = 9;
UPDATE bank_account SET balance = 3.500 WHERE client_id = 11;
UPDATE bank_account SET account_number = 0303 WHERE client_id = 7;
UPDATE bank_account SET account_number = 0404 WHERE client_id = 9;
UPDATE bank_account SET account_number = 0505 WHERE client_id = 11;

SELECT * FROM bank_account;

-- Inserts Tabela address
INSERT INTO address (city, state, address, district, house_number, cep, address_2)
VALUES ('Sao Paulo', 'SP', 'Avenida Imirim', 'Imirim', 2383, 02465300, 'Loja')

INSERT INTO address (city, state, address, district, house_number, cep, address_2)
VALUES ('Sao Paulo', 'SP', 'Rua Abura', 'Imirim', '641 A', 02542110, 'Casa 2')

INSERT INTO address (city, state, address, district, house_number, cep, address_2)
VALUES ('Sao Paulo', 'SP', 'Rua Mere Marie', 'Tucuruvi', '472', 09929129, 'Apto 8')

INSERT INTO address (city, state, address, district, house_number, cep, address_2)
VALUES ('Sao Paulo', 'SP', 'Rua Algum Lugar', 'Aquele Bairro', '100', 05939149, 'Fundos')

INSERT INTO address (city, state, address, district, house_number, cep, address_2)
VALUES ('Sao Paulo', 'SP', 'Rua Outro Lugar', 'Outro Bairro', '200', 01234567, 'Blc 2 - Apto 27')

-- Inserts Tabela access
INSERT INTO access (client_id, account_id, password_acess)
VALUES (4, 1, '547341')

INSERT INTO access (client_id, account_id, password_acess)
VALUES (6, 2, '173622')

INSERT INTO access (client_id, account_id, password_acess)
VALUES (7, 3, '472808')

INSERT INTO access (client_id, account_id, password_acess)
VALUES (9, 4, '157123')

INSERT INTO access (client_id, account_id, password_acess)
VALUES (11, 5, '247021')

SELECT * FROM bank_account, access;

-- Inserts Tabela transactions
INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (100.00, 'ENTRADA', '2022-08-15 12:01:34', 1, 'DEPOSITO')

ALTER TABLE transactions CHANGE COLUMN transaction_account_id transfer_account_id INT (10) NOT NULL;
SELECT * FROM transactions;

SELECT house_number FROM address ad  INNER JOIN client cl ON (ad.id = cl.address_id)
WHERE ad.district = :estado AND cl.name = :nome