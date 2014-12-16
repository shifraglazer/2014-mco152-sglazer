package glazer.acm2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class num {
public static void main(String args[]){
	 PrintWriter out;
	try {
		out = new PrintWriter(new FileWriter("output.txt"));
	
	out.println(10000);
	for(int i=1;i<=10000;i++){
		out.println(i+" "+i);
		
	}
	out.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
