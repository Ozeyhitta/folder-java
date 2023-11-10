package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//lớp folder đại diện cho 1 thư mục trong 1 tập tin, có khả năng chứa danh sách các folder con và file.
//lớp này cung cấp phương thức truy cập , cập nhật thông tin về folder và file
public class Folder { //lop dai dien cho thu muc, chua folder con va file
    private String name;
    private List<Folder> folders = new LinkedList<>(); //khoi tao danh sach thu muc con duoi dang LinkedList
    private List<File> files = new LinkedList<>(); //khoi tao danh sach các thư mục con dưới dạng LinkedList

    public Folder(String name) { //hàm khởi tạo (constructor) của lớp folder nhận 1 tham số là name để thiết lập
                                // tên cho thư mục
        this.name = name;
    }
    //phương thức getter và setter
    public String getName() {
        return name;
    } //cho phép đọc giá trị từ bàn phím thuộc tính name

    public void setName(String name) {
        this.name = name;
    } //thiết lập giá trị name mới được nhập

    public List<Folder> getFolders() {
        return folders;
    } //cho phép đọc giá trị từ bàn phím

    public void setFolders(List<Folder> folders) {
        this.folders = folders;
    } // thiết lập giá trị folder mới
                                                                            // vừa nhập

    public List<File> getFiles() {
        return files;
    } //cho phép đọc giá trị từ bàn phím thuộc tính file

    public void setFiles(List<File> files) {
        this.files = files;
    } //thiết lập giá trị file mới được nhập từ bàn phím
}
