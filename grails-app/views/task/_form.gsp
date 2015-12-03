<%@ page import="bfcrowd.Task" %>



			<div class="${hasErrors(bean: recommendationInstance, field: 'checkboxMode', 'error')} ">
				<label for="checkboxMode" class="control-label"><g:message code="recommendation.checkboxMode.label" default="Checkbox Mode" /></label>
				<div>
					<g:select class="form-control" name="checkboxMode" from="${recommendationInstance.constraints.checkboxMode.inList}" value="${recommendationInstance?.checkboxMode}" valueMessagePrefix="recommendation.checkboxMode" noSelection="['': '']"/>
					<span class="help-inline">${hasErrors(bean: recommendationInstance, field: 'checkboxMode', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: recommendationInstance, field: 'contribution', 'error')} ">
				<label for="contribution" class="control-label"><g:message code="recommendation.contribution.label" default="Contribution" /></label>
				<div>
					<g:select class="form-control" id="contribution" name="contribution.id" from="${bfcrowd.Contribution.list()}" optionKey="id" value="${recommendationInstance?.contributions?.id}" class="many-to-one" noSelection="['null': '']"/>
					<span class="help-inline">${hasErrors(bean: recommendationInstance, field: 'contribution', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: recommendationInstance, field: 'dateAssigned', 'error')} required">
				<label for="dateAssigned" class="control-label"><g:message code="recommendation.dateAssigned.label" default="Date Assigned" /><span class="required-indicator">*</span></label>
				<div>
					<g:datePicker name="dateAssigned" precision="minute"  value="${new Date(0)}"  />
					<span class="help-inline">${hasErrors(bean: recommendationInstance, field: 'dateAssigned', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: recommendationInstance, field: 'fromPage', 'error')} ">
				<label for="fromPage" class="control-label"><g:message code="recommendation.fromPage.label" default="From Page" /></label>
				<div>
					<g:textField class="form-control" name="fromPage" value="${recommendationInstance?.fromPage}"/>
					<span class="help-inline">${hasErrors(bean: recommendationInstance, field: 'fromPage', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: recommendationInstance, field: 'instructions', 'error')} ">
				<label for="instructions" class="control-label"><g:message code="recommendation.instructions.label" default="Instructions" /></label>
				<div>
					<g:textField class="form-control" name="instructions" value="${recommendationInstance?.instructions}"/>
					<span class="help-inline">${hasErrors(bean: recommendationInstance, field: 'instructions', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: recommendationInstance, field: 'path', 'error')} ">
				<label for="path" class="control-label"><g:message code="recommendation.path.label" default="Path" /></label>
				<div>
					<g:textField class="form-control" name="path" value="${recommendationInstance?.path}"/>
					<span class="help-inline">${hasErrors(bean: recommendationInstance, field: 'path', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: recommendationInstance, field: 'project', 'error')} required">
				<label for="project" class="control-label"><g:message code="recommendation.project.label" default="Project" /><span class="required-indicator">*</span></label>
				<div>
					<g:select class="form-control" id="project" name="project.id" from="${bfcrowd.Project.list()}" optionKey="id" required="" value="${recommendationInstance?.project?.id}" class="many-to-one"/>
					<span class="help-inline">${hasErrors(bean: recommendationInstance, field: 'project', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: recommendationInstance, field: 'property', 'error')} ">
				<label for="property" class="control-label"><g:message code="recommendation.property.label" default="Property" /></label>
				<div>
					<g:textField class="form-control" name="property" value="${recommendationInstance?.property}"/>
					<span class="help-inline">${hasErrors(bean: recommendationInstance, field: 'property', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: recommendationInstance, field: 'toPage', 'error')} ">
				<label for="toPage" class="control-label"><g:message code="recommendation.toPage.label" default="To Page" /></label>
				<div>
					<g:textField class="form-control" name="toPage" value="${recommendationInstance?.toPage}"/>
					<span class="help-inline">${hasErrors(bean: recommendationInstance, field: 'toPage', 'error')}</span>
				</div>
			</div>
			
			<div class="${hasErrors(bean: recommendationInstance, field: 'solved', 'error')} ">
				<label for="solved" class="control-label"><g:message code="recommendation.solved.label" default="Solved" /></label>
				<div>
					<bs:checkBox name="solved" value="${recommendationInstance?.solved}" />
					<span class="help-inline">${hasErrors(bean: recommendationInstance, field: 'solved', 'error')}</span>
				</div>
			</div>
			
			<div class="${hasErrors(bean: recommendationInstance, field: 'repeatableBetweenUsers', 'error')} ">
				<label for="repeatableBetweenUsers" class="control-label"><g:message code="bfcrowd.recommendation.repeatableBetweenUsers.label" default="repeatableBetweenUsers" /></label>
				<div>
					<bs:checkBox name="repeatableBetweenUsers" value="${recommendationInstance?.repeatableBetweenUsers}" />
					<span class="help-inline">${hasErrors(bean: recommendationInstance, field: 'repeatableBetweenUsers', 'error')}</span>
				</div>
			</div>
			
			<div class="${hasErrors(bean: projectInstance, field: 'maxRepeats', 'error')} ">
				<label for="name"><g:message code="bfcrowd.recommendation.maxRepeats.label" default="maxRepeats" /></label>
				<div>
					<g:textField name="maxRepeats" value="${projectInstance?.maxRepeats}"/>
					<a id="maxRepeats" rel="maxRepeats" title="${message(code:'bfcrowd.recommendation.maxRepeats.maxRepeats.help')}">
						<i class="icon-question-sign"></i>
					</a> 					
					<span class="help-inline">${hasErrors(bean: projectInstance, field: 'maxRepeats', 'error')}</span>
				</div>
			</div>
			
			<div class="${hasErrors(bean: projectInstance, field: 'maxRepeats', 'imagePath')} ">
				ImagePath
				<div>
					<g:textField name="imagePath" value="${projectInstance?.imagePath}"/>
					<a id="maxRepeats" rel="maxRepeats" title="${message(code:'bfcrowd.recommendation.maxRepeats.maxRepeats.help')}">
						<i class="icon-question-sign"></i>
					</a> 					
					<span class="help-inline">${hasErrors(bean: projectInstance, field: 'imagePath', 'error')}</span>
				</div>
			</div>			

