import java.util.ArrayList;
import java.util.Scanner;

public class Ass5_LRU {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter How Many Frames You Want : ");
        int frame = sc.nextInt(), pagefault = 0,hits=0;
        System.out.print("How Many Pages You Want : ");
        int pg = sc.nextInt(), pages[] = new int[pg];
        
        System.out.print("\nEnter Pages : ");
        for (int i = 0; i < pg; i++) pages[i] = sc.nextInt();

        ArrayList<Integer> s = new ArrayList<>(frame);
        for (int i : pages) {
            if (!s.contains(i)) {
                if (s.size() == frame) s.remove(0);
                s.add(i);
                pagefault++;
            } else {
                s.remove((Integer) i);
                s.add(i);
                hits++;
            }
        }
        System.out.print("Page Faults: " + pagefault);
        System.out.print("Page Hits :"+hits);
        sc.close();
    }
}
