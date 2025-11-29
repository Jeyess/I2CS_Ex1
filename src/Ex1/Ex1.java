package Ex1;


import java.util.Arrays;
import java.util.regex.*;


/**
 * Introduction to Computer Science 2026, Ariel University,
 * Ex1: arrays, static functions and JUnit
 * https://docs.google.com/document/d/1GcNQht9rsVVSt153Y8pFPqXJVju56CY4/edit?usp=sharing&ouid=113711744349547563645&rtpof=true&sd=true
 * <p>
 * This class represents a set of static methods on a polynomial functions - represented as an array of doubles.
 * The array {0.1, 0, -3, 0.2} represents the following polynomial function: 0.2x^3-3x^2+0.1
 * This is the main Class you should implement (see "add your code below")
 *
 * @author boaz.benmoshe
 */
public class Ex1
{
	//<editor-fold desc="Initial functions">
	/**
	 * Epsilon value for numerical computation, it serves as a "close enough" threshold.
	 */
	public static final double EPS = 0.001; // the epsilon to be used for the root approximation.
	/**
	 * The zero polynomial function is represented as an array with a single (0) entry.
	 */
	public static final double[] ZERO = {0};


	/**
	 * Provides a simpler way to print lines, for debugging purposes.
	 */
	public static void pl(String text)
	{
		IO.println(text);
	}


	/**
	 * Computes the f(x) value of the polynomial function at x.
	 *
	 * @param poly - polynomial function
	 * @param x    - x position
	 * @return f(x) - the polynomial function value at x.
	 * <p>
	 * Test Link {@link Ex1Test#testF()}
	 */
	public static double f(double[] poly, double x)
	{
		double ans = 0;
		for (int i = 0; i < poly.length; i++)
		{
			double c = Math.pow(x, i);
			ans += c * poly[i];
		}
		return ans;
	}


	/**
	 * Given a polynomial function (p), a range [x1,x2] and an epsilon eps.
	 * This function computes an x value (x1<=x<=x2) for which |p(x)| < eps,
	 * assuming p(x1)*p(x2) <= 0.
	 * This function should be implemented recursively.
	 *
	 * @param p   - the polynomial function
	 * @param x1  - minimal value of the range
	 * @param x2  - maximal value of the range
	 * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
	 * @return an x value (x1<=x<=x2) for which |p(x)| < eps.
	 * <p>
	 * Test Link {@link Ex1Test}
	 */
	public static double root_rec(double[] p, double x1, double x2, double eps)
	{
		double f1 = f(p, x1);
		double x12 = (x1 + x2) / 2;
		double f12 = f(p, x12);
		if (Math.abs(f12) < eps)
		{
			return x12;
		}
		if (f12 * f1 <= 0)
		{
			return root_rec(p, x1, x12, eps);
		}
		else
		{
			return root_rec(p, x12, x2, eps);
		}
	}
	//</editor-fold>


	//<editor-fold desc="polynomFromPoints NC!">
	//TODO: fix the deviations resulting from double operations
	/**
	 * This function computes a polynomial representation from a set of 2D points on the polynom.
	 * The solution is based on: //	http://stackoverflow.com/questions/717762/how-to-calculate-the-vertex-of-a-parabola-given-three-points
	 * Note: this function only works for a set of points containing up to 3 points, else returns null.
	 *
	 * @param xx - x positions array
	 * @param yy - y positions array
	 * @return an array of doubles representing the coefficients of the polynom.
	 * <p>
	 * Test Link {@link Ex1Test}
	 *
	 * 	   double denom = (x1 - x2) * (x1 - xx[2]) * (x2 - xx[2]);
	 *     double A     = (xx[2] * (yy[1] - yy[0]) + x2 * (yy[0] - yy[2]) + x1 * (yy[2] - yy[1])) / denom;
	 *     double B     = (xx[2]*xx[2] * (yy[0] - yy[1]) + x2*x2 * (yy[2] - yy[0]) + x1*x1 * (yy[1] - yy[2])) / denom;
	 *     double C     = (x2 * xx[2] * (x2 - xx[2]) * yy[0] + xx[2] * x1 * (xx[2] - x1) * yy[1] + x1 * x2 * (x1 - x2) * yy[2]) / denom;
	 *
	 */
	public static double[] polynomFromPoints(double[] xx, double[] yy)
	{
		double[] ans = null;
		int lx = xx.length;
		int ly = yy.length;
		if (xx != null && yy != null && lx == ly)
		{
			if (lx == 2)
			{
				double A = (yy[1] - yy[0]) / (xx[1] - xx[0]);
				double B = yy[0] - A * xx[0];
				ans = new double[]{B, A};
			}
			else
			{
				double denom = (xx[0] - xx[1]) * (xx[0] - xx[2]) * (xx[1] - xx[2]);
	      		double A     = (xx[2] * (yy[1] - yy[0]) + xx[1] * (yy[0] - yy[2]) + xx[0] * (yy[2] - yy[1])) / denom;
	     		double B     = (xx[2] * xx[2] * (yy[0] - yy[1]) + xx[1] * xx[1] * (yy[2] - yy[0]) + xx[0] * xx[0] * (yy[1] - yy[2])) / denom;
	     		double C     = (xx[1] * xx[2] * (xx[1] - xx[2]) * yy[0] + xx[2] * xx[0] * (xx[2] - xx[0]) * yy[1] + xx[0] * xx[1] * (xx[0] - xx[1]) * yy[2]) / denom;
				ans = new double[]{C, B, A};
			}
		}
		return ans;
	}
	//</editor-fold>


	//<editor-fold desc="equals">
	/**
	 * Two polynomials functions are equal if and only if they have the same values f(x) for n+1 values of x,
	 * where n is the max degree (over p1, p2) - up to an epsilon (aka EPS) value.
	 *
	 * @param p1 - first polynomial function
	 * @param p2 - second polynomial function
	 * @return true if p1 represents the same polynomial function as p2.
	 * <p>
	 * Test Link {@link Ex1Test#testEquals()}
	 */
	public static boolean equals(double[] p1, double[] p2)
	{
		boolean ans = true;
		int highestLength = 0;
		if (p1.length > p2.length)
		{
			highestLength = p1.length;
		}
		else
		{
			highestLength = p2.length;
		}

		for (int i = 0; i < highestLength; i++)
		{
			if (Math.abs(f(p1, i) - f(p2, i)) > EPS)
			{
				return false;
			}
		}
		return ans;
	}
	//</editor-fold>


	//<editor-fold desc="poly">
	/**
	 * Computes a String representing the polynomial function.
	 * For example the array {2,0,3.1,-1.2} will be presented as the following String  "-1.2x^3 +3.1x^2 +2.0"
	 *
	 * @param poly - the polynomial function represented as an array of doubles
	 * @return String representing the polynomial function:
	 * <p>
	 * Test Link {@link Ex1Test}
	 */
	public static String poly(double[] poly)
	{
		String ans = "";
		if (poly.length == 0)
		{
			ans = "0";
		}
		else
		{
			for (int i = poly.length - 1; i > 1; i--)
			{
				ans = ans + poly[i] + "x^" + i;
				if (poly[i - 1] > 0)
				{
					ans += "+";
				}
			}

			/*
			  The following code is for the 2 last components, which the x wouldn't have the power sign (it can but not typically done)
			  and the last value which doesn't contain an x sign at all
			 */
			ans = ans + poly[1] + "x";
			if (poly[0] > 0)
			{
				ans += "+";
			}
			ans = ans + poly[0];
		}
		return ans;
	}
	//</editor-fold>


	//<editor-fold desc="sameValue">
	/**
	 * Given two polynomial functions (p1,p2), a range [x1,x2] and an epsilon eps. This function computes an x value (x1<=x<=x2)
	 * for which |p1(x) -p2(x)| < eps, assuming (p1(x1)-p2(x1)) * (p1(x2)-p2(x2)) <= 0.
	 *
	 * @param p1  - first polynomial function
	 * @param p2  - second polynomial function
	 * @param x1  - minimal value of the range
	 * @param x2  - maximal value of the range
	 * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
	 * @return an x value (x1<=x<=x2) for which |p1(x) - p2(x)| < eps.
	 * <p>
	 * Test Link {@link Ex1Test#testSameValue2()}
	 */
	public static double sameValue(double[] p1, double[] p2, double x1, double x2, double eps)
	{
		double ans = x1;
		/** add you code below

		 /////////////////// */
		return ans;
	}
	//</editor-fold>


	//<editor-fold desc="length">
	/**
	 * Given a polynomial function (p), a range [x1,x2] and an integer with the number (n) of sample points.
	 * This function computes an approximation of the length of the function between f(x1) and f(x2)
	 * using n inner sample points and computing the segment-path between them.
	 * assuming x1 < x2.
	 * This function should be implemented iteratively (none recursive).
	 *
	 * @param p                - the polynomial function
	 * @param x1               - minimal value of the range
	 * @param x2               - maximal value of the range
	 * @param numberOfSegments - (A positive integer value (1,2,...).
	 * @return the length approximation of the function between f(x1) and f(x2).
	 * <p>
	 * Test Link {@link Ex1Test}
	 */
	public static double length(double[] p, double x1, double x2, int numberOfSegments)
	{
		double ans = 0.0;
		double segmentLength = (x2 - x1) / numberOfSegments;
		//EPS is required because of double operation deviations
		for (double i = x1; i < x2 - EPS; i += segmentLength)
		{
			pl("i: "+ i);
			ans += Math.sqrt(Math.pow(f(p, i + segmentLength) - f(p, i), 2) + Math.pow(segmentLength, 2));
		}

		return ans;
	}
	//</editor-fold>


	//<editor-fold desc="area">
	/**
	 * Given two polynomial functions (p1,p2), a range [x1,x2] and an integer representing the number of Trapezoids between the functions (number of samples in on each polynom).
	 * This function computes an approximation of the area between the polynomial functions within the x-range.
	 * The area is computed using Riemann's like integral (https://en.wikipedia.org/wiki/Riemann_integral)
	 *
	 * @param p1                - first polynomial function
	 * @param p2                - second polynomial function
	 * @param x1                - minimal value of the range
	 * @param x2                - maximal value of the range
	 * @param numberOfTrapezoid - a natural number representing the number of Trapezoids between x1 and x2.
	 * @return the approximated area between the two polynomial functions within the [x1,x2] range.
	 * <p>
	 * Test Link {@link Ex1Test#testArea()}
	 */
	public static double area(double[] p1, double[] p2, double x1, double x2, int numberOfTrapezoid)
	{
		double ans = 0;
		/** add you code below

		 /////////////////// */
		return ans;
	}
	//</editor-fold>


	//<editor-fold desc="PolyFromString and related functions">
	/**
	 * This function takes a string and removes all whitespaces from it.
	 * Input: 	a bc\nd		(\n is a newline)
	 * Result: 	abcd
	 *
	 * @param p - a String.
	 * @return  - a String without whitespaces.
	 */
	public static String whitespaceRemover(String p)
	{
		return p.replaceAll("\\s", "");
	}


	/**
	 * This function takes a mathematical expression and expands it as much as possible (not currently implemented).
	 * Input:	x^3 + 3(x+2)
	 * Result: 	x^3+3x+6
	 * And then parses the expression, adds missing multipliers/exponents and then converts it into an array.
	 * Output: {"1x^3", "0x^2", "3x", "+6"}
	 *
	 * @param p - a String representing a function with brackets.
	 * @return 	- a parsed String array.
	 *
	 * Test Link {@link Ex1Test#testFromString()}
	 */
	public static String[] expressionOrganizer(String p)
	{
		//TODO: add an expression expander (make sure that regular values with exponents get calculated as well)

		String updatedString = p;

		//finds x chars without ^ to their right and then adds ^1 to them (will only occur once at most)
		Pattern pat = Pattern.compile("x(?!\\^)", Pattern.CASE_INSENSITIVE);
		Matcher mat = pat.matcher(updatedString);
		if (mat.find())
		{
			updatedString = updatedString.substring(0, mat.end()) + "^1" + updatedString.substring(mat.end());
		}


		//finds x chars without a value before them (i.e. multiplied by 1) and adds one before them
		pat = Pattern.compile("(?<!\\d)x", Pattern.CASE_INSENSITIVE);
		for (int i = 0; true;)
		{
			mat = pat.matcher(updatedString);
			if(mat.find())
			{
				updatedString = updatedString.substring(0, mat.start()) + "1" + updatedString.substring(mat.start());
				i++;
				continue;
			}
			break;
		}
		//pl(updatedString);


		//finds the highest exponent among all the x's.
		int highestExponent = 0;
		Pattern exponentPat = Pattern.compile("(?<=x\\^).+?(?=[+-]|$)", Pattern.CASE_INSENSITIVE);
		mat = exponentPat.matcher(updatedString);
		while(mat.find())
		{
			//pl(mat.group(0));
			if(Integer.parseInt(mat.group(0)) > highestExponent)
			{
				highestExponent = Integer.parseInt(mat.group(0));
			}
		}
		//pl("Highest Exponent: " + highestExponent);


		//parses the String based on '-' and '+' chars (first char included) into a pre-built String array according to it's exponent
		pat = Pattern.compile("(.|[+-]).*?(?=[+-]|$)");
		mat = pat.matcher(updatedString);
		String[] results = (new String[highestExponent + 1]);
		Arrays.fill(results, "0.0");
		Matcher exponentMat;

		while (mat.find())
		{
			//pl("ITEM: " + mat.group());
			exponentMat = exponentPat.matcher(mat.group());
			if (exponentMat.find())
			{
				results[results.length - 1 - Integer.parseInt(exponentMat.group())] = mat.group();
				//pl(Integer.parseInt(exponentMat.group()) + "\n");
				continue;
			}
			results[results.length - 1] = mat.group();
		}
		return results;
	}


	/**
	 * This function computes the array representation of a polynomial function from a String
	 * representation. Note: given a polynomial function represented as a double array,
	 * getPolynomFromString(poly(p)) should return an array equals to p.
	 *
	 * @param p - a String representing polynomial function.
	 * @return
	 *
	 * Test Link {@link Ex1Test#testFromString()}
	 */
	public static double[] getPolynomFromString(String p)
	{
		double[] ans = ZERO;//  -1.0x^2 +3.0x +2.0

		pl(p);
		String fixed = whitespaceRemover(p);
		pl(fixed);

		String[] fixedArr = expressionOrganizer(fixed);
		pl(Arrays.toString(fixedArr));

		int fixedLength = fixedArr.length;
		ans = new double[fixedLength];


		//.+?(?=x)
		Pattern pat = Pattern.compile(".+?(?=x)", Pattern.CASE_INSENSITIVE);
		Matcher mat;

		for (int i = 0; i < fixedLength; i++)
		{
			mat = pat.matcher(fixedArr[i]);
			mat.find();
			pl(mat.lookingAt() + " " + fixedArr[i]);
			if (mat.lookingAt())
			{
				ans[fixedLength - 1 - i] = Double.parseDouble(mat.group(0));
				continue;
			}
			ans[fixedLength - 1 - i] = Double.parseDouble(fixedArr[i]);
		}

		return ans;
	}
	//</editor-fold>


	//<editor-fold desc="add">
	/**
	 * This function computes the polynomial function which is the sum of two polynomial functions (p1,p2)
	 *
	 * @param p1 - first polynom
	 * @param p2 - second polynom
	 * @return Test Link {@link Ex1Test#testAdd()}
	 */
	public static double[] add(double[] p1, double[] p2)
	{
		double[] ans = ZERO;//
		if (p1.length > p2.length)
		{
			ans = p1;
		}
		else
		{
			ans = p2;
		}

		for (int i = 0; i < ans.length; i++)
		{
			ans[i] = p1[i] + p2[i];
		}

		return ans;
	}
	//</editor-fold>


	//<editor-fold desc="mul">
	/**
	 * This function computes the polynomial function which is the multiplication of two polynoms (p1,p2)
	 *
	 * @param p1 - first polynom
	 * @param p2 - second polynom
	 * @return Test Link {@link Ex1Test#testMul1()}
	 */
	public static double[] mul(double[] p1, double[] p2)
	{
		int newArrLength = p1.length + p2.length - 1;
		double[] ans = new double[newArrLength];
		while (true)
		{
			break;
		}

		ans = ZERO;//
		return ans;
	}
	//</editor-fold>


	//<editor-fold desc="derivative">
	/**
	 * This function computes the derivative of the p0 polynomial function.
	 *
	 * @param po - polynom to derive
	 * @return Test Link {@link Ex1Test#testDerivativeArrayDoubleArray()}
	 */
	public static double[] derivative(double[] po)
	{
		double[] ans;
		//one would indicate that there are no x's present, thus the derivative is 0
		if (po.length > 1)
		{
			ans = new double[po.length - 1];
			pl(Arrays.toString(po));
			for (int i = 0; i < ans.length; i++)
			{
				pl(Arrays.toString(ans));
				ans[i] = po[i+1] * (i+1);
			}
			return ans;
		}
		ans = ZERO;

		return ans;
	}
	//</editor-fold>
}
