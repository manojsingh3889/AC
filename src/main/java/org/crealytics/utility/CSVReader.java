package org.crealytics.utility;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class contains utility method to convert a homogeneous csv file into java beans
 * @author manoj
 */
public class CSVReader {

    private static final String SEPARATOR = "\\s*,\\s*";
    private static final String COMMA = ",";
    private static final String ENCODING = "UTF-8";
    private List<String> files;

    /**
     * Create CSVReader object with initial set of file paths
     * @param filePaths vararg for csv paths
     */
    public CSVReader(List<String> filePaths){
        this.files=filePaths;
    }

    /**
     * no arg constructor, make sure you add file using {@link #addFile(String)} if you're using this constructor
     */
    public CSVReader() {
        this.files = new ArrayList<>();
    }

    /**
     * Add file more files to CSV reader
     * @param filePath relative file path e.g. /src/main/resources/file.csv
     * @return {@link CSVReader}
     */
    public CSVReader addFile(String filePath) {
        this.files = files;
        return this;
    }

    /**
     * Convert csv file record into 2D-matrix of {@link String}
     * @param filePath relative file path e.g. /src/main/resources/file.csv
     * @return 2D-matrix of csv data using list
     * @throws IOException asd
     */
    public static List<List<String>> getRecords(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        try(Reader reader = Files.newBufferedReader(path, Charset.forName(ENCODING));
            BufferedReader br = new BufferedReader(reader)){
            return br.lines()
                    .skip(1)
                    .map(line -> Arrays.asList((line.concat(COMMA).concat(path.getFileName().toString())).split(SEPARATOR)))
                    .collect(Collectors.toList());
        }catch (IOException e){
            throw e;
        }
    }

    public List<List<String>> getRecords() throws Exception {
        if(files.isEmpty())
            throw new Exception("No file added");

        List<List<String>> result = new ArrayList<>();

        /*convert file records to bean list*/
        for(String file: files)
            result.addAll(getRecords(file));

        return result;
    }
    /**
     * Convert csv file header into List of {@link String}
     * @param filePath relative file path e.g. /src/main/resources/file.csv
     * @return list of headers
     * @throws IOException asd
     */
    public static List<String> getHeaders(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        try(Reader reader = Files.newBufferedReader(path, Charset.forName(ENCODING));
            BufferedReader br = new BufferedReader(reader)){
            return br.lines().findFirst()
                    .map(line -> Arrays.asList(line.concat(COMMA).concat("filename").split(SEPARATOR)))
                    .get();
        }catch (IOException e){
            throw e;
        }
    }

    public List<String> getHeaders() throws Exception {
        if(files.isEmpty())
            throw new Exception("No file added");
        /*since file are assumed to be homogeneous, therefore header can be build once*/
        return getHeaders(files.get(0));
    }

    public static <T> List<T> getObjects(Class<T> t, List<String> headers, List<List<String>> records){
        return records.parallelStream()
                .map(record -> getObject(t, headers, record))
                .collect(Collectors.toList());
    }

    public static <T> T getObject(Class<T> clazz, List<String> headers, List<String> record){
        T obj = null;
        try {

            obj = clazz.newInstance();
            T finalObj = obj;
            Arrays.stream(Introspector.getBeanInfo(clazz).getPropertyDescriptors())
                    .forEach(propertyDescriptor -> {
                        try {
                            //if field exist this statement will not exit with exception
                            Field field = clazz.getDeclaredField(propertyDescriptor.getName());
                            int index = headers.indexOf(propertyDescriptor.getName());

                            if(index==-1 && field.getAnnotation(CSVProperty.class)!=null)
                                index = headers.indexOf(field.getAnnotation(CSVProperty.class).value());


                            if (index != -1)
                                propertyDescriptor.getWriteMethod().invoke(finalObj,
                                        GlobalUtils.parseValue(propertyDescriptor.getPropertyType(),record.get(index)));

                        } catch (NoSuchFieldException e){
                            //TODO write handler
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
        } catch (InstantiationException|IllegalAccessException|IntrospectionException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public <T> List<T> convertTo(Class<T> clazz) throws Exception {
        List<T> result = new ArrayList<>();

        /*convert file records to bean list*/
        for(String file: files)
            result.addAll(getObjects(clazz,getHeaders(),getRecords(file)));

        return result;
    }
}
