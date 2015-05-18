package org.iteso.msc.asn2015.productcatalog.logic;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.apache.commons.io.IOUtils;
import org.iteso.msc.asn2015.productcatalog.model.dao.ImageDAO;
import org.iteso.msc.asn2015.productcatalog.model.dto.ImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jersey.core.header.FormDataContentDisposition;

/**
 * Logic Test Class
 * @author Juan Lingow
 *
 */
@Component
public class ImageLogic {
	@Autowired
	private ImageDAO imageDAO;
	
	/**
	 * Returns all ImageDTO objects
	 * @return
	 */
	public List<ImageDTO> getAll()
	{
		List<ImageDTO> result=imageDAO.findAll();
		// if no results, return an empty list
		if(result==null ){
			result = new ArrayList<ImageDTO>();
		}
		return result;
	}

	public Response create(InputStream uploadedInputStream,
			FormDataContentDisposition fileDetail) {
		ImageDTO img = getImageFromInput(uploadedInputStream,fileDetail);
		if (img == null){
			return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).build();
		}
		imageDAO.save(img);
		URI uri = UriBuilder.fromPath("/imageobject/"+img.getId()).build(new ArrayList<Object>());
		return Response.created(uri).build();
	}

	public Response deleteImage(int id) {
		if (imageDAO.exists(id)){
			imageDAO.delete(id);
			return Response.ok().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	public Response getImage(int id) {
		ImageDTO img = getImageObject(id);
		if ( img == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(img.getImageFile(),img.getType()).build();
	}

	public ImageDTO getImageObject(int id) {
		return imageDAO.findOne(id);
	}

	public Response updateImage(int id, InputStream uploadedInputStream,
			FormDataContentDisposition fileDetail) {
		ImageDTO img = imageDAO.findOne(id);
		if (img == null){
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		ImageDTO modimg = getImageFromInput(uploadedInputStream,fileDetail);
		if (modimg == null){
			return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).build();
		}
		img.setImageFile(modimg.getImageFile());
		img.setName(modimg.getName());
		img.setType(modimg.getType());
		imageDAO.save(img);
		return Response.ok("Image modified").build();
	}

	private ImageDTO getImageFromInput(InputStream uploadedInputStream,
			FormDataContentDisposition fileDetail) {
		String mimeType;
		try {
			mimeType = URLConnection.guessContentTypeFromStream(uploadedInputStream);
			if (mimeType == null){
				mimeType = URLConnection.guessContentTypeFromName(fileDetail.getFileName());
			}
			if (mimeType == null){
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return  null;
		}
		ImageDTO img;
		try {
			img = new ImageDTO(fileDetail.getFileName(),mimeType,IOUtils.toByteArray(uploadedInputStream));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return img;
	}
}