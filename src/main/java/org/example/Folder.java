package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//lớp folder đại diện cho 1 thư mục trong 1 tập tin, có khả năng chứa danh sách các folder con và file.
//lớp này cung cấp phương thức truy cập , cập nhật thông tin về folder và file
public class Folder { //lop dai dien cho thu muc, chua folder con va file
    private String name;
    private LinkedListManual<Folder> folders = new LinkedListManual<>();
    //khoi tao danh sach thu muc con duoi dang LinkedList
    private LinkedListManual<File> files = new LinkedListManual<>();
    //khoi tao danh sach các thư mục con dưới dạng LinkedList

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

    public LinkedListManual<Folder> getFolders() {
        return folders;
    } //cho phép đọc giá trị từ bàn phím

    public void setFolders(LinkedListManual<Folder> folders) {
        this.folders = folders;
    } // thiết lập giá trị folder mới
                                                                            // vừa nhập

    public LinkedListManual<File> getFiles() {
        return files;
    } //cho phép đọc giá trị từ bàn phím thuộc tính file

    public void setFiles(LinkedListManual<File> files) {
        this.files = files;
    } //thiết lập giá trị file mới được nhập từ bàn phím

    private String path;
    public String getPath(){
        return this.path;
    }

    private Folder parent; //lưu trữ tham chiếu đến folder cha.
    //cho biết con trỏ đến folder hiện tại, hỗ trợ việc di chuyển lùi trong cấu trúc thư mục

    //constructor cap nhat
    public Folder(String name,Folder parent) {
    this.name=name; //gan ten nhan duoc cho truong name cuar doi tuong
        //dung de dat ten cho folder
    this.parent=parent;//gan folder cha cho truong parent cua doi tuong
    //dung de dat folder cha cua thư mục hiện tại

    }

    public Folder getParent(){
        return this.parent; // tra ve thu muc cha cua thu muc hien tai
        // neu thu muc nay la thu muc goc(root) thi tra ve null
    }

}
