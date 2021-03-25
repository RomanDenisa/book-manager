package utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import domain.Book;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class PdfExport {

    public static void export(List<Book> books) {

        OutputStream file = null;
        try {
            file = new FileOutputStream(new File("books.pdf"));

            Document document = new Document();

            PdfWriter.getInstance(document, file);

            document.open();

            document.add(new Paragraph("These are the books you've read!"));
            for(Book b:books){
                document.add(new Paragraph(b.toString()));
            }

            document.addTitle("Books read");

            document.close();


        } catch (
                Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (file != null) {
                    file.close();
                }
            } catch (IOException io) {
                System.out.println(io.getMessage());
            }

        }

    }
}
