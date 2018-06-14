package org.crealytics.repository;

import org.crealytics.bean.AdDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends JpaRepository<AdDetail, Long> {
	AdDetail findBySite(String site);
}
