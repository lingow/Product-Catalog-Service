/**
 * 
 */
$(document).ready(function(){
	$.get("/rest/images",function(data){
		data.forEach(function(img,i,images){
			$("#imageTable > tbody")
				.append($("<tr>")
					.append($("<td>").text(img.id))
					.append($("<td>").text(img.name))
					.append($("<td>").text(img.type))
					.append($("<td>")
						.append($("<img>")
							.attr("src","data:"+img.type+";base64,"+img.imageFile)
							.attr("style","height:auto; width:auto; max-width:100px; max-height:100px;")
						)
					)
					.append($("<td>")
							.append($("<a>")
								.attr("href","#")
								.attr("class","btn btn-info")
								.append($("<i>")
									.attr("class","glyphicon glyphicon-map-marker"))
								.text("Hello")))
				);
		});
	});
});