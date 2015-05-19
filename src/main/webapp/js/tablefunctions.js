/**
 * 
 */
function textCell(val,name,id){
		var x = $("<td>")
			.append($("<div>")
				.attr("class","notediting_"+id)
				.text(val)
			)
			.append($("<div>")
				.attr("class","editing_"+id)
				.append($("<input>")
					.attr("type","text")
					.attr("id",name + "_" + id)
					.attr("value",val)
					.attr("class","edit_" + name)
				).hide()
			)
		return x;
	}
	
	function categoryCell(cat,name,id){
		var x = $("<td>")
			.append($("<div>")
				.attr("class","notediting_"+id)
				.text(cat.name)
			)
			.append($("<div>")
				.attr("class","editing_"+id)
				.append($("<input>")
					.attr("type","text")
					.attr("id",name + "_" + id)
					.attr("value",cat.id)
					.attr("class","edit_" + name)
				).hide()
			)
		return x;
	}
	
	function imageCell(image,name,id){
		var x = $("<td>")
			.append($("<div>")
				.attr("class","notediting_"+id)
				.append((image == null)?"null":$("<img>")
						.attr("id","img_"+image.id)
						.attr("src",image.imageUrl)
						.attr("style","height:auto; width:auto; max-width:100px; max-height:100px;"))
			)
			.append($("<div>")
				.attr("class","editing_"+id)
				.append($("<input>")
					.attr("type","text")
					.attr("id",name + "_" + id)
					.attr("value",image.id)
					.attr("class","edit_" + name)
				).hide()
			)
		return x;
	}
	
	function startEditing(id){
		$(".notediting_" + id ).hide();
		$(".editing_" + id).show();
	}
	function stopEditing(id){
		$(".notediting_" + id ).show();
		$(".editing_" + id).hide();
	}
	
	function actionCell(type,id,callback){
		var ret = $("<td>")
			.append($("<a>")
				.attr("href","#")
				.attr("class","btn btn-info notediting_"+ id)
				.text("Edit")
				.click(function() {
					startEditing(id);
				})
			)
			.append($("<a>")
				.attr("href","#")
				.attr("class","btn btn-warning editing_"+ id)
				.text("Save")
				.click(function() {
					updateRow(type,id,callback);
				}).hide()
			)
			.append($("<a>")
				.attr("href","#")
				.attr("class","btn btn-danger notediting_"+id)
				.text("Delete")
				.click(function() {
					sendDelete(type,id,callback);
				})
			)
			.append($("<a>")
				.attr("href","#")
				.attr("class","btn btn-info editing_"+id)
				.text("Cancel")
				.click(function() {
					stopEditing(id);
				}).hide()
			)
		return ret;
	}
	
	function updateRow(type,id,callback){
		switch(type){
		case "product":
			name = $("#name_"+id).val();
			description = $("#description_"+id).val();
			imageID = $("#image_"+id).val();
			categoryId = $("#category_"+id).val();
			price = $("#price_"+id).val();
			currency = $("#currency_"+id).val();
			$.post("/rest/product/"+id,
					{
					"name":name,
					"description":description,
					"imageId":imageID,
					"currency":currency,
					"price":price,
					"categoryId":categoryId,
					},function(a,b,c){
						callback();
					});
			break;
		case "category":
			name = $("#name_"+id).val();
			description = $("#description_"+id).val();
			imageID = $("#image_"+id).val();
			$.post("/rest/category/"+id,
					{
					"name":name,
					"description":description,
					"imageId":imageID
					},function(a,b,c){
						callback();
					});
			break;
		case "image":
			var data = new FormData();
			$.each($("#imgUpload_"+id)[0].files, function(i, file) {
			    data.append("file", file);
			});
			jQuery.ajax({
			    url: "/rest/image/"+id,
			    data: data,
			    cache: false,
			    contentType: false,
			    processData: false,
			    type: "POST",
			    success: function(data){
			        callback();
			    }
			});
		default:
		}
		stopEditing();
	}
	
	function sendDelete(type,id,callback){
		$.ajax({
			url: "/rest/"+type+"/"+id,
			type: 'DELETE',
			success: function(response) {
					callback();
			},
			error: function(response){
				alert("Failed to Delete "+type+" "+id+" Do other objects depend on this one?");
			}
		});
	}