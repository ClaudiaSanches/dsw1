<h1>Trabalho 1 da disciplina de Desenvolvimento de Software para Web 1</h1>

<h2>R1:</h2> 
    <h3>CRUD de clientes (requer login de administrador).</h3>

    O ADMIN deve conseguir:
        DONE - Inserir clientes no banco de dados
        DONE - Ler clientes cadastrados no banco de dados
        DONE - Atualizar clientes no banco de dados
        DONE - Remover clientes do banco de dados

    SE SOBRAR TEMPO:
        TODO - Adicionar data de nascimento
        TODO - Adicionar as opções de sexo no formulário de cadastro de clientes

<h2>R2:</h2> 
    <h3>CRUD de agências de turismo (requer login de administrador).</h3>
    
    O ADMIN deve conseguir:
        DONE - Inserir agências no banco de dados
        DONE - Ler agências cadastrados no banco de dados
        DONE - Atualizar agências no banco de dados
        DONE - Remover agências do banco de dados

    SE SOBRAR TEMPO:
        DONE - CNPJ não está sendo lido do banco de dados
        DONE - Erro ao editar agência que possui pacotes cadastrados
        DONE - Erro ao remover agências que possuem pacotes cadastrados (adicionado ON DELETE CASCADE na criação do pacote no banco de dados)

<h2>R3:</h2> 
    <h3>Cadastro de pacotes turísticos para venda (requer login da agência via e-mail + senha). Depois de fazer login, a agência de turismo pode cadastrar um pacote turístico para venda. O cadastro de pacotes turísticos deve possuir os seguintes dados: CNPJ da agência de turismo, destinos (cidade/estado/país), data de partida, duração (em dias), valor, fotos (no máximo 10 imagens) dos locais turísticos a serem visitados. Por fim, é necessária, no cadastro, a descrição (arquivo PDF) com o roteiro detalhado do pacote turístico.</h3>

    A AGENCIA deve conseguir:
        DONE - Cadastrar pacotes turísticos

    SE SOBRAR TEMPO:
        TODO - Adicionar fotos
        TODO - Adicionar PDF da descrição

<h2>R4:</h2> 
    <h3>Listagem de todos os pacotes turísticos em uma única página (não requer login). O sistema deve prover a funcionalidade de filtrar os pacotes turísticos por destino, por agência de turismo ou por data de partida.</h3>

    TODOS devem conseguir:
        DONE - Listar os pacotes turísticos cadastrados no banco de dados
        DONE - Filtrar os pacotes por destino
        DONE - Filtrar os pacotes por agência
        DONE - Filtrar os pacotes por data de partida
        
    SE SOBRAR TEMPO:
        TODO - Estado não está sendo mostrado

<h2>R5:</h2> 
    <h3>Compra de pacote turístico (requer login do cliente via e-mail + senha). Ao clicar em um pacote turístico (requisito R4), o cliente pode efetuar a compra do pacote. O cliente e a agência de turismo devem ser informados (via e-mail) sobre a compra. Nesse caso, o sistema deve também informar um horário para uma reunião (via videoconferência) entre o cliente e a agência de turismo para acertar os detalhes da compra/venda do pacote turístico -- o link da videoconferência (google meet, zoom, etc) deve estar presente no corpo da mensagem enviada.</h3>

    TODO - Deve ser criada a classe COMPRA (Compra.java, CompraDAO.java, CompraController.java)
    TODO - Ao ser efetuada uma compra pelo cliente, ambos o CLIENTE e a AGENCIA devem ser notificados por email
    TODO - O email deve conter o horário e link para a call

<h2>R6:</h2>  
    <h3>Listagem de todos os pacotes turísticos de um cliente (requer login do cliente via e-mail + senha). Depois de fazer login, o cliente pode visualizar todos os seus pacotes turísticos adquiridos.</h3>

    O CLIENTE deve conseguir:
        TODO - Visualizar todos os pacotes que comprou

<h2>R7:</h2>  
    <h3>Listagem de todos os pacotes turísticos de uma agência de turismo (requer login da agência via e-mail + senha). Depois de fazer login, a agência pode visualizar todos os seus pacotes turísticos cadastrados. O sistema apenas deve prover a funcionalidade de filtrar apenas os pacotes "vigentes" -- com a data de partida posterior a data atual do sistema.</h3>

    A AGENCIA deve conseguir:
        DONE - Listar todos os seus pacotes cadastrados
        DONE - Filtrar os pacotes de acordo com a data de partida, entre vigentes e passados

<h2>R8:</h2> 
    <h3>O sistema deve ser internacionalizado em pelo menos dois idiomas: português + outro de sua escolha.</h3>

    ONGOING - Internacionalizar todas as mensagens do site 
