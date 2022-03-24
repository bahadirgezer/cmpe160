
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package main;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		
		//
		// Main receives two arguments: path to input file and path to output file.
		// You can assume that they will always be provided, so no need to check them.
		// Scanner and PrintStream are already defined for you.
		// Use them to read input and write output.
		// 
		// Good Luck!
		// 
		
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));

		int N = in.nextInt();
		
		for (int i=0; i<N; i++) {
			String input = in.nextLine();
			RegularExpressions.inputParser(input);
		}
		
		Operations.portOutput(out);
		

		in.close();
		out.close();
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

