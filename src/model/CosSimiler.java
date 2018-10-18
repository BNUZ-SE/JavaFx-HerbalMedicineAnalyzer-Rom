package model;


import java.text.NumberFormat;

public class CosSimiler {
    /*余弦相似度*/
    public static String cosineSimilarity(double[] A, double[] B) {
        long fenzi = 0;
        for (int i = 0; i < A.length; i++) {
            fenzi += A[i] * B[i];
        }
        long left = 0;
        long right = 0;
        for (int i = 0; i < A.length; i++) {
            left += A[i] * A[i];
            right += B[i] * B[i];
        }
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format(fenzi / Math.sqrt(left * right)*100);
        return result + "%";
    }
}
