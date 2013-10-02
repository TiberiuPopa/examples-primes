public class PrintPrimes {
  int numberOfPrimes;
  int RR;
  int CC;
  int WW;
  int ORDMAX;
  int listOfPrimes[];

  public PrintPrimes(int numberOfPrimes, int RR, int CC, int WW, int ORDMAX) {
    this.numberOfPrimes   = numberOfPrimes;
    this.RR  = RR;
    this.CC  = CC;
    this.WW  = WW;
    this.ORDMAX = ORDMAX;
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
      int N;
      int MULT[] = new int[ORDMAX + 1];

      int currentInt = 1;
      int ORD = 2;
      int currentIntSquared = 9;

      for(int primesFoundSoFar = 2; primesFoundSoFar <= numberOfPrimes; primesFoundSoFar++) {
        do {
          currentInt = currentInt + 2;
          if (currentInt == currentIntSquared) {
            ORD = ORD + 1;
            currentIntSquared = listOfPrimes[ORD] * listOfPrimes[ORD];
            MULT[ORD - 1] = currentInt;
          }
          N = 2;
          intIsPrime = true;
          while (N < ORD && intIsPrime) {
            while (MULT[N] < currentInt)
              MULT[N] = MULT[N] + listOfPrimes[N] + listOfPrimes[N];
            if (MULT[N] == currentInt)
              intIsPrime = false;
            N = N + 1;
          }
        } while (!intIsPrime);
        listOfPrimes[primesFoundSoFar] = currentInt;
      }
    }

    public void displayPrimes() {
        int PAGENUMBER = 1;
        int PAGEOFFSET = 1;
        while (PAGEOFFSET <= numberOfPrimes) {
          System.out.println("The First " + numberOfPrimes +
                               " Prime Numbers --- Page " + PAGENUMBER);
          System.out.println("");
          for (int ROWOFFSET = PAGEOFFSET; ROWOFFSET < PAGEOFFSET + RR; ROWOFFSET++){
            for (int C = 0; C < CC;C++)
              if (ROWOFFSET + C * RR <= numberOfPrimes)
                System.out.format("%10d", listOfPrimes[ROWOFFSET + C * RR]);
            System.out.println("");
          }
          System.out.println("\f");
          PAGENUMBER = PAGENUMBER + 1;
          PAGEOFFSET = PAGEOFFSET + RR * CC;
        }
    }
}

					 
