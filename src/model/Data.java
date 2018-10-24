package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;

public class Data {
    private SimpleDoubleProperty similar;
    private SimpleStringProperty name;
    public Data(int index, double similar) {
        String s = "GL-";
        s += (index + 1);
        this.name = new SimpleStringProperty(s);
        this.similar = new SimpleDoubleProperty(new BigDecimal(similar).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    public double getSimilar() {
        return similar.get();
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setSimilar(double similar) {
        this.similar.set(similar);
    }
}
