package Ex1;


import java.util.Arrays;

public class Sandbox
{
	static void main(String[] args)
	{
//		String testString = "bob \n rob\nasdasd\n  ";
//		IO.println(testString);
//		IO.println(Ex1.whitespaceRemover(testString));

		String test = "X^4+3x -1.1";
		double[] data = Ex1.getPolynomFromString(test);
		double[] bData = {0};
		IO.println(Arrays.toString(data) + " " + Arrays.toString(Ex1.derivative(data)));


//		String test = "X^4+3x -1.1";
//		double[] data = Ex1.getPolynomFromString(test);
//
//		String test2 = "5X^4+4x^2+69.3";
//		double[] data2 = Ex1.getPolynomFromString(test2);
//
//		IO.println(Arrays.toString(data));
//		IO.println(Arrays.toString(data2));
//
//		double[] result = Ex1.add(data, data2);
//		IO.println(Arrays.toString(result));
	}
}
