import java.util.*;

public class Ass4_FCFS {
    public static void main(String args[]) { 
        Scanner sc = new Scanner(System.in); 
        System.out.println("Enter no. of processes: ");
        int n = sc.nextInt(); 
        int pid[] = new int[n], ar[] = new int[n], bt[] = new int[n], ct[] = new int[n], ta[] = new int[n], wt[] = new int[n];
        float avgwt = 0, avgta = 0; 
        for(int i = 0; i < n; i++) {
            System.out.println("Enter process " + (i+1) + " arrival time and burst time: ");
            ar[i] = sc.nextInt(); bt[i] = sc.nextInt(); pid[i] = i+1;
        } 
        // Sorting processes by arrival time
        for(int i = 0; i < n-1; i++) {
            for(int j = 0; j < n-i-1; j++) {
                if(ar[j] > ar[j+1]){
                    swap(ar, j); swap(bt, j); swap(pid, j);
                }
            }
        }
        // Calculating completion, turnaround, waiting times
        for(int i = 0; i < n; i++) {
            if (i == 0) {
                ct[i] = ar[i] + bt[i];  // For the first process
            } else {
                ct[i] = Math.max(ar[i], ct[i-1]) + bt[i];  // For subsequent processes
            }
            
            ta[i] = ct[i] - ar[i];  // Turnaround time
            wt[i] = ta[i] - bt[i];  // Waiting time
            avgwt += wt[i];         // Accumulating total waiting time
            avgta += ta[i];         // Accumulating total turnaround time
        }
        
        
        // Display results
        System.out.println("\nPid\tArrival\tBurst\tComplete\tTurnaround\tWaiting");
        for(int i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t" + ar[i] + "\t" + bt[i] + "\t" + ct[i] + "\t\t" + ta[i] + "\t\t" + wt[i]);
        }
        System.out.printf("\nAverage waiting time: %.2f\nAverage turnaround time: %.2f\n", avgwt/n, avgta/n);
        sc.close(); 
    }
    // Swap method to reduce repetitive swap code
    static void swap(int arr[], int i) {
        int temp = arr[i];
        arr[i] = arr[i+1];
        arr[i+1] = temp;
    }
}