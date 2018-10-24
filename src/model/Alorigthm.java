package model;

import java.util.ArrayList;


abstract class Algorithm implements DatasAnalyseService{
    //TODO 算法的抽象类
    private ArrayList resultDatas;
    public ArrayList getResultDatas(){
        return this.resultDatas;
    }
    public void setResultDatas(ArrayList resultDatas) {
        this.resultDatas = resultDatas;
    }
}


class EuclideanMetric extends Algorithm {
    @Override
    public void doAnalyse(double[][] data, int n) {
        //TODO 算法实现　
        ArrayList<Double> result = new ArrayList<>();
        double temp;
        double distance;
        double[] count_distance = new double[20];
        int t = 0;
        for (int j = 0;  j < data[0].length; j++) {
            distance = 0;
            for (int i = 0; i < data.length; i++) {
                distance += Math.pow((data[i][j] - data[i][n]), 2);
            }
            count_distance[t++] = distance;
        }
        for (int i = 0; i < count_distance.length; i++) {
            count_distance[i] = 1 / (1 + Math.sqrt(count_distance[i]));
            result.add((1 - count_distance[i]) * 100);
        }
        this.setResultDatas(result);
    }
}


class CosSimiler extends Algorithm {
    @Override
    public void doAnalyse(double[][] data, int n) {
        double upper = 0;
        double downerA = 0;
        double downerB = 0;
        ArrayList<Double> result = new ArrayList<>();
        for(int j = 0; j < data[n].length; j++) {
            upper = 0;
            downerA = 0;
            downerB = 0;
            for(int i = 0; i < data.length; i++) {
                upper += data[i][j] * data[i][n];
                downerA += Math.pow(data[i][j], 2);
                downerB += Math.pow(data[i][n], 2);
            }
            result.add(upper / (Math.sqrt(downerA) * Math.sqrt(downerB)) * 100);
        }
        this.setResultDatas(result);
    }
}

class Pearson extends Algorithm {
    @Override
    public void doAnalyse(double [][] data, int n) {
        ArrayList<Double> result = new ArrayList<>();
        double[] x = new double[35];
        double[] y =  new double[35];
        for(int j = 0; j < data[n].length; j++) {
            for(int i = 0; i < data.length; i++) {
                x[i] = data[i][j];
                y[i] = data[i][n];
            }
            result.add(getPearson(x,y)*100);
        }
        this.setResultDatas(result);
    }
    private double getPearson(double[] x, double[] y){
        if (x.length != y.length)
            throw new RuntimeException("shujuyichang!");
        double upper = 0;
        double left = 0, right = 0;
        double avgX = 0;
        double avgY = 0;
        int n = x.length;

        for (int i = 0; i < n; i++){
            avgX += x[i];
            avgY += y[i];
        }
        avgX /= n;
        avgY /= n;
        for(int i =0; i < n; i++) {
            upper += ((x[i] - avgX) * (y[i] - avgY));
            right += ((y[i] - avgY) * (y[i] - avgY));
            left += ((x[i]-avgX) * (x[i]-avgX));
        }
        double fenzi = upper;

        double fenmu = Math.sqrt(right * left);
        return fenzi / fenmu;
    }

}
