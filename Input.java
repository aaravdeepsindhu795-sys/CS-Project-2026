import java.util.*;

public class Input {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter destination (A-J): ");
        char dest = sc.next().toUpperCase().charAt(0);

        TrafficGenerator tg = new TrafficGenerator();
        Display dp = new Display();

        tg.generateTraffic();      
        tg.generateEmergency();    
        tg.findBestRoute();       

        dp.showAll(tg, dest);     

        sc.close();
    }
}
}