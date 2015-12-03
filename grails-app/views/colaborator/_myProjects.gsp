<h3>My projects</h3>

	<br>
	<ul class="option-list">
		<g:each in="${myProjects}" var="item">
			<li><g:remoteLink  class="btn btn-danger"
			action="leaveProjectById" id="${item.id}" update="joinedProjects" 
			before="if(!confirm('Are you sure?')) return false"><i class="icon-remove"></i>
			
			<g:link controller="colaborator" action="project" 
			id="${item.id}"><div class="text">${item.name}</div></g:link></g:remoteLink></li>
		</g:each>
	</ul>
	<br>



<g:formRemote class="form-inline" name="joinProject" url="[controller:'colaborator', action:'joinProject']" update="joinedProjects">
    <legend>Other Projects</legend>
    <label for="name">Search: </label><g:textField placeholder="Type project name…" name="name"/><br/>
    <input type="submit" value="Join"/>
</g:formRemote>

<div id="otherProjects">
	<ul class="option-list">
		<g:each in="${otherProjects}" var="item">
			<li><g:remoteLink class="btn btn-success" action="joinProjectById" id="${item.id}" update="joinedProjects"><i class="icon-plus"></i></g:remoteLink>${item.name}</li>
		</g:each>
	</ul>
</div>