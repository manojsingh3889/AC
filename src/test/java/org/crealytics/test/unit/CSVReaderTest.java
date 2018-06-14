package org.crealytics.test.unit;

import org.crealytics.utility.CSVReader;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This test class contains 2 type of test case
 * 1. pre loaded : pre loaded test cased works on instance member function
 *      i.e. you need to build CSVReader and load it with files
 * 2. direct - check static function with custom workflow
 */
public class CSVReaderTest {

    @Test
    public void readPreLoadedHeader() throws Exception {
        CSVReader csvReader = createSingleFileCsvReader();
        List<String> header = null;
        header = csvReader.getHeaders();
        assertThat(header)
                .contains("requests")
                .contains("revenue (USD)")
                .contains("SITE")
                .doesNotContain("sites")
                .contains("filename")
                .hasSize(7);
    }

    @Test
    public void readPreLoadedRecords() throws Exception {
        List<List<String>> records = createSingleFileCsvReader().getRecords();
        assertThat(records)
                .contains(Arrays.asList("desktop web","12483775","11866157","30965","7608","23555.46","2018_01_report.csv"))
                .hasSize(4);
    }
    @Test
    public void getRecords() throws IOException {
        List<List<String>> records = CSVReader.getRecords("src/main/resources/exportData/2018_02_report.csv");
        assertThat(records)
                .contains(Arrays.asList("iOS","5000221","4512765","18987","6001","11931.37","2018_02_report.csv"))
                .doesNotContain((Arrays.asList("desktop web","12483775","11866157","30965","7608","23555.46","2018_01_report.csv")))
                .hasSize(4);
    }

    @Test
    public void getHeaders() throws IOException {
        List<String> header = CSVReader.getHeaders("src/main/resources/exportData/2018_02_report.csv");
        assertThat(header)
                .contains("requests")
                .contains("revenue (USD)")
                .contains("SITE")
                .doesNotContain("sites")
                .contains("filename")
                .hasSize(7);
    }


    private CSVReader createSingleFileCsvReader() {
        return new CSVReader("src/main/resources/exportData/2018_01_report.csv");
    }
}
