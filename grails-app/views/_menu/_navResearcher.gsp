<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="grails.plugin.nimble.core.UserBase"%>
<%@page import="grails.plugin.nimble.core.Role"%>


<li>
<g:link controller="recommendationCsvImporter" action="index">
	<g:message code="bfcrowd.label.import.navbar" />
</g:link>
</li>