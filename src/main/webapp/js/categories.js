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
					.append($("<td>").text(cat.description))
					.append($("<td>")
						.append((cat.image == null)?"null":$("<img>")
							.attr("id","img_"+cat.image.id)
							.attr("src",cat.image.imageUrl)
							.attr("style","height:auto; width:auto; max-width:100px; max-height:100px;")
						)
					)
				)
		});
	});
});