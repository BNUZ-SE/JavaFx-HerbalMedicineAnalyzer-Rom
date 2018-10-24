package model;

import javafx.beans.property.SimpleDoubleProperty;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;

public class GetMaxOrMinValue {
    private int index;
    private double percent;
    public void setMaxValue(ArrayList arrayList, int in) {
        double max = -1;
        int index = 0;
        for(int i = 0; i < arrayList.size(); i++) {
            if(in == i){
                continue;
            }
            else if(Double.parseDouble(arrayList.get(i).toString()) > max) {
                max = Double.parseDouble(arrayList.get(i).toString());
                index = i;
            }
        }
        this.index = index;
        this.percent = max;
    }
    public void setMinValue(ArrayList arrayList) {
        double min = 1000;
        int index = 0;
        for(int i = 0; i < arrayList.size(); i++) {
            if(Double.parseDouble(arrayList.get(i).toString()) < min) {
                min = Double.parseDouble(arrayList.get(i).toString());
                index = i;
            }
        }
        this.index = index;
        this.percent = min;
    }

    public int getIndex() {
        return index + 1;
    }

    public double getPercent() {
        double newp = new BigDecimal(percent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return newp;
    }
}
