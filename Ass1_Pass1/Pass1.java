
import java.io.*;

public class Pass1 {
    public static void main(String args[]) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:/Users/sujal/Desktop/LP1/Ass1_Pass1/input.txt"));

        final int MAX = 100;
        String[][] SymbolTab = new String[MAX][3];
        String[][] OpTab = new String[MAX][3];
        String[][] LitTab = new String[MAX][2];
        int[] PoolTab = new int[MAX];
        int LC = 0, line_count = 0, symTabLine = 0, opTabLine = 0, litTabLine = 0, poolTabLine = 0;
        String line;

        System.out.println("_");

        while ((line = bufferedReader.readLine()) != null) {
            String[] tokens = line.split("\t");
            if (line_count == 0) {
                LC = Integer.parseInt(tokens[1]);
                for (String token : tokens) System.out.print(token + "\t");
            } else {
                System.out.println();
                for (String token : tokens) System.out.print(token + "\t");
                System.out.println();

                if (!tokens[0].isEmpty()) {
                    SymbolTab[symTabLine++] = new String[]{tokens[0], Integer.toString(LC), "1"};
                } else if (tokens[1].equalsIgnoreCase("DS") || tokens[1].equalsIgnoreCase("DC")) {
                    SymbolTab[symTabLine++] = new String[]{tokens[0], Integer.toString(LC), "1"};
                }

                if (tokens.length == 3 && tokens[2].charAt(0) == '=') {
                    LitTab[litTabLine++] = new String[]{tokens[2], Integer.toString(LC)};
                } else if (tokens[1] != null) {
                    OpTab[opTabLine][0] = tokens[1];
                    if ("START END ORIGIN EQU LTORG".contains(tokens[1].toUpperCase())) {
                        OpTab[opTabLine++] = new String[]{tokens[1], "AD", "R11"};
                    } else if ("DS DC".contains(tokens[1].toUpperCase())) {
                        OpTab[opTabLine++] = new String[]{tokens[1], "DL", "R7"};
                    } else {
                        OpTab[opTabLine++] = new String[]{tokens[1], "IS", "(04,1)"};
                    }
                }
            }
            line_count++;
            LC++;
        }

        displayTable("SYMBOL TABLE", "SYMBOL\tADDRESS\tLENGTH", SymbolTab, symTabLine);
        displayTable("OPCODE TABLE", "MNEMONIC\tCLASS\tINFO", OpTab, opTabLine);
        displayTable("LITERAL TABLE", "LITERAL\tADDRESS", LitTab, litTabLine);

        for (int i = 0; i < litTabLine - 1; i++) {
            if (LitTab[i][0] != null && (i == 0 || Integer.parseInt(LitTab[i][1]) < Integer.parseInt(LitTab[i + 1][1]) - 1)) {
                PoolTab[poolTabLine++] = i + 1;
            }
        }

        System.out.println("\n\n   POOL TABLE    ");
        System.out.println("-----------------");
        System.out.println("LITERAL NUMBER");
        for (int i = 0; i < poolTabLine; i++) System.out.println(PoolTab[i]);
        System.out.println("------------------");

        bufferedReader.close();
    }

    private static void displayTable(String title, String header, String[][] table, int count) {
        System.out.println("\n\n  " + title);
        System.out.println("--------------------------");
        System.out.println(header);
        for (int i = 0; i < count; i++) {
            for (String col : table[i]) System.out.print(col + "\t");
            System.out.println();
        }
        System.out.println("--------------------------");
    }
}