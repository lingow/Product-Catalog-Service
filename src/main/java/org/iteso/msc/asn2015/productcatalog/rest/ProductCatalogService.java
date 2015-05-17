package org.iteso.msc.asn2015.productcatalog.rest;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;

import org.apache.commons.io.IOUtils;
import org.iteso.msc.asn2015.productcatalog.logic.ImageLogic;
import org.iteso.msc.asn2015.productcatalog.model.dao.ImageDAO;
import org.iteso.msc.asn2015.productcatalog.model.dto.ImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

/**
 * Test 
 * @author Juan Lingow
 * @see ImageLogic
 * @see ImageDAO
 * @see ImageDTO
 */
@Component
@Path("/")
@Scope("request")
public class ProductCatalogService {
	
	@Autowired
	ImageLogic imageLogic;
	
	/**
	 * Returns all ImageDTO objects as JSON 
	 * @return List<ImageDTO>
	 */
	@GET
	@Path("/images")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ImageDTO> getAll()
	{
		return imageLogic.getAll();
	}
	
	@POST
	@Path("/image")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response addImage(
        @FormDataParam("file") InputStream uploadedInputStream,
        @FormDataParam("file") FormDataContentDisposition fileDetail) {
		Integer id;
		id = imageLogic.create(uploadedInputStream,fileDetail);
		if (id < 0){
			return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).build();
		}
		URI uri = UriBuilder.fromPath("/image/"+id).build(new ArrayList<Object>());
		return Response.created(uri).build();
	}


	@GET
	@Path("/imageobject/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ImageDTO getImage(@PathParam("id") String id){
		return imageLogic.get(Integer.parseInt(id));
	}
	
	@GET
	@Path("/image/{id}")
	public Response getImageData(@PathParam("id") String id){
		ImageDTO img = imageLogic.get(Integer.parseInt(id));
		if ( img == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(img.getImageFile(),img.getType()).build();
	}

}
