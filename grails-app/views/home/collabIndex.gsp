<html>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="grails.plugin.nimble.core.UserBase"%>
<%@page import="grails.plugin.nimble.core.Role"%>
<%@page import="bfcrowd.Project"%>
<head>
	<title><g:message code="default.welcome.title" args="[meta(name:'app.name')]"/> </title>
	<meta name="layout" content="kickstart" />
	<g:javascript src="facebookLogin.js" />

</head>

<body>

<div class="row-fluid">

	<div class="span8">
		<!-- Proyectos del usuario & resto de proyectos a los cuales unirse (mockup vista de la página de inicio del colaborador, lado izquierdo) -->
		
		<h3> <g:message code="bfcrowd.label.collab.myProjects"/></h3>
			<div id="myProjects">
				<g:render template="/home/myProjects"/>
			</div>
	</div>
	
	<div class="span4">
		<div class="show-component"> 
			<h4> <g:message code="bfcrowd.label.collab.myBadges"/> </h4>
			<div><!-- Insignias. TO DO --></div>
			
			<br/>
			<h4> <g:message code="bfcrowd.label.collab.myAchievements"/> </h4>
			<div><!-- Logros. TO DO --></div>
			
			<br/>
			<h4> <g:message code="bfcrowd.label.collab.toNext"/> </h4>
			<div><!-- Progress bar para la siguiente insignia. TO DO --></div>
		</div>
		<br/>
		<div class="show-component"> 
			<h4> <g:message code="bfcrowd.label.collab.ranking"/> </h4>
		</div>	
		
		<br/>
		<g:set var="currentUser" value="${UserBase.get(SecurityUtils.subject.principal)}" />	
		<g:if test="${!currentUser.requests}">
			<g:link controller="userBF" action="requestPromotion" id="${currentUser.id }"> <g:message code="bfcrowd.label.collab.requestPromotion"/>  </g:link>
		</g:if>		
	</div>

</div>

</body>

</html>