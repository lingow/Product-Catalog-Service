/**
 * 
 */
$(document).ready(function(){
	
	$.getScript("js/tablefunctions.js",function(){
		function refreshTable(){
			$("#imageTable tbody").empty();
			$.ajax({
				url:"/rest/images",
				success: function(data){
					data.forEach(function(img,i,images){
						$("#imageTable > tbody")
							.append($("<tr>")
								.append($("<td>").text(img.id))
								.append($("<td>").text(img.name))
								.append($("<td>").text(img.type))
								.append(imageCell(img,"imgUpload",img.id))
								.append(actionCell("image",img.id,refreshTable))
							);
					});
					$(".edit_imgUpload").each(function(){
						var cl = $(this).attr("class");
						var id = $(this).attr("id");
						$(this).replaceWith($("<input>")
							.attr("type","file")
							.attr("class",cl + " form-control input-file" )
							.attr("id",id)
						);
					})
				},
				cache:false
			});
		};
		refreshTable();
		$("#newImageForm").submit(function(){
			var data = new FormData();
			$.each($("#newImage")[0].files, function(i, file) {
			    data.append("file", file);
			});
			jQuery.ajax({
			    url: "/rest/image",
			    data: data,
			    cache: false,
			    contentType: false,
			    processData: false,
			    type: "POST",
			    success: function(data){
			    	refreshTable();
			    }
			});
			return false;
		});
	});
	
	
});


/*
$("<td>")
.append($("<img>")
	.attr("id","img_"+img.id)
	.attr("src",img.imageUrl)
	.attr("style","height:auto; width:auto; max-width:100px; max-height:100px;")
)
.append($("<div>")
	.append($("<form>")
		.attr("action","rest/image/1")
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
	
	function editImage(imgId) {
		$("#img_"+imgId).hide();
		$("#form_"+imgId).show();
	}
	
	function deleteImage(imgId) {
		$.ajax({
			url: "rest/image/"+imgId,
			type: 'DELETE',
			success: function(response) {
			}
		});
	}
	
	
)
*/