import java.util.Scanner;

public class TransitCalculator {
  public static int days; //up to 30 days
  public static int rides; //no. of rides in that day
  public static double[] fareOptions = {2.75, 33, 127}; //fare options 0: single, 1: 7-day, 2: 30-day
  public static double[] reducedFare = {1.35, 16.5, 63.50}; //fare options 0: single, 1: 7-day, 2: 30-day

  public TransitCalculator(int d, int r){
      days = d;
      rides = r;
  }

  public static void main(String[] args){
    Scanner input = new Scanner(System.in);

    System.out.print("Rides Complete: ");
    int ridesComplete = input.nextInt();
    System.out.print("Days used: ");
    int daysUsed = input.nextInt();
    System.out.print("65 or older? Y or N: ");
    char ageCheck = input.next().charAt(0);

    double[] fare = new double[3];
    switch (ageCheck){
      case 'Y': case 'y':
        fare = reducedFare;
        break;
      case 'N': case 'n':
        fare = fareOptions;
        break;
    }
    String bestOption = getBestFare(getRidePrices(ridesComplete, daysUsed, fare));
    System.out.println(bestOption);

  }

  public static double[] getRidePrices(double ridesComplete, double daysUsed, double[] fare){
    double[] ridePrices = new double[3];
    //Single day
    ridePrices[0] = fare[0];
    ridePrices[1] = unlimitedPrice(ridesComplete, daysUsed, 7, fare);
    ridePrices[2] = unlimitedPrice(ridesComplete, daysUsed, 30, fare);

    return ridePrices;
  }
  public static double unlimitedPrice(double ridesComplete, double daysUsed, int option, double[] farePrice){
    double fare = 0;
    int daysNeeded = 0;
    switch (option){
      case 7:
        daysNeeded = (int) Math.ceil(daysUsed/7);
        fare = farePrice[1];
        break;
      case 30:
        daysNeeded = (int) Math.ceil(daysUsed/30);
        fare = farePrice[2];
        break;
    }

    return (fare*daysNeeded)/ridesComplete;
  }

  public static String getBestFare(double[] ridePrice){
    double lowestPrice = ridePrice[0];
    int lowestPriceIndex = 0;
    for (int i = 1; i <= 2; i++){ //only loop for index 1, 2 since 0 is the default value.
      if (ridePrice[i] < lowestPrice){
        lowestPrice = ridePrice[i];
        lowestPriceIndex = i;
      }
	  }

    //Return strings
    switch (lowestPriceIndex){
      case 0:
        return String.format("You should get the Pay-per-ride option at $%.2f per ride.\n", ridePrice[lowestPriceIndex]);
      case 1:
        return String.format("You should get the 7-day Unlimited option at $%.2f per ride.\n", ridePrice[lowestPriceIndex]);
      case 2:
        return String.format("You should get the 30-day Unlimited option at $%.2f per ride.\n", ridePrice[lowestPriceIndex]);
    }

    return "";
  }
}