import java.lang.Math;

public class Planet {

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(){
	}

	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
	}

	public Planet(Planet b){
		this.xxPos=b.xxPos;
		this.yyPos=b.yyPos;
		this.xxVel=b.xxVel;
		this.yyVel=b.yyVel;
		this.mass=b.mass;
		this.imgFileName=b.imgFileName;
	}

	public double calcDistance(Planet another){
		return Math.sqrt(Math.pow((this.xxPos-another.xxPos),2)+Math.pow((this.yyPos-another.yyPos),2));
	}

	public double calcForceExertedBy(Planet another){
		double G=6.67e-11;
		return G*this.mass*another.mass/(this.calcDistance(another)*this.calcDistance(another));
	}

	public double calcForceExertedByX(Planet another){
		return this.calcForceExertedBy(another)*(another.xxPos-this.xxPos)/this.calcDistance(another);
	}

	public double calcForceExertedByY(Planet another){
		return this.calcForceExertedBy(another)*(another.yyPos-this.yyPos)/this.calcDistance(another);
	}

	public double calcNetForceExertedByX(Planet[] allP){
		double sumFx=0;
		for (Planet p : allP){
			if (this.equals(p)){
				continue;
			}
			sumFx+=this.calcForceExertedByX(p);
		}
		return sumFx;
	}

	public double calcNetForceExertedByY(Planet[] allP){
		double sumFy=0.0;
		for (Planet p : allP){
			if (this.equals(p)){
				continue;
			}
			sumFy+=this.calcForceExertedByY(p);
		}
		return sumFy;
	}

	public void update(double dt, double Fx, double Fy){
		double ax=Fx/mass;
		double ay=Fy/mass;
		xxVel+=ax*dt;
		yyVel+=ay*dt;
		xxPos+=xxVel*dt;
		yyPos+=yyVel*dt;
	}

	public void draw(){
		String img_root="./images/"+this.imgFileName;
		StdDraw.picture(this.xxPos,this.yyPos,img_root);
	}
}