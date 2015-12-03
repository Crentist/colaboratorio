<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="grails.plugin.nimble.core.UserBase"%>
<%@page import="bfcrowd.Project"%>
<g:set var="currentUser" value="${UserBase.get(SecurityUtils.subject.principal)}" />
<g:set var="myProjects" value="${currentUser.getMyProjects()}" />
<g:if test="${!(myProjects.size() == 0)}">
	<g:each var="proj" in="${myProjects}">
		<div class="show-component"> 
			<g:link controller="colaborator" action="project" id="${proj.id}"> <strong> ${proj.name } </strong> </g:link>
			<p> ${proj.description} </p>
			<g:remoteLink action="leaveProjectById" id="${proj.id}" style="float:right" update="myProjects"	before="if(!confirm('Are you sure?')) return false"> <g:message code="bfcrowd.label.collab.leaveProject"/> </g:remoteLink>
		</div>
		<br/>
	</g:each>	
</g:if>
<g:else>
	<g:message code="bfcrowd.label.collab.emptyProjectList" />					
</g:else>

<h3> <g:message code="bfcrowd.label.collab.otherProjects"/></h3>	
<g:set var="allProjects" value="${Project.getAll()}" />
<!-- Listar los proyectos disponibles dentro de recuadros o algo por el estilo -->
<g:each var="proj" in="${allProjects}">
	<g:if test="${!currentUser.getMyProjects().contains(proj)}">
	<div class="show-component"> 
		<strong> ${proj.name } </strong>
		<p> ${proj.description}  </p>
		<g:if test="${currentUser.getFacebookID()}">
			<g:remoteLink action="joinProjectById" onSuccess="facebookShare()" id="${proj.id}" style="float:right" update="myProjects"><g:message code="bfcrowd.label.collab.joinProject"/></g:remoteLink>
		</g:if>
		<g:else>
			<g:remoteLink action="joinProjectById" id="${proj.id}" style="float:right" update="myProjects"><g:message code="bfcrowd.label.collab.joinProject"/></g:remoteLink>
		</g:else>
	</div>
	<br/>
	</g:if>
</g:each>