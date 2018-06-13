package org.crealytics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdService {

    @Autowired
    AdDetailRepo repo;

    public AdDetail insertAd(AdDetail adDetail){
        return repo.save(adDetail);
    }
}
