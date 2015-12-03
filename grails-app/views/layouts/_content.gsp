<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="grails.plugin.nimble.core.UserBase"%>
<%@page import="grails.plugin.nimble.core.Role"%>
<div id="Content" class="container">
	
	<!-- Secondary menu in one row (e.g., actions for current controller) -->
	<g:if test="${!layout_nosecondarymenu}">
		<g:render template="/_menu/submenubar"/>														
	</g:if>

	<!-- print system messages (infos, warnings, etc) - not validation errors -->
	<g:if test="${flash.message && !layout_noflashmessage}">
		<div class="alert alert-info">${flash.message}</div>
	</g:if>

	<!-- Show page's content -->
	<g:layoutBody />
	<g:pageProperty name="page.body" />
</div>
