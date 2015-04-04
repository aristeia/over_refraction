import java.lang.Math;


/*! \mainpage
 * This set of code is used to calculate the sum of two spherical lenses
 * and cylinders. This is useful for fighting glasses. The patients old
 * perscprition is the first sphere, cylinder and axis. The patient
 * wears the glasses while being refracted. The results from the phoropter
 * are the second set of sphere, cylinder and axis. The new perscription
 * is the sum of these lenses. <br/>
 *
 * This technique of refraction is more accurate than doing a new refraction
 * using the phoropter. When lenses are put into glasses, and the paient wears
 * the glases, there are several effects, which cause the refraction to be
 * inaccurate. The glasses are closer to the patient's eye. The combination
 * of the corrective lense, and the lense of the eye can be viewed as a
 * coumpound lense system. The power of the compound lense system is a
 * of the distance between the two lenses: <br/>
 *<br/>
 * Pcombined = 1 / Fcombined = 1 / F1 - 1 / (d - F2) <br/>
 * <br/>
 * Here is a nice article about <a
href="http://www.colorado.edu/physics/phys1230/phys1230_fa01/topic27.html">
compound lense systems </a>.
 *<br/>
 *
 * A patient typically wears his glasses at an angle, and the thinest point
 * of lenses is typically not directly in front of the pupil. The lense
 * itself is a compound lense, with a front curviture and a rear curvature.
 * The lense was ground with the approximation, that there was 0 distance
 * betwene the front and rear lense, but this is not true, and the true
 * distance is a function of the style of the glasses, and how the patient
 * wears the glasses.<br/>
 *
 * Therefore, by adding a small correction to the existing glasses,
 * the new perscpription largely compensates for the two systematic errors
 * just mentioned. Therefore overrefraction is more accurate than
 * a simple refraction.<br/>
 *
 * The algorithm for adding two sets of spheres and cylinders is as follows.
 * <ol>
 * <li> Let the two lenses be described as sphere_a, cylinder_a, axis_a
 *   and sphere_b, cylinder_b, axis_b </li>
 *
 * <li> The sphere and cylinder for each lense is normalized so that the
 * cylinder has a positive power, and the axis of the cylinder is between
 * 0 and 180 degrees. </li>
 *
 * <li> The cylinder with the greater axis is the second set of lenses
 * so that the difference between the 2 axis is a positive number between
 * 0 and 180 degrees. </li>
 *
 * <li> The axis of the two lenses are converted to radians, so that
 * they can be put through the trigometric functions in standard math
 * libraries. </li>
 *
 * <li> The normalized lenses are called, Sphere_a, Cylinder_a, Axis_a
 *   and Sphere_b, Cylinder_b, Axis_b
 * </li>
 *
 * <li> The sum of the two lenses will be called sphere_c, cylinder_c,
 * axis_c.  Axis_c is the radian equivalent of axis_c which is in degrees. </li>
 *
 * <li> alpha is defined as Axis_b - Axis_a </li>
 *
 * <li> cylinder_c is calculated as sqrt( Cylinder_a^2 + Cylinder_b*^2
+ 2*Cylinder_a*Cylinder_b*cos(2*alpha)) </li>
 *
 * <li> If cylinder_c is 0, then the two cylinders have added to create a new
 * sphere. Axis_c, and axis_c are arbitrarly set to 0, but this has no real
 * meaning. It is done to prevent pseudo random output, for axis_c, which
 * might confuse the end user. </li>
 *
 * <li> if cylinder_c is greater than 0, then <ol>
 *   <li> a new constant gamma is
 *    calculated as .5 * asin( sin(2*alpha) * cylinder_b / cylinder_c).</li>
 *   <li> Axis_c is calculated as Axis_a + gamma </li>
 *   <li> Axis_c is normalized so that it is between 0 and pi. </li>
 *   <li> axis_c is calculated as Axis_a * 180 / pi </li>
 *   </ol>
 * </li>
 *
 * <li> The spherical component, Scyl, of the two cylinders is calculated as
 *  (Cylinder_a + Cylinder_b - cylinder_c) / 2 </li>
 *
 * <li> sphere_c is calculated as sphere_a + sphere_b + Scyl </li>
 *
 * </ol>
 * The code for this alorithm is in
over_refraction::optical_addition::add_lenses
 * @author Jon Sims
 * @version 1.0
 */

public class optical_addition{

    
    //internal class attributes
    protected double sphere_a;
    protected double cylinder_a;
    protected double axis_a;
    protected double sphere_b;
    protected double cylinder_b;
    protected double axis_b;
    protected double alpha;
    protected double cylinder_c;
    protected double gamma;
    protected double Scyl;
    protected double sphere_c;
    protected double axis_c;

    //clears internal attributes
    public void clear() {
      sphere_a = 0;
      cylinder_a = 0;
      axis_a = 0;
      sphere_b = 0;
      cylinder_b = 0;
      axis_b = 0;
      alpha = 0;
      cylinder_c = 0;
      gamma = 0;
      Scyl = 0;
      sphere_c = 0;
      axis_c = 0;
    }


    // add_lense() calculates values for alpha, cylinder_c, gamma, Scyl, sphere_c, and axis_c
    public void add_lenses() {
	Double a_s = new Double(sphere_a);
	Double a_c = new Double(cylinder_a);
	Double a_a = new Double(axis_a);
	normalize(a_s,a_c,a_a);
	
	Double b_s = new Double(sphere_b);
	Double b_c = new Double(cylinder_b);
	Double b_a = new Double(axis_b);
	normalize(b_s,b_c,b_a);
	
	sphere_a = a_s.doubleValue();
	cylinder_a = a_c.doubleValue();
	axis_a = a_a.doubleValue();
	
	sphere_b = b_s.doubleValue();
	cylinder_b = b_c.doubleValue();
	axis_b = b_a.doubleValue();
		
	if (cylinder_b > cylinder_a) {
	    double temp = sphere_a;
	    sphere_a = sphere_b;
	    sphere_b = temp;
	    
	    temp = cylinder_a;
	    cylinder_a = cylinder_b;
	    cylinder_b = temp;

	    temp = axis_a;
	    axis_a = axis_b;
	    axis_b = temp;
	}
	
	alpha = axis_b - axis_a;

	cylinder_c = Math.sqrt(Math.pow(cylinder_a, 2.0) + Math.pow(cylinder_b,2.0) + 2*cylinder_a*cylinder_b*Math.cos(2*alpha));

	if (cylinder_c < Math.pow(10.0,-6)) {
	    gamma = 0.0;
	    axis_c = 0.0;
	}
	else {
	    gamma = 0.5 * Math.asin( Math.sin(2*alpha) * (cylinder_b / cylinder_c));
	    axis_c = axis_a+gamma;
	    while (axis_c < 0) {
		axis_c += Math.PI;
	    }
	    while (axis_c >= Math.PI) {
		axis_c -= Math.PI;
	    }

	}	    
	Scyl = (cylinder_a + cylinder_b - cylinder_c)/2;
	sphere_c = sphere_a + sphere_b + Scyl;
    }


    /* Constructor
       @param rhs optical_addition object whose internal attributes are used for this class' internal attributes
    */
    public optical_addition( optical_addition rhs) {
	sphere_a = rhs.get_sphere_a();
	cylinder_a = rhs.get_cylinder_a();
	axis_a = rhs.get_axis_a();
	sphere_b = rhs.get_sphere_b();
	cylinder_b = rhs.get_cylinder_b();
	axis_b = rhs.get_axis_b();
	alpha = rhs.get_alpha();
	cylinder_c = rhs.get_cylinder_c();
	gamma = rhs.get_gamma();
	Scyl = rhs.get_Scyl();
	sphere_c = rhs.get_sphere_c();
	axis_c = rhs.get_axis_c();
    }

    /* Constructor
       @param Sphere_a sets internal sphere_a
       @param Cylinder_a sets internal cylinder_a
       @param Axis_a sets internal axis_a
       @param Sphere_b sets internal sphere_a
       @param Cylinder_b sets internal cylinder_b
       @param Axis_b sets internal axis_b
    */	
    public optical_addition(
			    double Sphere_a,
			    double Cylinder_a,
			    double Axis_a,
			    double Sphere_b,
			    double Cylinder_b,
			    double Axis_b)
    {
	sphere_a = Sphere_a;
	cylinder_a = Cylinder_a;
	axis_a = Axis_a;
	sphere_b = Sphere_b;
	cylinder_b = Cylinder_b;
	axis_b = Axis_b;
	
	add_lenses();
    }

    //data access functions

    double get_sphere_a() { return sphere_a;}
    double get_cylinder_a() { return cylinder_a;}
    double get_axis_a() { return axis_a;}
    double get_sphere_b() { return sphere_b;}
    double get_cylinder_b() { return cylinder_b;}
    double get_axis_b() { return axis_b;}
    double get_alpha() { return alpha;}
    double get_cylinder_c() { return cylinder_c;}
    double get_gamma() { return gamma;}
    double get_Scyl() { return Scyl;}
    double get_sphere_c() { return sphere_c;}
    double get_axis_c() { return axis_c;}


    /* normalize normalizes the input variables
       @param sphere a double object representing one of the sphere primatives
       @param cylinder a double object representing one of the cylinder primatives
       @param axis a double object representing one of the axis primatives
    */
    private void normalize( Double sphere, Double cylinder, Double theta) {
	if (cylinder >= 0) {
	    return;
	}
	sphere+= cylinder;
	cylinder*= -1.0;
	theta += Math.PI/2.0;
	
	while (theta < 0) {
	    theta += Math.PI;
	}
	while (theta >= Math.PI) {
	    theta -= Math.PI;
	}
    }


    public String toString() {
	return "A: "+Double.toString(sphere_a)+ " Sphere, + " +Double.toString(cylinder_a)+ " X, " +Double.toString(Math.toDegrees(axis_a)) +" degrees\nB: "+ Double.toString(sphere_b)+ " Sphere, + " +Double.toString(cylinder_b)+ " X, " +Double.toString(Math.toDegrees(axis_b)) +" degrees\nC: "+ Double.toString(sphere_c)+ " Sphere, + "+Double.toString(cylinder_c)+" X, " +Double.toString(Math.toDegrees(axis_c)) +" degrees"; 
    }
}
