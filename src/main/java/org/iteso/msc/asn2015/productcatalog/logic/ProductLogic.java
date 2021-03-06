package org.iteso.msc.asn2015.productcatalog.logic;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLConnection;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import net.webservicex.CurrencyConvertor;
import net.webservicex.CurrencyConvertorSoap;

import org.apache.axis.AxisFault;
import org.apache.commons.io.IOUtils;
import org.iteso.msc.asn2015.productcatalog.model.dao.CategoryDAO;
import org.iteso.msc.asn2015.productcatalog.model.dao.ImageDAO;
import org.iteso.msc.asn2015.productcatalog.model.dao.ImageMetadataDAO;
import org.iteso.msc.asn2015.productcatalog.model.dao.ProductDAO;
import org.iteso.msc.asn2015.productcatalog.model.dto.CategoryDTO;
import org.iteso.msc.asn2015.productcatalog.model.dto.ImageMetadataDTO;
import org.iteso.msc.asn2015.productcatalog.model.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.sun.jersey.core.header.FormDataContentDisposition;

/**
 * Logic Test Class
 * @author Juan Lingow
 *
 */
@Component
public class ProductLogic {
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ImageMetadataDAO imageMetadataDAO;
	
	@Autowired
	private ProductDAO productDAO;

	public Response addProduct(String name, String description, int imageId,
			int categoryId, String currency, float price) {
		if(name == null){
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Product should have a name").build();
		}
		if (currency == null){
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Product should have a currency").build();
		}
		if ( ! getCurrencies().contains(currency)){
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Currency is not supported").build();
		}
		
		CategoryDTO cat = categoryDAO.findOne(categoryId);
		if (cat == null){
			return Response.status(Response.Status.NOT_FOUND)
					.entity("Category with id " + categoryId + " wasn't found").build();
		}
		ImageMetadataDTO img = imageMetadataDAO.findOne(imageId);
		ProductDTO prod = new ProductDTO(name,currency,price,cat);
		prod.setDescription(description);
		prod.setImage(img);
		productDAO.save(prod);
		URI uri = UriBuilder.fromPath("/product/"+prod.getId()).build(new ArrayList<Object>());
		return Response.created(uri).build();
	}

	public ProductDTO getProduct(int id) {
		return productDAO.findOne(id);
	}

	public Response updateProduct(int id, String name,
			String description, int imageId, int categoryId, String currency,
			float price) {
		ProductDTO prod = productDAO.findOne(id);
		if (prod == null){
			return Response.status(Response.Status.NOT_FOUND)
					.entity("Product with id " + id + " was not found").build();
		}
		if(name == null){
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Product should have a name").build();
		}
		if (currency == null){
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Product should have a currency").build();
		}
		if ( ! getCurrencies().contains(currency)){
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Currency is not supported").build();
		}
		
		CategoryDTO cat = categoryDAO.findOne(categoryId);
		if (cat == null){
			return Response.status(Response.Status.NOT_FOUND)
					.entity("Category with id " + categoryId + " wasn't found").build();
		}
		ImageMetadataDTO img = imageMetadataDAO.findOne(imageId);
		prod.setName(name);
		prod.setCategory(cat);
		prod.setImage(img);
		prod.setCurrency(currency);
		prod.setDescription(description);
		prod.setPrice(price);
		productDAO.save(prod);
		return Response.ok("Product modified").build();
	}

	public Response deleteProduct(int id) {
		if (productDAO.exists(id)){
			productDAO.delete(id);
			return Response.ok("Product Deleted").build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	public List<ProductDTO> getProducts(String id, String currency) {
		List<ProductDTO> list;
		if(id == null || id.isEmpty()){
			list = productDAO.findAll();			
		} else {
			list = getProductsByCategory(Integer.parseInt(id));		
		}
		
		if(currency != null && !currency.isEmpty()) {
			convertProductCurrency(list, currency);
		}
		
		return list;
		
	}
	
	public List<ProductDTO> getProductsByCategory(int id) {
		List <ProductDTO> list = productDAO.findAll();
		List <ProductDTO> categoryList = new ArrayList<ProductDTO>();
		ListIterator<ProductDTO> it = list.listIterator();
		while(it.hasNext()) {
			ProductDTO p = it.next();
			CategoryDTO c = p.getCategory();
			if(c.getId() == id) {
				categoryList.add(p);
			}
		}
		
		return categoryList;
	}
	
	public void convertProductCurrency(List<ProductDTO> list, String toCurrency) {
		ListIterator<ProductDTO> it = list.listIterator();
		try {
			
			CurrencyConvertor co = new CurrencyConvertor();
			CurrencyConvertorSoap ccs = co.getCurrencyConvertorSoap();
			Double currency;
			while(it.hasNext()) {
				ProductDTO p = it.next();
				currency = ccs.conversionRate(net.webservicex.Currency.fromValue(p.getCurrency()), net.webservicex.Currency.fromValue(toCurrency));
				p.setPrice((float) (p.getPrice() * currency));
				p.setCurrency(toCurrency);
			}
		
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getCurrencies() {
		net.webservicex.Currency[] currencies = net.webservicex.Currency.values();
		List<String> list = new ArrayList<String>();
		for (net.webservicex.Currency cur : currencies){
			list.add(cur.name());
		}
		return list;
		
	}
	
}