package com.demoblaze.tests.base;

import com.demoblaze.utils.ExcelUtil;
import org.testng.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TestResultListener implements ITestListener {

    private static final String OUT_FILE = "reports/test-results.xlsx";
    private static final String SHEET = "Results";
    private ExcelUtil excel;

    public TestResultListener() {
        excel = new ExcelUtil(OUT_FILE, SHEET);
        // ensure header
        try {
            excel.ensureWorkbookWithHeader(Arrays.asList(
                "Timestamp","TestClass","TestMethod","Status","Duration(ms)","Message"
            ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        writeResult(result, "PASS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        writeResult(result, "FAIL: " + getShortMessage(result));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        writeResult(result, "SKIPPED");
    }

    private String getShortMessage(ITestResult result) {
        if (result.getThrowable() == null) return "";
        return result.getThrowable().toString();
    }

    private void writeResult(ITestResult result, String status) {
        try {
            long duration = result.getEndMillis() - result.getStartMillis();
            String className = result.getTestClass().getName();
            String method = result.getMethod().getMethodName();
            String msg = status.startsWith("FAIL") ? getShortMessage(result) : "";

            excel.appendRow(Arrays.asList(
                now(),
                className,
                method,
                status.startsWith("FAIL") ? "FAIL" : status,
                String.valueOf(duration),
                msg
            ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // other overridden methods can be no-op
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
    @Override public void onTestStart(ITestResult result) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
}
