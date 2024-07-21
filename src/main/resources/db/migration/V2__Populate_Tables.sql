-- Inserção na tabela departamentos
INSERT INTO departamentos (id, nome) VALUES
(1, 'Cardiologia'),
(2, 'Neurologia'),
(3, 'Ortopedia');

-- Inserção na tabela funcionarios
-- Primeiro inserimos os funcionários gerais para depois associá-los como medicos e enfermeiros
INSERT INTO funcionarios (id, nome, data_nascimento, data_contratacao, salario, telefone, endereco, email, departamento_id) VALUES
(1, 'Dr. João Silva', '1980-01-15', '2010-03-01', 12000.00, '123456789', 'Rua A, 123', 'joao@hospital.com', 1),
(2, 'Dra. Ana Costa', '1985-05-23', '2015-07-19', 11000.00, '987654321', 'Rua B, 456', 'ana@hospital.com', 2),
(3, 'Enf. Pedro Martins', '1990-11-10', '2017-02-20', 6000.00, '123123123', 'Rua C, 789', 'pedro@hospital.com', 1),
(4, 'Enf. Maria Oliveira', '1992-12-30', '2018-05-15', 6200.00, '321321321', 'Rua D, 101', 'maria@hospital.com', 2);

-- Inserção na tabela medicos (herdando de funcionarios)
INSERT INTO medicos (id, crm) VALUES
(1, 'CRM123'),
(2, 'CRM456');

-- Inserção na tabela enfermeiros (herdando de funcionarios)
INSERT INTO enfermeiros (id, coren) VALUES
(3, 'COREN123'),
(4, 'COREN456');

-- Inserção na tabela pacientes
INSERT INTO pacientes (id, nome, data_nascimento, telefone, endereco, email, observacoes_paciente) VALUES
(1, 'Carlos Santos', '1970-09-25', '555555555', 'Rua E, 202', 'carlos@hospital.com', 'Observações sobre Carlos'),
(2, 'Juliana Lima', '1988-03-12', '666666666', 'Rua F, 303', 'juliana@hospital.com', 'Observações sobre Juliana');

-- Inserção na tabela cirurgias
INSERT INTO cirurgias (id, medico_id, paciente_id, data_marcada, motivo_da_cirurgia, status_procedimento, status_pagamento) VALUES
(1, 1, 1, '2024-08-15', 'Cirurgia de coração', 1, 1),
(2, 2, 2, '2024-09-10', 'Cirurgia de coluna', 2, 2);

-- Inserção na tabela consultas
INSERT INTO consultas (id, medico_id, paciente_id, data_marcada, motivo_da_consulta, status_procedimento, status_pagamento) VALUES
(1, 1, 1, '2024-07-30', 'Consulta de rotina', 1, 1),
(2, 2, 2, '2024-08-25', 'Consulta especializada', 2, 2);

-- Inserção na tabela cirurgias_enfermeiros (Associação Many-to-Many)
INSERT INTO cirurgias_enfermeiros (cirurgias_id, enfermeiros_id) VALUES
(1, 3),  -- Cirurgia 1 com Enfermeiro 1
(1, 4),  -- Cirurgia 1 com Enfermeiro 2
(2, 3);  -- Cirurgia 2 com Enfermeiro 1
