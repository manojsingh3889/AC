package org.crealytics.startup;

import org.crealytics.bean.AdDetail;
import org.crealytics.service.AdService;
import org.crealytics.utility.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoadCSV implements ApplicationRunner {

    @Autowired
    AdService adService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<String> files = new ArrayList<>();
        Files.newDirectoryStream(Paths.get("src/main/resources/exportData/"), path -> path.toString().endsWith(".csv"))
                .forEach(path -> {
                    files.add(path.toAbsolutePath().toString());
                });

        CSVReader reader = new CSVReader(files);
        adService.insertAll(reader.convertTo(AdDetail.class));
    }
}
