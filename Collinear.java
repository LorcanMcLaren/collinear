// -------------------------------------------------------------------------
/**
 *  This class contains only two static methods that search for points on the
 *  same line in three arrays of integers. 
 *
 *  @author  Lorcan McLaren
 *  @version 03/10/16 17:10:35
 */
class Collinear
{

   // ----------------------------------------------------------
    /**
     * Counts for the number of non-horizontal lines that go through 3 points in arrays a1, a2, a3.
     * This method is static, thus it can be called as Collinear.countCollinear(a1,a2,a3)
     * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the point (a1[i], 1) on the plain.
     * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the point (a2[i], 2) on the plain.
     * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the point (a3[i], 3) on the plain.
     * @return the number of points which are collinear and do not lie on a horizontal line.
     *
     * Array a1, a2 and a3 contain points on the horizontal line y=1, y=2 and y=3, respectively.
     * A non-horizontal line will have to cross all three of these lines. Thus
     * we are looking for 3 points, each in a1, a2, a3 which lie on the same
     * line.
     *
     * Three points (x1, y1), (x2, y2), (x3, y3) are collinear (i.e., they are on the same line) if
     * 
     * x1(y2-y3)+x2(y3-y1)+x3(y1-y2)=0 
     *
     * In our case y1=1, y2=2, y3=3.
     *
     * You should implement this using a BRUTE FORCE approach (check all possible combinations of numbers from a1, a2, a3)
     *
     * ----------------------------------------------------------
     *
     * Experimental Performance:
     * -------------------------
     *  Write the running time of the algorithm when run with the following input sizes
     *  
     *  Input Size N      Running Time (sec)
     *  ------------------------------------
     *  1000              0.557
     *  2000              3.633
     *  4000              21.089
     *
     *  Assuming that the running time of your algorithm is of the form aN^b,
     *  estimate 'b' and 'a' by fitting a line to the experimental points:
     *
     *  b = 2.621
     *  a = 7.77*10^-9
     *
     *  What running time do you predict using your results for input size 5000?
     *  What is the actual running time you get with such an input?
     *  What is the error in percentage?
     *
     *  Error = ( (Actual time) - (Predicted time) ) * 100 / (Predicted time)
     *
     *  Input Size N      Predicted Running Time (sec)        Actual Running Time (sec)       Error (%)
     *  ------------------------------------------------------------------------------------------------
     *  5000              38.5                                40.602                           5.46
     * 
     *  Order of Growth
     *  -------------------------
     *
     *  Calculate and write down the order of growth of your algorithm. You can use the asymptotic notation.
     *  You should adequately explain your answer. Answers without adequate explanation will not be counted.
     *
     *  Order of growth: N^3
     *
     *  Explanation: The length of each array is N (the input size). As each 'for' loop will run N times
     *  and there are three 'for' loops, nested one inside the other, the order of growth is N*N*N or N^3.
     */
    static int countCollinear(int[] a1, int[] a2, int[] a3)
    {
    	int count = 0;
    	int y1 = 1;
    	int y2 = 2;
    	int y3 = 3;
    	
    	for (int i = 0; i < a1.length; i++)
    	{
    		for ( int j = 0; j < a2.length; j++)
    		{
    			for (int k = 0; k < a3.length; k++)
    			{    	    		
    				if (a1[i] * (y2 - y3) + a2[j] * (y3 - y1) + a3[k] * (y1 - y2) == 0)
    					count++;
    			}
    		}
    	}
    	      
    	return count;
    }

    // ----------------------------------------------------------
    /**
     * Counts for the number of non-horizontal lines that go through 3 points in arrays a1, a2, a3.
     * This method is static, thus it can be called as Collinear.countCollinearFast(a1,a2,a3)
     * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the point (a1[i], 1) on the plain.
     * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the point (a2[i], 2) on the plain.
     * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the point (a3[i], 3) on the plain.
     * @return the number of points which are collinear and do not lie on a horizontal line.
     *
     * In this implementation you should make non-trivial use of InsertionSort and Binary Search.
     * The performance of this method should be much better than that of the above method.
     *
     * Experimental Performance:
     * -------------------------
     *  Measure the running time of the algorithm when run with the following input sizes
     *  
     *  Input Size N      Running Time (sec)
     *  ------------------------------------
     *  1000              0.061
     *  2000              0.288
     *  4000              1.203
     *  5000              1.963
     *
     *
     *  Compare Implementations:
     *  ------------------------
     *  Show the speedup achieved by this method, using the times you got from your experiments.
     *
     *  Input Size N      Speedup = (time of countCollinear)/(time of countCollinearFast)
     *  ---------------------------------------------------------------------------------
     *  1000              9.13
     *  2000              12.62
     *  4000              17.53
     *  5000              20.68
     *
     *
     *  Order of Growth
     *  -------------------------
     *
     *  Calculate and write down the order of growth of your algorithm. You can use the asymptotic notation.
     *  You should adequately explain your answer. Answers without adequate explanation will not be counted.
     *
     *  Order of Growth: N^2 log N
     *
     *  Explanation: The length of each array is N (the input size). As each 'for' loop will run N times
     *  and there are 2 'for' loops, nested one inside the other, the order of growth is N*N or N^2. However,
     *  we call the binarySearch method each time the inner 'for' loop iterates and this has an order of growth
     *  of log N, so our final order of growth is going to be N^2 log N
     *
     *
     */
    static int countCollinearFast(int[] a1, int[] a2, int[] a3)
    {
    	sort(a2);
    	int possibleElement;	// possible element of a2
    	int count = 0;
    	
    	for (int i = 0; i < a1.length; i++)
    	{
    		for (int j = 0; j < a3.length; j++)
    		{
    			possibleElement = (a1[i] + a3[j]) / 2;	// x2 = (x1 + x3) / 2  formula with y values substituted in and simplified
    			
    			if (binarySearch(a2, possibleElement) == true && a1[i] * (2 - 3) + possibleElement * (3 - 1) + a3[j] * (1 - 2) == 0)
    				count++;
    		}
    	}
 
      return count;
    }

    // ----------------------------------------------------------
    /**
     * Sorts an array of integers according to InsertionSort.
     * This method is static, thus it can be called as Collinear.sort(a)
     * @param a: An UNSORTED array of integers. 
     * @return after the method returns, the array must be in ascending sorted order.
     *
     * ----------------------------------------------------------
     *
     * Approximate Mathematical Performance:
     * -------------------------------------
     *  Using an appropriate cost model, give the performance of your algorithm.
     *  Explain your answer.
     *
     *  Performance: N^2
     *
     *  Explanation: The length of each array is N (the input size). As each 'for' loop will run N times
     *  and there are 2 'for' loops, nested one inside the other, the order of growth is N*N or N^2.
     *
     */
    static void sort(int[] a)
    {
    	 int temp;
         for (int i = 1; i < a.length; i++) 
         {
             for(int j = i ; j > 0 ; j--)
             {
                 if(a[j] < a[j - 1])
                 {
                     temp = a[j];
                     a[j] = a[j - 1];
                     a[j - 1] = temp;
                 }
             }
         }
    }

    // ----------------------------------------------------------
    /**
     * Searches for an integer inside an array of integers.
     * This method is static, thus it can be called as Collinear.binarySearch(a,x)
     * @param a: A array of integers SORTED in ascending order.
     * @param x: An integer.
     * @return true if 'x' is contained in 'a'; false otherwise.
     *
     * ----------------------------------------------------------
     *
     * Approximate Mathematical Performance:
     * -------------------------------------
     *  Using an appropriate cost model, give the performance of your algorithm.
     *  Explain your answer.
     *
     *  Performance: log N
     *
     *  Explanation: The binarySearch function halves the area it must search with
     *  each iteration of the 'while' loop. Hence its performance is log N.
     *
     */
    static boolean binarySearch(int[] a, int x)
    {
    	int lo = 0, hi = a.length - 1;
    	
        while (lo <= hi)
        {
            int mid = lo + (hi - lo) / 2;
            
            if ( x < a[mid]) 
            	hi = mid - 1;
            else if ( x > a[mid]) 
            	lo = mid + 1;
            else 
            	return true;
        }
      return false;
    }

}
