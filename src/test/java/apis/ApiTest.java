package apis;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import xray.JiraXrayService;

import java.io.IOException;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.ReporteUtil.generateCucumberReport;


class ApiTest {

    private static final Logger LOGGER = Logger.getLogger(ApiTest.class.getName());

    @Test
    void pruebasParalelas() {
        Results results =
                Runner.path("classpath:apis")
                        .outputCucumberJson(true)
                        .parallel(2);
        generateCucumberReport(results.getReportDir());
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }


    @AfterAll
    static void afterAll() throws IOException {
        try {
            JiraXrayService jiraService = JiraXrayService.getSingletonInstance();
            jiraService.importResultsInJiraXraySaas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
