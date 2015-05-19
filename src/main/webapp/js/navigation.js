/**
 * 
 */
$(document).ready(function(){	
	function  setActiveNavButton(elem){
		$("#navButtonList li").attr("class","inactive")
		$(elem).attr("class","active");
	}
	
	function loadNav(element,source){
		$.ajax({
		    url: source,
		    cache: false,
		    dataType: "html",
		    success: function(data) {
		        $(element).html(data);
		    }
		});
	}
	$('#navHome').click(function(){
		setActiveNavButton(this);
		loadNav("#contents","/home.html");
	})
	$('#navCategories').click(function(){
		setActiveNavButton(this);
		loadNav("#contents","/categories.html");
	})
	$('#navProducts').click(function(){
		setActiveNavButton(this);
		loadNav("#contents","/products.html");
	})
	$('#navImages').click(function(){
		setActiveNavButton(this);
		loadNav("#contents","/images.html");
	})
	$('#navHome').click();
})