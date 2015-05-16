package org.iteso.msc.asn2015.productcatalog.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.iteso.msc.asn2015.productcatalog.logic.ProductLogic;
import org.iteso.msc.asn2015.productcatalog.model.dao.TestDAO;
import org.iteso.msc.asn2015.productcatalog.model.dto.TestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Test 
 * @author Juan Lingow
 * @see ProductLogic
 * @see TestDAO
 * @see TestDTO
 */
@Component
@Path("/products")
@Scope("request")
public class ProductCatalogService {
	
	@Autowired
	ProductLogic productLogic;
	
	/**
	 * Returns all TestDTO objects as JSON 
	 * @return List<TestDTO>
	 */
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TestDTO> getAll()
	{
		return productLogic.getAll();
	}
	
}
