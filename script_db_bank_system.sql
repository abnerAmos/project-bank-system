DROP DATABASE DB_BANK_SYSTEM;

CREATE DATABASE DB_BANK_SYSTEM;

USE DB_BANK_SYSTEM;

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

CREATE TABLE transactions (
id						INT									NOT NULL	AUTO_INCREMENT	,
value					DECIMAL						(13, 2)	NOT NULL					,
operation				ENUM ('ENTRADA', 'SAIDA', 'ACESSO')	NOT NULL					,	-- saida, entrada ou acesso
operation_dt			DATETIME														,
account_id				INT							(10)	NOT NULL					,	-- pagante
transfer_account_id		INT							(10)	NOT NULL					,	-- recebedor
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

SELECT * FROM address;

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

SELECT * FROM client;

-- Inserts Tabela bank_account

INSERT INTO bank_account (account_number, client_id, balance, registration_dt)
VALUES (101, 2, 25.500, '2019-02-01 13:15:12')

INSERT INTO bank_account (account_number, client_id, registration_dt)
VALUES (202, 3, '2022-05-25 10:51:21')

INSERT INTO bank_account (account_number, client_id, balance, registration_dt, account_tp)
VALUES (303, 4, 165.100, '2021-07-03 11:24:45', 'INVESTIMENTO' )

INSERT INTO bank_account (account_number, client_id, balance, registration_dt, account_tp)
VALUES (404, 5, 101, '2020-08-10 12:46:17', 'POUPANCA' )

INSERT INTO bank_account (account_number, client_id, balance, registration_dt)
VALUES (505, 6, 3.500, '2015-05-15 14:34:26')

SELECT * FROM bank_account;

-- Inserts Tabela access

INSERT INTO access (client_id, account_id, password_acess)
VALUES (2, 1, '547341')

INSERT INTO access (client_id, account_id, password_acess)
VALUES (3, 2, '173622')

INSERT INTO access (client_id, account_id, password_acess)
VALUES (4, 3, '472808')

INSERT INTO access (client_id, account_id, password_acess)
VALUES (5, 4, '157123')

INSERT INTO access (client_id, account_id, password_acess)
VALUES (6, 5, '247021')

SELECT * FROM access;

-- Inserts Tabela transactions

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (1.99, 'ENTRADA', '2022-08-15 12:01:34', 1, 'DEPOSITO')

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (135.40, 'SAIDA', '2022-08-15 12:01:34', 1, 'SAQUE')

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp, transfer_account_id)
VALUES (945.49, 'SAIDA', '2022-08-15 12:01:34', 1, 'TRANSFERENCIA', 2)

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (0, 'ACESSO', '2022-08-15 12:01:34', 1, 'TENTATIVA_ACESSO')

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (151.15, 'ENTRADA', '2022-08-15 12:01:34', 2, 'DEPOSITO')

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (335.57, 'SAIDA', '2022-08-15 12:01:34', 2, 'SAQUE')

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp, transfer_account_id)
VALUES (99.99, 'SAIDA', '2022-08-15 12:01:34', 2, 'TRANSFERENCIA', 3)

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (0, 'ACESSO', '2022-08-15 12:01:34', 2, 'TENTATIVA_ACESSO')

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (175.35, 'ENTRADA', '2022-08-15 12:01:34', 3, 'DEPOSITO')

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (35.20, 'SAIDA', '2022-08-15 12:01:34', 3, 'SAQUE')

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp, transfer_account_id)
VALUES (5.66, 'SAIDA', '2022-08-15 12:01:34', 3, 'TRANSFERENCIA', 4)

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (0, 'ACESSO', '2022-08-15 12:01:34', 3, 'TENTATIVA_ACESSO')

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (10.00, 'ENTRADA', '2022-08-15 12:01:34', 4, 'DEPOSITO')

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (55.90, 'SAIDA', '2022-08-15 12:01:34', 4, 'SAQUE')

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp, transfer_account_id)
VALUES (235.99, 'SAIDA', '2022-08-15 12:01:34', 4, 'TRANSFERENCIA', 5)

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (0, 'ACESSO', '2022-08-15 12:01:34', 4, 'TENTATIVA_ACESSO')

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (534.75, 'ENTRADA', '2022-08-15 12:01:34', 5, 'DEPOSITO')

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (1000.50, 'SAIDA', '2022-08-15 12:01:34', 5, 'SAQUE')

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp, transfer_account_id)
VALUES (150.25, 'SAIDA', '2022-08-15 12:01:34', 5, 'TRANSFERENCIA', 1)

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (0, 'ACESSO', '2022-08-15 12:01:34', 5, 'TENTATIVA_ACESSO')

SELECT * FROM bank_account ba ;

-- ---------------------------------------------

ALTER TABLE transactions CHANGE COLUMN transfer_account_id transfer_account_id INT (10);
ALTER TABLE transactions CHANGE COLUMN operation operation ENUM ('ENTRADA', 'SAIDA', 'ACESSO') NOT NULL;
ALTER TABLE transactions CHANGE COLUMN transaction_tp transaction_tp VARCHAR (30) NOT NULL;

SELECT * FROM client, address, access, bank_account, transactions;

-- ----------------------------------------------

-- OPERACOES QUERYS:

-- - PESQUISA EM JUNCAO DE TABELAS:

SELECT house_number AS numero_casa, state AS estado, agency, name FROM address ad INNER JOIN client cl ON (ad.id = cl.address_id)
INNER JOIN bank_account ba ON (ba.client_id = cl.id)
WHERE ad.district = :estado AND cl.name = :nome

-- - <> DIFERENTE:

SELECT * FROM bank_account ba INNER JOIN transactions tr ON (tr.account_id = ba.id)
WHERE	ba.account_number = :numero_conta
AND		ba.agency = :agencia
AND		tr.operation <> 'ACESSO';

-- - BETWEEN (pesquisa por range de data):

SELECT * FROM bank_account ba INNER JOIN transactions tr ON (tr.account_id = ba.id)
WHERE	ba.account_number = :numero_conta
AND		ba.agency = :agencia
AND		tr.operation <> 'ACESSO'
AND		tr.operation_dt  BETWEEN '2020-01-01' AND '2022-12-30';

-- - IN (pesquisa por categoria informada):
-- - ORDER BY (ordenacao por ascendencia e descendencia ASC / DESC)

SELECT * FROM bank_account ba INNER JOIN transactions tr ON (tr.account_id = ba.id)
WHERE	tr.transaction_tp IN ('SAQUE', 'DEPOSITO')
ORDER BY tr.transaction_tp ASC;

-- - NOT IN (pesquisa tudo, menos a categoria informada):

SELECT * FROM bank_account ba INNER JOIN transactions tr ON (tr.account_id = ba.id)
WHERE	tr.transaction_tp NOT IN ('SAQUE', 'DEPOSITO');

-- - COUNT (pesquisa tudo, menos a categoria informada):

SELECT COUNT(*) FROM transactions;

-- - LIMIT (limita a quantidade de resultados obtidos):
-- - OFFSET (pula quantidade de linhas especificadas):

SELECT * FROM bank_account ba INNER JOIN transactions tr ON (tr.account_id = ba.id)
LIMIT 4 OFFSET 3;

-- - LIKE (pesquisa criterios dentro do texto informado):
-- % na frente pesquisa textos com criterios especificados na frente
-- % atrás pesquisa textos com criterios especificados atras
-- % frente e atrás, pesquisa textos com criterios especificados entre a porcentagem

SELECT * FROM client cl WHERE cl.email LIKE '%.com';
SELECT * FROM client cl WHERE cl.email LIKE 'r%';
SELECT * FROM client cl WHERE cl.email LIKE '%na%';

-- - MAIOR > e MENOR < (pesquisa valores maior ou menor que o especificado)
-- >= ou <= tambem é valido

SELECT * FROM bank_account ba WHERE ba.balance <100;
SELECT * FROM bank_account ba WHERE ba.balance >100;
