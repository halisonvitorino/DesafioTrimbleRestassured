package Desafio_Trimble.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;

import static com.aventstack.extentreports.reporter.configuration.Theme.DARK;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports createInstance(String fileName) {

        ExtentSparkReporter htmlReporter  = new ExtentSparkReporter(fileName);

        htmlReporter.config().setCss("css-string");
        htmlReporter.config().setDocumentTitle("Testes API Trimble");
        htmlReporter.config().setTimelineEnabled(true);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setJs("js-string");
        htmlReporter.config().setProtocol(Protocol.HTTP);
        htmlReporter.config().setReportName("Teste Regressivo");
        htmlReporter.config().setTheme(DARK);

        extent  = new ExtentReports();
        extent.attachReporter(htmlReporter);
        return extent;
    }
    public static ExtentReports getInstance() {
        if (extent == null) {
            // Pode lançar uma exceção ou lidar de outra maneira
            throw new IllegalStateException("ExtentReports não foi inicializado");
        }
        return extent;
    }
}
