package org.iteso.msc.asn2015.productcatalog.model.dao;

import org.iteso.msc.asn2015.productcatalog.model.dto.CategoryDTO;
import org.iteso.msc.asn2015.productcatalog.model.dto.ImageDTO;
import org.iteso.msc.asn2015.productcatalog.model.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ProductDAO (Repository) for ProductDTO objects
 * @author Juan Lingow
 *
 */
@Repository("productDAO")
public interface ProductDAO extends JpaRepository<ProductDTO, Integer> {
}
