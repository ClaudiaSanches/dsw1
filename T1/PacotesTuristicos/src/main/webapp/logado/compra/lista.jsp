<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
	<fmt:bundle basename="message">
		<head>
			<title><fmt:message key="client.trip.list"/></title>
		</head>
		<body>
			<%
				String contextPath = request.getContextPath().replace("/", "");
			%>
			<a href="/<%=contextPath%>/index.jsp"><fmt:message key="homepage"/></a><br/>
			<a href="/<%=contextPath%>/cliente"><fmt:message key="client.title"/></a><br/>
			<a href="/<%=contextPath%>/logout/logout"><fmt:message key="logout"/></a>
			<div align="center">
				<h1><fmt:message key="client.trip.list"/></h1>
				<h2>
					<a href="/<%=contextPath%>/compras/cadastro"><fmt:message key="client.trip.buy"/></a> 
				</h2>
				<br />
				<h3><fmt:message key="client.trip.list"/></h3>
				<br />
			</div>
			<div align="center">
				<table border="1">
					<caption></caption>
					<tr>
						<th><fmt:message key="trip.list.id"/></th>
						<th><fmt:message key="purchase.date"/></th>
						<th><fmt:message key="trip.list.price"/></th>
						<th><fmt:message key="purchase.good"/></th>
						<th><fmt:message key="trip.list.location"/></th>
						<th><fmt:message key="trip.list.departure"/></th>
						<th><fmt:message key="trip.list.duration"/></th>
						<th><fmt:message key="trip.filter.agency"/></th>
					</tr>
					<c:forEach var="compra" items="${requestScope.listaCompras}">
						<tr>
							<td>${compra.id}</td>
							<td>${compra.data}</td>
							<td>${compra.valor}</td>
							<td>${compra.pacote.nome}</td>
							<td>${compra.pacote.cidade}</td>
							<td>${compra.pacote.partida}</td>
							<td>${compra.pacote.duracao}</td>
							<td>${compra.pacote.agencia.nome}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</body>
	</fmt:bundle>
</html>
