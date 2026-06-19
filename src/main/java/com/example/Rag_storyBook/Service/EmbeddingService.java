package com.example.Rag_storyBook.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

    @Service
public class EmbeddingService {

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Double> generateEmbedding(String text) {

        String url = "http://localhost:11434/api/embed";

        Map<String, Object> request = new HashMap<>();//ye empty map banaya hai
        request.put("model", "nomic-embed-text");//ab us map mei model wale field mei model ka naam daal diya
        request.put("input", text);//prompt wale mei wo text daaldiya jo hume embed krna hai

        // Map<String, Object> response =
        //         restTemplate.postForObject(url, request, Map.class);//yaha pe json format mei embeddings
        //         //ajayengi us text ki


        Map<String, Object> response =
        restTemplate.postForObject(
                url,
                request,
                Map.class
        );

// System.out.println("FULL RESPONSE:");
// System.out.println(response);

//return (List<Double>) response.get("embedding");
List<List<Double>> embeddings =
        (List<List<Double>>) response.get("embeddings");

return embeddings.get(0);
//             Map<String, Object> response =
//         restTemplate.postForObject(url, request, Map.class);

// System.out.println(response);

// return (List<Double>) response.get("embedding");
        //return (List<Double>) response.get("embedding");//return sirf wo wali values jiski
        //key embedding hai.

        
    }
}




