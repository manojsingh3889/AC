package org.crealytics.service;

import org.crealytics.bean.AdDetail;
import org.crealytics.repository.AdRepository;
import org.crealytics.utility.GlobalUtils;
import org.crealytics.utility.GlobalUtils.site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if(site!=null && GlobalUtils.site.valueOf(site)!=null){
            ex.setSite(GlobalUtils.site.valueOf(site).getName());
        }
        return ex;
    }

    public AdDetail compositeReport(Integer month, String site){
        List<AdDetail> ads = get(createExample(month,site));

        AdDetail aggregated = new AdDetail();
        aggregated.setSite(site);
        aggregated.setMonth();


    }

}
