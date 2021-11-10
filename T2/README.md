<h1>Trabalho 2 da disciplina de Desenvolvimento de Software para Web 1</h1>

Trabalho desenvolvido por:<br/>
- Claudia Rincon Sanches
<br/>

<b>Sistema:</b> Compra e venda de pacotes turísticos (B)<br/>
<b>Tecnologias usadas:</b> Spring MVC, Spring Data JPA, Spring Security & Thymeleaf<br/>
<b>SGBD utilizado:</b> Derby<br/>
<b>Nome do banco de dados:</b> PacotesTuristicos<br/>
<br/>

<p align="justify">Credenciais para login no sistema:</p>

- Papel: ADMIN | Credenciais -> admin2@gmail.com:admin2
- Papel: CLIENTE | Credenciais -> clienteNovo@gmail.com:clienteNovo
- Papel: AGENCIA | Credenciais -> agenciaNova@gmail.com:agenciaNova

O sistema foi internacionalizado nos idiomas: português e inglês.
<br/><br/>

<h2>Requisitos do sistema:<h2/>

<h3 align="justify">R1: CRUD de clientes (requer login de administrador).</h3> 
    
    (X) Totalmente implementado
    ( ) Parcialmente implementado
    ( ) Não implementado

<h3 align="justify">R5: Compra de pacote turístico (requer login do cliente via e-mail + senha). Ao clicar em um pacote turístico (requisito R4), o cliente pode efetuar a compra do pacote. O cliente e a agência de turismo devem ser informados (via e-mail) sobre a compra. Nesse caso, o sistema deve também informar um horário para uma reunião (via videoconferência) entre o cliente e a agência de turismo para acertar os detalhes da compra/venda do pacote turístico -- o link da videoconferência (google meet, zoom, etc) deve estar presente no corpo da mensagem enviada.</h3> 

    ( ) Totalmente implementado
    (X) Parcialmente implementado: a funcionalidade de enviar emails à agência e ao cliente não foi implementada
    ( ) Não implementado

<h3 align="justify">R6: Listagem de todos os pacotes turísticos de um cliente (requer login do cliente via e-mail + senha). Depois de fazer login, o cliente pode visualizar todos os seus pacotes turísticos adquiridos.</h3>  

    (X) Totalmente implementado
    ( ) Parcialmente implementado
    ( ) Não implementado

<h3 align="justify">R8: O sistema deve ser internacionalizado em pelo menos dois idiomas: português + outro de sua escolha.</h3> 

    (X) Totalmente implementado
    ( ) Parcialmente implementado
    ( ) Não implementado

<h3 align="justify">R9: O sistema deve validar (tamanho, formato, etc) todas as informações (campos nos formulários) cadastradas e/ou editadas.</h3>  

    ( ) Totalmente implementado
    ( ) Parcialmente implementado
    ( ) Não implementado
