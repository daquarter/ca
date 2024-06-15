package scheduler;

import java.io.*;
import java.util.*;


public class FileCreate {
	public static void main(String[] args) {
		File f = new File("text.txt");
		
		try {
			if(f.createNewFile()) {
				System.out.println("Success : "+f.getName());
				
				FileWriter fw = new FileWriter(f);
				fw.write("1,4\n2,6\n3,3\n4,7");
				fw.close();
			}else {
				System.out.println("file is already");
				
				Scanner sc = new Scanner(f);
				while(sc.hasNextLine()) {
					System.out.println(sc.nextLine());
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
