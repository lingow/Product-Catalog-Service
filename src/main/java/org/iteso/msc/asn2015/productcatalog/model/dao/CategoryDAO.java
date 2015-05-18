package org.iteso.msc.asn2015.productcatalog.model.dao;

import org.iteso.msc.asn2015.productcatalog.model.dto.CategoryDTO;
import org.iteso.msc.asn2015.productcatalog.model.dto.ImageDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ImageDAO (Repository) for ImageDTO objects
 * @author Juan Lingow
 *
 */
@Repository("categoryDAO")
public interface CategoryDAO extends JpaRepository<CategoryDTO, Integer> {
}
