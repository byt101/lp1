import java.util.Scanner;

public class Ass4_Prio {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter No. of processes: ");
        int n = sc.nextInt();
        
        int[] process = new int[n], priority = new int[n], brust = new int[n];
        int[] completion = new int[n], WT = new int[n], TAT = new int[n];
        float totalAroundTime = 0, waitTime = 0;

        for (int i = 0; i < n; i++) {
            System.out.print("Enter Process " + (i + 1) + " Brust Time: ");
            brust[i] = sc.nextInt();
            System.out.print("Enter Process " + (i + 1) + " Priority: ");
            priority[i] = sc.nextInt();
            process[i] = i + 1;
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (priority[i] > priority[j]) {
                    // Swap priority
                    int temp = priority[i]; priority[i] = priority[j]; priority[j] = temp;
                    // Swap process
                    temp = process[i]; process[i] = process[j]; process[j] = temp;
                    // Swap brust time
                    temp = brust[i]; brust[i] = brust[j]; brust[j] = temp;
                }
            }
        }

        for (int i = 0, sum = 0; i < n; i++) {
            sum += brust[i];
            completion[i] = sum;
            TAT[i] = completion[i];
            WT[i] = TAT[i] - brust[i];
            totalAroundTime += TAT[i];
            waitTime += WT[i];
        }

        System.out.print("\n\nPriority\tProcess\t\tBrusttime\tCompletion\tWT\tTAT");
        for (int i = 0; i < n; i++) {
            System.out.print("\n" + priority[i] + "\t\t" + process[i] + "\t\t" + brust[i] + "\t\t" + completion[i] + "\t\t" + WT[i] + "\t" + TAT[i]);
        }

        System.out.print("\n\nAverage Turn Around Time: " + (totalAroundTime / n));
        System.out.print("\nAverage Wait Time: " + (waitTime / n));
        sc.close();
    }
}
