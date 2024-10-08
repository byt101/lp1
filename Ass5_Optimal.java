import java.util.*;
import java.io.*;

public class Ass5_Optimal {
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter number of Frames: ");
        int numberOfFrames = sc.nextInt();
        int frame[] = new int[numberOfFrames];
        Arrays.fill(frame, -1);  // Initialize frame array with -1

        System.out.println("Enter number of Pages: ");
        int numberOfPages = sc.nextInt();
        int pages[] = new int[numberOfPages];
        
        System.out.println("Enter the pages: ");
        for (int i = 0; i < numberOfPages; i++) pages[i] = sc.nextInt();

        int faults = 0, hit = 0, pos = 0;
        for (int i = 0; i < numberOfPages; i++) {
            boolean hitFlag = false;

            // Check if the page is already in one of the frames
            for (int j : frame) {
                if (j == pages[i]) {
                    hit++;
                    hitFlag = true;
                    break;
                }
            }

            if (!hitFlag) {
                boolean emptyFrameFound = false;
                // Place page in the first empty frame (if any)
                for (int j = 0; j < numberOfFrames; j++) {
                    if (frame[j] == -1) {
                        frame[j] = pages[i];
                        faults++;
                        emptyFrameFound = true;
                        break;
                    }
                }

                if (!emptyFrameFound) {
                    int temp[] = new int[numberOfFrames];
                    Arrays.fill(temp, -1);

                    // Check future positions of frames
                    for (int j = 0; j < numberOfFrames; j++) {
                        for (int k = i + 1; k < numberOfPages; k++) {
                            if (frame[j] == pages[k]) {
                                temp[j] = k;
                                break;
                            }
                        }
                    }

                    // Find the frame that won't be used soonest
                    int max = -1;
                    for (int j = 0; j < numberOfFrames; j++) {
                        if (temp[j] == -1) {
                            pos = j;
                            break;
                        } else if (temp[j] > max) {
                            max = temp[j];
                            pos = j;
                        }
                    }

                    frame[pos] = pages[i];
                    faults++;
                }
            }
        }

        System.out.println("\n\nTotal Page Faults: " + faults);
        System.out.println("Total Page Hits: " + hit);
        sc.close();
    }
}
