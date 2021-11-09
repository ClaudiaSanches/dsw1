<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
	<fmt:bundle basename="message">
		<head>
			<title><fmt:message key="trip.title"/></title>
		</head>
		<body>
			<%
				String contextPath = request.getContextPath().replace("/", "");
			%>
			<a href="login.jsp"><fmt:message key="homepage.login"/></a>
			<div align="center">
				<h1><fmt:message key="trip.title"/></h1>
			</div>
			<div align="center">
				<table border="1">
					<tr>
						<th><fmt:message key="trip.list.id"/></th>
						<th><fmt:message key="trip.list.name"/></th>
						<th><fmt:message key="trip.list.agency"/></th>
						<th><fmt:message key="trip.list.location"/></th>
						<th><fmt:message key="trip.list.departure"/></th>
						<th><fmt:message key="trip.list.duration"/></th>
						<th><fmt:message key="trip.list.price"/></th>
					</tr>
					<c:forEach var="pacote" items="${requestScope.listaPacotes}">
						<tr>
							<td><c:out value="${pacote.id}" /></td>
							<td><c:out value="${pacote.nome}" /></td>
							<td><c:out value="${pacote.agencia.nome}" /></td>
							<td><c:choose>
									<c:when test="${pacote.estado} != null">
										<c:out value="${pacote.cidade}, ${pacote.estado} - ${pacote.pais}"/>
									</c:when>
									<c:otherwise>
										<c:out value="${pacote.cidade} - ${pacote.pais}"/>
									</c:otherwise>
								</c:choose>
							</td>
							<td><c:out value="${pacote.partida}" /></td>
							<td><c:out value="${pacote.duracao}" /></td>
							<td><c:out value="${pacote.valor}" /></td>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<h3>Filtrar pacotes por:</h3>
			Agência<br/>
			<c:forEach var="agencia" items="${requestScope.listaAgencias}">
				&emsp;<a href="/<%=contextPath%>/filtro/listaPacotesPorAgencia">
						<input type="hidden" name="destino" value="<c:out value='${agencia}'/>"/>
						<c:out value="${agencia.nome}"/></a><br/>
			</c:forEach>
			Destino<br/>
			<c:forEach var="destino" items="${requestScope.listaDestinos}">
				&emsp;<a href="/<%=contextPath%>/filtro/listaPacotesPorDestino">
						<input type="hidden" name="destino" value="<c:out value='${destino}'/>"/>
						<c:out value="${destino}"/></a><br/>
			</c:forEach>
			Partida<br/>
			<c:forEach var="partida" items="${requestScope.listaPartidas}">
				&emsp;<a href="/<%=contextPath%>/filtro/listaPacotesPorPartida">
						<input type="hidden" name="destino" value="<c:out value='${destino}'/>"/>
						<c:out value="${partida}"/></a><br/>
			</c:forEach>
		</body>
	</fmt:bundle>
</html>