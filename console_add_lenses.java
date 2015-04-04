import java.util.Scanner;
import java.lang.Math;

//@author Jon Sims
//@version 1.0
public class console_add_lenses {
    public static void main(String args[]) {
	Scanner reader = new Scanner(System.in);
	double sphere_a = 0.0;
	double cylinder_a = 0.0;
	double axis_a = 0.0;
	double sphere_b = 0.0;
	double cylinder_b = 0.0;
	double axis_b = 0.0;
	String temp;
	boolean works;
	System.out.println("Enter ctrl-c at anytime to exit" );

	do {
	    System.out.println("Enter sphere_a" );
	    try{
		temp = reader.nextLine();
		sphere_a=Double.parseDouble(temp);
		works=true;
	    } catch(NumberFormatException e) {
		System.out.println("Err: input does not contain double");
		works=false;
	    }
	} while (!works) ;

	do {
	    System.out.println("Enter cylinder_a" );
	    try{
		temp = reader.nextLine();
		cylinder_a=Double.parseDouble(temp);
		works=true;
	    } catch(NumberFormatException e) {
		System.out.println("Err: input does not contain double");
		works=false;
	    }
	} while (!works) ;

	do {
	    System.out.println("Enter axis_a (in angles)" );
	    try{
		temp = reader.nextLine();
		axis_a = Double.parseDouble(temp);
		axis_a = Math.toRadians(axis_a);
		works=true;
	    } catch(NumberFormatException e) {
		System.out.println("Err: input does not contain double");
		works=false;
	    }
	} while (!works) ;

	do {
	    System.out.println("Enter sphere_b" );
	    try{
		temp = reader.nextLine();
		sphere_b=Double.parseDouble(temp);
		works=true;
	    } catch(NumberFormatException e) {
		System.out.println("Err: input does not contain double");
		works=false;
	    }
	} while (!works) ;

	do {
	    System.out.println("Enter cylinder_b" );
	    try{
		temp = reader.nextLine();
		cylinder_b=Double.parseDouble(temp);
		works=true;
	    } catch(NumberFormatException e) {
		System.out.println("Err: input does not contain double");
		works=false;
	    }
	} while (!works) ;

	do {
	    System.out.println("Enter axis_b (in angles)" );
	    try{
		temp = reader.nextLine();
		axis_b = Double.parseDouble(temp);
		axis_b = Math.toRadians(axis_b);
		works=true;
	    } catch(NumberFormatException e) {
		System.out.println("Err: input does not contain double");
		works=false;
	    }
	} while (!works) ;

	optical_addition obj = new optical_addition(sphere_a,cylinder_a,axis_a,sphere_b,cylinder_b,axis_b);
	System.out.println(obj.toString());
    }
}
