package lab7;

import java.util.Scanner;

public class Task1 {

	public static void main(String[] args) {
		
		RightTriangle [] triangles = new RightTriangle[10];
		int nextIDNumber = 1;
		boolean exit = false;
		int selection=0;
		Scanner input = new Scanner(System.in);
		int id=0;
		double sf = 1;
		int x=0, y=0;
		double base=0, height=0;
		int xLoc = 0, yLoc = 0;
		boolean found = false;

		
		do {
			System.out.println("1 - Enter a new right triangle\n2 – Delete a right triangle\n3 – Delete all right triangles\n4 – Display all right triangles\n5 – Move a triangle\n6 – Resize a triangle\n7 – Enter a scale factor\n8 – Scale all triangles\n9 – Exit program");
			do {
			System.out.println("Please select an option by typing in the corresponding number: ");
			selection = input.nextInt();
			}while (selection <1 || selection >9);
			
			switch(selection) {
			
			case 1://Enter new triangle
				System.out.println("Enter a value for the base: ");
				base = input.nextDouble();
				System.out.println("Enter a value for the height: ");
				height = input.nextDouble();
				RightTriangle t = new RightTriangle();
				t.setValues(base,height);
				
				System.out.println("Enter an X Location: ");
				x = input.nextInt();
				System.out.println("Enter an Y Location: ");
				y = input.nextInt();
				
				found = false;
				
				for (int i = 0; i < triangles.length; i++)
				{
					//Check if empty
					if (triangles[i] == null)
					{
	 					// create new RightTriangle
						triangles[i] = new RightTriangle(nextIDNumber++, base, height, x, y);

						found = true;

						// break out of loop
						break;
					}
				}
				if (!found) {
					System.out.println("Error, this array is full.");
				}
				break;

				
			case 2://Delete a triangle
				// get id number to delete
				System.out.println("Enter the triangle ID you would like to delete: ");
				id = input.nextInt();
				
				found = false;

				// loop through array
				for (int i = 0; i < triangles.length; i++)
				{
					// if this is a valid object and the correct object
					if (triangles[i] != null && triangles[i].getID() == id)
					{
						// delete object
						triangles[i] = null;

						found = true;

						break;
					}
				

				else
					System.out.println("Error, triangle with this ID not found.");
			}
				break;
				
			case 3://Delete all triangles
				for (int i=0;i <triangles.length;i++) {
					if (triangles[i]!=null) {
						triangles[i]=null;
					}
				}
				break;
			case 4://Display all triangles
				System.out.print("\n");
				for (int i = 0; i < triangles.length; i++) {
					if (triangles[i] != null){
						System.out.print("| ID: "+triangles[i].getID());
						System.out.print("\t| Base: "+triangles[i].getBase());
						System.out.print("\t| Height: "+triangles[i].getHeight());
						System.out.print("\t| Hypotenuse: "+triangles[i].getHypotenuse());
						System.out.print("\t| X Location: "+triangles[i].getxLoc());
						System.out.print("\t| Y Location: "+triangles[i].getyLoc());
						System.out.print("\t| Area: "+triangles[i].getArea());
						System.out.print("\t| Perimeter: "+triangles[i].getPerimeter());
						System.out.print("\n");
					}
				}
				
				System.out.print("\n");
				break;
				
			case 5://Move a triangle
				System.out.println("Enter the ID of a triangle to move: ");
				id = input.nextInt();
				System.out.println("Enter a new X location: ");
				x = input.nextInt();
				System.out.println("Enter a new Y location: ");
				y = input.nextInt();
				
				for(int i=0;i<triangles.length;i++) {
					if (triangles[i] != null && triangles[i].getID() == id){
						triangles[i].setxLoc(x);
						triangles[i].setyLoc(y);
						found = true;
						
						break;
					}
				}
				
				if (!found) {
					System.out.println("Error, triangle not found.");
				}
				break;
				
			case 6://Resize a triangle
				System.out.println("Enter the ID of a triangle to resize: ");
				id = input.nextInt();
				System.out.println("Enter a new value for the base: ");
				base = input.nextDouble();
				System.out.println("Enter a new value for the height: ");
				height = input.nextDouble();
				found = false;
				
				for (int i=0;i<triangles.length;i++) {
					if (triangles[i] != null && triangles[i].getID() == id) {
						triangles[i].setValues(base, height);
						found = true;
						break;
					}
				}
				
				if (!found) {
					System.out.println("Error, triangle not found.");
				}
				break;


			case 7://Enter scale factor
				System.out.println("Enter a value for the scale factor: ");
				sf = input.nextDouble();
				RightTriangle.setSf(sf);
				break;
				
				
			case 8://Scale all triangles
				for (int i=0;i<triangles.length;i++) {
					if (triangles[i] != null) {
						triangles[i].ScaleShape(sf);
					}
				}
				break;
				
				
			case 9://Exit w/ a confirmation
				System.out.println("Are you sure you would like to exit?  Type y or n: ");
				char confirm = input.next().toLowerCase().charAt(0);
				if (confirm == 'y')
					exit = true;
				else 
					exit = false;
				break;
			}
			
			
		}while (!exit);
		
				
	}//End of main

}//End Task1


class RightTriangle {

	private double base;
	private double height;
	//Hyp is computed value
	private double hypotenuse;
	private int xLoc;
	private int yLoc;
	private int ID;
	static double sf = 1;
	
	
	public RightTriangle(int i, double b, double h, int x, int y) {
		setValues(b,h);
		hypotenuse = Math.sqrt((b*b)+(h*h));
		xLoc = x;
		yLoc = y;
		ID = i;
	}
	
	public RightTriangle () {
		setValues(0.0, 0.0);
		hypotenuse = ((0.0*0.0)+ (0.0*0.0));
		hypotenuse = Math.sqrt(hypotenuse);
	
	}
		
	
	public void setValues(double b, double h) {
		if (b >= 0.0)
			base = b;
		if (h >= 0.0)
			height = h;
		hypotenuse = Math.sqrt( Math.pow(base, 2) * Math.pow(height, 2));
		
	}
	
	public void setID(int id) {
		if (id > 0)
			ID = id;
	}
	
	public void setxLoc(int x) {
		xLoc = x;
	}
	
	public void setyLoc(int y) {
		yLoc = y;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getxLoc() {
		return xLoc;
	}
	
	public int getyLoc() {
		return yLoc;
	}
	
	
	public double getBase() {
		return base;
	}
	
	public double getHeight() {
		return height;
	}
	
	public double getHypotenuse() {
		double roundHyp = Math.round(hypotenuse*100.0)/100.0;
		return roundHyp;
	}
	
	public double getArea() {
		double area;
		area = .5* base * height;
		double roundArea = Math.round(area*100.0)/100.0;
		return roundArea;
	}
	
	public double getPerimeter() {
		double perimeter;
		perimeter = base + height + hypotenuse;
		double roundPer = Math.round(perimeter*100.0)/100.0;
		return roundPer;
	}
	
	public void ScaleShape(double sf) {
		base = base * sf;
		height = height * sf;
		setValues(base, height);
		hypotenuse = Math.sqrt( Math.pow(base, 2) * Math.pow(height, 2));
	}
	
	public static void setSf(double sf) {
		if (sf > 0)
			RightTriangle.sf = sf;
	}
	
	public static double getSf() {
		return sf;
	}
	
}//End RightTriangle class
