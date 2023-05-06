package Utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private static final String REPORTS_DIR = "test-output/";
    private static final String ARCHIVE_DIR = "Archived test results/";
    private static final String CURRENT_DIR = "Current test results/";


    public static ExtentReports createExtentReports() {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String reportName = "Test_Report_" + timestamp + ".html";
        String reportPath = REPORTS_DIR + CURRENT_DIR + reportName;
        // Move current test results to archive and clean the current test results folder
        moveCurrentToArchive();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        return extentReports;
    }

    private static void moveCurrentToArchive() {
        try {
            Path currentDirPath = Paths.get(REPORTS_DIR + CURRENT_DIR);
            Path archiveDirPath = Paths.get(REPORTS_DIR + ARCHIVE_DIR);
            if (Files.notExists(archiveDirPath)) {
                Files.createDirectories(archiveDirPath);
            }
            if (Files.exists(currentDirPath)) {
                Files.list(currentDirPath)
                        .forEach(file -> {
                            try {
                                Files.move(file, archiveDirPath.resolve(file.getFileName()));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
            } else {
                Files.createDirectories(currentDirPath);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
