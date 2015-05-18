/**
 * 
 */
$(document).ready(function(){
	$.get("/rest/images",function(data){
		data.forEach(function(img,i,images){
			$("#imageTable > tbody")
				.append($("<tr>")
					.append($("<td>").text(img.id))
					.append($("<td>").append($("<span>").addClass("nameText").text(img.name)))
					.append($("<td>").append($("<span>").addClass("typeText").text(img.type)))
					.append($("<td>")
						.append($("<img>")
							.attr("id","img_"+img.id)
							.attr("src","data:"+img.type+";base64,"+img.imageFile)
							.attr("style","height:auto; width:auto; max-width:100px; max-height:100px;")
						)
						.append($("<div>")
							.append($("<form>")
								.attr("action","rest/image")
								.attr("method","post")
								.attr("enctype","multipart/form-data")
								.append($("<p>")
										.append($("<input>")
											.attr("type","file")
											.attr("name","file")
											.attr("size","50")
										)
								)
								.append($("<input>")
									.attr("type","submit")
									.attr("value","Upload It")
								)
							)
							.attr("class","editForm")
							.attr("id","form_"+img.id)
							.hide()
						)
					)
					.append($("<td>")
							.append($("<a>")
								.attr("href","#")
								.attr("class","btn btn-info")
								.append($("<i>")
									.attr("class","glyphicon glyphicon-map-marker"))
								.text("Edit")))
								.click(function() {
									editImage(img.id);
								})
					.append($("<td>")
							.append($("<a>")
								.attr("href","#")
								.attr("class","btn btn-info")
								.append($("<i>")
									.attr("class","glyphicon glyphicon-map-marker"))
								.text("Delete")))
								.click(function() {
									deleteImage(img.id);
								})
				);
		});
	});
	
	function editImage(imgId) {
		$("#img_"+imgId).hide();
		$("#form_"+imgId).show();
	}
	
	function deleteImage(imgId) {
		$.ajax({
			url: "rest/image"+imgId,
			type: 'DELETE',
			success: function(response) {
			}
		});
	}
	
});