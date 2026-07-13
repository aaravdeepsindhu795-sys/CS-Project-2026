import java.util.*;

public class TrafficSystem {

    static int[] r = new int[10];
    static int[] c = {10,12,8,15,10,9,11,7,13,10};

    static int[][] map = {
        {1,4},
        {0,2,5},
        {1,3,6},
        {2,7},
        {0,5,8},
        {1,4,6,9},
        {2,5,7},
        {3,6},
        {4,9},
        {5,8}
    };

    static String[] signal = new String[10];
    static Random rand = new Random();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        generateTraffic(); // populate roads & signals randomly at startup

        while(true) {
            System.out.println("\n===== SMART TRAFFIC MANAGEMENT SYSTEM =====");
            System.out.println("1. Show Traffic Status");
            System.out.println("2. Regenerate Random Traffic & Signals");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            if(choice == 0) {
                System.out.println("System stopped");
                break;
            }

            switch(choice) {

                case 1:
                    display();
                    break;

                case 2:
                    generateTraffic();
                    System.out.println("🔄 Traffic and signals regenerated.");
                    display();
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    // randomly populates vehicle counts and signals for every road
    public static void generateTraffic() {
        for(int i = 0; i < 10; i++) {

            // random vehicle count between 0 and road capacity
            r[i] = (int)(Math.random() * (c[i] + 1));

            // random signal: 30% RED, 70% GREEN
            signal[i] = (Math.random() < 0.3) ? "RED" : "GREEN";
        }

        // apply rerouting for any road that ends up over 80% capacity
        for(int i = 0; i < 10; i++) {
            while(r[i] > c[i] * 0.8) {
                boolean rerouted = false;
                for(int j : map[i]) {
                    if(r[j] < c[j] * 0.8) {
                        r[i]--;
                        r[j]++;
                        System.out.println("⚠ REROUTE: Road " + (i+1) + " → Road " + (j+1));
                        rerouted = true;
                        break;
                    }
                }
                if(!rerouted) break; // no available road to reroute to
            }
        }
    }

    public static void display() {
        System.out.println("\n========= TRAFFIC STATUS =========");

        for(int i = 0; i < 10; i++) {

            double percent = (r[i] * 100.0) / c[i];
            String status;

            if(percent < 50)
                status = "LOW";
            else if(percent <= 80)
                status = "MEDIUM";
            else
                status = "HIGH";

            System.out.printf("Road %-2d : %2d / %-2d  [%s]  Signal: %s\n",
                    (i+1), r[i], c[i], status, signal[i]);

            if((i+1) % 5 == 0)
                System.out.println("---------------------------------");
        }

        System.out.println("=================================\n");
    }
}