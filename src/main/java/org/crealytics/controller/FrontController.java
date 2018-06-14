package org.crealytics.controller;

import org.crealytics.bean.AdDetailReport;
import org.crealytics.exception.AppException;
import org.crealytics.service.AdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FrontController {

    public static final Logger logger = LoggerFactory.getLogger(RestController.class);

    @Autowired
    AdService service;

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public ResponseEntity<AdDetailReport> reports(@RequestParam(value = "month") String month,
                                          @RequestParam(value = "site", required = false) String site) throws AppException {

        AdDetailReport detailReport = service.aggregatedReport(month, site);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(detailReport, header, HttpStatus.OK);
    }

    @RequestMapping(value = "/report/list", method = RequestMethod.GET)
    public ResponseEntity<List<AdDetailReport>> reportsAsList(@RequestParam(value = "month") String month,
                                                  @RequestParam(value = "site", required = false) String site) throws AppException {

        List<AdDetailReport> reports = service.report(month, site);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(reports, header, HttpStatus.OK);
    }
}