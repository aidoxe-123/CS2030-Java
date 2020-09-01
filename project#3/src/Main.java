import java.util.Scanner;

import cs2030.simulator.Manager;

class Main {
    /**
     * Read the input, then pass on the variables to the Manager class to deal with it.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int seed = sc.nextInt(); // seed
        int serverNum = sc.nextInt();
        int selfCheckoutCounterNum = sc.nextInt();
        int maxQ = sc.nextInt();
        int customerNum = sc.nextInt();
        double arrivalRate = sc.nextDouble(); // lamda
        double serviceRate = sc.nextDouble(); // mu
        double restingRate = sc.nextDouble(); // rho
        double restingProp = sc.nextDouble();
        double greedyCustomerProp = sc.nextDouble();
        Manager.main(seed, serverNum, selfCheckoutCounterNum, maxQ, customerNum,
                arrivalRate, serviceRate, restingRate, restingProp, greedyCustomerProp);
    }
}
