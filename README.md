<h1>Trabalho 1 da disciplina de Desenvolvimento de Software para Web 1</h1>

Trabalho desenvolvido por membro(s?) do Grupo 10, composto por:<br/>
- Alain Gauthier Ndamwey Djamba (desistente)
- Claudia Rincon Sanches
- Marcus Vinicius Soares de Oliveira (??)
<br/>

<b>Sistema:</b> Compra e venda de pacotes turísticos (B1)<br/>
<b>Tecnologias usadas:</b> Servlet, JSP, JSTL e JDBC<br/>
<b>SGBD utilizado:</b> Derby<br/>
<b>Nome do banco de dados:</b> PacotesTuristicos<br/>
<b>Script de criação do banco de dados:</b> disponível em [dsw1/PacotesTuristicos/db/Derby/create.sql](https://github.com/AlainNgauthier/dsw1/blob/master/PacotesTuristicos/db/Derby/create.sql)<br/>
<br/>

<p align="justify">O banco de dados possui três tabelas: <b>Usuario</b>, <b>Pacote</b> e <b>Compra</b>. Todas foram populadas com pelo menos cinco entradas. A tabela Usuario foi populada com usuários de três tipos diferentes de papéis: <b>ADMIN</b>, <b>CLIENTE</b> e <b>AGENCIA</b>. Cada usuário possui acesso a determinadas áreas de acordo com seu papel.</p>

O sistema foi internacionalidade nos idiomas: português e inglês.
<br/><br/>

<h2>Requisitos do sistema:<h2/>

<h3>R1: CRUD de clientes (requer login de administrador).</h3> 
    
    (X) Totalmente implementado
    ( ) Parcialmente implementado
    ( ) Não implementado
    
    Divisão na implementação da funcionalidade: Claudia (100%)

<h3>R2: CRUD de agências de turismo (requer login de administrador).</h3> 
    
    (X) Totalmente implementado
    ( ) Parcialmente implementado
    ( ) Não implementado
    
    Divisão na implementação da funcionalidade: Claudia (95%), Marcus (5%)

<h3 align="justify">R3: Cadastro de pacotes turísticos para venda (requer login da agência via e-mail + senha). Depois de fazer login, a agência de turismo pode cadastrar um pacote turístico para venda. O cadastro de pacotes turísticos deve possuir os seguintes dados: CNPJ da agência de turismo, destinos (cidade/estado/país), data de partida, duração (em dias), valor, fotos (no máximo 10 imagens) dos locais turísticos a serem visitados. Por fim, é necessária, no cadastro, a descrição (arquivo PDF) com o roteiro detalhado do pacote turístico.</h3> 

    ( ) Totalmente implementado
    (X) Parcialmente implementado: pacotes não possuem fotos nem arquivo de descrição em PDF
    ( ) Não implementado
    
    Divisão na implementação da funcionalidade: Claudia (100%)

<h3  align="justify">R4: Listagem de todos os pacotes turísticos em uma única página (não requer login). O sistema deve prover a funcionalidade de filtrar os pacotes turísticos por destino, por agência de turismo ou por data de partida.</h3> 

    (X) Totalmente implementado
    ( ) Parcialmente implementado
    ( ) Não implementado
    
    Divisão na implementação da funcionalidade: Claudia (100%)

<h3  align="justify">R5: Compra de pacote turístico (requer login do cliente via e-mail + senha). Ao clicar em um pacote turístico (requisito R4), o cliente pode efetuar a compra do pacote. O cliente e a agência de turismo devem ser informados (via e-mail) sobre a compra. Nesse caso, o sistema deve também informar um horário para uma reunião (via videoconferência) entre o cliente e a agência de turismo para acertar os detalhes da compra/venda do pacote turístico -- o link da videoconferência (google meet, zoom, etc) deve estar presente no corpo da mensagem enviada.</h3> 

    ( ) Totalmente implementado
    (X) Parcialmente implementado: a funcionalidade de enviar emails à agência e ao cliente não foi implementada
    ( ) Não implementado
    
    Divisão na implementação da funcionalidade: Claudia (100%)

<h3  align="justify">R6: Listagem de todos os pacotes turísticos de um cliente (requer login do cliente via e-mail + senha). Depois de fazer login, o cliente pode visualizar todos os seus pacotes turísticos adquiridos.</h3>  

    (X) Totalmente implementado
    ( ) Parcialmente implementado
    ( ) Não implementado
    
    Divisão na implementação da funcionalidade: Claudia (100%)

<h3 align="justify">R7: Listagem de todos os pacotes turísticos de uma agência de turismo (requer login da agência via e-mail + senha). Depois de fazer login, a agência pode visualizar todos os seus pacotes turísticos cadastrados. O sistema apenas deve prover a funcionalidade de filtrar apenas os pacotes "vigentes" -- com a data de partida posterior a data atual do sistema.</h3>  

    (X) Totalmente implementado
    ( ) Parcialmente implementado
    ( ) Não implementado
    
    Divisão na implementação da funcionalidade: Claudia (100%)

<h3>R8: O sistema deve ser internacionalizado em pelo menos dois idiomas: português + outro de sua escolha.</h3> 

    (X) Totalmente implementado
    ( ) Parcialmente implementado
    ( ) Não implementado
    
    Divisão na implementação da funcionalidade: Claudia (100%)
