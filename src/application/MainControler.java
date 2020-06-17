package application;

import javafx.application.Platform;

/*Features
1-Design of first window
2-Fast Searching process for loading Contacts AND Templates


*/

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuItem;
//Enter the value in Mile to Convert it to KM
public class MainControler {

	//////// instance variables***********************
	private static String path;
	private String path2;
	private String path3;
	private String path4;
	
	private static String messageToBeSent;
	private static int coulmns;
	private static int rows;
	private static String userName;
	private static String password;
	private static String[][] contents;
	private static ArrayList<String> tags = new ArrayList<String>(15);
	private static ArrayList<String> RowsContacts = new ArrayList<String>(15);

	////// comboBox **************
	@FXML
	ComboBox<String> comboBox = new ComboBox<String>();

	////////// labels*********
	@FXML
	private Label avalabality;
	@FXML
	Label Account;
	@FXML 
	Label checkPDF;


	////////////// Buttons*****************
	@FXML
	Button MailMerge;
	@FXML
	Button Refresh;
	@FXML
	Button CreatPDF;
	@FXML
	Button SendEmail;

	/////////// Text
	@FXML
	TextArea area;
	@FXML
	TextField Title;
	@FXML
	TextField TitlePDF;

	////////////// Lists
	@FXML
	public ListView<String> listView;

	//// Boxes ***********
	@FXML
	VBox root;

	/////////////// MenuItem *************
	@FXML
	MenuItem ContactManeger;

	
	//Load Contacts method which will open dialog window to search for CSV file
	public void SelectFile(ActionEvent e) throws IOException {

		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(new ExtensionFilter("CSV Files", "*.csv"));
		fc.setTitle("Choose CSV File to open");
		File selectedFile = fc.showOpenDialog(null);
		
		
		if (selectedFile != null) {
		
			path = selectedFile.getAbsolutePath();

			avalabality.setText("Loaded Succesfully");
			avalabality.setTextFill(Color.web("#000080"));
			avalabality.setFont(new Font("Arial", 19));

			Scanner ContactsFile = new Scanner(new FileInputStream(path));
			String Tags = ContactsFile.nextLine();

			Scanner split = new Scanner(Tags);
			split.useDelimiter(",");

			while (split.hasNext()) {
				tags.add(split.next());
			}

			coulmns = tags.size();


			for (int i = 0; i < tags.size(); i++) {

				comboBox.getItems().add(tags.get(i));
			}

			while (ContactsFile.hasNextLine()) {
				String temp = ContactsFile.nextLine();
				RowsContacts.add(temp);
				rows++;
			}
			ContactsFile.close();

			contents = new String[rows][coulmns];
			Scanner ContactsFile2 = new Scanner(new FileInputStream(path));
			ContactsFile2.nextLine();

			Scanner split2 = null;
			for (int i = 0; i < rows; i++) {
				String ContentLine = ContactsFile2.nextLine();
				split2 = new Scanner(ContentLine);
				split2.useDelimiter(",");
				for (int j = 0; j < coulmns; j++) {
					contents[i][j] = split2.next();
				}
			}

			ContactsFile2.close();
			split.close();
			split2.close();


			if (avalabality.getText() == "Loaded Succesfully") {
				ContactManeger.setDisable(true);
				MailMerge.setDisable(false);
				area.setDisable(false);
			}

		} else
			path = null;

	}

	//this method shows the of mail merge through window
	@FXML
	public void Help() throws IOException {
		Parent Awindow = FXMLLoader.load(getClass().getResource("/application/help.fxml"));
		Scene scene = new Scene(Awindow, 350, 200);

		Stage newWinsow = new Stage();


		newWinsow.initModality(Modality.APPLICATION_MODAL);
		newWinsow.setTitle("Help");
		newWinsow.setScene(scene);
		newWinsow.setResizable(false);
		newWinsow.show();
	}

	//THis method will exit the platform 
	@FXML
	private void exitMethode() {
		Platform.exit();
		System.exit(0);
	}

	//Starting mail merge process(Chooses to create PDF file and Send Email
	@FXML
	public void MailMergeProccess() throws IOException {
		try {
			messageToBeSent = area.getText();
			Parent Awindow = FXMLLoader.load(getClass().getResource("/application/process.fxml"));
			Scene scene = new Scene(Awindow, 1000, 600);

			Stage newWinsow = new Stage();


			PasswordDialog windowlogin = new PasswordDialog();
			windowlogin.start(newWinsow);
			userName = PasswordDialog.login.getUserName();
			password = PasswordDialog.login.getPassword();



			//this loop will show you if the user name was incorrect 
			
while (!(userName.contains("@kfupm.edu.sa"))) {

	
		Parent Awindow2 = FXMLLoader.load(getClass().getResource("/application/WrongUsername.fxml"));
		Scene scene2 = new Scene(Awindow2, 350, 200);
		Stage newWinsow2 = new Stage();
		newWinsow2.initModality(Modality.APPLICATION_MODAL);
		newWinsow2.setTitle("Wrong user name");
		newWinsow2.setScene(scene2);
		newWinsow2.setResizable(false);
		newWinsow2.showAndWait();
	
		PasswordDialog windowlogin2 = new PasswordDialog();
		windowlogin2.start(newWinsow);
		userName = PasswordDialog.login.getUserName();
		password = PasswordDialog.login.getPassword();

}
			
			newWinsow.initModality(Modality.APPLICATION_MODAL);
			newWinsow.setTitle("Mail Merge Process");
			newWinsow.setScene(scene);
			newWinsow.setResizable(false);
			newWinsow.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	//this will create PDF file by choosing the folder and name of file
	@FXML
	public void PDFmaker() {
		try {
		
			FileChooser file2 = new FileChooser();
			file2.setTitle("Choose The Name And Location of PDF file");
			File selectFile = file2.showSaveDialog(null);
			
			if (selectFile != null) {
				path3 = selectFile.getAbsolutePath();
		
			}
			
			
			
			OutputStream file = new FileOutputStream(new File(path3 + ".pdf"));
			Scanner in = null;
			Document document = new Document();
			PdfWriter.getInstance(document, file);
			document.open();
			for (int i = 0; i < rows; i++)
				for (int j = 0; j < coulmns; j++)
			System.out.println(rows);
			for (int i = 0; i < rows; i++) {
				String message = messageToBeSent;
				 in = new Scanner(message);
				while (in.hasNext()) {
					String word = in.next();
					System.out.println(word);
					for (int j = 0; j < coulmns; j++) {
						if (word.equals(tags.get(j)) || word.equals(tags.get(j) + ",") || word.equals("," + tags.get(j))
								|| word.equals(tags.get(j) + ".")) {
							message = message.replace(word, contents[i][j]);
						}
					}
				}
				document.add(new Paragraph(userName + "\n" + message));
				// go to new page
				document.newPage();

			}
			in.close();
			document.close();
			file.close();
			checkPDF.setText("PDF was created successfully");
			
		} catch (Exception e) {

			e.printStackTrace();

		}

	}
	
	//Sending email method

	@FXML
	private void EMAILsender() {
		Scanner in = null;
		
		// [[EMAIL_ADDRESS]]
		String toEmail = "s201769570@kfupm.edu.sa";

		int EMAIL_coulmn = 0;
		// to know the email column
		for (int i = 0; i < coulmns; i++) {
			if (tags.get(i).equals("[[EMAIL_ADDRESS]]"))
				EMAIL_coulmn = i;
		}
		// to send to all emails in the contacts
		for (int k = 0; k < rows; k++) {

			String message = messageToBeSent;
			 in = new Scanner(message);
			while (in.hasNext()) {
				String word = in.next();

				for (int j = 0; j < coulmns; j++) {
					if (word.equals(tags.get(j)) || word.equals(tags.get(j) + ",") || word.equals("," + tags.get(j))
							|| word.equals(tags.get(j) + ".")) {
						message = message.replace(word, contents[k][j]);
					}
				}
			}

			toEmail = contents[k][EMAIL_coulmn];

			SendEmailOffice365 mailer = new SendEmailOffice365(userName, password, toEmail, Title.getText(), message);
			if (mailer.sendEmail())
				System.out.println("Email was sent to: " + toEmail + "  Successfully");
			else
				System.out.println("Error, email wasn't sent to " + toEmail);

			Account.setText("The Message was Sent Successfully from: " + userName);

		}
		 in.close();
	}
	//Place tags method

	@FXML
	private void placeTags() {

		int paint = area.getCaretPosition();
		area.insertText(paint, (" " + comboBox.getValue()));

	}

	//Load template method
	@FXML
	private void laodtamplate() throws FileNotFoundException {

		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(new ExtensionFilter("TXT Files", "*.txt"));
		File selectedFile = fc.showOpenDialog(null);
		String Tamplatetext = null;
		if (selectedFile != null) {
			path = selectedFile.getAbsolutePath();
			Tamplatetext = new Scanner(new File(path)).useDelimiter("\\Z").next();
		}

		int paint = area.getCaretPosition();
		area.insertText(paint, Tamplatetext);
	}


//a method to show the contact
	@FXML
	private void showListView() {
		ObservableList<String> items = FXCollections.observableArrayList(RowsContacts);
		ListView<String> list = new ListView<>(items);

		list.setCellFactory(TextFieldListCell.forListView());
		list.setEditable(true);



		VBox root = new VBox(list);

		Scene scene = new Scene(root);
		Stage primaryStage = new Stage();
		primaryStage.setMinHeight(300);
		primaryStage.setMinWidth(1000);
		primaryStage.setResizable(false);
		primaryStage.setTitle("test!");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	//Saving template method 
	@FXML
	private void saveTampate() throws IOException {

		FileChooser file = new FileChooser();
		File selectFile = file.showSaveDialog(null);

		if (selectFile != null) {
			path2 = selectFile.getAbsolutePath();
			FileOutputStream outTamplate = new FileOutputStream(new File(path2 + ".txt"));
			PrintWriter pwrit = new PrintWriter(outTamplate);
			pwrit.print(area.getText());
			pwrit.close();
			outTamplate.close();

		}

	}

	//A method to enable Send Email bottom
	@FXML
	private void RefreshMM() {

		if (Title != null)
			SendEmail.setDisable(false);


	}

	//Contact Manager method
	@FXML
	private void ContactManage() throws IOException {
	System.out.println("chick excel method");
		
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(new ExtensionFilter("CSV Files", "*.csv"));
		fc.setTitle("Choose CSV File to open");
		File selectedFile = fc.showOpenDialog(null);
		path4 = selectedFile.getAbsolutePath();

		Desktop.getDesktop().open(new File(path4));
		
	
	}
	

}
