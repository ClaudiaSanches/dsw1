<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
	<fmt:bundle basename="message">
		<head>
			<title><fmt:message key="admin.agency.list"/></title>
		</head>
		<body>
			<%
				String contextPath = request.getContextPath().replace("/", "");
			%>
			<a href="/<%=contextPath%>/index.jsp"><fmt:message key="homepage"/></a><br/>
			<a href="/<%=contextPath%>/admin"><fmt:message key="admin.title"/></a><br/>
			<a href="/<%=contextPath%>/logout/logout"><fmt:message key="logout"/></a>
			<div align="center">
				<h1><fmt:message key="admin.agency"/></h1>
			</div>
			<div align="center">
				<table border="1">
					<tr>
						<th><fmt:message key="admin.list.id"/></th>
						<th><fmt:message key="admin.list.name"/></th>
						<th><fmt:message key="admin.list.email"/></th>
						<th><fmt:message key="admin.list.pwd"/></th>
						<th><fmt:message key="admin.list.cnpj"/></th>
						<th><fmt:message key="admin.list.desription"/></th>
						<th><fmt:message key="actions"/></th>
					</tr>
					<c:forEach var="agencia" items="${requestScope.listaAgencias}">
						<tr>
							<td><c:out value="${agencia.id}" /></td>
							<td><c:out value="${agencia.nome}" /></td>
							<td><c:out value="${agencia.email}" /></td>
							<td><c:out value="${agencia.senha}" /></td>
							<td><c:out value="${agencia.cnpj}" /></td>
							<td><c:out value="${agencia.descricao}" /></td>
							<td><a href="/<%= contextPath %>/admin/atualizaAgencia?id=<c:out value='${agencia.id}' />"><fmt:message key="edit"/></a> 
	                        	&nbsp;&nbsp;&nbsp;&nbsp;
	                            <a href="/<%= contextPath %>/admin/removeAgencia?id=<c:out value='${agencia.id}' />"
										onclick="return confirm('<fmt:message key="admin.agency.remove.comfirm"/>');">
										<fmt:message key="remove"/></a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</body>
	</fmt:bundle>
</html>