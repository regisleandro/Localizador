<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url var="actionPath" value='${action}' />
<form:form method="post" action="${UsuarioController.deletar}" modelAttribute="usuario" cssClass="form-horizontal">
	 <form:hidden path="id" />
	  ${usuario.user}
	  <div class="form-group">
		 	<button type="submit"  class="btn btn-default">Apagar este cadastro?</button>
	  </div>
</form:form>