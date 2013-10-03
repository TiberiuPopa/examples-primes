public class PrintPrimes {
  int numberOfPrimes;
  int RR;
  int CC;
  int orderMaximum;
  int listOfPrimes[];

  public PrintPrimes(int numberOfPrimes, int RR, int CC, int WW, int orderMaximum) {
    this.numberOfPrimes   = numberOfPrimes;
    this.RR  = RR;
    this.CC  = CC;
    this.orderMaximum = orderMaximum;
    this.listOfPrimes = new int[numberOfPrimes + 1];
  }


  public static void main(String[] args) {
      PrintPrimes printPrimes = new PrintPrimes(300, 50, 4, 10, 30);
      printPrimes.calculatePrimes();
      printPrimes.displayPrimes();
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
      int oddMultiples[] = new int[orderMaximum + 1];
      int currentInt = 1;
      int order = 2;
      int nextTargetSquare = 9;

      for(int primesFoundSoFar = 2; primesFoundSoFar <= numberOfPrimes; primesFoundSoFar++) {
        do {
          currentInt = currentInt + 2;
          if (currentInt == nextTargetSquare) {
        	  oddMultiples[order] = currentInt;
        	  order++;
        	  nextTargetSquare = listOfPrimes[order] * listOfPrimes[order];
          }

          intIsPrime = true;
          
          for (int i = 2; i < order && intIsPrime; i++) {
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
          for (int rowOffset = pageOffset; rowOffset < pageOffset + RR; rowOffset++){
            for (int i = 0; i < CC;i++)
              if (rowOffset + i * RR <= numberOfPrimes)
                System.out.format("%10d", listOfPrimes[rowOffset + i * RR]);
            System.out.println("");
          }
          System.out.println("\f");
          pageNumber = pageNumber + 1;
          pageOffset = pageOffset + RR * CC;
        }
    }
}

					 
