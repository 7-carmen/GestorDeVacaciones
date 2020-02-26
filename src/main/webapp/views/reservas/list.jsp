<%--
 * index.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div id="reservas" style="float: right;">
<display:table name="reservas" id="row" requestURI="/jefedepartamento/reservas/list.do" pagesize="6" class="displaytag">
	
	<spring:message code="welcome.reservas.tipo" var="nombreEmpleadoHeader" />
	<display:column value="${row.empleado.nombre} ${row.empleado.apellidos}"  title="${nombreEmpleadoHeader}" sortable="true" />
	
	<spring:message code="welcome.reservas.tipo" var="TipoHeader" />
	<display:column property="tipo"  title="${TipoHeader}" sortable="true" />
	
	<spring:message code="welcome.reservas.fecha" var="FechaHeader" />
	<display:column property="fecha"  title="${FechaHeader}" sortable="true" />
	
	<spring:message code="welcome.reservas.estado" var="EstadoHeader" />
	<display:column property="estado"  title="${EstadoHeader}" sortable="true" />
	
	<spring:message code="welcome.reservas.estado" var="EstadoHeader" />
	<display:column  title="${EstadoHeader}" sortable="false">
		<a href="/jefedepartamento/reservas/edit.do?idReserva=${row.id}&accion='APROBAR'">Visit W3Schools</a>
	</display:column>
	
	<spring:message code="welcome.reservas.estado" var="EstadoHeader" />
	<display:column  title="${EstadoHeader}" sortable="false" >
		<a href="/jefedepartamento/reservas/edit.do?idReserva=${row.id}&accion='DENEGAR'">Visit W3Schools</a>
	</display:column>
</display:table>
</div>