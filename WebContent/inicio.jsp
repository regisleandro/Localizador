<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<span class="fa fa-spinner bigicon"></span>
<h2>Login</h2>
<c:url var="actionPath" value='login/' />
<form:form method="post" action="${actionPath}" id="logar">
<div>
    <input id="username" name="username" type="text" placeholder="usuário" onkeypress="check_values();">
    <input id="password" name="password" type="password" placeholder="senha" onkeypress="check_values();">
    <button id="button1" class="btn btn-default wide hidden" type="submit">
    	<span class="fa fa-check med"></span>
    </button>
    <span id="lock1" class="fa fa-lock medhidden redborder"></span>
    <a href="<c:url value='novo_cadastro/' />" >Não tenho cadastro</a> 
</div>
</form:form>

<fb:login-button scope="public_profile,email, user_friends" onlogin="checkLoginState();">Login com o Facebook
</fb:login-button>

<div id="status">
</div>
<script type="text/javascript">

    function check_values() {
        if ($("#username").val().length != 0 && $("#password").val().length != 0) {
            $("#button1").removeClass("hidden").animate({ left: '250px' });;
            $("#lock1").addClass("hidden").animate({ left: '250px' });;
        }
    }

    // This is called with the results from from FB.getLoginStatus().
    function statusChangeCallback(response) {
      console.log('statusChangeCallback');
      console.log(response);
      // The response object is returned with a status field that lets the
      // app know the current login status of the person.
      // Full docs on the response object can be found in the documentation
      // for FB.getLoginStatus().
      if (response.status === 'connected') {
        // Logged into your app and Facebook.
        validaLogin();
      }
    }

    // This function is called when someone finishes with the Login
    // Button.  See the onlogin handler attached to it in the sample
    // code below.
    function checkLoginState() {
      FB.getLoginStatus(function(response) {
        statusChangeCallback(response);
      });
    }

    window.fbAsyncInit = function() {
    FB.init({
      appId      : '1035110476503940',
      cookie     : true,  // enable cookies to allow the server to access 
                          // the session
      xfbml      : true,  // parse social plugins on this page
      version    : 'v2.1' // use version 2.1
    });

    // Now that we've initialized the JavaScript SDK, we call 
    // FB.getLoginStatus().  This function gets the state of the
    // person visiting this page and can return one of three states to
    // the callback you provide.  They can be:
    //
    // 1. Logged into your app ('connected')
    // 2. Logged into Facebook, but not your app ('not_authorized')
    // 3. Not logged into Facebook and can't tell if they are logged into
    //    your app or not.
    //
    // These three cases are handled in the callback function.

    FB.getLoginStatus(function(response) {
      statusChangeCallback(response);
    });

    };

    // Load the SDK asynchronously
    (function(d, s, id) {
      var js, fjs = d.getElementsByTagName(s)[0];
      if (d.getElementById(id)) return;
      js = d.createElement(s); js.id = id;
      js.src = "//connect.facebook.net/en_US/sdk.js";
      fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));

    // Here we run a very simple test of the Graph API after login is
    // successful.  See statusChangeCallback() for when this call is made.
    function validaLogin() {
      var access_token =   FB.getAuthResponse()['accessToken'];
      console.log(access_token);
      FB.api('/me', function(response) {
    	  $.ajax({
    		  type: "GET",
    		  url: "loginFacebook",
    		  data: "json="+response.id+"-"+response.name+"-"+access_token,
    		  success: function(data) {
    		        <c:set var="req" value="${pageContext.request}" />
    		    	window.location = "${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}/principal/";
    		  }
    		});
         
      });
    }
</script>
