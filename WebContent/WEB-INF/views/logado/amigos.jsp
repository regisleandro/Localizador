<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <style>
    .round {
        border-radius: 50%;
        overflow: hidden;
        width: 50px;
        height: 50px;
        float: left;
    }
    .round img {
   	   display: block;
       min-width: 100%;
       min-height: 100%;
    }
</style>

<c:forEach items="${amigos}" var="p">
	<div class="round">
            <img src="http://graph.facebook.com/${p.id}/picture?type=small"></img>
    </div>
	${p.name}
</c:forEach>
