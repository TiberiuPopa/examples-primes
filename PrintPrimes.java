public class PrintPrimes {
  int numberOfPrimes;
  int resultsPerColumn;
  int columnsPerPage;
  int listOfPrimes[];
 
  public PrintPrimes(int numberOfPrimes, int resultsPerColumn, int columnsPerPage) {
    this.numberOfPrimes   = numberOfPrimes;
    this.resultsPerColumn  = resultsPerColumn;
    this.columnsPerPage  = columnsPerPage;
    this.listOfPrimes = new int[numberOfPrimes + 1];
  }

  public void calculatePrimes() {
      /* Two is the only even prime. All other prime numbers are odd.
       * To simplify the code, we simply add 2 as a prime number, and
       * delegate the task of finding all odd prime numbers to a helper
       * function.
       */
      listOfPrimes[1] = 2;
      calculateOddPrimes();
  }

  private void calculateOddPrimes() {
 
	  boolean intIsPrime;
      int oddMultiples[] = new int[numberOfPrimes/10 + 1];
      int currentInt = 1;
      int arrayIndex = 2;
      int nextTargetSquare = 9;

      for(int primesFoundSoFar = 2; primesFoundSoFar <= numberOfPrimes; primesFoundSoFar++) {
        do {
          currentInt = currentInt + 2;		// Check next odd integer
          if (currentInt == nextTargetSquare) {	/* If reaching the previously computed targeted square,
          					 note this number as an odd multiple */
        	  oddMultiples[arrayIndex] = currentInt;
        	  arrayIndex++;
        	  nextTargetSquare = listOfPrimes[arrayIndex] * listOfPrimes[arrayIndex];
        	  	// Compute this integer's square, as the next target
          }

          intIsPrime = true;		// First declare the integer to be prime, then try to disprove this
          
          for (int i = 2; i < arrayIndex && intIsPrime; i++) {
            while (oddMultiples[i] < currentInt)	/* Add all multiples and primes to see
            						 if the evaluated integer is a multiple */
            	oddMultiples[i] = oddMultiples[i] + 2*listOfPrimes[i];
            if (oddMultiples[i] == currentInt)		/* The evaluated integer is a multiple if
            						 any addition gives exactly itself */
              intIsPrime = false;			// A multiple is not a prime, so reject this integer
          }
          
        } while (!intIsPrime);
        listOfPrimes[primesFoundSoFar] = currentInt;
      }
    }

    public void displayPrimes() {
        int pageNumber = 1;
        int pageOffset = 1;
        while (pageOffset <= numberOfPrimes) {
          System.out.println("The First " + numberOfPrimes +
                               " Prime Numbers --- Page " + pageNumber);
          System.out.println("");
          for (int rowOffset = pageOffset; rowOffset < pageOffset + resultsPerColumn; rowOffset++){
            for (int i = 0; i < columnsPerPage;i++)
              if (rowOffset + i * resultsPerColumn <= numberOfPrimes)
                System.out.format("%10d", listOfPrimes[rowOffset + i * resultsPerColumn]);
            System.out.println("");
          }
          System.out.println("\f");
          pageNumber = pageNumber + 1;
          pageOffset = pageOffset + resultsPerColumn * columnsPerPage;
        }
    }
    
    public static void main(String[] args) {
      PrintPrimes printPrimes = new PrintPrimes(300, 50, 4);
      printPrimes.calculatePrimes();
      printPrimes.displayPrimes();
  }
}

					 
