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
import org.iteso.msc.asn2015.productcatalog.model.dao.ImageMetadataDAO;
import org.iteso.msc.asn2015.productcatalog.model.dto.ImageDTO;
import org.iteso.msc.asn2015.productcatalog.model.dto.ImageMetadataDTO;
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
	private ImageMetadataDAO imageMetadataDAO;
	
	@Autowired
	private ImageDAO imageDAO;
	
	/**
	 * Returns all ImageMetadataDTO objects
	 * @return
	 */
	public List<ImageMetadataDTO> getAll()
	{
		List<ImageMetadataDTO> result=imageMetadataDAO.findAll();
		// if no results, return an empty list
		if(result==null ){
			result = new ArrayList<ImageMetadataDTO>();
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
		ImageMetadataDTO imgMetadata = new ImageMetadataDTO(fileDetail.getFileName(),"/rest/image/" + img.getId());
		imageMetadataDAO.save(imgMetadata);
		URI uri = UriBuilder.fromPath("/imageobject/"+imgMetadata.getId()).build(new ArrayList<Object>());
		return Response.created(uri).build();
	}

	public Response deleteImage(int id) {
		ImageMetadataDTO img = imageMetadataDAO.findOne(id);
		if (img != null){
			int fileId = getImgIdfromMetadata(img);
			imageDAO.delete(fileId);
			imageMetadataDAO.delete(id);
			return Response.ok("Image Deleted").build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	public int getImgIdfromMetadata(ImageMetadataDTO img){
		return Integer.parseInt(img.getImageUrl().replaceFirst("/rest/image/", ""));
	}

	public Response getImage(int id) {
		ImageDTO img = imageDAO.findOne(id);
		if ( img == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(img.getImageFile(),img.getType()).build();
	}

	public ImageMetadataDTO getImageObject(int id) {
		return imageMetadataDAO.findOne(id);
	}

	public Response updateImage(int id, InputStream uploadedInputStream,
			FormDataContentDisposition fileDetail) {
		ImageMetadataDTO imgMetadata = imageMetadataDAO.findOne(id);
		if (imgMetadata == null){
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		ImageDTO img = imageDAO.findOne(getImgIdfromMetadata(imgMetadata));
		if (img == null){
			return Response.serverError().entity("Cant find image in " + imgMetadata.getImageUrl()).build();
		}
		ImageDTO modimg = getImageFromInput(uploadedInputStream,fileDetail);
		if (modimg == null){
			return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).build();
		}
		img.setImageFile(modimg.getImageFile());
		img.setType(modimg.getType());
		imgMetadata.setName(fileDetail.getFileName());
		imageDAO.save(img);
		imageMetadataDAO.save(imgMetadata);
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
			img = new ImageDTO(mimeType,IOUtils.toByteArray(uploadedInputStream));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return img;
	}
}