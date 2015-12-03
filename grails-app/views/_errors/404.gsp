<html>
	<head>
		<title><g:message code="error.404.title"/></title>
		<meta name="layout" content="kickstart" />
		<g:set var="layout_nomainmenu"		value="${true}" scope="request"/>
		<g:set var="layout_nosecondarymenu"	value="${true}" scope="request"/>
	</head>

<body>
	<content tag="header">
		<!-- Empty Header -->
	</content>
	
  	<section id="Error" class="">
		<div class="big-message">
			<div class="container">
				<h1>
					<g:message code="bfcrowd.label.error.notFound.callout"/>
				</h1>
				<h2>
					<g:message code="bfcrowd.label.error.notFound.title"/>
				</h2>
				<p>
					<g:message code="bfcrowd.label.error.notFound.message"/>
				</p>
				
				<div class="actions">
					<a href="${createLink(uri: '/')}" class="btn btn-large btn-primary">
						<i class="glyphicon glyphicon-chevron-left icon-white"></i>
						<g:message code="bfcrowd.label.error.forbidden.backToHome"/>
					</a>					
				</div>
			</div>
		</div>
	</section>
  
  
  </body>
</html>