package org.iteso.msc.asn2015.productcatalog.logic;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.iteso.msc.asn2015.productcatalog.model.dao.ImageDAO;
import org.iteso.msc.asn2015.productcatalog.model.dto.ImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

	public Integer create(byte[] bs, String fileName,
			String type) {
		ImageDTO img = new ImageDTO(fileName,type,bs);
		imageDAO.save(img);
		return img.getId();
	}

	public ImageDTO get(Integer id) {
		return imageDAO.findOne(id);
	}
}