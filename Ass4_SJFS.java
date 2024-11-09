import java.util.Scanner;
public class Ass4_SJFS {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no. of process:");
        int n = sc.nextInt(), pid[] = new int[n], at[] = new int[n], bt[] = new int[n], 
            ct[] = new int[n], ta[] = new int[n], wt[] = new int[n], f[] = new int[n];
        int st = 0, tot = 0;
        float avgwt = 0, avgta = 0;
        
        for (int i = 0; i < n; i++) {
            System.out.println("Enter process " + (i + 1) + " arrival time:");
            at[i] = sc.nextInt();
            System.out.println("Enter process " + (i + 1) + " burst time:");
            bt[i] = sc.nextInt();
            pid[i] = i + 1;
        }
        
        while (tot < n) {
            int c = n, min = 999;
            for (int i = 0; i < n; i++)
                if (at[i] <= st && f[i] == 0 && bt[i] < min) { min = bt[i]; c = i; }
            
            if (c == n) st++;
            else {
                ct[c] = st + bt[c];
                st += bt[c];
                ta[c] = ct[c] - at[c];
                wt[c] = ta[c] - bt[c];
                f[c] = 1;
                tot++;
            }
        }
        
        System.out.println("\nPid\tArrival\tBurst\tComplete\tTurnaround\tWaiting");
        for (int i = 0; i < n; i++) {
            avgwt += wt[i];
            avgta += ta[i];
            System.out.println(pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t\t" + ta[i] + "\t\t" + wt[i]);
        }
        System.out.println("\nAverage turnaround time is " + avgta / n);
        System.out.println("Average waiting time is " + avgwt / n);
        sc.close();
    }
}
