import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.*;
import java.util.*;


public class Kmp {
    //algorithm knuth morris pratt
    /**
     *
     * @param stroka string which we should find
     * @return
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

    /*
    public static Integer[] kmp(String[] string) {
        ;
    }
*/
}
