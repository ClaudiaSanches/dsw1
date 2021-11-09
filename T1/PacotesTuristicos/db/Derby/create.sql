connect 'jdbc:derby:PacotesTuristicos;create=true;user=root;password=root;';

create table Usuario(id bigint not null generated always as identity, 
					 email varchar(64) not null unique, 
					 senha varchar(64) not null, 
					 nome varchar(256) not null, 
					 papel varchar(16) not null, 
					 cpf varchar(32) unique,
					 telefone varchar(32),
					 sexo char(32), 
					 nasc date,
					 cnpj varchar(32) unique,
					 descricao varchar(256),
					 constraint Usuario_PK primary key (id));

create table Pacote(id bigint not null generated always as identity,
					nome varchar(64) not null,
					cnpj varchar(32) not null,
					cidade varchar(64) not null,
					estado varchar(64),
					pais varchar(64) not null,
					partida date not null,
					duracao integer not null,
					valor float not null,
					constraint Pacote_PK primary key (id), 
					constraint Agencia_FK foreign key (cnpj) references Usuario(cnpj) ON DELETE CASCADE);

create table Compra(id bigint not null generated always as identity,
					data date not null,
					valor float not null,
					usuario_id bigint not null,
					pacote_id bigint not null,
					constraint Compra_PK primary key (id), 
					constraint Usuario_FK foreign key (usuario_id) references Usuario(id) ON DELETE CASCADE,
					constraint Pacote_FK foreign key (pacote_id) references Pacote(id) ON DELETE CASCADE);

insert into Usuario(email, senha, nome, papel) 
	values ('admin@gmail.com', 'admin', 'Admin', 'ADMIN');

insert into Usuario(email, senha, nome, papel, cpf, telefone, sexo, nasc) 
	values ('cliente1@gmail.com', 'cliente1', 'Fulano da Silva', 'CLIENTE', '123456789-00', '(16) 94444-5555', 'Masculino', '1999-06-13');

insert into Usuario(email, senha, nome, papel, cpf, telefone, sexo, nasc)
	values ('cliente2@gmail.com', 'cliente2', 'Ciclana de Souza', 'CLIENTE', '456789123-00', '(17) 98888-3333', 'Feminino', '1987-02-25');

insert into Usuario(email, senha, nome, papel, cpf, telefone, sexo, nasc)
	values ('cliente3@gmail.com', 'cliente3', 'Beltrano Barbosa', 'CLIENTE', '789123456-00', '(34) 97777-9999', 'Masculino', '2002-11-04');

insert into Usuario(email, senha, nome, papel, cnpj, descricao)
	values ('agencia1@gmail.com', 'agencia1', 'ViagemMais', 'AGENCIA', '13.444.222/0001-77', 'Agencia de viagens');

insert into Usuario(email, senha, nome, papel, cnpj, descricao)
	values ('agencia2@gmail.com', 'agencia2', 'Tour Viagens', 'AGENCIA', '52.333.111/0001-99', 'Organizamos sua viagem para voce!!!');

insert into Usuario(email, senha, nome, papel, cnpj, descricao)
	values ('agencia3@gmail.com', 'agencia3', 'Turisticando', 'AGENCIA', '15.111.555/0001-00', 'Quer planejar sua viagem? Vem com a gente!!');

insert into Usuario(email, senha, nome, papel, cnpj, descricao)
	values ('agencia4@gmail.com', 'agencia4', 'Muitas Viagens', 'AGENCIA', '17.333.999/0001-37', 'Agencia de viagens 4');

insert into Usuario(email, senha, nome, papel, cnpj, descricao)
	values ('agencia5@gmail.com', 'agencia5', 'Travel More', 'AGENCIA', '12.555.875/0001-28', 'Agencia de viagens 5');

insert into Pacote(nome, cnpj, cidade, estado, pais, partida, duracao, valor) 
	values ('Pacote 1', '13.444.222/0001-77', 'Sao Paulo', 'Sao Paulo', 'Brasil', '2021-10-12', 5, 350.59);

insert into Pacote(nome, cnpj, cidade, estado, pais, partida, duracao, valor) 
	values ('Pacote 2', '15.111.555/0001-00', 'Balneario Camburiu', 'Santa Catarina', 'Brasil', '2021-12-08', 10, 942.50);

insert into Pacote(nome, cnpj, cidade, pais, partida, duracao, valor) 
	values ('Pacote 3', '13.444.222/0001-77', 'Miami', 'EUA', '2020-03-23', 7, 2538.30);

insert into Pacote(nome, cnpj, cidade, pais, partida, duracao, valor) 
	values ('Pacote 4', '17.333.999/0001-37', 'Berlim', 'Alemanha', '2022-05-05', 20, 4920.10);

insert into Pacote(nome, cnpj, cidade, pais, partida, duracao, valor) 
	values ('Pacote 5', '17.333.999/0001-37', 'Toquio', 'Japao', '2019-07-01', 31, 10328.99);

insert into Compra(data, valor, usuario_id, pacote_id)
	values ('2021-03-15', 942.50, 2, 2);

insert into Compra(data, valor, usuario_id, pacote_id)
	values ('2021-06-23', 942.50, 1, 2);

insert into Compra(data, valor, usuario_id, pacote_id)
	values ('2020-12-03', 4920.10, 1, 4);

insert into Compra(data, valor, usuario_id, pacote_id)
	values ('2018-02-24', 10328.99, 1, 5);

insert into Compra(data, valor, usuario_id, pacote_id)
	values ('2020-05-21', 4920.10, 3, 4);

disconnect;

quit;