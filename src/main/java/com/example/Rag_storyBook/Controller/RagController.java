package com.example.Rag_storyBook.Controller;

import com.example.Rag_storyBook.Service.RagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RagController {

    @Autowired
    private RagService ragService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/ask")
    public String ask(
            @RequestParam String question,
            Model model
    ) throws Exception {

        String answer =
                ragService.ask(question);

        model.addAttribute(
                "question",
                question
        );

        model.addAttribute(
                "answer",
                answer
        );

        return "index";
    }
}
