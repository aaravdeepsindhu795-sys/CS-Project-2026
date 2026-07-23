public class TrafficGenerator {

    private int[] r, c;
    private String[] signal;
    private int[][] map;
    private boolean[] blocked;

    public TrafficGenerator(int[] r, int[] c, String[] signal, int[][] map, boolean[] blocked) {
        this.r = r;
        this.c = c;
        this.signal = signal;
        this.map = map;
        this.blocked = blocked;
    }

    public void generateTraffic() {
        for(int i = 0; i < 10; i++) {

            if(blocked[i]) {
                r[i] = 0;
                signal[i] = "BLOCKED";
                continue;
            }

            r[i] = (int)(Math.random() * (c[i] + 1));
            signal[i] = "GREEN";
        }

        adjustSignals();
        reroute();
    }

    private void adjustSignals() {
        for(int i = 0; i < 10; i++) {
            if(blocked[i]) continue;

            double load = (r[i] * 100.0) / c[i];

            if(load > 80)
                signal[i] = "LONG GREEN";
            else if(load > 50)
                signal[i] = "NORMAL";
            else
                signal[i] = "SHORT GREEN";
        }
    }

    private void reroute() {
        for(int i = 0; i < 10; i++) {

            while(r[i] > c[i] * 0.8) {
                boolean moved = false;

                for(int j : map[i]) {

                    if(!blocked[j] && r[j] < c[j] * 0.7) {
                        r[i]--;
                        r[j]++;
                        System.out.println("↪ Reroute: " + (i+1) + " → " + (j+1));
                        moved = true;
                        break;
                    }
                }

                if(!moved) break;
            }
        }
    }

    public void handleEmergency(int road) {
        System.out.println("🚑 Emergency on Road " + (road+1));

        signal[road] = "GREEN PRIORITY";

        for(int j : map[road]) {
            r[j] = Math.max(0, r[j] - 2);
            signal[j] = "RED";
        }
    }
}