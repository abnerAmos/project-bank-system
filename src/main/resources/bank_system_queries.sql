-- AUTENTICAR:
-- (buscar cliente por senha, conta, agencia na tabela bank_account e access)

SELECT * FROM bank_account ba INNER JOIN access ac ON (ba.id = ac.account_id)
WHERE ba.account_number = :conta AND ba.agency = :agencia
AND ac.password_acess = :senha;

-- ATUALIZAR SALDO:
-- (faz update do saldo na tabela bank_account pelo numero da conta e numero da agencia)

UPDATE bank_account SET balance = :novo_saldo
WHERE account_number = :conta AND agency = :agencia;

-- INATIVAR CONTA:
-- (update na tabela bank_account, set a data atual no campo: deactivation_dt)

UPDATE bank_account SET deactivation_dt = :data_desativacao
WHERE account_number = :conta AND agency = :agencia

SELECT * FROM bank_ccount ba WHERE account_number = :conta

-- ATIVAR CONTA:
-- (update na tabela bank_account, deixar nulo o campo: deactivation_dt)

UPDATE bank_account SET deactivation_dt = :data_desativacao
WHERE account_number = :conta AND agency = :agencia

-- SALVAR HISTÓRICO TRANSAÇÃO:
-- (inserir dados na tabela transactions)

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (150.00, 'ENTRADA', '2022-08-18 11:40:43', 1, 'DEPOSITO')

SELECT * FROM transactions WHERE account_id = :id

-- LISTAR TRANSAÇÕES:
-- (buscar dados na tabela transacoes a partir de: dtInico, dtFim, numeroConta, agencia)

SELECT * FROM bank_account ba INNER JOIN transactions tr ON (tr.account_id = ba.id)
WHERE ba.account_number = :conta AND ba.agency = :agencia
AND tr.operation_dt BETWEEN :dtInicio AND :dtFim
AND tr.transaction_tp <> 'TENTATIVA_ACESSO';
