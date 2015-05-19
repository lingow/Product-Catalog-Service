/**
 * 
 */
$(document).ready(function(){
	$.getScript("js/tablefunctions.js",function(){
		function refreshTable(){
			$("#categoriesTable tbody").empty();
			$.ajax({
				  url: "/rest/categories",
				  success: function(data){
						data.forEach(function(cat,c,categories){
							$("#categoriesTable > tbody")
								.append($("<tr>")
									.append($("<td>").text(cat.id))
									.append(textCell(cat.name,"name",cat.id))
									.append(textCell(cat.description,"description",cat.id))
									.append(imageCell(cat.image,"image",cat.id))
									.append(actionCell("category",cat.id,refreshTable))
								)
						});
					},
				  cache: false
				});
		}
		refreshTable();
	});
});