import java.io.FileNotFoundException;

public class Interpreter
{
	public static void main(String[] args) {
		try {

			System.out.print("FIrst Program Output: ");
			Parser p = new Parser ("src/test1.lua");
			Program program1 = p.parse();
			program1.execute();
			System.out.println();

			System.out.print("Second Program Output: ");
			Parser p1 = new Parser ("src/test2.lua");
			Program program2 = p1.parse();
			program2.execute();
			System.out.println();

			System.out.print("Third Program Output: ");
			Parser p2 = new Parser ("src/test3.lua");
			Program program3 = p2.parse();
			program3.execute();
			System.out.println();

			System.out.print("Fourth Program Output: ");
			Parser p3 = new Parser ("src/test4.lua");
			Program program4 = p3.parse();
			program4.execute();
		}
		catch (ParserException e) {
			System.out.println (e.getMessage());
		}
		catch (LexicalException e) {
			System.out.println (e.getMessage());
		}
		catch (IllegalArgumentException e) {
			System.out.println (e.getMessage());
		}
		catch (FileNotFoundException e) {
			System.out.println ("source file is not found");
		}
		catch (Exception e) {
			System.out.println ("unknown error occurred - terminating");
		}
	}
}