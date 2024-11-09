import java.util.Scanner; 

public class Ass4_RR { 
    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in); 
        System.out.print("Enter How Many Processes You Want: "); 
        int n = sc.nextInt(); 

        int[] brust = new int[n]; 
        int[] completion = new int[n]; 
        int[] oribrust = new int[n]; 

        for (int i = 0; i < n; i++) { 
            System.out.print("Enter Process " + (i + 1) + " burst time: "); 
            brust[i] = sc.nextInt(); 
            oribrust[i] = brust[i]; 
        } 

        System.out.print("Enter Time Quantum: "); 
        int tq = sc.nextInt(); 
        int sum = 0, count; 

        while (true) { 
            count = 0; 
            for (int i = 0; i < n; i++) { 
                if (brust[i] == 0) { 
                    count++; 
                    continue; 
                } 
                int temp = Math.min(brust[i], tq); 
                brust[i] -= temp; 
                sum += temp; 
                completion[i] = sum; 
            } 
            if (count == n) break; 
        } 

        System.out.print("\nProcess\tBurst Time\tCompletion\tTurnaround Time\tWaiting Time"); 
        float totalavgtime = 0, totalwaittime = 0; 
        for (int i = 0; i < n; i++) { 
            int TAT = completion[i]; 
            totalavgtime += TAT; 
            int WT = TAT - oribrust[i]; 
            totalwaittime += WT; 
            System.out.print("\n " + (i + 1) + "\t " + oribrust[i] + "\t\t " + completion[i] + 
                             " \t\t" + TAT + " \t\t " + WT); 
        } 

        System.out.println("\nAverage Waiting Time = " + (totalwaittime / n)); 
        System.out.println("Average Turnaround Time = " + (totalavgtime / n)); 
        sc.close();
    } 
} 
