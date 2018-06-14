package org.crealytics.service;

import org.crealytics.bean.AdDetail;
import org.crealytics.bean.AdDetailReport;
import org.crealytics.exception.AppException;
import org.crealytics.exception.ExceptionCode;
import org.crealytics.exception.ExceptionMessage;
import org.crealytics.repository.AdRepository;
import org.crealytics.utility.GlobalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdServiceImpl implements AdService{

    @Autowired
    AdRepository repo;

    public AdDetail insert(AdDetail adDetail){
        return repo.save(adDetail);
    }

    public List<AdDetail> insertAll(List<AdDetail> adDetails){
        return repo.saveAll(adDetails);
    }

    private List<AdDetail> get(AdDetail example){
        return repo.findAll(Example.of(example));
    }

    private AdDetail createExample(Integer month, String site){
        AdDetail ex = new AdDetail();

        if(month != null)
            ex.setMonth(month);

        if(site!=null)
            ex.setSite(site);

        return ex;
    }

    public AdDetailReport aggregatedReport(String monthStr, String siteStr) throws AppException {
        int month = GlobalUtils.getMonth(monthStr);
        String site = null;

        if(siteStr != null)
            site = GlobalUtils.getSite(siteStr);

        return aggregatedReport(month,site);
    }

    private AdDetailReport aggregatedReport(Integer month, String site) throws AppException {
        List<AdDetail> ads = get(createExample(month,site));

        if(ads.isEmpty())
            throw new AppException(ExceptionCode.NO_RECORD_FOUND,ExceptionMessage.NO_RECORD_FOUND);

        AdDetailReport detailReport = new AdDetailReport()
                .setMonth(Month.of(month).toString())
                .setSite(site)
                .setRequests(ads.stream().mapToLong(AdDetail::getRequests).sum())
                .setImpressions(ads.stream().mapToLong(AdDetail::getImpressions).sum())
                .setClicks(ads.stream().mapToLong(AdDetail::getClicks).sum())
                .setConversions(ads.stream().mapToLong(AdDetail::getConversions).sum())
                .setRevenue((float) ads.stream().mapToDouble(AdDetail::getRevenue).sum())
                .fullBuild();

        return detailReport;
    }

    @Override
    public List<AdDetailReport> report(String monthStr, String siteStr) throws AppException {
        int month = GlobalUtils.getMonth(monthStr);
        String site = null;

        if(siteStr != null)
            site = GlobalUtils.getSite(siteStr);

        return report(month,site);
    }

    private List<AdDetailReport> report(int month, String site) throws AppException {
        List<AdDetail> ads = get(createExample(month,site));

        if(ads.isEmpty())
            throw new AppException(ExceptionCode.NO_RECORD_FOUND,ExceptionMessage.NO_RECORD_FOUND);

        return ads.stream()
                .map(ad -> new AdDetailReport(ad))
                .collect(Collectors.toList());
    }
}
