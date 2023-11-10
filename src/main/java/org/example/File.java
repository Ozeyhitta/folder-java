package org.example;
//
public class File { // lop dai dien cho tep tin, bao gom ten, noi dung va phan mo rong cua tep
    private String name; // khai báo tên của file
    private String content; //khai báo nội dung của file
    //phương thức getter và setter
    public String getExtension() {
        return extension;
    } //cho phép đọc giá trị từ bàn phím thuộc tính extension

    public void setExtension(String extension) {  //tạo 1 constructor tạo đối tượng extension
        this.extension = extension;
    }

    private String extension;


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

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
