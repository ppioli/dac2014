//AMBIENTE

var store;
var selection = [];
var selectionPane;
var reg;
var dom;
var domConst;
var gal;
var isSelection;
var tableImg = document.getElementById("imagenes");
var tableSel = document.getElementById("seleccion");
var lightBox;
require(["dojo/parser", "dijit/Dialog", 
	         "dijit/form/Form", 
	         "dijit/form/Button", 
	         "dijit/form/ValidationTextBox",
	         "dijit/layout/BorderContainer", 
	         "dijit/layout/ContentPane",
	         "dojo/store/JsonRest",
	         "dijit/registry",
	         "dojo/dom",
	         "dojo/dom-construct",
	         "dojox/image/Lightbox"],
function(ap, ad, af, ab, av, abb, ContentPane, JsonRest, registry, d, dc, lb){
	store = new JsonRest({
	   target: "/json/json/imagens",
	   accepts: "application/json"
	});
	reg = registry;
	selectionPane = new ContentPane();
	selectionPane.set({region:'bottom', style:{height: "160px"}});
	var imagenThumbs = new ContentPane();
	imagenThumbs.set({region:'bottom', style:{height: "120px"}});
	imagenThumbs.setContent("<table><td id=\"seleccion\">asd</td></table>");
	var botones = new ContentPane();
	botones.set({region:'bottom', style:{height: "40px"}});
	botones.setContent(
			'<button data-dojo-type="dijit/form/Button" type="button" id="ok">OK</button>' +
			'<button data-dojo-type="dijit/form/Button" type="button" data-dojo-props="onClick:function(){gal.hide();}"' +
            'id="cancel">Cancel</button>'
	);
	selectionPane.addChild(imagenThumbs);
	selectionPane.addChild(botones);
	lightBox = new lb();
	
});

function populate(){
	
  	store.query("?query=asdasdas", {}).then(function(results){
		for(var imagen in results){
		
			var td = document.createElement("TR");
			td.id="img_"+results[imagen].id;
			td.onclick=select;
			console.log(results[imagen].id);
			td.innerHTML="<td><img src=\"imagens/"+results[imagen].id+"/image?thumb=true\"/></td>" +
						"<td>nombre:"+results[imagen].nombre+"</td>";
			
			document.getElementById("imagenes").appendChild(td);
			
		}    
	})
}
function select(event){
	
	var target = event.target;
	var a = event.target;
	while(a.onclick != select) a=a.parentNode;
	a = a.id;
	
	var b = a.indexOf("_");
	a = a.substring(b+1, a.length);
	
	if(isSelection){
		document.getElementById("imagenes").removeChild(document.getElementById(a.id));
		selection.push(a);
		
		var tr = document.createElement("TD");
		tr.id="img_"+a;
		tr.onclick=remove;
		
		tr.innerHTML="<img src=\"imagens/"+a+"/image?thumb=true\"/>";
		
		document.getElementById("seleccion").appendChild(tr);
	}else{
		var url = "imagens/"+a+"/image";
		lightBox = new dojox.image.LightboxDialog().startup();
		lightBox.show({ href: url, title:"My Remote Image"});
	}
	
}
function remove(event){
	
}
function show(){
	isSelection = false;
	gal = reg.byId("galeria");
	populate();
	gal.draggable=false;
	gal.show();
}
function startSelection(){
	isSelection = true;
	gal = reg.byId("galeria");
	gal.draggable=false;
	populate();
	reg.byId("galeria_content").addChild(selectionPane);
	gal.show();
}
function endSelection(){
	
}