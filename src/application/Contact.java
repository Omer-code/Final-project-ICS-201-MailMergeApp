/*package application;import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.stage.FileChooser;

public class Contact {

	public static void main(String [] args) throws FileNotFoundException{
		MainControler m =new MainControler();

		Scanner ContactsFile =new Scanner(new FileInputStream(m.getPath()));
		String Tags =ContactsFile.nextLine();

		Scanner split =new Scanner(Tags);
		split.useDelimiter(",");
		while(split.hasNext()){
			tags.add(split.next());
		}

		System.out.println(tags);
		System.out.println(tags.size());




		 while (ContactsFile.hasNextLine()) {

			 String information =ContactsFile.nextLine();
				Scanner split_info =new Scanner(information);
				split_info.useDelimiter(",");


		}
	}





}
*/