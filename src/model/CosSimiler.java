package model;


import java.text.NumberFormat;
import java.lang.String;
public class CosSimiler {
    private static Object count_fenzi;

    /*余弦相似度*/
    public static String cosineSimilarity(double[][] num,int n) {/*n是选择的行数*/
        long fenzi = 0;
        long []count_fenzi = new long[34];
        int t = 0;
        for (int x = 0; x < num.length; x++) {
            for (int y = 0; y < num[n].length; y++) {
                if (x != n) {
                    fenzi += num[n][y] * num[x][y];
                    count_fenzi[t] = fenzi;
                    t++;
                }
            }
        }
        long left = 0;
        long right = 0;
        long []count_right = new long[34];
        int temp = 0;
        for (int x = 0; x < num.length; x++) {
            for (int y = 0; y < num[n].length; y++) {
                if (x != n) {
                    right += num[x][y] * num[x][y];
                    count_right[temp] = right;
                    temp++;
                }
            }
        }
            for (int i = 0; i < num[n].length; i++) {
                left += num[n][i] * num[n][i];
            }
            String Str = "";
            NumberFormat numberFormat = NumberFormat.getNumberInstance();
            numberFormat.setMaximumFractionDigits(2);
            for(int i = 0;i < num[n].length-1;i++){
                String result = numberFormat.format(count_fenzi[i] / Math.sqrt(left * count_right[i]) * 100);
                Str += result + "%"+" ";
            }
            return Str;
        }
    }
