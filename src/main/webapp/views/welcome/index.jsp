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

<security:authorize access="hasAnyRole('EMPLEADO', 'JEFEDEPARTAMENTO')">
<div id="datos" style="float: left;">
<p style="font-weight: bold;"><spring:message code="welcome.dias.nombre" /> <spam style="font-weight: normal;">${empleado.nombre}</spam>&nbsp;&nbsp;&nbsp;<spring:message code="welcome.dias.apellido" /> <spam style="font-weight: normal;">${empleado.apellidos}</spam></p>
<p style="font-weight: bold;"><spring:message code="welcome.dias.direccion" /> <spam style="font-weight: normal;">${empleado.direccion}</spam></p>
<p style="font-weight: bold;"><spring:message code="welcome.dias.telefono" /> <spam style="font-weight: normal;">${empleado.telefono}</spam></p>
<p style="font-weight: bold;"><spring:message code="welcome.dias.correo" /> <spam style="font-weight: normal;">${empleado.correo}</spam></p>
<p style="font-weight: bold;"><spring:message code="welcome.dias.departamento" /> <spam style="font-weight: normal;">${empleado.departamento.nombre}</spam></p>
<p style="font-weight: bold;"><spring:message code="welcome.dias.totales" /> <spam style="font-weight: normal;">${vacaciones.dias_totales}</spam>&nbsp;&nbsp;&nbsp;
<spring:message code="welcome.dias.usados" /> <spam style="font-weight: normal;">${vacaciones.dias_usados}</spam>&nbsp;&nbsp;&nbsp;
<spring:message code="welcome.dias.disponibles" /> <spam style="font-weight: normal;">${vacaciones.dias_totales-vacaciones.dias_usados}</spam>&nbsp;&nbsp;&nbsp;
<jstl:choose>
	<jstl:when test="${vacaciones.dias_totales-vacaciones.dias_usados=='0'}">
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" disabled>
		Reservar</button>
	</jstl:when>
	<jstl:otherwise>
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
		Reservar</button>
	</jstl:otherwise>
</jstl:choose>
</p>
<p style="font-weight: bold;"><spring:message code="welcome.dias.totales" /> <spam style="font-weight: normal;">${diasPersonales.dias_totales}</spam>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<spring:message code="welcome.dias.usados" /> <spam style="font-weight: normal;">${diasPersonales.dias_usados}</spam>&nbsp;&nbsp;&nbsp;
<spring:message code="welcome.dias.disponibles" /> <spam style="font-weight: normal;">${diasPersonales.dias_totales-diasPersonales.dias_usados}</spam>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<jstl:choose>
	<jstl:when test="${diasPersonales.dias_totales-diasPersonales.dias_usados=='0'}">
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal1" disabled>
		Reservar</button>
	</jstl:when>
	<jstl:otherwise>
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal1">
		Reservar</button>
	</jstl:otherwise>
</jstl:choose>

</p>
</div>
<div id="reservas" style="float: right;">
<display:table name="reservas" id="row" requestURI="/" pagesize="6" class="displaytag">
	<spring:message code="welcome.reservas.tipo" var="TipoHeader" />
	<display:column property="tipo"  title="${TipoHeader}" sortable="true" />
	
	<spring:message code="welcome.reservas.fecha" var="FechaHeader" />
	<display:column property="fecha"  title="${FechaHeader}" sortable="true" />
	
	<spring:message code="welcome.reservas.estado" var="EstadoHeader" />
	<display:column property="estado"  title="${EstadoHeader}" sortable="true" />
</display:table>
<security:authorize access="hasRole('JEFEDEPARTAMENTO')">
<div id="adminbtn">
	<button class="btn btn-primary">Añadir Empleado</button>
	<button class="btn btn-primary"  onclick="window.location.href='jefedepartamento/reservas/list.do'">Administrar reservas</button>
</div>
</security:authorize>
</div>
<!-- VENTANA MODAL PARA AÑADIR VACACIONES -->
<div class="modal" tabindex="-1" role="dialog" id="exampleModal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Añadir Vacaciones:</h5>
        <button type="button" id="cerrar" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form:form action="reservas/create.do" method="post" modelAttribute="reserva" role="form">
        	<form:hidden path="id" />
			<form:hidden path="version" />
			<form:hidden path="estado"  value="Solicitadas"/>
			<form:hidden path="tipo" value="Vacaciones"/>
			<form:hidden path="empleado" />
			
        	&nbsp;&nbsp;&nbsp;&nbsp;Día de inicio: &nbsp;&nbsp;&nbsp;&nbsp;<form:input path="fecha" type="date" required="true"/>
        	<br/>
        	<div class="modal-footer">
        		<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
        		<button type="submit" name="save" class="btn btn-primary">Añadir</button>
      		</div>
        </form:form>
      </div>
    </div>
  </div>
</div>

<!-- VENTANA MODAL PARA AÑADIR DIAS PROPIOS -->
<div class="modal" tabindex="-1" role="dialog" id="exampleModal1">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Añadir Dias Propios:</h5>
        <button type="button" id="cerrar" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form:form action="reservas/create.do" method="post" modelAttribute="reserva" role="form">
        	<form:hidden path="id" />
			<form:hidden path="version" />
			<form:hidden path="estado"  value="Solicitadas"/>
			<form:hidden path="tipo" value="Dias Personales"/>
			<form:hidden path="empleado" />
			
        	&nbsp;&nbsp;&nbsp;&nbsp;Día de inicio: &nbsp;&nbsp;&nbsp;&nbsp;<form:input path="fecha" type="date" required="true"/>
        	<br/>
        	<div class="modal-footer">
        		<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
        		<button type="submit" name="save" class="btn btn-primary">Añadir</button>
      		</div>
        </form:form>
      </div>
    </div>
  </div>
</div>
</security:authorize>