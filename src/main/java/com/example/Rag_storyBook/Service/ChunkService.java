package com.example.Rag_storyBook.Service;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class ChunkService {

public List<String> createChunks(String text) {

    int CHUNK_SIZE = 2000;
    int SENTENCE_OVERLAP = 2;   // Last 2 sentences repeat hoyenge

    List<String> chunks = new ArrayList<>();

    String[] sentences = text.split("(?<=[.!?])\\s+"); //sentences ke basis pe split hoyenge fir unke chunks banayenge. 

    StringBuilder currentChunk = new StringBuilder();
    List<String> currentSentences = new ArrayList<>();//current chunk ki sentences track kr ri hai

    for (String sentence : sentences) {

        if (currentChunk.length() + sentence.length() > CHUNK_SIZE) {

            // Save current chunk
            chunks.add(currentChunk.toString());

            // New chunk start hoyega last two sentences se
            currentChunk = new StringBuilder();

            int start = Math.max(0, currentSentences.size() - SENTENCE_OVERLAP);//yaha se hum second last sentence ka index lere hain

            List<String> overlapSentences =
                    currentSentences.subList(start, currentSentences.size());//yaha pe currentSentences wali list mei se last two index wale 
                    //sentences return hojayenge ek sublist ki form mei jiska naam overlapSentences hai.

            currentSentences = new ArrayList<>(overlapSentences);  

            for (String s : currentSentences) {
                currentChunk.append(s).append(" ");
            }
        }

        currentChunk.append(sentence).append(" ");
        currentSentences.add(sentence);
    }

    if (currentChunk.length() > 0) {
        chunks.add(currentChunk.toString());
    }

    return chunks;
    }
}























//******************************************************************************************************************************** */
//character based overlapping

// package com.example.Rag_storyBook.Service;
// import org.springframework.stereotype.Service;
// import java.util.*;
// @Service
// public class ChunkService {
//     private static final int CHUNK_SIZE = 2000;
//     private static final int OVERLAP_SIZE = 500;
//     public List<String> createChunks(String text){
//         List<String> chunks = new ArrayList<>();

//          String[] sentences = text.split("(?<=[.!?])\\s+");

//     StringBuilder current = new StringBuilder();

//     for (String sentence : sentences) {

//         if (current.length() + sentence.length() > CHUNK_SIZE) {
//             chunks.add(current.toString());

//             // overlap
//             String overlap = current.substring(
//                 Math.max(0, current.length() - OVERLAP_SIZE)
//             );

//             current = new StringBuilder(overlap);
//         }

//         current.append(" ").append(sentence);
//     }

//     if (!current.isEmpty()) {
//         chunks.add(current.toString());
//     }

//     return chunks;
//     }
// }



