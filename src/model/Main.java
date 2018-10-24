package model;

import controller.IndexViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import controller.IndexViewController;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;


/*
 *  所有接口的定义
 *
 * */

interface ReadExcelService {
    //TODO 读xlsx然后返回一个二维数组
    double [][] getData_2DArray(String xlsAddress);
}


interface FilePathService {
    //TODO 获取文件所在的路径
    String getFilePath();
    String getFileName();
}

interface DatasAnalyseService {
    //TODO 传一个二维数组data进去根据组的ID去和其他组匹配 返回与每个组的匹配度
    void doAnalyse(double[][] data, int id);
    ArrayList getResultDatas();
}
/*
 *
 *
 *
 *
 * */
public class Main extends Application {
    private Stage stage;
    private int columnId;
    private double[][] datas = null;
    public IndexViewController indexView;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        stage.setTitle("分析");
        stage.setResizable(false);
        goToIndexView(); // 调用进入首页
        stage.show();
    }

    //进入首页
    public void goToIndexView() {
        try {
            this.indexView = (IndexViewController) replaceSceneContent("../view/IndexView.fxml");
            indexView.setApp(this);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    //实现选择XLSX
    public void onFileChooseAction(double[][] datas) {
        try {
            this.datas = datas;
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    //实现切换原始样本ID
    public void onChangeColumnId(int columnId) {
        try {
             this.columnId = columnId;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //实现分析数据
    public void onAnalyseAction(int algorithmValue) {
        try {
            EuclideanMetric euclideanMetric = new EuclideanMetric();
            euclideanMetric.doAnalyse(this.datas, this.columnId);
            CosSimiler cosSimiler = new CosSimiler();
            cosSimiler.doAnalyse(this.datas, this.columnId);
            Pearson pearson = new Pearson();
            pearson.doAnalyse(this.datas, this.columnId);
            if(algorithmValue == 0) {
                indexView.fetchChart(cosSimiler.getResultDatas(), this.columnId );
                indexView.fetchTable(cosSimiler.getResultDatas(), this.columnId );
            } else if (algorithmValue == 1) {
                indexView.fetchChart(euclideanMetric.getResultDatas(), this.columnId );
                indexView.fetchTable(euclideanMetric.getResultDatas(), this.columnId );
            } else if(algorithmValue == 2) {
                indexView.fetchChart(pearson.getResultDatas(), this.columnId);
                indexView.fetchTable(pearson.getResultDatas(), this.columnId);
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }


    //切换舞台 控制器
    Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page, 900, 600);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
