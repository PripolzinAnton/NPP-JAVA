package ru.ivmiit;

import java.io.*;
import java.util.*;

public class Homework2 {
	
	public static Scanner in = new Scanner (System.in);
	public static PrintStream out = System.out;
	
	static int count;
	static final int N = 100;
	static String Array[] = new String [N];
	
	public static void AddTail() {
		String element;
		element=in.nextLine();
		Array[count]=element;
		count++;
	}

	public static void AddHead() {
		String element;
		if (count==0) {
			AddTail();
			}
		else {
			element=in.nextLine();
		for (int i=count;i>0;i--) {
			Array[i]=Array[i-1];
		}
		Array[0]=element;
		count++;
		}
	}

	public static void Delete() {
		int number;
		number = in.nextInt();
		for (int i = number;i<count;i++) {
		Array[i]=Array[i+1];
		}
		count--;
	}
	
	public static void RecordInFile() throws IOException {
		File file = new File("output.txt");
		if(!file.exists()){
	          file.createNewFile();
	    }
		PrintWriter outf = new PrintWriter(file.getAbsoluteFile());
		for (int i=0;i<count;i++) {
			outf.println(Array[i]);
		}
		outf.close();
	}
	
	public static void RecordOutFile() throws IOException {
		File file = new File("input.txt");
		if(!file.isFile()) {
	           file.createNewFile();
	           System.out.println("LOG: File 'input.txt' was created in src directory, please repeat your change");
	           Menu();
	    }
		else {
			StringBuilder str = new StringBuilder();
			String strtemp;
			BufferedReader br = new BufferedReader (new FileReader("input.txt"));
			int count=0;
			while ((strtemp = br.readLine())!=null && count<N) {
				str.append(strtemp);
				str.append("\n");
				Array[count]=str.toString();
				count++;
			}
			in.close();
		}
		for (int i=0;i<count;i++) {
			out.println(Array[i]);
		}
	}
	
	public static boolean Comp (String Arr, String ArrNext) {
		int countL; boolean flag1 = false, flag2 = false;
		if (Arr.length()<ArrNext.length()) {
			countL=Arr.length();
		}
		else countL = ArrNext.length();
		for (int i=0;i<countL;i++) {
			if (Arr.charAt(i)<ArrNext.charAt(i)) {
				flag1 = false; flag2 = false; break;
			}
			else if (Arr.charAt(i)>ArrNext.charAt(i)) {
				flag1 = true; flag2 = false; break;
			}
			else flag2 = true;
		}
		if (flag2) {
			if (countL==Arr.length())
				flag1=true;
			else flag1=false;
		}
		return flag1;
	}
	
	public static void Sort() {
		for (int i=count-1;i>=0;i--) {
			for (int j=0; j<i;j++) {
				if (Comp(Array[j],Array[j+1]))
			      {
			        String tmp = Array[j];
			        Array[j] = Array[j + 1];
			        Array[j + 1] = tmp;
			      }
			}
			
		}
	}
	
	 public static void Menu() throws IOException {
		 int point = 0;
		 out.println("Enter 1. Count of elements in the Array");
		 out.println("Enter 2. Add element in Head ");
		 out.println("Enter 3. Add element in Tail ");
		 out.println("Enter 4. Delete element by number");
		 out.println("Enter 5. Record Array in File");
		 out.println("Enter 6. Record File in Array");
		 out.println("Enter 7. Sort of Array");
		 out.println("Enter 8. Exit");
		 while (point!=8) {
			 point=in.nextInt();
			 switch (point) {
			 case 1: out.println("Count:"+count); break;
			 case 2: AddHead(); break;
			 case 3: AddTail(); break;
			 case 4: Delete(); break;
			 case 5: RecordInFile(); break;
			 case 6: RecordOutFile(); break;
			 case 7: Sort(); break;
			 }
		 }
	 }
	public static void main(String[] args) throws IOException {
		count=0;
		Menu();
	}

}
