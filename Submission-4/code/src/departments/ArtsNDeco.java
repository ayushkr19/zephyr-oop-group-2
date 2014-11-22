package departments;

import java.util.Random;

public class ArtsNDeco extends Department {

	Random random = new Random();
	double rating = random.nextInt(5)+5;
	
	public long budget() {
		// TODO Auto-generated method stub
		long bud = (long) rating;
		return bud*500;
	}

	public double decorate(){
		System.out.println("Decorating campus");
		return rating;
	}
}
