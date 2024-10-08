import java.io.*;

class Ass5_FIFO {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Enter the number of FRAMES: ");
        int f = Integer.parseInt(br.readLine()), fifo[] = new int[f];
        
        System.out.println("Enter the number of INPUTS: ");
        int n = Integer.parseInt(br.readLine()), inp[] = new int[n];
        
        System.out.println("Enter INPUT: ");
        for (int i = 0; i < n; i++) inp[i] = Integer.parseInt(br.readLine());

        for (int i = 0; i < f; i++) fifo[i] = -1;
        
        int Hit = 0, Fault = 0, j = 0;

        for (int i = 0; i < n; i++) {
            boolean check = false;
            for (int k = 0; k < f; k++) 
                if (fifo[k] == inp[i]) {
                    check = true;
                    Hit++;
                    break;
                }

            if (!check) {
                fifo[j] = inp[i];
                if (++j >= f) j = 0;
                Fault++;
            }
        }

        System.out.println("HIT: " + Hit + " FAULT: " + Fault + " HIT RATIO: " + (float) Hit / n);
    }
}