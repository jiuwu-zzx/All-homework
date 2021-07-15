public class NBody{

	public static double readRadius(String path){
		In in = new In(path);
		int planetsNumber=in.readInt();
		double radius=in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String path){
		In in = new In(path);
		int planetsNumber=in.readInt();
		double radius=in.readDouble();
		Planet[] array=new Planet[planetsNumber];
		for(int i=0;i<planetsNumber;i++){
			double xP,yP,xV,yV,m;
			String img;
			
			xP=in.readDouble();
			yP=in.readDouble();
			xV=in.readDouble();
			yV=in.readDouble();
			m=in.readDouble();
			img=in.readString();

			Planet p=new Planet(xP,yP,xV,yV,m,img);
			array[i]=p;
		}
		return array;
	}

	public static void main(String[] args){
		double T=Double.parseDouble(args[0]);
		double dt=Double.parseDouble(args[1]);
		String filename=args[2];

		Planet[] planets=readPlanets(filename);
		double radius=readRadius(filename);

		double now_t = 0;
		double[] xForces = new double[planets.length];
		double[] yForces = new double[planets.length];

		StdDraw.enableDoubleBuffering();
	
		while(now_t<T){
			for(int i=0;i<planets.length;i++){
				xForces[i]=planets[i].calcNetForceExertedByX(planets);
				yForces[i]=planets[i].calcNetForceExertedByY(planets);
				planets[i].update(dt,xForces[i],yForces[i]);
			}
			/** Sets up the universe */
			StdDraw.setScale(-radius, radius);

			/* Clears the drawing window. */
			StdDraw.clear();

			StdDraw.picture(0, 0, "./images/starfield.jpg");

			for (Planet p:planets){
				p.draw();
			}

			/* Shows the drawing to the screen */
			StdDraw.show();
			StdDraw.pause(10);

			now_t += dt;
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  		planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  		planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
}

	}
}