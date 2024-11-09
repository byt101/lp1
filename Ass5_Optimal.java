import java.util.*;

public class Ass5_Optimal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter number of Frames: ");
        int frames[] = new int[sc.nextInt()];
        Arrays.fill(frames, -1);

        System.out.println("Enter number of Pages: ");
        int pages[] = new int[sc.nextInt()];

        System.out.println("Enter the pages: ");
        for (int i = 0; i < pages.length; i++) pages[i] = sc.nextInt();

        int faults = 0, hits = 0;
        for (int i = 0; i < pages.length; i++) {
            if (contains(frames, pages[i])) hits++;
            else {
                int pos = findReplacement(frames, pages, i);
                frames[pos] = pages[i];
                faults++;
            }
        }

        System.out.println("\nTotal Page Faults: " + faults);
        System.out.println("Total Page Hits: " + hits);
        sc.close();
    }

    static boolean contains(int[] frames, int page) {
        for (int f : frames) if (f == page) return true;
        return false;
    }

    static int findReplacement(int[] frames, int[] pages, int currentPageIndex) {
        int pos = -1, farthest = currentPageIndex;
        for (int i = 0; i < frames.length; i++) {
            int j;
            for (j = currentPageIndex + 1; j < pages.length; j++)
                if (frames[i] == pages[j]) break;
            if (j > farthest) { farthest = j; pos = i; }
            if (frames[i] == -1) return i;
        }
        return pos;
    }
}
