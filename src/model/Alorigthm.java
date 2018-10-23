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

class firstAlgorithm extends Algorithm {

    public void doAnalyse(double[][] data, int id) {
        //TODO 算法实现　
        ArrayList result = new ArrayList();
        this.setResultDatas(result);
    }

}

//TODO 补充算法

