package org.iteso.msc.asn2015.productcatalog.logic;

import java.util.ArrayList;
import java.util.List;

import org.iteso.msc.asn2015.productcatalog.model.dao.TestDAO;
import org.iteso.msc.asn2015.productcatalog.model.dto.TestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Logic Test Class
 * @author Daniel Rodriguez Millan drm@chocodev.com
 *
 */
@Component
public class ProductLogic {
	@Autowired
	private TestDAO testDAO;
	
	/**
	 * Returns all TestDTO objects
	 * @return
	 */
	public List<TestDTO> getAll()
	{
		List<TestDTO> result=testDAO.findAll();
		// if no results, lets add one :)
		if(result==null || result.isEmpty())
		{
			TestDTO testDTO=new TestDTO();
			testDTO.setTestName("ElementName");
			testDTO.setTestDescription("ElementDescription");
			testDAO.save(testDTO);
			result=testDAO.findAll();
		}
		return testDAO.findAll();
	}
}
