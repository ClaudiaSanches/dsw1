<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table border="1" style="width: 400px; border: 1px solid black">
	<tr>
		<th><fmt:message key="trip.list.id"/></th>
		<th><fmt:message key="purchase.good"/></th>
		<th><fmt:message key="trip.filter.agency"/></th>
		<th><fmt:message key="trip.list.location"/></th>
		<th><fmt:message key="trip.list.departure"/></th>
		<th><fmt:message key="trip.list.duration"/></th>
		<th><fmt:message key="trip.list.price"/></th>
	</tr>
	<c:forEach var="pacote" items="${pacotes}">
		<tr>
			<td style="width: 10%; text-align: center"><input type="radio"
				id="${pacote.key}" name="pacote" value="${pacote.key}" required></td>
			<td>${pacote.value.nome}</td>
			<td>${pacote.value.agencia.nome}</td>
			<td>${pacote.value.cidade}</td>
			<td>${pacote.value.partida}</td>
			<td>${pacote.value.duracao}</td>
			<td>${pacote.value.valor}</td>
		</tr>
	</c:forEach>
</table>
<br/>
<br/>
<tr>
	<td colspan="2" align="center"><input type="submit" value="<fmt:message key='save'/>" /></td>
</tr>