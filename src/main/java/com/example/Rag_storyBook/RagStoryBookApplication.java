// package com.example.Rag_storyBook;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication
// public class RagStoryBookApplication {

// 	public static void main(String[] args) {
// 		SpringApplication.run(RagStoryBookApplication.class, args);
// 	}

// }

package com.example.Rag_storyBook;
// import org.apache.pdfbox.Loader;
// import com.example.Rag_storyBook.Service.ChunkService;
// import com.example.Rag_storyBook.Service.EmbeddingService;
// import com.example.Rag_storyBook.Service.QdrantService;
// import java.io.File;
// import org.apache.pdfbox.pdmodel.PDDocument;
// import org.apache.pdfbox.text.PDFTextStripper;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.apache.pdfbox.Loader;
// import com.example.Rag_storyBook.Service.*;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.util.List;


@SpringBootApplication
public class RagStoryBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(
            RagStoryBookApplication.class,
            args
        );
    }
}

// @SpringBootApplication
// public class RagStoryBookApplication implements CommandLineRunner {

//     @Autowired
//     private EmbeddingService embeddingService;

//     @Autowired
//     private QdrantService qdrantService;

//     @Autowired 
//     private ChatService chatService;

//     public static void main(String[] args) {
//         SpringApplication.run(RagStoryBookApplication.class, args);
//     }

//     @Override
// public void run(String... args) throws Exception  {

//         PDDocument document = Loader.loadPDF(
//         new File("C:\\Users\\Isha Gupta\\OneDrive\\Desktop\\Grandma's Bag of Stories by Sudha Murthy.pdf")
// );

// PDFTextStripper stripper = new PDFTextStripper();

// String text = stripper.getText(document);

// ChunkService chunkService = new ChunkService();

// List<String> chunks =
//         chunkService.createChunks(text);

// // System.out.println(
// //         "Total Chunks = " + chunks.size()
// // );

// // for (int i = 0; i < chunks.size(); i++) {

// //     String chunk = chunks.get(i);

// //     List<Double> embedding =
// //             embeddingService.generateEmbedding(chunk);

// //     qdrantService.storeChunk(
// //             chunk,
// //             embedding
// //     );

// //     System.out.println(
// //             "Chunk " + (i + 1)
// //             + " uploaded"
// //     );
// // }

// document.close();

//         System.out.println("DONE");

//         String query = "summarise the first chapter.";
// List<Double> queryEmbedding =
//         embeddingService.generateEmbedding(query);
//         List<String> chunkss = qdrantService.searchChunks(queryEmbedding);

//                 String answer = chatService.askQuestion(query , chunkss);
//                 System.out.println(answer);
//         // System.out.println("Chunks Found = " + 
//         // chunkss.size());

//         // for(String chunk:chunkss){
//         //         System.out.println(chunk);
//         // }





// //     String result =
// //         qdrantService.searchChunks(queryEmbedding);
// //         System.out.println(result);
//     }

// }
