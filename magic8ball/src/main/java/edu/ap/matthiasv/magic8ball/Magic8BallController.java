package edu.ap.matthiasv.magic8ball;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class Magic8BallController {

    @Autowired
    private RedisService service;

    @GetMapping(value = "/")
    public String index() {
        return "redirect:/question";
    }

    @GetMapping(value = "/question")
    public String getQuestionForm(Model model) {
        ArrayList<String> questions = new ArrayList<String>();
        for (String q : this.service.keys("questions:*")) {
            questions.add(this.service.getKey(q));
        }
        model.addAttribute("questions", questions);
        return "addQuestion";
    }

    @PostMapping(value = "/question/{question}")
    public String addQuestion(@RequestParam("question") String question, @RequestParam("answer") String answer) {

        this.service.setKey("question:" + question, answer);
        return "getQuestionForm";
    }

}