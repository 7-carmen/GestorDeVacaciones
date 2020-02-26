<%--
 * header.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div class="cabecera">
<div style="float: left;">
	<a href="security/login.do"><img src="images/logo.png" alt="Gestor de Vacaciones 3.0"/></a>
<br/>
<!-- HREF para escoger el lenguaje -->
<a id="lenguaje" href="?language=en">en</a> | <a href="?language=es">es</a>
</div>
	<div class="titulos">
	<p style="font-weight: bold; font-size:30px; line-height:10px;">CeliaTOUR S.A.</p>
	<p style="line-height:10px; margin-left: 5px;">Gestor de Vacaciones 3.0</p>
	</div>
<div class="bntcabecera" style="float: right;"> 
<!-- Menu del LOGIN -->
		<security:authorize access="isAnonymous()">
		<ul>
			<li class="nav-item"><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		</ul>
		</security:authorize>
		
<!--Menu para cerrar sesión-->
		<security:authorize access="isAuthenticated()">
		<ul class="nav justify-content-end">
			<li class="nav-item">
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
			<li class="nav-item"><a class="nav-link active" href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
		</ul>
		</security:authorize>
</div>
</div>

