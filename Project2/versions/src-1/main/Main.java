
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package main;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));

		Operations.input(in);
		Operations.output(out);
	
		in.close();
		out.close();
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

