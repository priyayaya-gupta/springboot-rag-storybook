package com.example.Rag_storyBook.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class RagService {

    @Autowired
    private EmbeddingService embeddingService;

    @Autowired
    private QdrantService qdrantService;

    @Autowired
    private ChatService chatService;

    public String ask(String question) throws Exception {

        List<Double> queryEmbedding =
                embeddingService.generateEmbedding(question);

        List<String> chunks =
                qdrantService.searchChunks(queryEmbedding);

        return chatService.askQuestion(
                question,
                chunks
        );
    }
}
