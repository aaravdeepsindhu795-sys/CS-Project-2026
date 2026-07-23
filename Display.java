public class Display {

    private int[] r, c;
    private String[] signal;
    private boolean[] blocked;

    public Display(int[] r, int[] c, String[] signal, boolean[] blocked) {
        this.r = r;
        this.c = c;
        this.signal = signal;
        this.blocked = blocked;
    }

    public void show() {

        System.out.println("\n========= TRAFFIC STATUS =========");

        for(int i = 0; i < 10; i++) {

            if(blocked[i]) {
                System.out.println("Road " + (i+1) + " : BLOCKED 🚧");
                continue;
            }

            double percent = (r[i] * 100.0) / c[i];
            String status;

            if(percent < 50)
                status = "LOW";
            else if(percent <= 80)
                status = "MEDIUM";
            else
                status = "HIGH";

            System.out.printf("Road %-2d : %2d/%-2d [%s] Signal: %s\n",
                    (i+1), r[i], c[i], status, signal[i]);
        }

        System.out.println("=================================\n");
    }
}