package controller;


import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.*;

public class IndexViewController implements Initializable {
    private Main application;
    private ArrayList resultData;
    private ObservableList<Data> datas = FXCollections.observableArrayList();
    private RadioButton radioButton;
    @FXML
    private Text resultName;
    @FXML
    private Text resultPercent;
    @FXML
    private Text fileName;
    @FXML
    private BarChart<String, Double> bc;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private TableView tv;
    @FXML
    private ToggleGroup methodGroup;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private JFXComboBox<Integer> ColumnIdSelect;
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn similarColumn;


    public void setApp(Main application) {
        this.application = application;
        for(int i =0 ;i < 20; i++) {
            ColumnIdSelect.getItems().add(i + 1);
        }
        ColumnIdSelect.setPromptText("原始样本id");
    }

    //刷新图表
    public void fetchChart(ArrayList data, int index) {
        GetMaxOrMinValue getMaxOrMinValue = new GetMaxOrMinValue();
        getMaxOrMinValue.setMaxValue(data, index);
        String result = "GL-";
        result += getMaxOrMinValue.getIndex();
        resultName.setText(result);
        String percent = "";
        percent += getMaxOrMinValue.getPercent();
        percent += "%";
        resultPercent.setText(percent);
        bc.getData().clear();
        bc.setTitle("分析结果");
        xAxis.setLabel("23");
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        for(int i = 0; i < data.size(); i++) {
            if(i == index){
                continue;
            }
            Data item = new Data(i, Double.valueOf(data.get(i).toString()));
            series.getData().add(new XYChart.Data<String, Double>(item.getName(),item.getSimilar()));
        }
        bc.getData().add(series);
    }

    public void fetchTable(ArrayList data, int index) {
        datas.clear();
        tv.setEditable(false);
        for(int i = 0; i < data.size(); i++) {
            if(i == index){
                continue;
            }
            Data item = new Data(i, Double.valueOf(data.get(i).toString()));
            datas.add(item);
        }
        idColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        similarColumn.setCellValueFactory(new PropertyValueFactory<>("similar"));
        similarColumn.setSortType(TableColumn.SortType.DESCENDING);
        idColumn.setSortType(TableColumn.SortType.ASCENDING);
        tv.setItems(datas);
    }

    //点击选择文件按钮action
    @FXML
    public void onChooseFileClick() {
        ReadExcelImpl readExcelImpl = new ReadExcelImpl();
        FilePathImpl file = new FilePathImpl();
        String path = file.getFilePath();
        if(file.getFileName() != null){
            fileName.setText(file.getFileName());
        }
        application.onFileChooseAction(readExcelImpl.getData_2DArray(path));
    }

    //点击开始分析文件的按钮action
    @FXML
    public void onAnalyseClick() {
        radioButton = (RadioButton)methodGroup.getSelectedToggle();
        application.onChangeColumnId(ColumnIdSelect.getValue() - 1);
        application.onAnalyseAction(getAlgorithmValue(radioButton.getText()));
    }
    public int getAlgorithmValue(String s) {
        if (s.equals("余弦相似度")) {
            return 0;
        } else if (s.equals("欧氏距离")){
            return 1;
        }
        return 2;
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }
}
