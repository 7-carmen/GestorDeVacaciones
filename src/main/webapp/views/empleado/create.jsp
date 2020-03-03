<%--
 * action-1.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<img src="images/espalda.png" id="btnvolver" onclick="window.history.back();">
		<form:form action="jefedepartamento/empleado/create.do" modelAttribute="empleadoForm"
			method="post" class="form-horizontal" role="form">
<div style="float: left; margin-left: 200px; margin-top: 20px;">
				<form:label path="anio_entrada"
					for="anio_entrada">
					<spring:message code="empleado.anio_entrada" />
				</form:label><br/>
				<form:input type="number" path="anio_entrada" id="anio_entrada"
					required="required" />
				<form:errors class="error" path="anio_entrada" />
				<br/><br/>
				<form:label path="nombre"
					for="nombre">
					<spring:message code="empleado.nombre" />
				</form:label><br/>
				<form:input path="nombre" id="nombre"
					required="required" />
				<form:errors class="error" path="nombre" />
				<br/><br/>
				<form:label path="apellidos"
					for="apellidos">
					<spring:message code="empleado.apellidos" />
				</form:label><br/>
				<form:input path="apellidos" id="apellidos"
					required="required" />
				<form:errors class="error" path="apellidos" />
				<br/><br/>
				<form:label path="correo"
					for="correo">
					<spring:message code="empleado.correo" />
				</form:label><br/>
				<form:input type="email" path="correo" id="correo"
					required="required" />
				<form:errors class="error" path="correo" />
				<br/><br/>
</div>
<div style="float: left; margin-left: 200px; margin-top: 20px;">
				<form:label path="direccion"
					for="direccion">
					<spring:message code="empleado.direccion" />
				</form:label><br/>
				<form:input path="direccion" id="direccion"
					required="required" />
				<form:errors class="error" path="direccion" />
				<br/><br/>
				<form:label path="telefono"
					for="telefono">
					<spring:message code="empleado.telefono" />
				</form:label><br/>
				<form:input type="number" path="telefono" id="telefono"
					required="required" />
				<form:errors class="error" path="telefono" />
				<br/><br/>
				<form:label path="username"
					for="username">
					<spring:message code="empleado.username" />
				</form:label><br/>
				<form:input path="username" id="username"
					required="required" />
				<form:errors class="error" path="username" />
				<br/><br/>
				<form:label path="password"
					for="password">
					<spring:message code="empleado.password" />
				</form:label><br/>
				<form:input type="password" path="password" id="password"
					required="required" />
				<form:errors class="error" path="password" />
				<br/><br/>
</div>
<div style="float: left; margin-left: 200px; margin-top: 20px;">
				<form:label path="vacaciones"
					for="vacaciones">
					<spring:message code="empleado.vacaciones" />
				</form:label><br/>
				<form:select id="vacaciones" path="vacaciones">
					<form:option value="22" label="22 Días" />
					<form:option value="25" label="25 Días" />
					<form:option value="30" label="30 Días" />
				</form:select>
				<form:errors class="error" path="vacaciones" />
				<br/><br/>
				<form:label path="diasPersonales"
					for="diasPersonales">
					<spring:message code="empleado.diasPersonales" />
				</form:label><br/>
				<form:select id="diasPersonales" path="diasPersonales">
					<form:option value="2" label="2 Días" />
					<form:option value="4" label="4 Días" />
				</form:select>
				<form:errors class="error" path="diasPersonales" />
				<br/><br/>
				<form:label path="idDepartamento"
					for="idDepartamento">
					<spring:message code="empleado.departamento" />
				</form:label><br/>
				<form:select id="idDepartamento" path="idDepartamento">
					<jstl:forEach items="${departamentos}" var="departamento">
						 <form:option value="${departamento.id }" label="${departamento.nombre}">
						</form:option>
					</jstl:forEach>
				</form:select>
				<form:errors class="error" path="idDepartamento" />
				<br/><br/>
				<button type="submit" name="save" class="btn  btn-primary">
						<spring:message code="empleado.crear" />
				</button>
				
				<button type="button" class="btn btn-secondary" onclick="window.history.back();" >
					<spring:message code="empleado.cancelar" />
				</button>
</div>
		</form:form>
