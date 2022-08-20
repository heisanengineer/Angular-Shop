//The program that finds the closest number 
//to the average of the previously entered numbers.

public class Main
{
	public static void main(String[] args) {
		int arr [] = new int[] { 37,12,25,45,35,70,41,18,99,56 };
		int avgClose= 0;
		int avgHandler = 0;
		int temp = 0;
		
		int sum = 0;
		int avg = 0;
		int i;
		
		for(i=0; i<arr.length;i++){
		    sum += arr[i];
		}
		
		avg = sum / arr.length;
		
		System.out.println("Avg: " + avg);
		
		// Avg find finish
		
		i = 0;
		
		
		if (avg > arr[0]) 
		    avgHandler = avg - arr[0]; 
		else
		    avgHandler = arr[0];
		
		
		if(avgHandler == 0){
		    avgClose = arr[0];
		    System.out.println("AvgClose: " + avgClose);
		}
        else
		{
		    	
		    for (i=0; i<arr.length; i++)
		    {
		    
		        temp = avg - arr[i]; 
		    
		        if (temp <= avgHandler && temp >= 0)
		            {
		                avgHandler = temp;
		                avgClose = arr[i];
		            }
		        
		        temp = 0;
		    }
		}
		System.out.println("AvgClose:" + avgClose);
	}
}
