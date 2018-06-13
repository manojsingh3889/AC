package org.crealytics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FrontController {

    public static final Logger logger = LoggerFactory.getLogger(RestController.class);

    @Autowired
    AdService service;


    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public ResponseEntity<String> insert() {
        AdDetail detail = new AdDetail();
        detail.setClicks(23123l);
        detail.setConversions(123123l);
        detail.setImpressions(123786l);
        detail.setRequests(0123l);
        detail.setRevenue(234123.0);
        detail.setSite("Desktop");

        service.insertAd(detail);
        HttpHeaders heeader = new HttpHeaders();
        heeader.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<String>(heeader,HttpStatus.OK);
    }
}