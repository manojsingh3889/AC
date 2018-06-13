package org.crealytics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdDetailRepo extends JpaRepository<AdDetail, Long> {
	AdDetail findBySite(String site);
}
