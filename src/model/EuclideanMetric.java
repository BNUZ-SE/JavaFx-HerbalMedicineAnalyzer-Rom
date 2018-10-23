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
    public String sim_distance(double[][]num,int n) {
        double distance = 0;
        double []count_ditance = new double [34];
        int t = 0;
        for(int x = 0;x < num.length;x++){
            for (int y = 0; y < num[n].length; y++) {
                if(x != n){
                    double temp = Math.pow((num[n][y] - num[x][y]), 2);
                    distance += temp;
                    count_ditance[t] = distance;
                    t++;
                }
            }
        }
        for(int i = 0;i < count_ditance.length;i++){
                count_ditance[i] = Math.sqrt(count_ditance[i]);
        }
        String Str = "";
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(2);
        for(int i = 0; i < count_ditance.length;i++){
            String result = numberFormat.format(count_ditance[i]);
            Str += result + "%"+ " ";
        }
        return Str;
    }
}
