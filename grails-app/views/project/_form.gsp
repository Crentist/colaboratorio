<%@ page import="bfcrowd.Project" %>



			<div class="${hasErrors(bean: projectInstance, field: 'name', 'error')}">
				<label for="name" ><g:message code="bfcrowd.label.researcher.createProject.name" default="Name" /></label>
				<div>
					<g:textField name="name" value="${projectInstance?.name}"/>   
					<span class="help-inline">${hasErrors(bean: projectInstance, field: 'name', 'error')}</span>
				</div>
			</div>
			
			<div class="${hasErrors(bean: projectInstance, field: 'description', 'error')} ">
				<label for="description"><g:message code="bfcrowd.label.researcher.createProject.description" default="Description" /></label>
				<div>
					<g:textArea name="description" value="${projectInstance?.description}" rows="5" cols="40"/>
					<a id="descriptionTipIcon" rel="descriptionTip" title="${message(code:'bfcrowd.label.researcher.createProject.description.help')}">
						<i class="icon-question-sign"></i>
					</a> 
					<span class="help-inline">${hasErrors(bean: projectInstance, field: 'description', 'error')}</span>
				</div>
			</div>
						
			<div class="${hasErrors(bean: projectInstance, field: 'xpValue', 'error')} ">
				<label for="name"><g:message code="bfcrowd.label.researcher.createProject.xpValue" default="XP Value" /></label>
				<div>
					<g:textField name="xpValue" value="${projectInstance?.xpValue}"/>
					<a id="xpValueTipIcon" rel="xpValueTip" title="${message(code:'bfcrowd.label.researcher.createProject.xpValue.help')}">
						<i class="icon-question-sign"></i>
					</a> 
					<span class="help-inline">${hasErrors(bean: projectInstance, field: 'xpValue', 'error')}</span>
				</div>
			</div>
			<div class="${hasErrors(bean: projectInstance, field: 'bonusXP', 'error')} ">
				<label for="name"><g:message code="bfcrowd.label.researcher.createProject.bonusXP" default="Bonus XP" /></label>
				<div>
					<g:textField name="bonusXP" value="${projectInstance?.bonusXP}"/>
					<a id="bonusXPTipIcon" rel="bonusXPTip" title="${message(code:'bfcrowd.label.researcher.createProject.bonusXP.help')}">
						<i class="icon-question-sign"></i>
					</a> 					
					<span class="help-inline">${hasErrors(bean: projectInstance, field: 'bonusXP', 'error')}</span>
				</div>
			</div>
			<div class="${hasErrors(bean: projectInstance, field: 'requiredForBonus', 'error')} ">
				<label for="name"><g:message code="bfcrowd.label.researcher.createProject.requiredForBonus" default="Required For Bonus" /></label>
				<div>
					<g:textField name="requiredForBonus" value="${projectInstance?.requiredForBonus}"/>
					<a id="requiredForBonusTipIcon" rel="requiredForBonusTip" title="${message(code:'bfcrowd.label.researcher.createProject.requiredForBonus.help')}">
						<i class="icon-question-sign"></i>
					</a> 
					<span class="help-inline">${hasErrors(bean: projectInstance, field: 'requiredForBonus', 'error')}</span>
				</div>
			</div>

			<!--
			<div class="${hasErrors(bean: projectInstance, field: 'recommendations', 'error')} ">
				<label for="recommendations"><g:message code="project.recommendations.label" default="Recommendations" /></label>
				<div>		
					<ul class="one-to-many">
						<g:each in="${projectInstance?.recommendations?}" var="r">
						    <li>
						    	<g:link controller="recommendation" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link>
						    </li>
						</g:each>
						<li class="add">
							<g:link controller="recommendation" action="create" params="['project.id': projectInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'recommendation.label', default: 'Recommendation')])}</g:link>
						</li>
					</ul>
					<span class="help-inline">${hasErrors(bean: projectInstance, field: 'recommendations', 'error')}</span>
				</div>
			</div>
			
			-->
			<br>
			<!--
			  <div class="${hasErrors(bean: projectInstance, field: 'users', 'error')} ">
				<label for="users"><g:message code="project.users.label" default="Users" /></label>
				<div>	
					<span class="help-inline">${hasErrors(bean: projectInstance, field: 'users', 'error')}</span>
				</div>
			</div>
			-->

			<div class="${hasErrors(bean: projectInstance, field: 'logo', 'error')} ">
			    <label for="logo"><g:message code="bfcrowd.label.researcher.createProject.logo"/></label>
			    <input type="file" name="logo" id="logo" />
			    <div style="font-size:0.8em; margin: 1.0em;">
			    	<g:message code="bfcrowd.label.researcher.createProject.logo.description"/>
			    </div>
			</div>
    