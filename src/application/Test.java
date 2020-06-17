package application;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String x ="xxx aaa bbb";
		String s=x;
		String a="aaa";
		s =x.replace("aaa", "bbb");
		s=s.replace("bbb", "aaa");
		System.out.println(s);
	/*Scanner s =new Scanner(str);
		String str2=s.nextLine();
		System.out.println(str2.contains("[["));

		while(str2.contains("[["))
			count++;

		System.out.println(count);
		for (int i = 0; i < count; i++) {
			String b = str2.replace(str2.substring(str2.indexOf("[["), str2.lastIndexOf("]]") + 2), "Ali");
			System.out.println(b);
		}*/

	}
}
