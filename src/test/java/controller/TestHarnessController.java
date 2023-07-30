package controller;

import com.geek.CucumberTestRunner;
import com.geek.helpers.Reporter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestHarnessController {

    @Value("${cucumber.extent.reporter.location}")
    private String cucumberReportLocation;

    @GetMapping("/runTests")
    public ResponseEntity<String> runCucumberTests() {

        CucumberTestRunner.runTests(null, null);
        return getReport();
    }

    @GetMapping("/runTestsByGroup")
    public ResponseEntity<String> runCucumberTestsByTag(@RequestParam String tag) {

        CucumberTestRunner.runTests(null, tag);
        return getReport();
    }

    @GetMapping("/runTestsByFeatureName")
    public ResponseEntity<String> runCucumberTestByFeatureName(@RequestParam String feature) {

        CucumberTestRunner.runTests(feature, null);
        return getReport();
    }

    @GetMapping("/report")
    public ResponseEntity<String> getReport() {

        ResponseEntity<String> report = Reporter.getReport(cucumberReportLocation);
        return report;
    }
}
