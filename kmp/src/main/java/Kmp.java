import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.*;
import java.util.*;

public class Kmp {
    //algorithm knuth morris pratt

    /**
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
     * @param string string where we should find
     * @param substring what we should find
     * @return array with indexes of substring in string
     */

    public Integer[] kmp(String string, String substring) throws IOException {
        int[] pref = prefixfunc(substring);
        int len = string.length();

        ArrayList<Integer> answer = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(string));

        int ch = bufferedReader.read();
        int j = 0;

        for (int i = 0; ch != 0; i++){
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
        Integer[] answ = {-1};
        answ = (answer.toArray(answ));

        if (answ[0] != -1)
            return answ;
        else
            return null;
        }
}
