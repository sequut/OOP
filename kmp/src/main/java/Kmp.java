import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.*;
import java.util.*;

public class Kmp {

    /**
     *
     * the prefix function builds an array with numbers,
     * where numbers are the maximum prefixes
     * that match the suffixes of the string,
     * while the indices of this array are
     * the number of characters that are included in the string
     *
     * @param stroka string which we should find
     * @return returns array with indexes
     */

    private static int[] prefixfunc(String stroka){
        int[] pref = new int[stroka.length()];
        for (int i = 1; i<stroka.length(); i++){
            int j = pref[i - 1];
            while(j>0 && stroka.charAt(i)!= stroka.charAt(j)){
                j = pref[j - 1];
            }
            if (stroka.charAt(i) == stroka.charAt(j))
                pref[i] = j + 1;
            else
                pref[j] = i;
        }
        return pref;
    }

    /**
     *
     * kmp finds an array of occurrences of a substring in a string
     * by calling the prefix function
     *
     * @param string string where we should find
     * @param substring what we should find
     * @return array with indexes of substring in string
     */

    public Integer[] kmp(String string, String substring) throws IOException {
        if (substring.length() == 0)
            return null;

        int[] pref = prefixfunc(substring);
        int len = substring.length();

        ArrayList<Integer> answer = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(string));

        int ch = bufferedReader.read();
        int j = 0;

        for (int i = 0; ch != -1; i++){
            char c = (char) ch;
            ch = bufferedReader.read();
            while(j > 0 && c != substring.charAt(j))
                j = pref[j - 1];
            if (c == substring.charAt(j))
                j++;
            if (j==len){
                answer.add(i - j + 1);
                j = pref[j - 1];
            }
        }

        Integer[] answ = new Integer[]{};
        answ = (answer.toArray(answ));
        return answ;
    }
}