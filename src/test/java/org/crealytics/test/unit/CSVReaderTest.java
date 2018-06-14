package org.crealytics.test.unit;

import org.crealytics.bean.AdDetail;
import org.crealytics.test.DummyProduct;
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
                .contains("site")
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
                .contains("site")
                .doesNotContain("sites")
                .contains("filename")
                .hasSize(7);
    }

    @Test
    public void getObjects() throws Exception {
//        CSVReader reader = new CSVReader("src/main/resources/exportData/2018_01_report.csv");
        List<String> header = Arrays.asList("quantity","cost_price","sell_price");
        List<List<String>> records = Arrays.asList(Arrays.asList("1","23.0","25.0"),Arrays.asList("33","231.0","252.0"));


        List<DummyProduct> list = CSVReader.getObjects(DummyProduct.class,header,records);
        assertThat(list)
                .isNotEmpty()
                .hasSize(2);
        assertThat(list.get(0))
                .hasFieldOrPropertyWithValue("quantity",1);
        assertThat(list.get(1))
                .hasFieldOrPropertyWithValue("costPrice",231.0f);
    }

    @Test
    public void getObject() throws Exception {
        List<String> header = Arrays.asList("quantity","cost_price","sell_price");
        List<String> record = Arrays.asList("1","2326.0","2523.0");
        DummyProduct detail = CSVReader.getObject(DummyProduct.class,header,record);
        assertThat(detail)
                .isNotNull()
                .isInstanceOf(DummyProduct.class)
                .hasFieldOrPropertyWithValue("quantity",1)
                .hasFieldOrPropertyWithValue("costPrice",2326.0f);
    }

    @Test
    public void convertTo() throws Exception {
        CSVReader csvReader = createMultifileCsvReader();
        List<AdDetail> adDetails = csvReader.convertTo(AdDetail.class);
        assertThat(adDetails)
                .hasSize(8)
                .contains(new AdDetail("iOS", 2550165l,
                        2419733l, 6331l,
                        1564l, 4692.28));
    }
    private CSVReader createSingleFileCsvReader() {
        return new CSVReader(Arrays.asList("src/main/resources/exportData/2018_01_report.csv"));
    }

    private CSVReader createMultifileCsvReader() {
        return new CSVReader(Arrays.asList("src/main/resources/exportData/2018_01_report.csv"
                ,"src/main/resources/exportData/2018_02_report.csv"));
    }
}
