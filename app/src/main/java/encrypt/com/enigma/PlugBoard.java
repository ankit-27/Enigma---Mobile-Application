package encrypt.com.enigma;

/**
 * Created by ankit on 27/9/16.
 */

public class PlugBoard {
    private char[] board = {'Q', 'E', 'T', 'U', 'O', 'W', 'R', 'Y', 'I', 'P', 'A', 'D', 'G', 'J', 'L', 'S', 'F', 'H', 'K', 'Z', 'C', 'B', 'M', 'X', 'V', 'N'};
    private char[] arr1 = new char[5];
    private char[] arr2 = new char[5];
    private int count = 0;
    private char c;

    public char plugBoardOutput(char ch) {
        int i;
        for (i = 0; i < 26; i++) {
            if (ch == board[i])
                break;
        }
        return board[25 - i];
    }

    public void getSetting(char pb1, char pb2) {


        arr1[count] = pb1;
        arr2[count] = pb2;
        count++;

    }

    public char pbOutput(char ch) {
        int i, flag = 0;
        for (i = 0; i < 5; i++) {
            if (ch == arr1[i]) {
                flag = 1;
                break;
            }
        }
        if (flag == 1)
            c = arr2[i];
        for (i = 0; i < 5; i++) {
            if (ch == arr2[i]) {
                flag = 2;
                break;
            }
        }
        if (flag == 2)
            c = arr1[i];
        if (flag == 0)
            c = ch;
        return c;
    }
}
