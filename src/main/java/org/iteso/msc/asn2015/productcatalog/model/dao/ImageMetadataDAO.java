package org.iteso.msc.asn2015.productcatalog.model.dao;

import org.iteso.msc.asn2015.productcatalog.model.dto.ImageMetadataDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ImageDAO (Repository) for ImageMetadataDTO objects
 * @author Juan Lingow
 *
 */
@Repository("imageMetadataDAO")
public interface ImageMetadataDAO extends JpaRepository<ImageMetadataDTO, Integer> {
}
