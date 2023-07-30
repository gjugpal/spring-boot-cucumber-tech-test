package com.geek;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class CucumberTestRunner {

        public static String runTests(String feature, String tag) {

            if (feature != null) {
                System.setProperty("cucumber.features", "src/test/resources/features/" + feature);
            }

            if (tag != null) {
                System.setProperty("cucumber.filter.tags", tag);
            }

            Result result = JUnitCore.runClasses(RunCucumberTest.class);
            System.clearProperty("cucumber.filter.tags");
            System.clearProperty("cucumber.features");

            // Check if there are any failures
            if (result.wasSuccessful()) {
                System.out.println("All Cucumber tests passed successfully!");
                return "All Cucumber tests passed successfully!";
            } else {
                System.out.println("Cucumber tests failed! Here are the details:");
                for (Failure failure : result.getFailures()) {
                    System.out.println(failure.toString());
                }
                return "Cucumber tests failed! Here are the details:";
            }
        }
}
