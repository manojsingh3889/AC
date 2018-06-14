package org.crealytics.service;

import org.crealytics.bean.AdDetail;
import org.crealytics.bean.AdDetailReport;
import org.crealytics.exception.AppException;

import java.util.List;

public interface AdService {
    AdDetail insert(AdDetail adDetail);
    List<AdDetail> insertAll(List<AdDetail> adDetails);
    AdDetailReport aggregatedReport(String month, String site) throws AppException;
    List<AdDetailReport> report(String month, String site) throws AppException;
}
