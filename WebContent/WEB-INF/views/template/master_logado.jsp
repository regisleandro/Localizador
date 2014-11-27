<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html class="no-js">
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<meta name="description" content="Slide and push menus that are initially hidden off screen, and transition into view with CSS transitions." />
<title>Onde Estou???</title>
<head>
<!-- css -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,700italic,400,600,700' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Bitter:400,700' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/font-awesome/css/font-awesome.min.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/base.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/font-awesome/css/font-awesome.min.css'/>" />
</head>
 <c:set var="req" value="${pageContext.request}" />
<body>

<nav class="menu slide-menu-left">
    <ul>
        <li><button class="close-menu">&larr; Fechar</button></li>
        <li> <a href="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}/principal/" >Onde Estou???</a></li>
        <li><a href="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}/amigos/" >Meus Amigos</a></li>
        <li><a  href="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}/historico/">Histórico Percurso</a></li>
        <li><a href="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}/inicio/alterar_cadastro/" >Cadastro</a></li>
        <li><a href="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}/inicio/deletar/" >Deletar Cadastro</a></li>
        <li><a href="#" onclick="fbLogoutUser()"> Logout </a></li>
    </ul>
</nav><!-- /slide menu left -->
<header>
 <div>
	 <div class="buttons">

	       
	       <button class="nav-toggler toggle-slide-left" 
	       		   style="background-color:transparent; border-color:transparent;">
	        <img src="<c:url value='/resources/images/menu.png'/>" height="35"/>
	       </button>

	   </div><!-- /buttons -->
 </div>
</header>
  
<div id="main">
        <div class="container">
			<tiles:insertAttribute name="body" />
		</div>
</div>
<footer></footer>
<!-- js -->
<script type="text/javascript" src="<c:url value='/resources/js/jquery-1.10.2.min.js'/>"></script>
<script src="<c:url value='/resources/js/classie.js'/>"></script>
<script src="<c:url value='/resources/js/nav.js'/>"></script>
<script type="text/javascript">
// Load the SDK asynchronously
(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/sdk.js";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

window.fbAsyncInit = function() {
    FB.init({
      appId      : '1035110476503940',
      cookie     : true,  // enable cookies to allow the server to access 
                          // the session
      xfbml      : true,  // parse social plugins on this page
      version    : 'v2.1' // use version 2.1
    });
     
} 

function fbLogoutUser(response){
	 //check if logout is 
    FB.getLoginStatus(function (response) {
        console.log('inside login status');
        if (response.status === 'connected') {
            // the user is logged in and has authenticated your
            // app, and response.authResponse supplies
            // the user's ID, a valid access token, a signed
            // request, and the time the access token 
            // and signed request each expire
            var uid = response.authResponse.userID;
            var accessToken = response.authResponse.accessToken;
            
            FB.logout(function (response) {
             	try{
             		console.log(accessToken);
                   	FB.Auth.setAuthResponse(null, 'unknown');
             	}catch(err){}
             	window.location = "${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}/sair/";
            });
        } else if (response.status === 'not_authorized') {
            // the user is logged in to Facebook, 
            // but has not authenticated your app

        } else {
            // the user isn't logged in to Facebook.
            console.log('response status not logged in');
            window.location = "${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}/sair/";
        }
    });	
 	
}

</script>
</body>
</html>
