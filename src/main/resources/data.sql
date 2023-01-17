INSERT INTO rhcontrol.cargo
(codigo_cargo, hora_salario, nome)
VALUES
(DEFAULT, 10, 'Dev. Junior I'),
(DEFAULT, 20, 'Dev. Junior II'),
(DEFAULT, 30, 'Dev. Junior III'),
(DEFAULT, 40, 'Dev. Pleno I'),
(DEFAULT, 40, 'Dev. Pleno II'),
(DEFAULT, 40, 'Dev. Pleno III'),
(DEFAULT, 40, 'Dev. Senior I'),
(DEFAULT, 40, 'Dev. Senior II'),
(DEFAULT, 40, 'Dev. Senior III');

INSERT INTO rhcontrol.funcionario
(codigo_funcionario, cpf, data_nascimento, bairro, cep, cidade, estado, numero, rua, nome, nome_social, rg, cargo_codigo_cargo, email)
VALUES
(DEFAULT, '639.235.154-37', '1990-04-20', 'Planalto', '53550-025', 'Abreu e Lima', 'PE', '129', 'Sanharol', 'Dyego Henrique', 'Dyego', '33.682.566-3', (SELECT codigo_cargo FROM rhcontrol.cargo  ORDER BY RAND() LIMIT 1), 'dyegohbb@gmail.com'),
(DEFAULT, '152.013.674-55', '1987-10-12', 'Caetes', '53550-025', 'Abreu e Lima', 'PE', '129', 'Sanharol', 'Dyego Henrique', 'Dyego', '15.289.105-5', (SELECT codigo_cargo FROM rhcontrol.cargo ORDER BY RAND() LIMIT 1), 'dyegohbb@gmail.com'),
(DEFAULT, '689.535.054-37', '1997-02-18', 'Iputinga', '50670-520', 'Recife', 'PE', '253', 'Rua Pedra Fina', 'Tereza Ayla Isabelly Almeida', 'Tereza', '26.648.002-0', (SELECT codigo_cargo FROM rhcontrol.cargo ORDER BY RAND() LIMIT 1), 'dyegohbb@gmail.com'),
(DEFAULT, '157.011.930-98', '1998-09-25', 'Jardim Marco Zero', '68903-384', 'Macapá', 'AP', '473', 'Avenida Júpiter', 'Anderson Pedro Henrique da Conceição', 'Anderson', '30.068.341-8', (SELECT codigo_cargo FROM rhcontrol.cargo ORDER BY RAND() LIMIT 1), 'dyegohbb@gmail.com'),
(DEFAULT, '425.817.660-50', '1993-02-07', 'Centro', '49535-970', 'São Miguel do Aleixo', 'SE', '574', 'Praça Oliveira Campos, s/n', 'Paulo Nicolas Ruan Vieira', 'Paulo', '44.783.839-8', (SELECT codigo_cargo FROM rhcontrol.cargo ORDER BY RAND() LIMIT 1), 'dyegohbb@gmail.com'),
(DEFAULT, '604.481.627-33', '2000-04-24', 'Lagoinha', '76829-694', 'Porto Velho', 'RO', '42', 'RUA', 'Carolina Ayla Josefa Gomes', 'Carolina', '39.582.272-5', (SELECT codigo_cargo FROM rhcontrol.cargo ORDER BY RAND() LIMIT 1), 'dyegohbb@gmail.com'),
(DEFAULT, '346.203.118-04', '1986-05-05', 'Vila Esmeralda', '65911-860', 'Imperatriz', 'MA', '971', 'Rua Três', 'Amanda Clara Mariah Rodrigues', 'Amanda', '19.007.762-1', (SELECT codigo_cargo FROM rhcontrol.cargo ORDER BY RAND() LIMIT 1), 'dyegohbb@gmail.com'),
(DEFAULT, '194.658.788-55', '1996-02-18', 'Henrique Leite', '56332-063', 'Petrolina', 'PE', '1006', 'Rua das Violetas', 'Luna Fernanda Vitória Caldeira', 'Luna', '34.423.162-8', (SELECT codigo_cargo FROM rhcontrol.cargo ORDER BY RAND() LIMIT 1), 'dyegohbb@gmail.com'),
(DEFAULT, '876.345.613-39', '1982-01-24', 'Conjunto Ceará', '60530-230', 'Fortaleza', 'CE', '25', 'Rua Cinquenta e Sete', 'Edson Isaac da Cruz', 'Edson', '48.266.758-8', (SELECT codigo_cargo FROM rhcontrol.cargo ORDER BY RAND() LIMIT 1), 'dyegohbb@gmail.com'),
(DEFAULT, '056.575.449-19', '1990-11-07', 'Universidade', '68903-513', 'Macapá', 'AP', '68', 'Servidão João Manoel Vieira', 'Calebe Thomas Monteiro', 'Calebe', '35.349.774-5', (SELECT codigo_cargo FROM rhcontrol.cargo ORDER BY RAND() LIMIT 1), 'dyegohbb@gmail.com'),
(DEFAULT, '322.842.990-32', '1984-01-24', 'Itaoca', '49070-570', 'Aracaju', 'RR', '54', 'Rua Rodolfo Longhi', 'Bryan Sebastião Melo', 'Bryan', '36.772.184-3', (SELECT codigo_cargo FROM rhcontrol.cargo ORDER BY RAND() LIMIT 1), 'dyegohbb@gmail.com'),
(DEFAULT, '899.912.506-89', '1991-12-25', 'Industrial', '38411-377', 'Betim', 'TO', '66', 'Vila Procópio dos Santos', 'Francisco Carlos Eduardo Melo', 'Francisco', '48.104.865-0', (SELECT codigo_cargo FROM rhcontrol.cargo ORDER BY RAND() LIMIT 1), 'dyegohbb@gmail.com'),
(DEFAULT, '211.734.172-14', '1982-07-14', 'Curado', '50950-021', 'Recife', 'PE', '884', 'Vila Brenand', 'Rosângela Caroline Figueiredo', 'Rosângela', '34.996.407-5', (SELECT codigo_cargo FROM rhcontrol.cargo ORDER BY RAND() LIMIT 1), 'dyegohbb@gmail.com'),
(DEFAULT, '578.523.666-43', '1982-06-16', 'Maria Pinheiro', '68908-455', 'Maceió', 'SC', '15', 'Rua Inspetor Marcelino', 'Antônia Catarina Regina Teixeira', 'Antônia', '23.718.746-2', (SELECT codigo_cargo FROM rhcontrol.cargo ORDER BY RAND() LIMIT 1), 'dyegohbb@gmail.com'),
(DEFAULT, '254.869.679-00', '1985-01-13', 'Cidade Nova', '49005-302', 'Araguaína', 'CE', '87', 'Rua Nelson de Souza Rebello', 'Pedro Henrique Levi Arthur de Paula', 'Pedro', '17.129.481-6', (SELECT codigo_cargo FROM rhcontrol.cargo ORDER BY RAND() LIMIT 1), 'dyegohbb@gmail.com');


INSERT INTO rhcontrol.ponto
(data, hora_entrada_almoco, hora_entrada_inicio, hora_saida_almoco, hora_saida_fim, status, codigo_funcionario)
VALUES
('2023-01-01','2023-01-20 13:00:00', '2023-01-20 09:00:00', '2023-01-20 12:00:00', '2023-01-20 17:00:00', 'OK', 1),
('2023-01-01','2023-01-20 13:00:00', '2023-01-20 09:00:00', '2023-01-20 12:00:00', '2023-01-20 17:00:00', 'OK', 2),
('2023-01-01','2023-01-20 13:00:00', '2023-01-20 09:00:00', '2023-01-20 12:00:00', '2023-01-20 17:00:00', 'OK', 3),
('2023-01-01','2023-01-21 13:00:00', '2023-01-21 09:00:00', '2023-01-21 12:00:00', '2023-01-21 17:00:00', 'OK', 1),
('2023-01-01','2023-01-21 13:00:00', '2023-01-21 09:00:00', '2023-01-21 12:00:00', '2023-01-21 17:00:00', 'OK', 2),
('2023-01-01','2023-01-21 13:00:00', '2023-01-21 09:00:00', '2023-01-21 12:00:00', '2023-01-21 17:00:00', 'OK', 3);