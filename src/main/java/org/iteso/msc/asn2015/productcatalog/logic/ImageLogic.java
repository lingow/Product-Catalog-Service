package org.iteso.msc.asn2015.productcatalog.logic;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.iteso.msc.asn2015.productcatalog.model.dao.ImageDAO;
import org.iteso.msc.asn2015.productcatalog.model.dto.ImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jersey.core.header.FormDataContentDisposition;

/**
 * Logic Test Class
 * @author Daniel Rodriguez Millan drm@chocodev.com
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
		// if no results, lets add one :)
		if(result==null ){
			result = new ArrayList<ImageDTO>();
		}
		return result;
	}

	public ImageDTO get(Integer id) {
		return  imageDAO.findOne(id);
	}

	public Integer create(InputStream uploadedInputStream,
			FormDataContentDisposition fileDetail) {
		String mimeType;
		try {
			mimeType = URLConnection.guessContentTypeFromStream(uploadedInputStream);
			if (mimeType == null){
				mimeType = URLConnection.guessContentTypeFromName(fileDetail.getFileName());
			}
			if (mimeType == null){
				return  - Response.Status.UNSUPPORTED_MEDIA_TYPE.ordinal();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return  - Response.Status.UNSUPPORTED_MEDIA_TYPE.ordinal();
		}
		ImageDTO img;
		try {
			img = new ImageDTO(fileDetail.getName(),mimeType,IOUtils.toByteArray(uploadedInputStream));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return - Response.Status.UNSUPPORTED_MEDIA_TYPE.ordinal();
		}
		imageDAO.save(img);
		return(img.getId());
	}
}