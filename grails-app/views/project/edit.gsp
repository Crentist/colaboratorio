<%@ page import="bfcrowd.Project" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'project.label', default: 'Project')}" />
	<title><g:message code="default.edit.label" args="[entityName]" /></title>
</head>

<body>
<ul id="Menu" class="nav nav-pills margin-top-small">
	
	<li class="${ params.action == "list" ? 'active' : '' }">
		<g:link action="list"><i class="glyphicon glyphicon-th-list"></i> <g:message code="bfcrowd.label.showProject.navbar.list"/></g:link>
	</li>
	<li class="${ params.action == "create" ? 'active' : '' }">
		<g:link action="create"><i class="glyphicon glyphicon-plus"></i> <g:message code="bfcrowd.label.showProject.navbar.create"/></g:link>
	</li>
	
	<g:if test="${ params.action == 'show' || params.action == 'edit' }">
		<!-- the item is an object (not a list) -->
		<li class="${ params.action == "edit" ? 'active' : '' }">
			<g:link action="edit" id="${params.id}"><i class="glyphicon glyphicon-pencil"></i> <g:message code="bfcrowd.label.showProject.navbar.edit"/></g:link>
		</li>
		<li class="">
			<g:render template="/_common/modals/deleteTextLink"/>
		</li>
	</g:if>
	
</ul> 
	<section id="edit-project" class="first">
		<div class="span5">
			<g:hasErrors bean="${projectInstance}">
			<div class="alert alert-danger">
				<g:renderErrors bean="${projectInstance}" as="list" />
			</div>
			</g:hasErrors>
	
			<g:form method="post" class="form-horizontal" role="form" >
				<g:hiddenField name="id" value="${projectInstance?.id}" />
				<g:hiddenField name="version" value="${projectInstance?.version}" />
				<g:hiddenField name="_method" value="PUT" />
				
				<g:render template="form"/>
				
				<div class="form-actions margin-top-medium">
					<g:actionSubmit class="btn btn-primary" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
		            <button class="btn" type="reset"><g:message code="default.button.reset.label" default="Reset" /></button>
				</div>
			</g:form>
		</div>
	</section>
<script type="text/javascript">

  $(function() {
    $("#descriptionTip").hide();
    $('#descriptionTipIcon').popover({animation:true, placement:'bottom', title:'A title', content:$('#descriptionTip').html()})
  });
  
  $(function() {
    $("#xpValueTip").hide();
    $('#xpValueTipIcon').popover({animation:true, placement:'bottom', title:'A title', content:$('#xpValueTip').html()})
  });  
  
  $(function() {
    $("#bonusXPTip").hide();
    $('#bonusXPTipIcon').popover({animation:true, placement:'bottom', title:'A title', content:$('#bonusXPTip').html()})
  });
  
  $(function() {
    $("#requiredForBonusTip").hide();
    $('#requiredForBonusTipIcon').popover({animation:true, placement:'bottom', title:'A title', content:$('#requiredForBonusTip').html()})
  });
  
  
</script>	
</body>

</html>
