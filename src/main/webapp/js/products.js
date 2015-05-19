/**
 * 
 */
$(document).ready(function(){
	$.get("/rest/categories",function(data){
		data.forEach(function(cat){
			$('#category').append($('<option>').attr("value",cat.id).text(cat.description));//);
		});
	});
	
	$.get("/rest/currencies",function(data){
		data.forEach(function(cur){
			$('#currency').append($('<option>').text(cur));
		});
	});
	
	$.getScript("js/tablefunctions.js",function(){
		function refreshTable(){
			$("#productsTable tbody").empty();
			categoryVal = $('#category').val();
			currencyVal = $('#currency').val();
			$.ajax({
				  url: "/rest/products?categoryId=" +categoryVal+"&currency="+currencyVal ,
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
						$.get("/rest/images",function(data){
							data.forEach(function(img){
								$(".edit_image").each(function(){
									var x = $(this).attr("value");
									$(this)
										.append($("<option>")
											.attr("value",img.id)
											.text(img.id)
										)
									$(this).val(x);
								})
							})
						});
						$.get("/rest/categories",function(data){
							data.forEach(function(cat){
								$(".edit_category").each(function(){
									var x = $(this).attr("value");
									$(this)
										.append($("<option>")
											.attr("value",cat.id)
											.text(cat.id)
										)
									$(this).val(x);
								})
							})
						});
						$(".edit_currency").each(function(){
							var val = $(this).attr("value");
							var id = $(this).attr("id");
							var cl = $(this).attr("class");
							$(this).replaceWith($("<select>")
									.attr("id",id)
									.attr("class",cl)
									.attr("value",val)
							)
						})
						$.get("/rest/currencies",function(data){
							data.forEach(function(curr){
								$(".edit_currency").each(function(){
									var x = $(this).attr("value");
									$(this)
										.append($("<option>")
											.attr("value",curr)
											.text(curr)
										)
									$(this).val(x);
								})
							})
						});
					},
				  cache: false
				});
		}
		refreshTable();
		$('#category').change(refreshTable);
		$('#currency').change(refreshTable);
		$("#newProductForm").submit(function(){
			name = $("#newProductName").val();
			description = $("#newProductDescription").val();
			imageID = $("#newProductImage").val();
			categoryId = $("#newProductCategory").val();
			price = $("#newProductPrice").val();
			currency = $("#newProductCurrency").val();
			$.post("/rest/product",
					{
					"name":name,
					"description":description,
					"imageId":imageID,
					"currency":currency,
					"price":price,
					"categoryId":categoryId,
					},function(a,b,c){
						refreshTable();
					});
			return false;
		});
	});
});

