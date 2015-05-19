package org.iteso.msc.asn2015.productcatalog.logic;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.apache.commons.io.IOUtils;
import org.iteso.msc.asn2015.productcatalog.model.dao.CategoryDAO;
import org.iteso.msc.asn2015.productcatalog.model.dao.ImageDAO;
import org.iteso.msc.asn2015.productcatalog.model.dao.ImageMetadataDAO;
import org.iteso.msc.asn2015.productcatalog.model.dto.CategoryDTO;
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
public class CategoryLogic {
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ImageMetadataDAO imageMetadataDAO;

	public Response addCategory(String name, String description, int imageId) {
		if (name == null){
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Name for category should be provided").build();
		}
		CategoryDTO cat = new CategoryDTO(name);
		ImageMetadataDTO img = imageMetadataDAO.findOne(imageId);
		cat.setDescription(description);
		cat.setImage(img);
		categoryDAO.save(cat);
		URI uri = UriBuilder.fromPath("/category/"+cat.getId()).build(new ArrayList<Object>());
		return Response.created(uri).build();
	}

	public CategoryDTO getCategory(int parseInt) {		
		return categoryDAO.findOne(parseInt);
	}

	public Response updateCategory(int id, String name, String description,
			int imageId) {
		if (name == null){
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Name for category should be provided").build();
		}
		ImageMetadataDTO img = imageMetadataDAO.findOne(imageId);
		CategoryDTO tmpCat = categoryDAO.findOne(id);
		if (tmpCat == null){
			return Response.status(Response.Status.NOT_FOUND)
					.entity("Category with id " + id + " not found").build();
		}
		tmpCat.setName(name);
		tmpCat.setDescription(description);
		tmpCat.setImage(img);
		categoryDAO.save(tmpCat);
		return Response.ok("Category modified").build();
	}

	public Response deleteCategory(int id) {
		if (categoryDAO.exists(id)){
			categoryDAO.delete(id);
			return Response.ok("Category Deleted").build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	public List<CategoryDTO> getCategories() {
		List<CategoryDTO> result=categoryDAO.findAll();
		// if no results, return an empty list
		if(result==null ){
			result = new ArrayList<CategoryDTO>();
		}
		return result;
	}
	
}