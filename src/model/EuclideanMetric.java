package model;

import java.text.NumberFormat;

public class EuclideanMetric {
    /**
     * 两个向量可以为任意维度，但必须保持维度相同，表示n维度中的两点
     *
     * @param v1
     * @param v2
     * @return 两点间距离
     */
    public String sim_distance(double[] v1, double[] v2) {
        double distance = 0;

        if (v1.length == v2.length) {
            for (int i = 0; i < v1.length; i++) {
                double temp = Math.pow((v1[i] - v2[i]), 2);
                distance += temp;
            }
            distance = Math.sqrt(distance);
        }
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format(distance);
        return result;

    }
}
