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

    ///  Initial Menu Method
    private static void generateMenu() {
        System.out.println("-----------------------");
        System.out.println("Welcome to PDF Generator");
        System.out.println("-----------------------");
    }

    ///  Method to check if variable is empty

    private static String checkVariableIsEmpty(Scanner scan) {

        String input = "";

        do {
            input = scan.nextLine();

            if (input.isEmpty()) {
                System.out.println("The value cannot be empty. You need to try again please:");
            }
        } while (input.isEmpty());

        return input;
    }


    public static void main(String[] args) {

        ///  Instance Classes
        Scanner scan = new Scanner(System.in);
        Document document = new Document();

        try {

            ///  Call menu method
            generateMenu();

            ///  Create variables for use in console
            String nameFile         = "";
            String titleDocument    = "";
            String textDocument     = "";

            /// Create interactive console
            System.out.println("Insert nameFile here:");
            nameFile = checkVariableIsEmpty(scan);

            System.out.println("Insert title document:");
            titleDocument = checkVariableIsEmpty(scan);

            System.out.println("Insert text document:");
            textDocument = checkVariableIsEmpty(scan);

            ///  Check if user insert .pdf together name file
            if (!nameFile.contains(".pdf")) {
                nameFile += ".pdf";
            }

            ///  Break line space
            System.out.println("\n");

            /// Show info preview
            System.out.println("-----------------------");
            System.out.println("Preview document file");
            System.out.println("-----------------------");

            ///  Testing if content was successfully added
            System.out.println(nameFile);
            System.out.println(titleDocument);
            System.out.println(textDocument);

            ///  Get info and write this in new PDF file
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