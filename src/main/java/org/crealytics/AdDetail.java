package org.crealytics;

import javax.persistence.*;

@Entity
@Table(name="ad_detail")
public class AdDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String site;
    private Long requests;
    private Long impressions;
    private Long clicks;
    private Long conversions;

    @CSVProperty("revenue (USD)")
    private Double revenue;

    public AdDetail() {
    }

    public AdDetail(String site, Long requests, Long impressions,
                    Long clicks, Long conversions, Double revenue) {
        this.site = site;
        this.requests = requests;
        this.impressions = impressions;
        this.clicks = clicks;
        this.conversions = conversions;
        this.revenue = revenue;
    }

    public String getSite() {
        return site;
    }

    public AdDetail setSite(String site) {
        this.site = site;
        return this;
    }

    public Long getRequests() {
        return requests;
    }

    public AdDetail setRequests(Long requests) {
        this.requests = requests;
        return this;
    }

    public Long getImpressions() {
        return impressions;
    }

    public AdDetail setImpressions(Long impressions) {
        this.impressions = impressions;
        return this;
    }

    public Long getClicks() {
        return clicks;
    }

    public AdDetail setClicks(Long clicks) {
        this.clicks = clicks;
        return this;
    }

    public Long getConversions() {
        return conversions;
    }

    public AdDetail setConversions(Long conversions) {
        this.conversions = conversions;
        return this;
    }

    public Double getRevenue() {
        return revenue;
    }

    public AdDetail setRevenue(Double revenue) {
        this.revenue = revenue;
        return this;
    }
}
