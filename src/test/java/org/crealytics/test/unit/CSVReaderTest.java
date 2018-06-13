package org.crealytics.test.unit;

import jdk.nashorn.internal.runtime.ListAdapter;
import org.crealytics.AdDetail;
import org.crealytics.utility.CSVReader;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CSVReaderTest {

    @Test
    public void readsPreLoadedHeader() throws Exception {
        CSVReader csvReader = createSingleCsvReader();
        List<String> header = null;
        header = csvReader.getHeaders();
        assertThat(header)
                .contains("requests")
                .contains("revenue (USD)")
                .contains("site")
                .doesNotContain("sites")
                .hasSize(6);
    }

    @Test
    public void readsPreLoadedRecords() throws Exception {
        List<List<String>> records = createSingleCsvReader().getRecords();
        assertThat(records)
                .contains(Arrays.asList("desktop web","12483775","11866157","30965","7608","23555.46"))
                .hasSize(4);
    }
    @Test
    public void getRecords() throws IOException {
        List<List<String>> records = CSVReader.getRecords("src/main/resources/exportData/2018_02_report.csv");
        assertThat(records)
                .contains(Arrays.asList("iOS","5000221","4512765","18987","6001","11931.37"))
                .doesNotContain((Arrays.asList("desktop web","12483775","11866157","30965","7608","23555.46")))
                .hasSize(4);
    }

    @Test
    public void getHeaders() throws IOException {
        List<String> header = CSVReader.getHeaders("src/main/resources/exportData/2018_02_report.csv");
        assertThat(header)
                .contains("requests")
                .contains("revenue (USD)")
                .contains("site")
                .doesNotContain("sites")
                .hasSize(6);
    }

    @Test
    public void getObjects() throws Exception {
        CSVReader reader = new CSVReader("src/main/resources/exportData/2018_01_report.csv");
        List<AdDetail> list = CSVReader.getObjects(AdDetail.class,reader.getHeaders(),reader.getRecords());
        assertThat(list).isNotEmpty();
    }

    @Test
    public void getObject() {
    }

    @Test
    public void convertTo() {

    }
    private CSVReader createSingleCsvReader() {
        return new CSVReader("src/main/resources/exportData/2018_01_report.csv");
    }
}