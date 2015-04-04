import java.lang.Math;

public class optical_addition{

    //    static void define_constants();
    
    //key attributes
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
	
    public optical_addition(
			    double Sphere_a,
			    double Cylinder_a,
			    double Axis_a,
			    double Sphere_b,
			    double Cylinder_b,
			    double Axis_b)
    {
	//copy all_key attributes
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


    //public boolean equal( double rhs ) 	eps = 1e-6;
	

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
