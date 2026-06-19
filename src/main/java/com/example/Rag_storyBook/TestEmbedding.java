

package com.example.Rag_storyBook;

import com.example.Rag_storyBook.Service.EmbeddingService;

import java.util.List;

public class TestEmbedding {

    public static void main(String[] args) {

        EmbeddingService embeddingService = new EmbeddingService();

        String text = "The rabbit entered the forest.";

        List<Double> embedding =
                embeddingService.generateEmbedding(text);

        System.out.println("Dimension = " + embedding.size());

        System.out.println("First 5 values:");

        for (int i = 0; i < 5; i++) {
            System.out.println(embedding.get(i));
        }
    }
}
