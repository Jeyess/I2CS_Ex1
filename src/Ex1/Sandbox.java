package Ex1;


import java.util.Arrays;

public class Sandbox
{
	static void main(String[] args)
	{
//		String testString = "bob \n rob\nasdasd\n  ";
//		IO.println(testString);
//		IO.println(Ex1.whitespaceRemover(testString));

//		String test = "X^4+3x -1.1";
//		double[] data = Ex1.getPolynomFromString(test);
//		double[] bData = {0};
//		IO.println(Arrays.toString(data) + " " + Arrays.toString(Ex1.derivative(data)));


		String test = "3.1x^2 +2.3x -1.1";
		double[] data = Ex1.getPolynomFromString(test);
		IO.println("gPFS : " + Arrays.toString(data));

		String test2 = "2.3x -1.1";
		double[] data2 = Ex1.getPolynomFromString(test2);
		IO.println("gPFS : " + Arrays.toString(data2));

		IO.println("mul : " + Arrays.toString(Ex1.mul(data, data2)));


//		String polyed = Ex1.poly(data);
//		IO.println("poly: " + polyed);
//
//		double[] xs = new double[]{-1.07272, -0.37097, 0.33078};
//		double[] ys = new double[]{0, -1.52661, 0};
//		double[] result = Ex1.polynomFromPoints(xs, ys);
//		IO.println(Arrays.toString(result));
//
//		IO.println(Ex1.equals(result, data));
//
//		IO.println(Ex1.length(data, -1, 3, 100));



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


		/**
		 * 	   double denom = (x1 - x2) * (x1 - x3) * (x2 - x3);
		 *     double A     = (x3 * (y2 - y1) + x2 * (y1 - y3) + x1 * (y3 - y2)) / denom;
		 *     double B     = (x3*x3 * (y1 - y2) + x2*x2 * (y3 - y1) + x1*x1 * (y2 - y3)) / denom;
		 *     double C     = (x2 * x3 * (x2 - x3) * y1 + x3 * x1 * (x3 - x1) * y2 + x1 * x2 * (x1 - x2) * y3) / denom;
		 *
		 *
		 *
		 *	   double denom = (x1 - x2) * (x1 - x3) * (x2 - x3);
		 *     double A     = (x3 * (y2 - y1) + x2 * (y1 - y3) + x1 * (y3 - y2)) / denom;
		 *     double B     = (y2 - y1) / (x2 -x1) - A * (x1 + x2);
		 *     double C     = y1 - A * x1^2 - B * X1;
		 */
	}
}
