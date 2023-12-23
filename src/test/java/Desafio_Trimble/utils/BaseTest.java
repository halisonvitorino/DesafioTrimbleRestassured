package Desafio_Trimble.utils;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
public class BaseTest {

    protected ExtentReports extent;
    protected ExtentTest test;
    @BeforeClass
    public void setup(){
        LocalDateTime dataHoraTeste = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy_HH.mm.ss");
        String dataHoraFormatada    = dataHoraTeste.format(formatter);
        String nomeArquivo          = "TestReport_" + dataHoraFormatada;

        extent = ExtentManager.createInstance("src/test/java/Desafio_Trimble/reports/" + nomeArquivo + ".html");
        //test = extent.createTest(getClass().getSimpleName());
        extent.setAnalysisStrategy(AnalysisStrategy.SUITE);
        test = extent.createTest("Teste Regressivo 1.0");
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        test.assignCategory("Regressao");
        test.assignAuthor("Halison Vitorino");

        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Case Failed: " + result.getName());
            test.log(Status.FAIL, "Error: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Case Skipped: " + result.getName());
        } else {
            test.log(Status.PASS, "Test Case Passed: " + result.getName());
        }
        extent.flush();
    }
    @BeforeMethod
    public void beforeMethod(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
    public String decodeString(String base64EncodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64EncodedString);
        return new String(decodedBytes);
    }
}
