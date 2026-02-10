package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
//для не стабильных тестов (flaki tests) -> @Test(retryAnalyzer = RetryAnalyser.class)

public class RetryAnalyser implements IRetryAnalyzer {
    private int retryCount = 0;
    private  static int maxTryValue = 3;

    @Override
    public boolean retry(ITestResult result){
        if(retryCount<maxTryValue){
            retryCount++;
            return true;
        }
        return false;
    }
}
