package workwithfiles;

import com.codeborne.pdftest.PDF;
import com.codeborne.pdftest.matchers.ContainsExactText;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ZipFileParseTest {

    private final String zipFilePath = "src/test/resources/zip/5_files.zip";

    @Test
    public void zipHasFiveFiles() throws IOException {

        List<String> filesNames = new ArrayList<>();

        try (ZipInputStream zip = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry;

            while ((entry = zip.getNextEntry()) != null) {
                filesNames.add(entry.getName());
            }

            zip.closeEntry();
        }

        List<String> expectedNames = List.of(
                "pdfExample.pdf",
                "xlsExample.xls",
                "csvExample.csv",
                "txtExample.txt",
                "jsonExample.json");

        assertThat(filesNames.size()).isEqualTo(5);
        assertThat(filesNames).containsAll(expectedNames);
    }

    @Test
    public void zipXlsParsing() throws IOException {

        List<UserXLS> users = parseXls();

        // count of records
        assertThat(users.size()).isEqualTo(1000);

        // data
        HashSet<Double> ids = new HashSet<>();
        HashSet<String> fullNames = new HashSet<>();
        SortedSet<Double> ages = new TreeSet<>();
        Set<String> genders = new HashSet<>();
        Set<String> countries = new HashSet<>();

        for (UserXLS user : users) {
            ids.add(user.id);
            fullNames.add(user.firstName + user.lastName);
            ages.add(user.age);
            genders.add(user.gender);
            countries.add(user.country);
        }

        // uniq ids
//        assertThat(ids.size()).isEqualTo(fullNames.size());

        // list of genders and countries
        List<String> genderList = List.of("Female", "Male");
        Set<String> expectedGenders = new HashSet<>(genderList);

        assertThat(genders).isEqualTo(expectedGenders);

        List<String> countryList = List.of("France", "Great Britain", "United States");
        Set<String> expectedCountries = new HashSet<>(countryList);

        assertThat(countries).isEqualTo(expectedCountries);

        // age boundaries between 21 and 60
        assertThat(ages.first()).isGreaterThanOrEqualTo(21);
        assertThat(ages.last()).isLessThan(60);
    }

    @Test
    public void zipCSVParserTest() throws Exception {
        ZipFile zipFile = new ZipFile(zipFilePath);
        try (InputStream stream = zipFile.getInputStream(Objects.requireNonNull(getEntry(zipFilePath, "csv")));
        CSVReader reader = new CSVReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            List<String[]> content = reader.readAll();

            assertThat(content).contains(
                    new String[]{"Name", "Surname"},
                    new String[]{"John", "Dough"},
                    new String[]{"Jane", "Dough"}
            );
        }
    }

    @Test
    public void zipTxtParserTest() throws IOException {
        ZipFile zipFile = new ZipFile(zipFilePath);
        Scanner sc = new Scanner(zipFile.getInputStream(Objects.requireNonNull(getEntry(zipFilePath, "txt"))));

        //find all english words in text
        List<String> englishWords = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\w+");

        while (sc.hasNext()) {
            Matcher match = pattern.matcher(sc.nextLine());
            while(match.find()) {
                englishWords.add(match.group());
            }
        }
        sc.close();
        assertThat(englishWords.size()).isEqualTo(11);
    }

    @Test
    public void zipJSONParserTest() throws IOException {
        Countries countries = getCountryListFromJson();

        assertThat(countries.countries.size()).isEqualTo(246);

        assertThat(countries.countries.get(0).isoCode).isEqualTo("AF");
        assertThat(countries.countries.get(245).name).isEqualTo("Zimbabwe");

    }

    private Countries getCountryListFromJson() throws IOException {
        ZipFile zipFile = new ZipFile(zipFilePath);
        ObjectMapper mapper = new ObjectMapper();
        InputStream stream = zipFile.getInputStream(Objects.requireNonNull(getEntry(zipFilePath, "json")));

        return mapper.readValue(stream, Countries.class);
    }

    @Test
    public void zipPDFParserTest() throws IOException {
        ZipFile zipFile = new ZipFile(zipFilePath);
        PDF pdf = new PDF(zipFile.getInputStream(Objects.requireNonNull(getEntry(zipFilePath, "pdf"))));

        // data
        String author = "cdaily";
        String title = "This is a test PDF file";
        String createDate = "29.06.2000, 03:21:08";
        int pages = 1;
        String text = "Adobe®";

        // author
        assertThat(pdf.author).isEqualTo(author);
        //title
        assertThat(pdf.title).isEqualTo(title);
        // creation date
        SimpleDateFormat fm = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss");
        assertThat(fm.format(pdf.creationDate.getTime())).isEqualTo(createDate);
        //number of pages
        assertThat(pdf.numberOfPages).isEqualTo(pages);
        //contains text
        org.hamcrest.MatcherAssert.assertThat(pdf, new ContainsExactText(text));
    }

    private List<UserXLS> parseXls() throws IOException {
        ZipFile zipFile = new ZipFile(zipFilePath);
        XLS xls = new XLS(zipFile.getInputStream(Objects.requireNonNull(getEntry(zipFilePath, "xls"))));
        List<UserXLS> users = new ArrayList<>();

        int rowCount = xls.excel.getSheetAt(0).getLastRowNum();
        for (int i = 1; i <= rowCount; i++) {
            UserXLS user = new UserXLS(
                    xls.excel.getSheetAt(0).getRow(i).getCell(0).getNumericCellValue(),
                    xls.excel.getSheetAt(0).getRow(i).getCell(1).getStringCellValue(),
                    xls.excel.getSheetAt(0).getRow(i).getCell(2).getStringCellValue(),
                    xls.excel.getSheetAt(0).getRow(i).getCell(3).getStringCellValue(),
                    xls.excel.getSheetAt(0).getRow(i).getCell(4).getStringCellValue(),
                    xls.excel.getSheetAt(0).getRow(i).getCell(5).getNumericCellValue(),
                    xls.excel.getSheetAt(0).getRow(i).getCell(6).getStringCellValue(),
                    xls.excel.getSheetAt(0).getRow(i).getCell(7).getNumericCellValue(),
                    i + 1

            );
            users.add(user);
        }
        return users;
    }

    private ZipEntry getEntry(String filePath, String fileType) throws IOException {
        ZipEntry entry;
        try (ZipInputStream zip = new ZipInputStream(new FileInputStream(filePath))) {
            while ((entry = zip.getNextEntry()) != null) {
                if (entry.getName().endsWith(fileType)) {
                    return entry;
                }
                zip.closeEntry();
            }
        }
        return null;
    }
}