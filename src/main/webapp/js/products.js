/**
 * 
 */
$(document).ready(function(){
	
	$.getScript("js/tablefunctions.js",function(){
		function refreshTable(){
			$("#productsTable tbody").empty();
			$.ajax({
				  url: "/rest/products",
				  success: function(data){
						data.forEach(function(prod,p,products){
							$("#productsTable > tbody")
								.append($("<tr>")
									.append($("<td>").text(prod.id))
									.append(textCell(prod.name,"name",prod.id))
									.append(textCell(prod.description,"description",prod.id))
									.append(categoryCell(prod.category,"category",prod.id))
									.append(textCell(prod.currency,"currency",prod.id))
									.append(textCell(prod.price,"price",prod.id))
									.append(imageCell(prod.image,"image",prod.id))
									.append(actionCell("product",prod.id,refreshTable))
								)
						});
					},
				  cache: false
				});
		}
		refreshTable();
	});
});

