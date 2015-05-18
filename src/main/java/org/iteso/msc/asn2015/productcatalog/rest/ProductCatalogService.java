package org.iteso.msc.asn2015.productcatalog.rest;

import java.awt.BufferCapabilities.FlipContents;
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
import javax.ws.rs.DELETE;
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
		return imageLogic.create(uploadedInputStream,fileDetail);
	}


	@GET
	@Path("/imageobject/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ImageDTO getImageObject(@PathParam("id") String id){
		return imageLogic.getImageObject(Integer.parseInt(id));
	}
	
	@GET
	@Path("/image/{id}")
	public Response getImage(@PathParam("id") String id){
		return imageLogic.getImage(Integer.parseInt(id));
	}

	@DELETE
	@Path("/image/{id}")
	public Response deleteImage(@PathParam("id") String id){
		return imageLogic.deleteImage(Integer.parseInt(id));
	}
	
	@POST
	@Path("/image/{id}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response updateImage(
			@PathParam("id") String id,
			@FormDataParam("file") InputStream uploadedInputStream,
	        @FormDataParam("file") FormDataContentDisposition fileDetail){
		return imageLogic.updateImage(Integer.parseInt(id),uploadedInputStream,fileDetail);
	}
	
}
