package Ex1;


import java.util.Arrays;

public class Sandbox
{
	static void main(String[] args)
	{
//		String testString = "bob \n rob\nasdasd\n  ";
//		IO.println(testString);
//		IO.println(Ex1.whitespaceRemover(testString));

		String test = "X^2+x -1.1";
		double[] data = Ex1.getPolynomFromString(test);
		IO.println(Arrays.toString(data));
	}
}
