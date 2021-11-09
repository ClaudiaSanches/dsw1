<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
	<fmt:bundle basename="message">
		<head>
			<title><fmt:message key="agency.trip.list"/></title>
		</head>
		<body>
			<%
				String contextPath = request.getContextPath().replace("/", "");
			%>
			<a href="/<%=contextPath%>/index.jsp"><fmt:message key="homepage"/></a><br>
			<a href="/<%= contextPath %>/agencia"><fmt:message key="agency.title"/></a><br/>
			<a href="/<%=contextPath%>/logout/logout"><fmt:message key="logout"/></a>
			<div align="center">
				<h1><fmt:message key="trip.title"/></h1>
				<c:choose>
					<c:when test="${requestScope.vigentes}">
						<a href="/<%=contextPath%>/agencia/listaPacotesAgencia?vigentes=false"><fmt:message key="agency.list.all.trip"/></a><br/><br/>
					</c:when>
					<c:otherwise>
						<a href="/<%=contextPath%>/agencia/listaPacotesAgencia?vigentes=true"><fmt:message key="agency.list.next.trip"/></a><br/><br/>
					</c:otherwise>
				</c:choose>
			</div>
			<div align="center">
				<table border="1">
					<tr>
						<th><fmt:message key="trip.list.id"/></th>
						<th><fmt:message key="trip.list.name"/></th>
						<th><fmt:message key="trip.list.location"/></th>
						<th><fmt:message key="trip.list.departure"/></th>
						<th><fmt:message key="trip.list.duration"/></th>
						<th><fmt:message key="trip.list.price"/></th>
						<th><fmt:message key="actions"/></th>
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
							<td><a href="/<%= contextPath %>/agencia/atualizaPacote?id=<c:out value='${pacote.id}' />"><fmt:message key="edit"/></a> 
	                        	&nbsp;&nbsp;&nbsp;&nbsp;
	                            <a href="/<%= contextPath %>/agencia/removePacote?id=<c:out value='${pacote.id}' />"
										onclick="return confirm('<fmt:message key="agency.trip.remove.comfirm"/>');">
										<fmt:message key="remove"/></a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</body>
	</fmt:bundle>
</html>