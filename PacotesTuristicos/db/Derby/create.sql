connect 'jdbc:derby:PacotesTuristicos;create=true;user=root;password=root;';

create table Usuario(id bigint not null generated always as identity, 
					 email varchar(64) not null unique, 
					 senha varchar(64) not null, 
					 nome varchar(256) not null, 
					 papel varchar(16) not null, 
					 cpf varchar(32) unique,
					 telefone varchar(32),
					 sexo char(2), 
					 dNasc varchar(16),
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
					constraint Agencia_FK foreign key (cnpj) references Usuario(cnpj) 
					ON DELETE CASCADE);

insert into Usuario(email, senha, nome, papel) 
	values ('admin@email.com', 'admin', 'Admin', 'ADMIN');

insert into Usuario(email, senha, nome, papel, cpf, telefone, sexo, dNasc) 
	values ('cliente1@email.com', 'cliente1', 'Fulano da Silva', 'CLIENTE', '123456789-00', '(16) 94444-5555', 'M', '13/06/1999');

insert into Usuario(email, senha, nome, papel, cpf, telefone, sexo, dNasc)
	values ('cliente2@email.com', 'cliente2', 'Ciclana de Souza', 'CLIENTE', '456789123-00', '(17) 98888-3333', 'F', '25/02/1987');

insert into Usuario(email, senha, nome, papel, cpf, telefone, sexo, dNasc)
	values ('cliente3@email.com', 'cliente3', 'Beltrano Barbosa', 'CLIENTE', '789123456-00', '(34) 97777-9999', 'M', '04/11/2002');

insert into Usuario(email, senha, nome, papel, cnpj, descricao)
	values ('agencia1@email.com', 'agencia1', 'ViagemMais', 'AGENCIA', '13.444.222/0001-77', 'Agencia de viagens');

insert into Usuario(email, senha, nome, papel, cnpj, descricao)
	values ('agencia2@email.com', 'agencia2', 'Tour Viagens', 'AGENCIA', '52.333.111/0001-99', 'Organizamos sua viagem para voce!!!');

insert into Usuario(email, senha, nome, papel, cnpj, descricao)
	values ('agencia3@email.com', 'agencia3', 'Turisticando', 'AGENCIA', '15.111.555/0001-00', 'Quer planejar sua viagem? Vem com a gente!!');

insert into Pacote(nome, cnpj, cidade, estado, pais, partida, duracao, valor) 
	values ('Pacote 1', '13.444.222/0001-77', 'Sao Paulo', 'Sao Paulo', 'Brasil', '2021-10-12', 5, 350.59);

insert into Pacote(nome, cnpj, cidade, estado, pais, partida, duracao, valor) 
	values ('Pacote 2', '15.111.555/0001-00', 'Balneario Camburiu', 'Santa Catarina', 'Brasil', '2021-12-08', 10, 942.50);

disconnect;

quit;
