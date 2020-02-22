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

<security:authorize access="isAnonymous()">

<p><spring:message code="welcome.greeting.prefix" /> ${name}<spring:message code="welcome.greeting.suffix" /></p>

<p><spring:message code="welcome.greeting.current.time" /> ${moment}</p> 

</security:authorize>

<security:authorize access="hasRole('EMPLEADO')">

<p><spring:message code="welcome.dias.totales" /> ${vacaciones.dias_totales}</p>
<p><spring:message code="welcome.dias.usados" /> ${vacaciones.dias_usados}</p>
<p><spring:message code="welcome.dias.disponibles" /> ${vacaciones.dias_totales-vacaciones.dias_usados}</p>

<display:table name="reservas" id="row" requestURI="/" pagesize="2" class="displaytag">
	<spring:message code="welcome.reservas.tipo" var="TipoHeader" />
	<display:column property="tipo"  title="${TipoHeader}" sortable="true" />
	
	<spring:message code="welcome.reservas.fecha" var="FechaHeader" />
	<display:column property="fecha"  title="${FechaHeader}" sortable="true" />
	
	<spring:message code="welcome.reservas.estado" var="EstadoHeader" />
	<display:column property="estado"  title="${EstadoHeader}" sortable="true" />
</display:table>

</security:authorize>