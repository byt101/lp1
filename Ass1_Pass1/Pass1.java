import java.io.*;

public class Pass1 {
    private static final int MAX = 100;

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("E:/CODE/LP1/Ass1_Pass1/Input.txt"));
        String[][] symbolTable = new String[MAX][3];
        String[][] opcodeTable = new String[MAX][3];
        String[][] literalTable = new String[MAX][2];
        int[] poolTable = new int[MAX];

        String line;
        int lineCount = 0, LC = 0, symTabLine = 0, opTabLine = 0, litTabLine = 0, poolTabLine = 0;

        System.out.println("_________________");

        while ((line = bufferedReader.readLine()) != null) {
            String[] tokens = line.split("\t");
            printLine(tokens);
            if (lineCount == 0) {
                LC = Integer.parseInt(tokens[1]); // Set LC to operand of START
            } else {
                if (!tokens[0].isEmpty()) {
                    addSymbol(symbolTable, tokens[0], LC, symTabLine++);
                } else if ("DS".equalsIgnoreCase(tokens[1]) || "DC".equalsIgnoreCase(tokens[1])) {
                    addSymbol(symbolTable, tokens[0], LC, symTabLine++);
                }

                if (tokens.length == 3 && tokens[2].charAt(0) == '=') {
                    literalTable[litTabLine][0] = tokens[2];
                    literalTable[litTabLine][1] = Integer.toString(LC);
                    litTabLine++;
                }

                if (tokens[1] != null) {
                    addOpcode(opcodeTable, tokens[1], opTabLine++);
                }
            }
            lineCount++;
            LC++;
        }

        System.out.println("_________________");
        printTable("SYMBOL TABLE", new String[]{"SYMBOL", "ADDRESS", "LENGTH"}, symbolTable, symTabLine);
        printTable("OPCODE TABLE", new String[]{"MNEMONIC", "CLASS", "INFO"}, opcodeTable, opTabLine);
        printTable("LITERAL TABLE", new String[]{"LITERAL", "ADDRESS"}, literalTable, litTabLine);

        poolTabLine = initializePoolTable(literalTable, poolTable, litTabLine);
        printPoolTable(poolTable, poolTabLine);
        bufferedReader.close();
    }

    private static void printLine(String[] tokens) {
        for (String token : tokens) {
            System.out.print(token + "\t");
        }
        System.out.println();
    }

    private static void addSymbol(String[][] symbolTable, String symbol, int address, int index) {
        symbolTable[index][0] = symbol;
        symbolTable[index][1] = Integer.toString(address);
        symbolTable[index][2] = "1"; // Default length
    }

    private static void addOpcode(String[][] opcodeTable, String mnemonic, int index) {
        opcodeTable[index][0] = mnemonic;
        opcodeTable[index][1] = getOpcodeClass(mnemonic);
        opcodeTable[index][2] = getOpcodeInfo(mnemonic);
    }

    private static String getOpcodeClass(String mnemonic) {
        switch (mnemonic.toUpperCase()) {
            case "START":
            case "END":
            case "ORIGIN":
            case "EQU":
            case "LTORG":
                return "AD";
            case "DS":
            case "DC":
                return "DL";
            default:
                return "IS";
        }
    }

    private static String getOpcodeInfo(String mnemonic) {
        switch (mnemonic.toUpperCase()) {
            case "START":
            case "END":
            case "ORIGIN":
            case "EQU":
            case "LTORG":
                return "R11";
            case "DS":
            case "DC":
                return "R7";
            default:
                return "(04,1)";
        }
    }

    private static void printTable(String title, String[] headers, String[][] table, int lines) {
        System.out.printf("\n\n %s\n", title);
        System.out.println("--------------------------");
        for (String header : headers) {
            System.out.printf("%s\t", header);
        }
        System.out.println();
        System.out.println("--------------------------");
        for (int i = 0; i < lines; i++) {
            for (String data : table[i]) {
                System.out.printf("%s\t", data);
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }

    private static int initializePoolTable(String[][] literalTable, int[] poolTable, int litTabLine) {
        int poolTabLine = 0;
        for (int i = 0; i < litTabLine; i++) {
            if (literalTable[i][0] != null) {
                // Ensure we don't check out-of-bounds and compare with the next entry
                if (i == litTabLine - 1 || Integer.parseInt(literalTable[i][1]) < Integer.parseInt(literalTable[i + 1][1]) - 1) {
                    poolTable[poolTabLine++] = i + 1;
                }
            }
        }
        return poolTabLine;
    }

    private static void printPoolTable(int[] poolTable, int poolTabLine) {
        System.out.println("\n\n   POOL TABLE");
        System.out.println("-----------------");
        System.out.println("LITERAL NUMBER");
        System.out.println("-----------------");
        for (int i = 0; i < poolTabLine; i++) {
            System.out.println(poolTable[i]);
        }
        System.out.println("------------------");
    }
}
