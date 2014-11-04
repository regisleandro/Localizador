<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<header>
Ol� ${usuario.user}!
</header>
<button id="btnEnviar">Minha Localiza��o </button></p>
<button id="btnPosition" style="visibility:hidden">Ver Mapa </button></p>
<div id="mapholder"></div>


<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script>
var username = "${usuario.user}" 

function getLocation()
  {
  if (navigator.geolocation){
    navigator.geolocation.getCurrentPosition(getCoordinates,showError);
    }
  else{x.innerHTML="Geolocation is not supported by this browser.";}
  }

function getCoordinates(position){
	console.log(position.coords.latitude + " " + position.coords.longitude);
	sendMessage(position);
}

function showPosition(lat, lon){
  latlon=new google.maps.LatLng(lat, lon)
  mapholder=document.getElementById('mapholder') 
  mapholder.style.height='350px';
  mapholder.style.width='100%';

  var myOptions={
  	center:latlon,zoom:14,
  	mapTypeId:google.maps.MapTypeId.ROADMAP,
  	mapTypeControl:false,
  	navigationControlOptions:{style:google.maps.NavigationControlStyle.SMALL}
  };
  var map=new google.maps.Map(document.getElementById("mapholder"),myOptions);
  var marker=new google.maps.Marker({position:latlon,map:map,title:"Eu estou aqui!"});
  
 }
  
function showError(error){
  switch(error.code) 
    {
    case error.PERMISSION_DENIED:
      x.innerHTML="User denied the request for Geolocation."
      break;
    case error.POSITION_UNAVAILABLE:
      x.innerHTML="Location information is unavailable."
      break;
    case error.TIMEOUT:
      x.innerHTML="The request to get user location timed out."
      break;
    case error.UNKNOWN_ERROR:
      x.innerHTML="An unknown error occurred."
      break;
    }
  }
  
  
  function log(msg) {
	   if (typeof console !== "undefined") console.log(msg); 
   } 
   if ('WebSocket' in window) { 
	 var websocket = new WebSocket("ws://" + document.location.host + "/Localizador/websocket?username=" + username); 
   } else if ('MozWebSocket' in window) { 
	   var websocket = new WebSocket("ws://" + document.location.host + "/Localizador/websocket?username=" + username); 
    } else {
		   alert("Browser n�o suporta WebSocket"); 
	}
    if (websocket != undefined) { 
	   websocket.onopen = function() { 
		   log("Conectou com sucesso");
		}; 
		websocket.onclose = function() { 
			log("Desconectou com sucesso"); 
			alert("Desconectou com sucesso"); 
		}; 
		websocket.onerror = function(e) { 
			log("Aconteceu um erro");
			log(e);
		}; 
		websocket.onmessage = function(data) {
			console.log("Recebeu " + data.data);
			var btnPosition = document.getElementById("btnPosition");
			var position = data.data;
			if (position != btnPosition && position !== '"${usuario.user}" se conectou.'){
				
				posicao = position.split("|");
				console.log('posicao' + posicao);
				lat = posicao[1];
				lon = posicao[2];
				showPosition(lat,lon);
/*				
				btnPosition.style.visibility = "visible";
				btnPosition.onclick = function(){
					posicao = position.split("|");
					console.log('posicao' + posicao);
					lat = posicao[1];
					lon = posicao[2];
					showPosition(lat,lon);
				};
*/			
			}
			
		}; 
		function sendMessage(position) { 
			  lat=position.coords.latitude;
			  lon=position.coords.longitude;
			websocket.send("|"+lat+"|"+lon); 
		} 
		document.getElementById("btnEnviar").onclick = function() {
			getLocation();			 
			return false;
		}; 
	} 
</script>
