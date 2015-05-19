/**
 * 
 */
$(document).ready(function(){
	$.get("/rest/products",function(data){
		data.forEach(function(prod,p,products){
			$("#productsTable > tbody")
				.append($("<tr>")
					.append($("<td>").text(prod.id))
					.append($("<td>").text(prod.name))
					.append($("<td>").text(prod.description))
					.append($("<td>").text(prod.imageUrl))
					.append($("<td>").text(prod.category.Id))
					.append($("<td>").text(prod.currency))
					.append($("<td>").text(prod.price))
			)
		});
	});
});

