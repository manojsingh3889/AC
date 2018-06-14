package org.crealytics.service;

import org.crealytics.bean.AdDetail;

import java.util.List;

public interface AdService {
    AdDetail insert(AdDetail adDetail);
    List<AdDetail> insertAll(List<AdDetail> adDetails);
}
