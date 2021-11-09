<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
	<fmt:bundle basename="message">
		<head>
			<title><fmt:message key="admin.client.list"/></title>
		</head>
		<body>
			<%
				String contextPath = request.getContextPath().replace("/", "");
			%>
			<a href="/<%=contextPath%>/index.jsp"><fmt:message key="homepage"/></a><br/>
			<a href="/<%=contextPath%>/admin"><fmt:message key="admin.title"/></a><br/>
			<a href="/<%=contextPath%>/logout/logout"><fmt:message key="logout"/></a>
			<div align="center">
				<h1><fmt:message key="admin.clients"/></h1>
			</div>
			<div align="center">
				<table border="1">
					<tr>
						<th><fmt:message key="admin.list.id"/></th>
						<th><fmt:message key="admin.list.name"/></th>
						<th><fmt:message key="admin.list.email"/></th>
						<th><fmt:message key="admin.list.pwd"/></th>
						<th><fmt:message key="admin.list.cpf"/></th>
						<th><fmt:message key="admin.list.phone"/></th>
						<th><fmt:message key="admin.list.gender"/></th>
						<th><fmt:message key="admin.list.birth"/></th>
						<th><fmt:message key="actions"/></th>
					</tr>
					<c:forEach var="cliente" items="${requestScope.listaClientes}">
						<tr>
							<td><c:out value="${cliente.id}" /></td>
							<td><c:out value="${cliente.nome}" /></td>
							<td><c:out value="${cliente.email}" /></td>
							<td><c:out value="${cliente.senha}" /></td>
							<td><c:out value="${cliente.cpf}" /></td>
							<td><c:out value="${cliente.telefone}" /></td>
							<td><c:out value="${cliente.sexo}" /></td>
							<td><c:out value="${cliente.nasc}" /></td>
							<td><a href="/<%= contextPath %>/admin/atualizaCliente?id=<c:out value='${cliente.id}'/>"><fmt:message key="edit"/></a> 
	                        	&nbsp;&nbsp;&nbsp;&nbsp;
	                            <a href="/<%= contextPath %>/admin/removeCliente?id=<c:out value='${cliente.id}'/>"
										onclick="return confirm('<fmt:message key="admin.client.remove.comfirm"/>');">
										<fmt:message key="remove"/></a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</body>
	</fmt:bundle>
</html>