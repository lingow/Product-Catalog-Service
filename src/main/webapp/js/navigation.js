/**
 * 
 */
$(document).ready(function(){	
	function  setActiveNavButton(elem){
		$("#navButtonList li").attr("class","inactive")
		$(elem).attr("class","active");
	}
	$('#navHome').click(function(){
		setActiveNavButton(this);
		$("#contents").load("/home.html")
	})
	$('#navCategories').click(function(){
		setActiveNavButton(this);
		$("#contents").load("/categories.html")
	})
	$('#navProducts').click(function(){
		setActiveNavButton(this);
		$("#contents").load("/products.html")
	})
	$('#navImages').click(function(){
		setActiveNavButton(this);
		$("#contents").load("/images.html")
	})
	$('#navHome').click();
})