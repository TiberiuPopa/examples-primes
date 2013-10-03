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
      int oddMultiples[] = new int[numberOfPrimes/10];
      int currentInt = 1;
      int multiplesIndex = 2;
      int nextTargetSquare = 9;

      for(int primesFoundSoFar = 2; primesFoundSoFar <= numberOfPrimes; primesFoundSoFar++) {
        do {
          currentInt = currentInt + 2;
          if (currentInt == nextTargetSquare) {
        	  oddMultiples[multiplesIndex] = currentInt;
        	  multiplesIndex++;
        	  nextTargetSquare = listOfPrimes[multiplesIndex] * listOfPrimes[multiplesIndex];
          }

          intIsPrime = true;
          
          for (int i = 2; i < multiplesIndex && intIsPrime; i++) {
            while (oddMultiples[i] < currentInt)
            	oddMultiples[i] = oddMultiples[i] + listOfPrimes[i] + listOfPrimes[i];
            if (oddMultiples[i] == currentInt)
              intIsPrime = false;
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

					 
