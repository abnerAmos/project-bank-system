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
PRIMARY KEY	(id)
FOREIGN KEY (address_id) REFERENCES address (id)
);

INSERT INTO client (name, birthdate, phone, email, person_tp, document_tp, document, address_id)
VALUES ('Erik Gonzaga MEI', '2000-03-05', 1122563138, 'erikgonzagamei@email.com', 'PJ', 'CNPJ', 35821066000127, 2)

INSERT INTO client (name, dt_birth, phone, email, document, adress)
VALUES ('Abner Amós', '1993-08-10', 11967851774, 'abneramos@email.com', 36998765432, 3)

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
FOREIGN KEY (cliente_id) REFERENCES client (id)
);

INSERT INTO bank_account (account_number, client_id, balance , registration_dt, account_tp)
VALUES (18, 2, 2000000, '2022-08-04')

INSERT INTO bank_account (account_number, client_id, balance , registration_dt, account_tp)
VALUES (2, 3, 200000, '2022-08-03', 'POUPANCA' )

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
PRIMARY KEY	(id)											,
);

INSERT INTO address (city, state, address, district, house_number, cep, address_2)
VALUES ('Sao Paulo', 'SP', 'Avenida Imirim', 'Imirim', 2383, 02465300, 'Loja')

INSERT INTO address (city, state, address, district, house_number, cep, address_2)
VALUES ('Sao Paulo', 'SP', 'Rua Abura', 'Imirim', '641 A', 02542110, 'Casa 2')

UPDATE address SET
cep = 02542110 WHERE district = 'Sitio Mandaqui'

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
client_id			INT			NOT NULL	,
account_id			INT			NOT NULL	,
password_acess		INT (6)		NOT NULL	,
client_block		DATETIME				,
PRIMARY KEY (client_id, account_id)			,
FOREIGN KEY (client_id) REFERENCES client (id)
FOREIGN KEY (account_id) REFERENCES bank_account (id)
);

INSERT INTO access (client_id, account_id, password_acess)
VALUES (2,2, '246800')

INSERT INTO access (id, account_id, password_acess)
VALUES (1, 3, '123456')

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

-- Cadastrar Cliente
INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_account_id, transaction_tp)
VALUES (100, 'ENTRADA', '2022-08-10', 'PAGANTE_X', 'RECEBEDOR_Y', 'TRANSFERENCIA')

-- Buscar Cliente
SELECT client, account, agency FROM bank_account ba, access a;

-------------------------------------------------

INSERT INTO address (city, state, address, house_number, cep, address_2)
VALUES ('Sao Paulo', 'SP', 'Rua Abura', 'Imirim', '641 A', 02542110, 'Casa 2')