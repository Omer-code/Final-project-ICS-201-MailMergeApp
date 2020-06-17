package application;


/**
 * @(#)MailMerge.java
 *
 * MailMerge application
 *
 * @author
 * @version 1.00 2010/5/15
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.application.Application;

public class MailMerge {

    public static void main(String[] args) {

    	// Get the portal UserName and Password
    	Application.launch(PasswordDialog.class, args);
    
    	String userName = PasswordDialog.login.getUserName();
    	String password = PasswordDialog.login.getPassword();

    	//type a recipient email, it could be any valid email
    	String toEmail = "ahmadaa977albogrin@gmail.com";
    	String subject = "A message from a caring friend";


    	
		 // messageToBeSent is a string.
		
		String messageToBeSent = "Hello,\n\n If you get this, it means that you have successfully sent an email from your java program\n\n Thank you!";


    	//This prompt the user to enter a password
    	//password = new PasswordDialog().getPasswordField().toString();

		//sends an email
		SendEmailOffice365 mailer =  new SendEmailOffice365(userName, password , toEmail, subject, messageToBeSent);
		if (mailer.sendEmail())
			System.out.println("Email was sent to: " + toEmail + "  Successfully");
		else
			System.out.println("Error, email wasn't sent to " + toEmail);


	//to make a PDF refer to the method makePDF
	 if (makePDF(messageToBeSent))
		 System.out.println("pdf file created successfully.");
	 else
		 System.out.println("Error creating pdf file.");

    }

	/**
	 * This method is an example of how to create a PDF document with multiple pages
	 */
    public static boolean makePDF(String messageToBeSent)
    {
    	System.out.println("Any thing");
    	  try {
    	    //You should change the file name
            OutputStream file = new FileOutputStream(new File("Test.pdf"));

	    //keep the following 3 lines unchanged
            Document document = new Document();
            PdfWriter.getInstance(document, file);
            document.open();

            //generate 10 pages
            for(int i=0; i<10;i++)
            {
                document.add(new Paragraph(messageToBeSent));
				//go to new page
				document.newPage();
            }
            document.close();
            file.close();
            return true;
        }catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
}
