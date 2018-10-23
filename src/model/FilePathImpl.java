package model;
import javafx.stage.*;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

/*
*
*    这个类实现了FilePathInterface中的getFilePath的方法
*
* */

public class FilePathImpl implements FilePathService{
    private Stage mainStage = null;
    private String filePath = null;
    private String fileName = null;
    @Override
    public String getFilePath() {
        try {
            Stage mainStage = null;
            FileChooser fileChooser = new FileChooser(); //构建一个文件选择器实例
            File selectedFile = fileChooser.showOpenDialog(mainStage); //在实例中选择“打开文件”模式在传入窗口中显示。可以看出他返回用户选择文件的一个实例
            fileChooser.setTitle("选择文件");
            this.filePath = selectedFile.getPath();
            this.fileName = selectedFile.getName();
            if(selectedFile.isFile()){
                return selectedFile.getPath();
            }
            else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    @Override
    public String getFileName() {
        return this.fileName;
    }
}
