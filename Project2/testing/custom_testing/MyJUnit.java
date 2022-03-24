import org.junit.jupiter.api.Test;
import static org.junit.Assert.fail;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import main.Main;

class MyJUnit {

	void singleCase(int n) throws FileNotFoundException{
		Scanner in = new Scanner(new File("custom_testing/myinput" + n + ".txt"));
		PrintStream out = new PrintStream(new File("custom_testing/myinput.txt"));
		while(in.hasNextLine()) {
			String line = in.nextLine();
			out.println(line);
		}
		
		Main.main(new String[] {"custom_testing/myinput.txt", "custom_testing/myoutput.txt"});
		
		in = new Scanner(new File("custom_testing/myoutput.txt"));
		Scanner inCorrect = new Scanner(new File("custom_testing/myoutput" + n + ".txt"));
		int lineNumber = 1;
		while(in.hasNextLine() && inCorrect.hasNextLine()) {
			String line1 = in.nextLine();
			String line2 = inCorrect.nextLine();
			if(!line1.equals(line2)) {
				fail("Wrong answer on case " + n + ", line " + lineNumber);
			}
			lineNumber++;
		}
		
		if(in.hasNextLine() || inCorrect.hasNextLine()) {
			fail("Wrong answer on case " + n +", number of lines do not match");
		}

		
	}
	
	@Test
	void test() throws FileNotFoundException{
		for(int i = 1; i <= 14; i++) {
			singleCase(i);
		}
	}

}
