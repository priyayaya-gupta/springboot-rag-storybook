package com.example.Rag_storyBook.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Value;
// import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
@Service
public class QdrantService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${qdrant.url}")
    private String qdrantUrl;

    @Value("${qdrant.api-key}")
    private String apiKey;

    @Value("${qdrant.collection}")
    private String collectionName;


    public void storeChunk(String chunkText,
                       List<Double> embedding) {
                        List<Float> vector = embedding.stream()
        .map(Double::floatValue)
        .toList();
        Map<String, Object> payload =
        new HashMap<>();

payload.put("text", chunkText);
Map<String, Object> point =
        new HashMap<>();
        point.put("id", UUID.randomUUID().toString());

point.put("vector", vector);

point.put("payload", payload);

Map<String, Object> body =
        new HashMap<>();
        body.put(
    "points",
    List.of(point)
);
HttpHeaders headers = new HttpHeaders();

headers.set("api-key", apiKey);

headers.setContentType(MediaType.APPLICATION_JSON);
HttpEntity<Map<String, Object>> entity =
        new HttpEntity<>(body, headers);
        String url =
        qdrantUrl
        + "/collections/"
        + collectionName
        + "/points";
System.out.println(url);
System.out.println("BODY = " + body);
        String response =
        restTemplate.exchange(
                url,
                HttpMethod.PUT,
                entity,
                String.class
        ).getBody();

System.out.println(response);
}

public List<String> searchChunks(List<Double> queryEmbedding) throws Exception{
Map<String, Object> body = new HashMap<>();

body.put("vector", queryEmbedding);
body.put("limit", 2);
body.put("with_payload", true);
HttpHeaders headers = new HttpHeaders();

headers.set("api-key", apiKey);

headers.setContentType(MediaType.APPLICATION_JSON);
HttpEntity<Map<String, Object>> entity =
        new HttpEntity<>(body, headers);
        String url =
        qdrantUrl
        + "/collections/"
        + collectionName
        + "/points/search";
        String response =
        restTemplate.postForObject(
                url,
                entity,
                String.class
        );

ObjectMapper mapper = new ObjectMapper();
JsonNode root = mapper.readTree(response);
JsonNode results = root.get("result");
List<String> chunks = new ArrayList<>();
for (JsonNode item : results){
String text = 
        item.get("payload")
        .get("text")
        .asText();

        chunks.add(text);
}

return chunks;


//System.out.println(response);
// return response;

}

}
