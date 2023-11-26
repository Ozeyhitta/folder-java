package org.example;
import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.InputResult;
import de.codeshelf.consoleui.prompt.ListResult;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Objects;


public class File { // lop dai dien cho tep tin, bao gom ten, noi dung va phan mo rong cua tep
    private String name; // khai báo tên của file
    private String content; //khai báo nội dung của file

    public File() {
    }
    public File(String name) {
        this.name = name;
    }

    //phương thức getter và setter
    public String getExtension() {
        return extension;
    } //cho phép đọc giá trị từ bàn phím thuộc tính extension

    public void setExtension(String extension) {  //tạo 1 constructor tạo đối tượng extension
        this.extension = extension;
    }

    private String extension;

    // Constructor, getters, setters, ...
    public File(String name, String content) {
        this.name = name;
        this.content = content;
    }

    // Phương thức để sao chép file này
    public File copy() {

        return new File(this.name, this.content); // Tạo và trả về một bản sao của file này
    }
    public static boolean KiemTraDinhDang(String tentep,String dinhdang){
        if(dinhdang==null)
        {
            return false;
        }
        return tentep.endsWith(dinhdang);
    }
    //phương thức getter và setter
    public String getName() {
        return name;
    }
    //lay ten nguoi dung nhop vao
    public void setName(String name) {
        this.name = name;
    }
    //gan ten bang voi gia tri nguoi dung nhap
    public String getContent() {
        return content;
    }
    //lay noi dung nguoi dung nhap
    public void setContent(String content) {
        this.content = content;
    }
    // Phương thức để sao chép file này đến một thư mục khác
    public void copyTo(Folder destinationFolder) throws IOException {
        // Giả định rằng file này chỉ là một đối tượng đơn giản và không thực sự liên kết với file hệ thống
        File newFile = new File(this.name); // Tạo một bản sao của file này
        destinationFolder.getFiles().add(newFile); // Thêm bản sao vào thư mục đích
    }
}
