package com.example.Rag_storyBook.Service;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;


@Service
public class ChatService {
    private final RestTemplate restTemplate = new RestTemplate();

    public String askQuestion(
        String question , List<String> chunks
    ){
        StringBuilder context = new StringBuilder();

        for (String chunk : chunks){
            context.append(chunk)
            .append("\n\n");
        }

        String prompt = """
                Answer the question only usinf the provided context.
                PLEASE DO NOT SHOW YOUR THINKING PROCESS
                context: 
                %s
                Question: 
                %s

                If the answer is not present in the context , then say 
                "Sorry , I could not find the answer in the book
                """
                .formatted(context.toString(),
            question);

            Map<String,Object> request = new HashMap<>();
                request.put(
        "model",
        "qwen3.5:0.8b"
    );
            request.put("prompt" , prompt);
            request.put("stream" , false);
            request.put("think" , false);

            String url = "http://localhost:11434/api/generate";

            Map<String,Object> response = new RestTemplate()
                                        .postForObject(url,request,Map.class);
                                    System.out.println(response);
                                        return (String)
                                                response.get("response");
    }
}
