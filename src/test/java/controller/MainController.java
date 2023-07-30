package controller;

import com.geek.helpers.CucumberFiles;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Value("${cucumber.supported.tags}") private String[] tags;

    @GetMapping("/")
    public String getPage() {
        return "index";
    }

    @GetMapping("/harness")
    public String getHarness(Model model) {
        List<String> filenames = CucumberFiles.getFeatureFilenames();

        model.addAttribute("tags", tags);
        model.addAttribute("features", filenames);
        return "harness";
    }

    @GetMapping("/university")
    public String getUniversity() {
        return "university";
    }
}
