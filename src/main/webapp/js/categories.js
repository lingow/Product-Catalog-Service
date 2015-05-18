/**
 * 
 */
$(document).ready(function(){
	$.get("/rest/categories",function(data){
		data.forEach(function(cat,c,categories){
			$("#categoriesTable > tbody")
				.append($("<tr>")
					.append($("<td>").text(cat.id))
					.append($("<td>").text(cat.name))
					.append($("<td>").text(cat.description)
				)
			)
		});
	});
});