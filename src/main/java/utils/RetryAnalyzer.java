package utils;

import annotations.RetryCount;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import reporting.ExtentTestManager;

public class RetryAnalyzer implements IRetryAnalyzer {

    public int count = 0;

    @Override
    public boolean retry(ITestResult testResult) {
        RetryCount retryCount = testResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(RetryCount.class);

        if ((testResult.getStatus() == ITestResult.FAILURE) && (retryCount != null)) {
            if (count < retryCount.value()) {
                ExtentTest extentTest = ExtentTestManager.getTest();
                testResult.setStatus(ITestResult.SKIP);
                extentTest.log(LogStatus.SKIP, String.format("RETRYING %s/%s", count, retryCount.value()));

                count++;
                return true;
            }
        }
        return false;
    }
}
