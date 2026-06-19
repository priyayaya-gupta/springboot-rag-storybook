package com.example.Rag_storyBook.Service;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.io.File;

public class PdfReader {

    public static void main(String[] args) throws Exception {

    // String chunk = "The rabbit entered the forest.";

   

    // System.out.println("Dimension: " + embedding.size());
    // System.out.println("First value: " + embedding.get(0));
     PDDocument document = Loader.loadPDF(
    new File("C:\\Users\\Isha Gupta\\OneDrive\\Desktop\\Grandma's Bag of Stories by Sudha Murthy.pdf")
);

PDFTextStripper stripper = new PDFTextStripper();

String text = stripper.getText(document);
 ChunkService chunkService= new ChunkService();//chunk ka object bana rhe hain
    List<String> chunks = chunkService.createChunks(text);//list type ka ek chunks
    //variable hai jisme hum text ke chunks daalre hain
EmbeddingService embeddingService = new EmbeddingService();//yaha embedding service ka object banaya hai
 List<Double> embedding = embeddingService.generateEmbedding(chunks.get(0));
System.out.println("chunk: ");
System.out.println(chunks.get(0));
System.out.println("Total Pages: " + document.getNumberOfPages());
System.out.println("Embedding size: " + embedding.size());
Files.writeString(
    Path.of("C:\\Users\\Isha Gupta\\OneDrive\\Desktop\\output.txt"),
    text
);

document.close();

System.out.println("Done!");
// System.out.println(text.substring(1000, 3000));

}
}
  





