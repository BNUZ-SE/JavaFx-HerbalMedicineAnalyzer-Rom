package controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.text.Text;
import model.FilePathImpl;
import model.ReadExcelImpl;
import model.Main;
public class IndexViewController implements Initializable{
    private Main application;

    @FXML
    private Text fileName;

    public void setApp(Main application) {
        this.application = application;
    }

    //点击选择文件按钮action
    @FXML
    public void onChooseFileClick() {
        ReadExcelImpl readExcelImpl = new ReadExcelImpl();
        FilePathImpl file = new FilePathImpl();
        String path = file.getFilePath();
        System.out.println(path);
        if(file.getFileName() != null){
            fileName.setText(file.getFileName());
        }
        application.onFileChooseAction(readExcelImpl.getData_2DArray(path));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }
}
