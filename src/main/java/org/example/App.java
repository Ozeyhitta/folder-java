//package org.example;
//
//import javax.swing.*;
//import java.io.BufferedReader; //doc du lieu tu dau ban phim
//import java.io.IOException; //ngoai le khi xay ra loi trong qua trinh doc hoac ghi du lieu
//import java.io.InputStreamReader; // ma hoa du lieu tu dang byte sang dang ki tu
//import java.util.Objects;
//
//
///**
// * Hello world!
// */
//public class App {
//
//    public static final String EXIT = "exit"; //định nghĩa 1 chuỗi để thoát khỏi chương trình
//    public static final Folder root = new Folder("root"); //tạo 1 list va folder dau đặt tên là root.
//    public static Folder currentFolder = root; //tạo 1 biến currentFolder hiện tại là thư mục gốc
//    //hàm main
//    public static void main(String[] args) throws IOException {
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        //tạo 1 đối tượng BufferedReader đọc dữ liệu từ bàn phím
//        String str = ""; //tạo 1 string str rỗng để lưu trữ thao tác mà người dùng nhập vào.
//        System.out.println("Enter Command, when you want to exit type ‘exit’");
//        while (!str.equals(EXIT)) { //vòng lặp khi str != exit
//            System.out.println("1. Create folder");
//            System.out.println("2. Create file");
//            System.out.println("3. List all file and folder");
//            System.out.println("4. Working with folder");
//            System.out.println("5. Working with file");
//            System.out.println("6. Vi tri hien tai");
//            System.out.println("7. xoa folder");
//            System.out.println("8. xoa file");
//            System.out.println("9. doi ten folder");
//            System.out.println("10. doi ten file");
//            System.out.println("type exit if you want to complete the project");
//            System.out.println();
//            str = br.readLine(); //dọc giá trị người dùng nhập vào từ bàn phím
//            switch (str) {
//                case "1" -> createFolder(br, currentFolder); // gọi hàm tạo folder
//                case "2" -> createFile(br, currentFolder); //gọi hàm tạo file
//                case "3" -> {
//                    listFoldersAndFiles(currentFolder); // gọi hàm liệt kê folder và file
//                }
//                case "4" -> {
//                    workWithFolder(br); // gọi hàm thao tác với folder
//                }
//                case "5" -> {
//                    workWithFile(br); // gọi hàm thao tác với file
//                }
//                case "6" -> {
//                    whereAmI(); //gọi hàm vị trí hiện tại (root hay file hay folder)
//                }
//                case "7"->{
//                    deleteFolder(br);
//                }
//                case "8"->{
//                    deleteFile(br);
//                }
//                case "9"->
//                {
//                    renameFolder(br);
//                }
//                case "10"->
//                {
//                    renameFile(br);
//                }
//               // default -> {
//           //         System.out.println("nhap so khong hop le");
//           //         System.out.println("vui long nhap lai: ");
//              //  }
//            }
//        }
//        if (str.contentEquals(EXIT)) {
//            System.out.println("KetThuc!!!");
//        }
//        br.close(); //giải phóng tài nguyên sau khi sử dụng
//    }
//
//    private static void deleteFolder(BufferedReader br ) throws IOException{
//        //nhận vào 1 BufferedReader: giá trị người dùng nhập vào
//        //ném ra ngoại lệ exception khi có lỗi trong quá trình đọc dữ liệu
//        System.out.println("nhap ten folder can xoa");
//        String folderName= br.readLine(); //doc ten folder can xoa
//        if(folderName.isBlank()) //neu ten de trong
//        {
//            System.out.println("the folder name cannot be blank");
//        }
//        else{
//            Folder folderToDelete=currentFolder.getFolders() // gán tên folder vừa nhập cho currentfolder
//                    .stream() //gọi hàm stream để xử lý LinkedList
//                    .filter(folder -> folder.getName().equals(folderName))
//                    //duyệt theo list lọc các phần tử theo điều kiện nếu giống thì trả về
//                    //nếu khác thì tìm kiếm Node tiếp theo : folder -> folder.getName().equals(folderName)
//                    .findFirst()
//                    //lay file dau tien bang voi cai ten minh nhap
//                    .orElse(null);// neu duyet het khong giong ten thi tra ve null
//            if(Objects.isNull(folderToDelete)) {
//                System.out.println("Folder not found"); // nếu giá trị currentFolder.getFolder ở trên là null thì in ra
//            }
//            else
//            {
//                currentFolder.getFolders().remove(folderToDelete); // nếu không thì xóa folder đã tạo bằng hàm remove
//                System.out.println("Folder deteted successful");
//            }
//        }
//
//    }
//    private static void deleteFile(BufferedReader br)throws IOException
//    {
//        //nhận vào 1 BufferedReader: giá trị người dùng nhập vào
//        //ném ra ngoại lệ exception khi có lỗi trong quá trình đọc dữ liệu
//        System.out.println("nhap ten file can xoa");
//        String fileName= br.readLine();
//        if(fileName.isBlank())
//        {
//            System.out.println("the file name cannot be blank");
//        }
//        else
//        {
//            File fileToDelete=currentFolder.getFiles()
//                    .stream()//gọi hàm stream để xử lý LinkedList
//                    .filter(file->file.getName().equals(fileName))
//                    //duyệt theo list lọc các phần tử theo điều kiện nếu giống thì trả về
//                    //nếu khác thì tìm kiếm Node tiếp theo : folder -> folder.getName().equals(fileName)
//                    .findFirst()//lay file dau tien bang voi cai ten minh nhap
//                    .orElse(null);// neu duyet het khong giong ten thi tra ve null
//            if(Objects.isNull(fileToDelete)){// nếu giá trị currentFolder.getFile ở trên là null thì in ra
//                System.out.println("file not found");
//
//            }
//            else {// nếu không thì đổi tên file
//                currentFolder.getFiles().remove(fileToDelete);
//                System.out .println("file deleted successfully");
//            }
//        }
//    }
//    private static void renameFolder(BufferedReader br) throws IOException
//    {   //nhận vào 1 BufferedReader: giá trị người dùng nhập vào
//        //ném ra ngoại lệ exception khi có lỗi trong quá trình đọc dữ liệu
//        System.out.println("nhap ten folder can doi ten");
//        String oldFolderName = br.readLine(); //khai báo 1 String oldFolderName lưu trữ và tìm kiếm giá trị folder
//
//        if(oldFolderName.isBlank()) //nếu người dùng không nhập j cả
//        {
//            System.out.println("the name cannot be blank");
//        }
//        else {
//            System.out.println("nhap ten folder moi");
//            String newFolderName = br.readLine(); //khai báo biến newFolderName và nhập vào để đổi tên
//            if(newFolderName.isBlank())
//            {
//                System.out.println("the folder name cannot be blank");
//            }
//            else {
//                Folder folderToRename = currentFolder.getFolders()// con trỏ đến folder hiện tại
//                        .stream() //gọi hàm stream để xử lý LinkedList
//                        .filter(folder -> folder.getName().equals(oldFolderName))
//                        //duyệt theo list lọc các phần tử theo điều kiện nếu giống thì trả về
//                        //nếu khác thì tìm kiếm Node tiếp theo : folder -> folder.getName().equals(fileName)
//                        .findFirst()//lay file dau tien bang voi cai ten minh nhap
//                        .orElse(null);// neu duyet het khong giong ten thi tra ve null
//                if (Objects.isNull(folderToRename)) //nếu giá trị folderToRename đã duyệt mà trả về null thì in ra
//
//                {
//                    System.out.println("Folder not found ");
//                }
//                else { //nếu không thì đổi tên folder bằng phương thức setName
//                    folderToRename.setName(newFolderName);
//                    System.out.println("Folder da duoc doi ten");
//                }
//            }
//        }
//    }
//    private static void renameFile(BufferedReader br) throws IOException
//    {//nhận vào 1 BufferedReader: giá trị người dùng nhập vào
//        //ném ra ngoại lệ exception khi có lỗi trong quá trình đọc dữ liệu
//        System.out.println("nhap ten file can doi ten");
//        String oldFileName = br.readLine(); //khai báo 1 String oldFileName lưu trữ và tìm kiếm giá trị folder
//
//        if(oldFileName.isBlank()) //nếu người dùng không nhập j cả
//        {
//            System.out.println("the name cannot be blank");
//        }
//        else {
//            System.out.println("nhap ten file moi");
//            String newFileName = br.readLine(); //khai báo biến newFileName và nhập vào để đổi tên
//            if(newFileName.isBlank())
//            {
//                System.out.println("the file name cannot be blank");
//            }
//            else {
//                File fileToRename = currentFolder.getFiles()// con trỏ đến folder hiện tại
//                        .stream()//gọi hàm stream để xử lý LinkedList
//                        .filter(folder -> folder.getName().equals(oldFileName))
//                        //duyệt theo list lọc các phần tử theo điều kiện nếu giống thì trả về
//                        //nếu khác thì tìm kiếm Node tiếp theo : folder -> folder.getName().equals(oldFileName)
//                        .findFirst()//lay file dau tien bang voi cai ten minh nhap
//                        .orElse(null);// neu duyet het khong giong ten thi tra ve null
//                if (Objects.isNull(fileToRename))//nếu giá trị fileToRename đã duyệt mà trả về null thì in ra
//                {
//                    System.out.println("Folder not found ");
//                }
//                else {  //nếu không thì đổi tên file bằng phương thức setName
//                    fileToRename.setName(newFileName);
//                    System.out.println("Folder da duoc doi ten");
//                }
//            }
//        }
//    }
//    private static void workWithFile(BufferedReader br) throws IOException {
//        // chuyển ngoại lệ cho phương thức hoặc mã khối để gọi nó để xử lý
//        //nhận vào 1 BufferedReader: giá trị người dùng nhập vào
//        //ném ra ngoại lệ exception khi có lỗi trong quá trình đọc dữ liệu
//        String str = ""; //lưu lại lựa chọn người dùng
//        while (!str.equals("3")) { // nếu str==3 thì gọi hàm main
//            System.out.println("1. Read file"); //đọc file
//            System.out.println("2. Write to file"); //ghi vào file
//            System.out.println("3. Previous"); //quay lại hàm main
//            System.out.println("4. kiem tra dinh dang"); //kiểm tra định dạng (.)
//            str = br.readLine();
//            switch (str) {
//                case "1" -> {
//                    readFile(br);// gọi hàm đọc file
//                }
//                case "2" -> {
//                    writeToFile(br); //gọi hàm ghi file
//                }
//                case "4" -> {
//                    kiemtradinhdang(br); //gọi hàm kiểm tra định dạng
//                }
//            }
//        }
//    }
//
//    private static void writeToFile(BufferedReader br) throws IOException {
//        //gọi hàm ghi file nhận vào 1 BufferedReader: giá trị người dùng nhập vào
//        //ném ra ngoại lệ exception khi có lỗi trong quá trình đọc dữ liệu
//        File file = getFile(br); //gọi hàm tìm kiếm file để ghi vào
//        if (Objects.isNull(file)) { // nếu không tìm thấy file thì trả về null
//            System.out.println("File not found");
//        } else {
//            System.out.println("Enter File Content");
//            String content = br.readLine(); //ghi vao file string
//            if (content.isBlank()) {
//                System.out.println("File content cannot be blank");
//            } else {
//                file.setContent(content); //ghi nội dung đã nhập vào file
//            }
//        }
//    }
//
//    public static boolean kiemtradinhdang(BufferedReader br) throws IOException {
//        File file = getFile(br);
//        //gọi hàm ghi file nhận vào 1 BufferedReader: giá trị người dùng nhập vào
//        //ném ra ngoại lệ exception khi có lỗi trong quá trình đọc dữ liệu
//        if (Objects.isNull(file)) {
//            System.out.println("file not found");
//            return false;
//        } else {
//            String tentep = file.getName(); //lay ten file can kiem tra dinh dang
//            if (tentep.contains(".")) {
//
//                System.out.println("hop le");
//                return true;
//            } else {
//                System.out.println("khong hop le");
//                return false;
//            }
//        }
//    }
//
//
//    private static void readFile(BufferedReader br) throws IOException {
//        File file = getFile(br);
//        //tim kiem file để đọc
//        if (Objects.isNull(file)) { //nếu không tìm thấy file trong list thì trả về null
//            System.out.println("File not found");
//        } else {
//            String content = file.getContent();
//            // đọc nội dung tring file
//            if (Objects.isNull(content)) {
//                System.out.println("File is empty");// nếu không có nội dung thì trả về null
//            } else {
//                System.out.println(content);// nếu có nội dung thì trả về nội dung.
//            }
//        }
//    }
//
//    private static File getFile(BufferedReader br) throws IOException {
//        //hàm tìm kiểm file đã tạo nhận 1 đối tượng BufferedReader đọc dữ liệu từ bàn phím
//        //ném ra ngoại lệ khi có lỗi khi đọc dữ liệu nhập vào.
//        System.out.println("Enter File name"); //
//        String fileName = br.readLine(); //đọc dữ liệu người dùng nhập vào
//        if (fileName.isBlank()) { //nếu không nhập trả về kết quả
//            System.out.println("File name cannot be blank");
//            return null;
//        }
//        return currentFolder.getFiles()
//                .stream().filter(file -> file.getName().equals(fileName))
//                //duyệt theo list lọc các phần tử theo điều kiện nếu giống thì trả về
//                //nếu khác thì tìm kiếm Node tiếp theo : folder -> folder.getName().equals(folderName)
//                .findFirst()//lay file dau tien bang voi cai ten minh nhap
//                .orElse(null);// neu duyet het khong giong ten thi tra ve null
//
//                // sử dụng phương thức .stream.filter duyệt từ findFirst() nếu kết quả đầu tiên trùng thì trả về
//                // nếu không thì duyệt tiếp list bằng lệnh file->file.getname().equal(fileName)
//                // nếu duyệt hết không có kết quả = thì trả về null
//    }
//
//    private static void workWithFolder(BufferedReader br) throws IOException {
//        //hàm thao tác với folder đã tạo nhận 1 đối tượng BufferedReader đọc dữ liệu từ bàn phím
//        //ném ra ngoại lệ khi có lỗi khi đọc dữ liệu nhập vào.
//        String str = ""; //tạo 1 str rỗng lưu giá trị người dùng nhập vào
//        while (!str.equals("4")) {
//            System.out.println("1. Go to folder");
//            System.out.println("2. Go to root");
//            System.out.println("3. Open file");
//            System.out.println("4. Previous");
//            str = br.readLine();
//            switch (str) {
//                case "1" -> {
//                    goToFolder(br);
//                }
//                case "2" -> {
//                    goToRoot(br);
//                }
//                case "3" -> {
//                    getFile(br);
//                }
//            }
//        }
//    }
//
//    private static void goToRoot(BufferedReader br) throws IOException {
//        //hàm gotoroot đã tạo nhận 1 đối tượng BufferedReader đọc dữ liệu từ bàn phím
//        //ném ra ngoại lệ khi có lỗi khi đọc dữ liệu nhập vào.
//
//        currentFolder = root;
//        //gán giá trị root cho giá trị currentFolder
//        //phương thức này đặt currentFolder về thư mục gốc
//        //cho phép người dùng quay lại folder gốc lúc đầu mà người dùng điều hướng đến.
//    }
//
//    private static void goToFolder(BufferedReader br) throws IOException {
//        //hàm gotoroot đã tạo nhận 1 đối tượng BufferedReader đọc dữ liệu từ bàn phím
//        //ném ra ngoại lệ khi có lỗi khi đọc dữ liệu nhập vào.
//        // hàm này dịch chuyển con trỏ đến tên folder đã nhập
//        System.out.println("Enter folder name");
//        String folderName = br.readLine(); //đọc vào giá trị nhập từ người dùng
//        if (folderName.isBlank()) { //nếu giá trị nhập là rỗng thì trả về
//            System.out.println("Folder name cannot be blank");
//        } else {
//            Folder folderToGo = currentFolder.getFolders() //
//                    .stream() //gọi hàm stream để xử lý LinkedList
//                    .filter(folder -> folder.getName().equals(folderName))
//                    //duyệt theo list lọc các phần tử theo điều kiện nếu giống thì trả về
//                    //nếu khác thì tìm kiếm Node tiếp theo : folder -> folder.getName().equals(folderName)
//                    .findFirst() //lay folder dau tien bang voi cai ten minh nhap
//                    .orElse(null);// neu duyet het khong giong ten thi tra ve null
//            if (Objects.isNull(folderToGo)) { //nếu trước đó không tìm thấy vì folder không tồn tại
//                                                // tên folder thì trả về null
//                System.out.println("Folder name not found");
//            } else {
//                currentFolder = folderToGo; //nếu tồn tại tên folder thì dịch con trỏ đến tên folder cần điều
//                                        //hướng đến
//            }
//        }
//    }
//
//    private static void listFoldersAndFiles(Folder currentFolder) {
//        //truyền tham số currentFolder đại diện cho mục cần liệt kê
//
//        System.out.println("Folders: ");
//        currentFolder.getFolders()
//                //tìm kiếm list chứa folder
//                .forEach(folder -> { //dùng phương thức forEach duyệt qua các tên folder trong list
//                    System.out.println(folder.getName()); //in ra tên folder đã duyệt
//                });
//        System.out.println("Files: ");
//        currentFolder.getFiles()
//                //tìm kiếm list chứa file
//                .forEach(file -> {//dùng phương thức forEach duyệt qua các tên file trong list
//                    System.out.println(file.getName());// in ra tên file đã duyệt
//                });
//    }
//
//    // list folder
//    public static void whereAmI() {
//        String name = Objects.requireNonNullElse(currentFolder, root).getName();
//        //phương thức requireNonNullElse kiểm currentFolder có null hay không, nếu không null thì trả về tên file hiện tại
//        //nếu null thì trả về Node root
//        System.out.println("You currently in folder " + name);
//    }
//
//    // create folder
//    public static void createFolder(BufferedReader br, Folder actualFolder) throws IOException {
//        //hàm tạo folder nhận vào 1 đối tượng BufferedReader cho người dùng nhập vào
//        //ném ra ngoại lệ IOException khi có lỗi nhập vào từ bàn phím
//        System.out.println("1. give a folder name");
//        String folderName = br.readLine();// nhập vào tên folder
//        if (folderName.isBlank()) {
//            System.out.println("Folder name cannot be blank");
//        } else {
//            Folder folder = new Folder(folderName); //
//            Objects.requireNonNullElse(actualFolder, root).getFolders().add(folder);
//            //sử dụng phương thức requireNonNullElse đảm bảo actualFolder != null
//            //nếu actualFolder== null thì trả về root hoặc giá trị mặc định
//            //sau khi actualFolder != null thì thêm tên folder mới vào list bằng phương thức add
//            //folder được thêm chỉ vào folder root
//        }
//    }
//
//    // create file
//    public static void createFile(BufferedReader br, Folder actualFolder) throws IOException {
//        //hàm tạo folder nhận vào 1 đối tượng BufferedReader cho người dùng nhập vào
//        //ném ra ngoại lệ IOException khi có lỗi nhập vào từ bàn phím
//        System.out.println("1. Give a File name");
//        String fileName = br.readLine();// nhập vào tên file
//        if (fileName.isBlank()) {
//            System.out.println("File name cannot be blank");
//        } else {
//            File file = new File();
//            file.setName(fileName);
//            Objects.requireNonNullElse(actualFolder, root).getFiles().add(file);
//            //sử dụng phương thức requireNonNullElse đảm bảo actualFolder != null
//            //nếu actualFolder== null thì trả về root hoặc giá trị mặc định được cung cấp
//            //sau khi actualFolder != null thì thêm tên folder mới vào list bằng phương thức add
//            //folder được thêm chỉ vào folder root
//        }
//    }
//}
//
