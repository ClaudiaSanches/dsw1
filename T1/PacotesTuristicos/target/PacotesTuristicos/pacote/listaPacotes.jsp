<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<!-- <fmt:bundle basename="message"> -->
	<head>
		<title>Lista de pacotes turísticos</title>
	</head>
	<body>
		<%
			String contextPath = request.getContextPath().replace("/", "");
			System.out.println("Chegou em /pacote/");
		%>
		<div align="center">
			<h1>Pacotes turísticos</h1>
		</div>
		<div align="center">
			<table border="1">
				<tr>
					<th>ID</th>
					<th>Nome</th>
					<th>Destino</th>
					<th>Partida</th>
					<th>Duração</th>
					<th>Valor</th>
					<th>Ações</th>
				</tr>
				<c:forEach var="pacote" items="${requestScope.lista}">
					<tr>
						<td><c:out value="${pacote.id}" /></td>
						<td><c:out value="${pacote.nome}" /></td>
						<td><c:choose>
								<c:when test="${pacote.estado} != null">
									<c:out value="${pacote.cidade}, ${pacote.estado} - ${pacote.pais}" />
								</c:when>
								<c:otherwise>
									<c:out value="${pacote.cidade} - ${pacote.pais}" />
								</c:otherwise>
							</c:choose>
						</td>
						<td><c:out value="${pacote.partida}" /></td>
						<td><c:out value="${pacote.duracao}" /></td>
						<td><c:out value="${pacote.valor}" /></td>
						<td><a href="/<%= contextPath %>/pacote/atualizaPacote?id=<c:out value='${pacote.id}' />">Editar</a> 
                        	&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="/<%= contextPath %>/pacote/removePacote?id=<c:out value='${pacote.id}' />"
									onclick="return confirm('Tem certeza que deseja excluir este pacote?');">
									Remover</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
<!-- </fmt:bundle> -->
</html>