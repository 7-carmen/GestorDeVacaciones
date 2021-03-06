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
<p><spring:message code="welcome.greeting" /></p>

<p><spring:message code="welcome.greeting.current.time" /> ${moment}</p> 
<div id="bienvenido"></div>
</security:authorize>

<security:authorize access="hasAnyRole('EMPLEADO', 'JEFEDEPARTAMENTO')">
<div id="datos" style="float: left;">
<p style="font-weight: bold;"><spring:message code="welcome.dias.nombre" /> <span style="font-weight: normal;">${empleado.nombre}</span>&nbsp;&nbsp;&nbsp;<spring:message code="welcome.dias.apellido" /> <span style="font-weight: normal;">${empleado.apellidos}</span></p>
<p style="font-weight: bold;"><spring:message code="welcome.dias.direccion" /> <span style="font-weight: normal;">${empleado.direccion}</span></p>
<p style="font-weight: bold;"><spring:message code="welcome.dias.telefono" /> <span style="font-weight: normal;">${empleado.telefono}</span></p>
<p style="font-weight: bold;"><spring:message code="welcome.dias.correo" /> <span style="font-weight: normal;">${empleado.correo}</span></p>
<p style="font-weight: bold;"><spring:message code="welcome.dias.departamento" /> <span style="font-weight: normal;">${empleado.departamento.nombre}</span></p>
<p style="font-weight: bold;"><spring:message code="welcome.dias.totales" /> <span style="font-weight: normal;">${vacaciones.dias_totales}</span>&nbsp;&nbsp;&nbsp;
<spring:message code="welcome.dias.usados" /> <span style="font-weight: normal;">${vacaciones.dias_usados}</span>&nbsp;&nbsp;&nbsp;
<spring:message code="welcome.dias.disponibles" /> <span style="font-weight: normal;">${vacaciones.dias_totales-vacaciones.dias_usados}</span>&nbsp;&nbsp;&nbsp;
<jstl:choose>
	<jstl:when test="${vacaciones.dias_totales-vacaciones.dias_usados=='0'}">
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" disabled>
		<spring:message code="welcome.boton.reservas"/> </button>
	</jstl:when>
	<jstl:otherwise>
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
		<spring:message code="welcome.boton.reservas"/></button>
	</jstl:otherwise>
</jstl:choose>
</p>
<p style="font-weight: bold;"><spring:message code="welcome.dias.totales" /> <span style="font-weight: normal;">${diasPersonales.dias_totales}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<spring:message code="welcome.dias.usados" /> <span style="font-weight: normal;">${diasPersonales.dias_usados}</span>&nbsp;&nbsp;&nbsp;
<spring:message code="welcome.dias.disponibles" /> <span style="font-weight: normal;">${diasPersonales.dias_totales-diasPersonales.dias_usados}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<jstl:choose>
	<jstl:when test="${diasPersonales.dias_totales-diasPersonales.dias_usados=='0'}">
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal1" disabled>
		<spring:message code="welcome.boton.reservas"/></button>
	</jstl:when>
	<jstl:otherwise>
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal1">
		<spring:message code="welcome.boton.reservas"/></button>
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
	<button class="btn btn-primary" onclick="window.location.href='jefedepartamento/empleado/create.do'"><spring:message code="welcome.boton.usuarios"/></button>
	<button class="btn btn-primary"  onclick="window.location.href='jefedepartamento/reservas/list.do'"><spring:message code="welcome.boton.admReservas"/></button>
</div>
</security:authorize>
</div>
<!-- VENTANA MODAL PARA A�ADIR VACACIONES -->
<div class="modal" tabindex="-1" role="dialog" id="exampleModal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title"><spring:message code="welcome.addVacaciones"/></h5>
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
			
        	&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="welcome.dia"/> &nbsp;&nbsp;&nbsp;&nbsp;<form:input path="fecha" type="date" required="true"/>
        	<br/>
        	<div class="modal-footer">
        		<button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message code="welcome.cancelar"/></button>
        		<button type="submit" name="save" class="btn btn-primary"><spring:message code="welcome.guardar"/></button>
      		</div>
        </form:form>
      </div>
    </div>
  </div>
</div>

<!-- VENTANA MODAL PARA A�ADIR DIAS PROPIOS -->
<div class="modal" tabindex="-1" role="dialog" id="exampleModal1">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title"><spring:message code="welcome.addDia"/></h5>
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
			
        	&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="welcome.dia"/>&nbsp;&nbsp;&nbsp;&nbsp;<form:input path="fecha" type="date" required="true"/>
        	<br/>
        	<div class="modal-footer">
        		<button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message code="welcome.cancelar"/></button>
        		<button type="submit" name="save" class="btn btn-primary"><spring:message code="welcome.guardar"/></button>
      		</div>
        </form:form>
      </div>
    </div>
  </div>
</div>
</security:authorize>