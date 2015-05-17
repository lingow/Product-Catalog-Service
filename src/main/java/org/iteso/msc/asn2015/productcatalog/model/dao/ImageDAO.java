package org.iteso.msc.asn2015.productcatalog.model.dao;

import org.iteso.msc.asn2015.productcatalog.model.dto.ImageDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ImageDAO (Repository) for ImageDTO objects
 * @author Daniel Rodriguez Millan drm@chocodev.com
 *
 */
@Repository("imageDAO")
public interface ImageDAO extends JpaRepository<ImageDTO, Integer> {
}
