package org.geradorpdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static void generateMenu() {
        System.out.println("-----------------------");
        System.out.println("Welcome to PDF Generator");
        System.out.println("-----------------------");
    }

    /*
    private static void checkVariableIsEmpty(Scanner scan, String args) {
         do {
            args = scan.nextLine();
        } while (args.isEmpty());
    }
    */

    public static void main(String[] args) {

        ///  Instance Classes
        Scanner scan = new Scanner(System.in);
        Document document = new Document();

        try {

            ///  Call menu method
            generateMenu();

            ///  Create variables for use in console
            String nameFile = "";
            String titleDocument = "";
            String textDocument = "";

            /// Create interactive console
            System.out.println("Insert nameFile here:");
            nameFile = scan.nextLine();

            System.out.println("Insert title document:");
            titleDocument = scan.nextLine();

            System.out.println("Insert text document:");
            textDocument = scan.next();

            System.out.println(nameFile);
            System.out.println(titleDocument);
            System.out.println(textDocument);

            PdfWriter.getInstance(document, new FileOutputStream(nameFile));
            document.open();
            document.add(new Paragraph(titleDocument));
            document.add(new Paragraph(textDocument));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            scan.close();
            document.close();
        }
    }
}