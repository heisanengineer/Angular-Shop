package ceng497_lab1;

public class question1 {

public static boolean prime (int a)
	{
	//prime search
	int d,c,i;
	i=a;
	d =1;
	for ( c=2; c<i; c++ ) 
	{ 
	if ( i%c == 0 ) 
	{
	    d=0;
	} 
	} 
	
	//optimus prime search
	if((i%5==0 || i%3==0) && d==1)
		{
			System.out.print("Optimus Prime, ");
			return true;
		}
	if (d==1 && i!=1) {
		System.out.print("Prime, ");
		return true;
		}
	return false; 
} 
	
static void optimusPrime() 
{
	int size = 100;
	int i;
	boolean a;
	
	for(i=1;i<=size;i++)
	{
		//prime find signal
		a=prime(i);
		
		if(i%5==0 && i%3==0 && a==false)
		{
			System.out.print("FizzBuzz, ");
		}
		else if(i%3==0 && a==false)
		{
			System.out.print("Fizz, ");
		}
		else if(i%5==0 && a==false)
		{
			System.out.print("Buzz, ");
		}
		else if(a==false)
		{
			System.out.print(i+", ");
		}
	}
}

static void fizzBuzz() {
		
		int size = 100;
		
		for(int i=1;i<=size;i++)
		{
			if(i%5==0 && i%3==0)
			{
				System.out.print("FizzBuzz, ");
			}
			else if(i%3==0)
			{
				System.out.print("Fizz, ");
			}
			else if(i%5==0)
			{
				System.out.print("Buzz, ");
			}
			else
			{
				System.out.print(i+", ");
			}
		}
	}
	public static void main(String[] args) {
		System.out.println("FizzBuzz: ");
		fizzBuzz();
		System.out.println("\n\nOptimusPrime: ");
		optimusPrime();
	}
	}