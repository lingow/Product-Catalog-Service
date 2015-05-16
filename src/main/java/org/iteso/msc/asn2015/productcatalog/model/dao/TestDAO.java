package org.iteso.msc.asn2015.productcatalog.model.dao;

import org.iteso.msc.asn2015.productcatalog.model.dto.TestDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TestDAO (Repository) for TestDTO objects
 * @author Daniel Rodriguez Millan drm@chocodev.com
 *
 */
@Repository("testDAO")
public interface TestDAO extends JpaRepository<TestDTO, Integer> {
}
