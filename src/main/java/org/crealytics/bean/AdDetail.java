package org.crealytics.bean;

import org.crealytics.utility.CSVProperty;

import javax.persistence.*;
import java.util.Objects;

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
    private Float revenue;

    private Float ctr;
    private Float cr;
    private Float fillRate;
    private Float ecpm;
    private Integer month;

    public AdDetail() {
    }

    public AdDetail(String site, Long requests, Long impressions,
                    Long clicks, Long conversions, Float revenue) {
        this.site = site;
        this.requests = requests;
        this.impressions = impressions;
        this.clicks = clicks;
        this.conversions = conversions;
        this.revenue = revenue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Long getRequests() {
        return requests;
    }

    public void setRequests(Long requests) {
        this.requests = requests;
    }

    public Long getImpressions() {
        return impressions;
    }

    public void setImpressions(Long impressions) {
        this.impressions = impressions;
    }

    public Long getClicks() {
        return clicks;
    }

    public void setClicks(Long clicks) {
        this.clicks = clicks;
    }

    public Long getConversions() {
        return conversions;
    }

    public void setConversions(Long conversions) {
        this.conversions = conversions;
    }

    public Float getRevenue() {
        return revenue;
    }

    public void setRevenue(Float revenue) {
        this.revenue = revenue;
    }

    public Float getCtr() {
        return ctr;
    }

    public void setCtr(Float ctr) {
        this.ctr = ctr;
    }

    public Float getCr() {
        return cr;
    }

    public void setCr(Float cr) {
        this.cr = Float.valueOf(String.format("%.2f", cr));
    }

    public Float getFillRate() {
        return fillRate;
    }

    public void setFillRate(Float fillRate) {
        this.fillRate = fillRate;
    }

    public Float getEcpm() {
        return ecpm;
    }

    public void seteCPM(Float eCPM) {
        this.ecpm = eCPM;
    }


    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdDetail detail = (AdDetail) o;
        return Objects.equals(id, detail.id) &&
                Objects.equals(site, detail.site) &&
                Objects.equals(requests, detail.requests) &&
                Objects.equals(impressions, detail.impressions) &&
                Objects.equals(clicks, detail.clicks) &&
                Objects.equals(conversions, detail.conversions) &&
                Objects.equals(revenue, detail.revenue) &&
                Objects.equals(month,detail.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, site, requests, impressions, clicks, conversions, revenue, month);
    }

    public AdDetail fullBuild(){
        setCr(((float)clicks/impressions)*100);
        ctr = Float.valueOf((float)clicks/impressions)*100;
        cr = ((float)conversions/impressions)*100;
        fillRate = ((float)impressions/requests)*100;
        ecpm = (float)(revenue*1000)/impressions;

        return this;
    }

    @PreUpdate
    @PrePersist
    protected void onCreate() {
        fullBuild();
    }
}
