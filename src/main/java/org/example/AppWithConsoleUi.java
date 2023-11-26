package org.example;

import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.InputResult;
import de.codeshelf.consoleui.prompt.ListResult;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
//Đây là các lớp từ thư viện de.codeshelf.consoleui,
// được sử dụng để tạo giao diện người dùng dòng lệnh tương tác.
// Thư viện này cung cấp các cách tiện lợi để xây dựng các prompt (câu hỏi, thông báo)
// đơn giản mà người dùng có thể tương tác qua console. Điều này giúp việc xây dựng
// các ứng dụng dòng lệnh trở nên dễ dàng và trực quan hơn.


import javax.swing.plaf.IconUIResource; //một thư viện giao diện người dùng (không cần thiết)
import java.io.BufferedReader;//đọc văn bản từ một luồng đầu vào (như luồng nhập từ file) một cách hiệu quả.
//IOException là một ngoại lệ được ném ra để xử lý các lỗi nhập/xuất.
import java.io.IOException;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//dùng để thao tác với file và đường dẫn một cách hiệu quả.
//Files cung cấp các phương thức tiện ích để làm việc với file,
// Path đại diện cho đường dẫn trong hệ thống tệp tin, Paths giúp tạo ra đối tượng Path
//StandardCopyOption xác định cách thức sao chép file.
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Objects;
//cấu trúc dữ liệu dùng để lưu trữ dữ liệu theo cặp khóa/giá trị.
// Objects cung cấp các phương thức tiện ích để làm việc với đối tượng, như so sánh, tạo hash, v.v.
public class AppWithConsoleUi {

  public static final String EXIT = "exit"; //định nghĩa 1 chuỗi để thoát khỏi chương trình
  public static final Folder root = new Folder("root"); //tạo 1 list va folder dau đặt tên là root.
  public static final String CREATE_FOLDER = "createFolder";
  public static final String CREATE_FILE = "createFile";
  public static final String LIST_FILES = "listFiles";
  public static final String WHERE_AM_I = "whereAmI";

  public static final String WORK_WITH_FOLDER = "workWithFolder";

  public static final String GO_TO_FOLDER="GoToFolder";
  public static final String GO_TO_ROOT = "GO_TO_ROOT";
  public static final String OPEN_FILE = "OPEN_FILE";

  public static final String PREVIOUS = "PREVIOUS";
  public static final String READ_FILE="readFile";
  public static final String WRITE_TO_FILE="writeToFile";
  public static final String KIEM_TRA_DINH_DANG="kiemtradinhdang";
  public static final String WORK_WITH_FILE="workWithFile";
  public static final String RENAME_FILE="RenameFile";
  public static final String RENAME_FOLDER = "RENAME_FOLDER";
  public static final String DELETE_FILE = "DELETE_FILE";
  public static final String DELETE_FOLDER = "DELETE_FOLDER";

  public static final String COPY_FILE_TO_FOLDER = "COPY_FILE_TO_FOLDER";
  public static final String COPY_FOLDER = "COPY_FOLDER";
  public static final String BACK_FOLDER = "BACK_FOLDER";
  public static Folder currentFolder = root; //tạo 1 biến currentFolder hiện tại là thư mục gốc

  public static void main(String[] args) throws IOException {

    String command = ""; //luu du lieu nguoi dung nhap vao
    while (!command.equals(EXIT)) {
      ConsolePrompt prompt = new ConsolePrompt();
      PromptBuilder promptBuilder = prompt.getPromptBuilder();
      //Khởi tạo đối tượng ConsolePrompt để tạo ra các câu hỏi tương tác trên console.
      //PromptBuilder được sử dụng để xây dựng các câu hỏi (prompt).

      //hiện cách lệnh ra màn hình và chọn lệnh
      promptBuilder.createListPrompt()
              .name("command")
              .message("Enter Command, when you want to exit type ‘exit’")
              .newItem(CREATE_FOLDER).text("Create folder").add()
              .newItem(CREATE_FILE).text("Create file").add()
              .newItem(LIST_FILES).text("List all file and folder").add()
              .newItem(WHERE_AM_I).text("Vi tri hien tai").add()
              .newItem(WORK_WITH_FOLDER).text("work with folder").add()
              .newItem(WORK_WITH_FILE).text("work with file").add()
              .newItem(EXIT).text(EXIT).add()
              .addPrompt();
        // tao list
      HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());//doc list

      command = ((ListResult) result.get("command")).getSelectedId();

      //Hiển thị menu lựa chọn và lưu lựa chọn của người dùng vào biến command.
      switch (command) {
        case CREATE_FOLDER -> createFolder(currentFolder);
        case CREATE_FILE -> createFile(currentFolder);
        case LIST_FILES -> listFoldersAndFiles(currentFolder);
        case WHERE_AM_I ->whereAmI();
        case WORK_WITH_FOLDER ->workWithFolder();
        case WORK_WITH_FILE ->workWithFile();

      }
    }

  }

  // create folder
  public static void createFolder(Folder actualFolder) throws IOException {
    //Khởi tạo đối tượng ConsolePrompt để tạo ra các câu hỏi tương tác trên console.
    //PromptBuilder được sử dụng để xây dựng các câu hỏi (prompt).
    ConsolePrompt prompt = new ConsolePrompt();
    PromptBuilder promptBuilder = prompt.getPromptBuilder();

    //Tạo một prompt yêu cầu người dùng nhập vào tên của file mới mà họ muốn tạo.
    promptBuilder.createInputPrompt()
            .name("folderName")
            .message("Give a folder name")
            .addPrompt();
    //Hiển thị prompt trên console và lấy tên file mà người dùng nhập vào. (mui ten len xuong)

    HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
    //nhap ten folder can tạo
    String folderName = ((InputResult) result.get("folderName")).getInput();

    if (folderName.isBlank()) {
      System.out.println("Folder name cannot be blank");
    } else { //tien hành tạo folder mới
      Folder folder = new Folder(folderName); //
      Objects.requireNonNullElse(actualFolder, root).getFolders().insert(acfolder); //bỏ folder mới tạo vào list
    }
  }

  public static void createFile(Folder actualFolder) throws IOException {
    //Khởi tạo đối tượng ConsolePrompt để tạo ra các câu hỏi tương tác trên console.
    //PromptBuilder được sử dụng để xây dựng các câu hỏi (prompt).
    ConsolePrompt prompt = new ConsolePrompt();
    PromptBuilder promptBuilder = prompt.getPromptBuilder();

    //Tạo một prompt yêu cầu người dùng nhập vào tên của file mới mà họ muốn tạo.

    promptBuilder.createInputPrompt()
            .name("fileName")
            .message("Give a File name")
            .addPrompt();

    //Hiển thị prompt trên console và lấy tên file mà người dùng nhập vào. (mui ten len xuong)
    HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
  //nhap ten file can tạo
    String fileName = ((InputResult) result.get("fileName")).getInput();

    if (fileName.isBlank()) {
      System.out.println("File name cannot be blank");
    } else {
      //tiến hành tạo file mới
      File file = new File();
      file.setName(fileName);
      Objects.requireNonNullElse(actualFolder, root).getFiles().add(file);//thêm file vào list
    }
  }

  private static void listFoldersAndFiles(Folder currentFolder) {
    //truyền tham số currentFolder đại diện cho mục cần liệt kê

    System.out.println("Folders: ");
    currentFolder.getFolders()
            //tìm kiếm list chứa folder
            .forEach(folder -> { //dùng phương thức forEach duyệt qua các tên folder trong list
              System.out.println(folder.getName()); //in ra tên folder đã duyệt
            });
    System.out.println("Files: ");
    currentFolder.getFiles()
            //tìm kiếm list chứa file
            .forEach(file -> {//dùng phương thức forEach duyệt qua các tên file trong list
              System.out.println(file.getName());// in ra tên file đã duyệt
            });
  }

  public static void whereAmI() throws IOException{
    //Khởi tạo đối tượng ConsolePrompt để tạo ra các câu hỏi tương tác trên console.
    //PromptBuilder được sử dụng để xây dựng các câu hỏi (prompt).
    ConsolePrompt prompt = new ConsolePrompt();
    PromptBuilder promptBuilder = prompt.getPromptBuilder();

    //Lấy tên của thư mục hiện tại. Nếu currentFolder là null
    // (có thể xảy ra trong trường hợp lỗi hoặc khi chương trình mới khởi động),
    // sẽ sử dụng thư mục gốc root thay thế.
    String folderName = Objects.requireNonNullElse(currentFolder, root).getName();
    String message = "You currently in folder " + folderName;

    //Tạo một prompt với nội dung là thông báo về thư mục hiện tại.
    promptBuilder.createInputPrompt()
            .name("message")
            .message(message)
            .addPrompt();

    //Hiển thị prompt trên console. Điều này sẽ cho người dùng biết họ đang ở thư mục nào.
    prompt.prompt(promptBuilder.build());
  }
  private static void goToRoot() throws IOException {

    //Khởi tạo đối tượng ConsolePrompt để tạo ra các câu hỏi tương tác trên console.
    //PromptBuilder được sử dụng để xây dựng các câu hỏi (prompt).
    ConsolePrompt prompt = new ConsolePrompt();
    PromptBuilder promptBuilder = prompt.getPromptBuilder();

    //Tạo một prompt yêu cầu người dùng xác nhận liệu họ có muốn chuyển về thư mục gốc hay không.
    promptBuilder.createInputPrompt()
            .name("confirmation")
            .message("Are you sure you want to go to the root folder? (Y/N)")
            .addPrompt();

    //Hiển Thị Prompt và Lấy Kết Quả Từ Người Dùng (su dung mui ten)
    HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
    String confirmation = ((InputResult) result.get("confirmation")).getInput();//doc chu nguoi dung nhap

    if (confirmation.equalsIgnoreCase("Y")) {
      currentFolder = root; //chuyen con tro ve thu muc root
      System.out.println("You are now in the root folder.");
    } else {
      System.out.println("Operation canceled.");
    }
  }
  private static void goToFolder() {
    try {
      //Khởi tạo đối tượng ConsolePrompt để tạo ra các câu hỏi tương tác trên console.
      //PromptBuilder được sử dụng để xây dựng các câu hỏi (prompt).
      ConsolePrompt prompt = new ConsolePrompt();
      PromptBuilder promptBuilder = prompt.getPromptBuilder();

      //Tạo một prompt yêu cầu người dùng nhập vào tên file mà họ muốn truy cập.
      promptBuilder.createInputPrompt()
              .name("folderName")
              .message("Enter folder name")
              .addPrompt();
      //Hiển thị prompt trên console và lấy tên file mà người dùng nhập vào.(mũi tên lên xuống )
      HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
      String folderName = ((InputResult) result.get("folderName")).getInput();

      if (folderName.isBlank()) {
        System.out.println("Folder name cannot be blank");
      } else {
        Folder folderToGo = currentFolder.getFolders()

                .search(Folder )


        if (Objects.isNull(folderToGo)) { //nếu file rỗng
          System.out.println("Folder name not found");
        } else {
          currentFolder = folderToGo; //chuyen con tro den file ngươi dung nhap
          System.out.println("You are now in folder: " + folderToGo.getName());
        }
      }
    } catch (IOException e) {
      System.out.println("An error occurred while getting user input: " + e.getMessage());
      //Bắt và xử lý bất kỳ IOException nào có thể xảy ra trong quá trình nhập liệu. Nếu có lỗi, in ra thông báo lỗi.
    }
  }

  private static File getFile() {
    try {
      //Khởi tạo đối tượng ConsolePrompt để tạo ra các câu hỏi tương tác trên console.
      //PromptBuilder được sử dụng để xây dựng các câu hỏi (prompt).
      ConsolePrompt prompt = new ConsolePrompt();
      PromptBuilder promptBuilder = prompt.getPromptBuilder();

      //Tạo một prompt yêu cầu người dùng nhập vào tên file mà họ muốn truy cập.
      promptBuilder.createInputPrompt()
              .name("fileName")
              .message("Enter File name")
              .addPrompt();
      //Hiển thị prompt trên console và lấy tên file mà người dùng nhập vào.
      HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
      String fileName = ((InputResult) result.get("fileName")).getInput(); //luu du lieu nguoi dung nhap

      if (fileName.isBlank()) { //neu ten file trong
        System.out.println("File name cannot be blank");
        return null;
      }
  //   Sử dụng Java Stream để lọc qua
      //   danh sách file trong currentFolder, tìm file có tên khớp với tên đã nhập.
      //   findFirst trả về file đầu tiên tìm thấy hoặc null nếu không tìm thấy.
      File fileToGet = currentFolder.getFiles()
              .stream()
              .filter(file -> file.getName().equals(fileName))
              .findFirst()
              .orElse(null);

      if (Objects.isNull(fileToGet)) { //neu file khong ton tai thi tra ve null
        System.out.println("File not found");
      } else {
        System.out.println("You are now opening file: " + fileToGet.getName());
      }

      return fileToGet; //tra ve neu tim thay

    } catch (IOException e) {
      System.out.println("An error occurred while getting user input: " + e.getMessage());
      return null;
      //Bắt và xử lý bất kỳ IOException nào có thể xảy ra trong quá trình nhập liệu.
      // Nếu có lỗi, in ra thông báo lỗi và trả về null.
    }
  }


  private static void workWithFolder() throws IOException {
    String option = ""; //tao 1 string de luu gia tri nguoi dung nhap vao
    while (!option.equals(PREVIOUS)) {

      //Khởi tạo đối tượng ConsolePrompt để tạo ra các câu hỏi tương tác trên console.
      //PromptBuilder được sử dụng để xây dựng các câu hỏi (prompt).
    ConsolePrompt prompt = new ConsolePrompt();
    PromptBuilder promptBuilder = prompt.getPromptBuilder();

    promptBuilder.createListPrompt()
            .name("option")
            .message("Select an option")
            .newItem(CREATE_FOLDER).text("Create Folder").add()
            .newItem(CREATE_FILE).text("Create File").add()
            .newItem(GO_TO_FOLDER).text("Go to folder").add()
            .newItem(GO_TO_ROOT).text("Go to root").add()
          //  .newItem(OPEN_FILE).text("Open file").add()
            .newItem(LIST_FILES).text("List file and folder").add()
            .newItem(RENAME_FOLDER).text("Rename folder").add()
            .newItem(DELETE_FOLDER).text("Delete folder").add()
            .newItem(COPY_FOLDER).text("Copy folder to folder").add()
            .newItem(BACK_FOLDER).text("back to previous folder").add()
            .newItem(PREVIOUS).text("Previous").add()
            .addPrompt();
    // // Hiển thị prompt trên console và lấy kết quả nhập vào từ người dùng.
      //    // Hiển thị menu cho người dùng và sau đó thu thập lựa chọn của họ.
    HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());//doc list
    option = ((ListResult) result.get("option")).getSelectedId();
    switch (option) {
       case CREATE_FOLDER -> createFolder(currentFolder);
       case CREATE_FILE -> createFile(currentFolder);
        case GO_TO_FOLDER ->goToFolder();
        case GO_TO_ROOT -> goToRoot();
        case LIST_FILES -> listFoldersAndFiles(currentFolder);
    //    case OPEN_FILE -> getFile();
        case RENAME_FOLDER -> renameFolder(currentFolder);
        case DELETE_FOLDER -> deleteFolder(currentFolder);
        case COPY_FOLDER-> copyFolderToFolder();
       case BACK_FOLDER -> backToPreviousFolder();
      }
    }
  }
  private static void readFile() throws IOException {

    File file = getFile();
    //tim kiem file để đọc
    if (Objects.isNull(file)) { //nếu không tìm thấy file trong list thì trả về null
      System.out.println("File not found");
    } else {
      String content = file.getContent();
      // đọc nội dung tring file
      if (Objects.isNull(content)) {
        System.out.println("File is empty");// nếu không có nội dung thì trả về null
      } else {
        System.out.println("File content: ");
        System.out.println(content);// nếu có nội dung thì trả về nội dung.
      }
    }
  }

  private static void writeToFile() throws IOException {
    //Gọi hàm getFile() để lấy file cần ghi nội dung.
    // Giả định rằng getFile() là một phương thức khác trong chương trình
    // nótìm và trả về một đối tượng File dựa trên tên file do người dùng nhập vào.
    File file = getFile();

    //Khởi tạo đối tượng ConsolePrompt để tạo ra các câu hỏi tương tác trên console.
    //PromptBuilder được sử dụng để xây dựng các câu hỏi (prompt).
    ConsolePrompt prompt = new ConsolePrompt();
    PromptBuilder promptBuilder = prompt.getPromptBuilder();
//Tạo một prompt yêu cầu người dùng nhập vào nội dung mà họ muốn ghi vào file.
    promptBuilder.createInputPrompt()
            .name("fileContent")
            .message("Enter File content: ")
            .addPrompt();
//Hiển thị prompt trên console và lấy kết quả nội dung nhập vào từ người dùng.
    HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
    String fileName = ((InputResult) result.get("fileContent")).getInput(); //luu du lieu nguoi dung nhap


    if (Objects.isNull(file)) { // nếu không tìm thấy file thì trả về null
      System.out.println("File not found");
    } else {
      System.out.println("Enter File Content: ");

      if (fileName.isBlank()) {
        System.out.println("File content cannot be blank");
      } else {
        file.setContent(fileName); //ghi nội dung đã nhập vào file
      }
    }
  }
  public static boolean kiemtradinhdang() throws IOException {
    File file = getFile();
    //gọi hàm ghi file nhận vào 1 BufferedReader: giá trị người dùng nhập vào
    //ném ra ngoại lệ exception khi có lỗi trong quá trình đọc dữ liệu
    if (Objects.isNull(file)) {
      System.out.println("file not found");
      return false;
    } else {
      String tentep = file.getName(); //lay ten file can kiem tra dinh dang
      if (tentep.contains(".")) {

        System.out.println(" Hop le");
        return true;
      } else {
        System.out.println(" Khong hop le");
        return false;
      }
    }
  }
public static void workWithFile() throws IOException {

  String option = ""; //lưu lại lựa chọn người dùng

  while (!option.equals(PREVIOUS)) {

    //Khởi tạo đối tượng ConsolePrompt để tạo ra các câu hỏi tương tác trên console.
    //PromptBuilder được sử dụng để xây dựng các câu hỏi (prompt).
    ConsolePrompt prompt = new ConsolePrompt();
    PromptBuilder promptBuilder = prompt.getPromptBuilder();

    //  Tạo danh sách các tùy chọn cho người dùng, mỗi tùy chọn được định nghĩa
    //  bằng .newItem() và có một nhãn riêng
    promptBuilder.createListPrompt()
            .name("option")
            .message("Select an option")
            .newItem(WRITE_TO_FILE).text("Write to file").add()
            .newItem(READ_FILE).text("Read File").add()
            .newItem(KIEM_TRA_DINH_DANG).text("kiem tra dinh dang").add()
            .newItem(RENAME_FILE).text("Rename file").add()
            .newItem(DELETE_FILE).text("Delete file").add()
            .newItem(COPY_FILE_TO_FOLDER).text("copy file").add()
            .newItem(PREVIOUS).text("Previous").add()
            .addPrompt();
    // Hiển thị prompt trên console và lấy kết quả nhập vào từ người dùng.
    // Hiển thị menu cho người dùng và sau đó thu thập lựa chọn của họ.
    HashMap<String, ? extends PromtResultItemIF>
            result = prompt.prompt(promptBuilder.build());//doc list
    option = ((ListResult) result.get("option")).getSelectedId();

    switch (option) {
      case WRITE_TO_FILE -> writeToFile(); //gọi hàm ghi file
      case READ_FILE-> readFile(); // gọi hàm đọc file

      case KIEM_TRA_DINH_DANG -> kiemtradinhdang();//gọi hàm kiểm tra định dạng

      case RENAME_FILE -> renameFile(currentFolder);

      case DELETE_FILE -> deleteFile(currentFolder);
      case COPY_FILE_TO_FOLDER -> copyFileToFolder();
     }
    }
  }
  private static void renameFile(Folder actualFolder) throws IOException
  {
    //Khởi tạo đối tượng ConsolePrompt để tạo ra các câu hỏi tương tác trên console.
    //PromptBuilder được sử dụng để xây dựng các câu hỏi (prompt).
    ConsolePrompt prompt = new ConsolePrompt();
    PromptBuilder promptBuilder = prompt.getPromptBuilder();

    // Tạo prompt để nhập tên file người dùng muốn đổi tên
    promptBuilder.createInputPrompt()
            .name("oldFileName")
            .message("Enter the name of the file you want to rename")
            .addPrompt();
    // Hiển thị prompt trên console và lấy kết quả nhập vào từ người dùng.
    HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());

    //Trích xuất tên của file cũ từ kết quả người dùng nhập vào.
    String oldFileName = ((InputResult) result.get("oldFileName")).getInput();

    if (oldFileName.isBlank()) { //nếu người dùng không nhập
      System.out.println("The name cannot be blank");
      return;
    }

    // Tạo prompt mới cho tên file mới
    promptBuilder = new ConsolePrompt().getPromptBuilder();
    promptBuilder.createInputPrompt()
            .name("newFileName")
            .message("Enter new file name")
            .addPrompt();

    //promptBuilder.build() xây dựng một prompt dựa trên các thiết lập được
    // thêm vào promptBuilder trước đó. Trong trường hợp này, nó tạo ra một prompt để người dùng nhập vào tên mới cho file.
    //prompt.prompt(...) hiển thị prompt này trên console và
    // chờ người dùng nhập thông tin. Khi người dùng hoàn tất và nhấn Enter, thông tin nhập vào sẽ được thu thập.
    result = prompt.prompt(promptBuilder.build());
    //result.get("newFileName"): Lấy kết quả nhập vào từ người dùng. result là một HashMap lưu trữ kết quả của các câu hỏi. "newFileName" là khóa được sử dụng để truy xuất kết quả của prompt mà bạn đã đặt tên là newFileName trong createInputPrompt().
    //((InputResult) result.get("newFileName")): Ép kiểu kết quả từ HashMap thành InputResult, là một loại đối tượng đặc biệt chứa kết quả nhập từ người dùng.
    //getInput(): Là phương thức của đối tượng InputResult, dùng để lấy chuỗi mà người dùng đã nhập vào.
    String newFileName = ((InputResult) result.get("newFileName")).getInput();

    if (newFileName.isBlank()) {
      System.out.println("The file name cannot be blank");
      return;
    }

    // Tiến hành đổi tên file
    File fileToRename = currentFolder.getFiles()
            .stream()//gọi hàm stream để xử lý LinkedList
            .filter(file -> file.getName().equals(oldFileName))
            //duyệt theo list lọc các phần tử theo điều kiện nếu giống thì trả về
            //nếu khác thì tìm kiếm Node tiếp theo : folder -> folder.getName().equals(oldFilename)
            .findFirst()//lay file dau tien bang voi cai ten minh nhap
            .orElse(null);// neu duyet het khong giong ten thi tra ve null


   //Kiểm tra xem file cần đổi tên có tồn tại hay không. Nếu file không tồn tại (tức fileToRename là null), thông báo rằng file không tìm thấy.
    //Nếu file tồn tại, thì thay đổi tên của file đó thành tên mới và thông báo rằng file đã được đổi tên thành công.

    if (Objects.isNull(fileToRename)) {
      System.out.println("File not found");
    } else {
      fileToRename.setName(newFileName);
      System.out.println("File has been renamed to " + newFileName);
    }
  }
  private static void renameFolder(Folder actualFolder) throws IOException {
    //Khởi tạo đối tượng ConsolePrompt để tạo ra các câu hỏi tương tác trên console.
    //PromptBuilder được sử dụng để xây dựng các câu hỏi (prompt).
    ConsolePrompt prompt = new ConsolePrompt();
    PromptBuilder promptBuilder = prompt.getPromptBuilder();

    // Tạo prompt để nhập tên folder cũ
    promptBuilder.createInputPrompt()
            .name("oldFolderName")
            .message("Enter the name of the folder you want to rename")
            .addPrompt();
    // Hiển thị prompt trên console và lấy kết quả nhập vào từ người dùng.
    HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
    //Trích xuất tên của file cũ từ kết quả người dùng nhập vào.
    String oldFolderName = ((InputResult) result.get("oldFolderName")).getInput();

    if (oldFolderName.isBlank()) {
      System.out.println("The name cannot be blank");
      return;
    }

    // Tạo prompt mới cho tên folder mới
    promptBuilder = new ConsolePrompt().getPromptBuilder();
    promptBuilder.createInputPrompt()
            .name("newFolderName")
            .message("Enter new folder name")
            .addPrompt();


    //promptBuilder.build() xây dựng một prompt dựa trên các thiết lập được
    // thêm vào promptBuilder trước đó. Trong trường hợp này, nó tạo ra một prompt để người dùng nhập vào tên mới cho file.
    //prompt.prompt(...) hiển thị prompt này trên console và
    // chờ người dùng nhập thông tin. Khi người dùng hoàn tất và nhấn Enter, thông tin nhập vào sẽ được thu thập.
    result = prompt.prompt(promptBuilder.build());
    String newFolderName = ((InputResult) result.get("newFolderName")).getInput();

    if (newFolderName.isBlank()) {
      System.out.println("The folder name cannot be blank");
      return;
    }

    // Tiến hành đổi tên folder
    Folder folderToRename = actualFolder.getFolders()
            .stream()//gọi hàm stream để xử lý LinkedList
            //duyệt theo list lọc các phần tử theo điều kiện nếu giống thì trả về
            .filter(folder -> folder.getName().equals(oldFolderName))
            //nếu khác thì tìm kiếm Node tiếp theo : folder -> folder.getName().equals(oldFilename)
            .findFirst()//lay file dau tien bang voi cai ten minh nhap
            .orElse(null);// neu duyet het khong giong ten thi tra ve null




    if (Objects.isNull(folderToRename)) {
      System.out.println("Folder not found");
    } else {
      folderToRename.setName(newFolderName);
      System.out.println("Folder has been renamed to " + newFolderName);
    }
  }

  private static void deleteFile(Folder actualFolder)throws IOException
  {
   // Khởi tạo đối tượng ConsolePrompt để tạo ra các câu hỏi tương tác trên console.
     //     PromptBuilder được sử dụng để xây dựng các câu hỏi (prompt).
    ConsolePrompt prompt = new ConsolePrompt();
    PromptBuilder promptBuilder = prompt.getPromptBuilder();

    //Tạo một prompt yêu cầu người dùng nhập vào tên của file mà họ muốn xóa.
    promptBuilder.createInputPrompt()
            .name("fileName")
            .message("Enter a file name to delete ")
            .addPrompt();
    //Hiển thị prompt trên console và lấy kết quả nhập vào từ người dùng.
    HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
   //   Trích xuất tên của file từ kết quả người dùng nhập vào.
    String fileName = ((InputResult) result.get("fileName")).getInput();//nhap vao

  //Kiểm tra để đảm bảo rằng tên file không được để trống.
    if(fileName.isBlank())
    {
      System.out.println("the file name cannot be blank");
    }
    else
    {
      File fileToDelete=currentFolder.getFiles()
              .stream()//gọi hàm stream để xử lý LinkedList
              .filter(file->file.getName().equals(fileName))
              //duyệt theo list lọc các phần tử theo điều kiện nếu giống thì trả về
              //nếu khác thì tìm kiếm Node tiếp theo : folder -> folder.getName().equals(fileName)
              .findFirst()//lay file dau tien bang voi cai ten minh nhap
              .orElse(null);// neu duyet het khong giong ten thi tra ve null
      if(Objects.isNull(fileToDelete)){// nếu giá trị currentFolder.getFile ở trên là null thì in ra
        System.out.println("file not found");

      }
      else {
        //Nếu file tồn tại, thì loại bỏ file đó khỏi danh sách file
        // của currentFolder và thông báo rằng file đã được xóa thành công.
        currentFolder.getFiles().remove(fileToDelete);
        System.out .println("file deleted successfully");
      }
    }
  }
  private static void deleteFolder(Folder actualFolder) throws IOException{
    // Khởi tạo đối tượng ConsolePrompt để tạo ra các câu hỏi tương tác trên console.
    //     PromptBuilder được sử dụng để xây dựng các câu hỏi (prompt).
    ConsolePrompt prompt = new ConsolePrompt();
    PromptBuilder promptBuilder = prompt.getPromptBuilder();

    //Tạo một prompt yêu cầu người dùng nhập vào tên của file mà họ muốn xóa.
    promptBuilder.createInputPrompt()
            .name("folderName")
            .message("Enter a folder name to delete ")
            .addPrompt();
    //Hiển thị prompt trên console và lấy kết quả nhập vào từ người dùng.
    HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
    //   Trích xuất tên của file từ kết quả người dùng nhập vào.
    String folderName = ((InputResult) result.get("folderName")).getInput();

    if(folderName.isBlank()) //neu ten de trong
    {
      System.out.println("the folder name cannot be blank");
    }
    else{
      Folder folderToDelete=currentFolder.getFolders() // gán tên folder vừa nhập cho currentfolder
              .stream() //gọi hàm stream để xử lý LinkedList
              .filter(folder -> folder.getName().equals(folderName))
              //duyệt theo list lọc các phần tử theo điều kiện nếu giống thì trả về
              //nếu khác thì tìm kiếm Node tiếp theo : folder -> folder.getName().equals(folderName)
              .findFirst()
              //lay file dau tien bang voi cai ten minh nhap
              .orElse(null);// neu duyet het khong giong ten thi tra ve null
      if(Objects.isNull(folderToDelete)) {
        System.out.println("Folder not found"); // nếu giá trị currentFolder.getFolder ở trên là null thì in ra
      }
      else
      {
        currentFolder.getFolders().remove(folderToDelete); // nếu không thì xóa folder đã tạo bằng hàm remove
        System.out.println("Folder deteted successful");
      }
    }

  }

  private static void copyFileToFolder() throws IOException {
    ConsolePrompt prompt = new ConsolePrompt();
    PromptBuilder promptBuilder = prompt.getPromptBuilder();

    // Tạo prompt để nhập tên file cần sao chép
    promptBuilder.createInputPrompt()
            .name("fileName")
            .message("Enter the name of the file to copy")
            .addPrompt();

    // Tạo prompt để nhập tên folder đích
    promptBuilder.createInputPrompt()
            .name("destinationFolderName")
            .message("Enter the name of the destination folder")
            .addPrompt();

    HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
    String fileName = ((InputResult) result.get("fileName")).getInput();
    String destinationFolderName = ((InputResult) result.get("destinationFolderName")).getInput();

    if (fileName.isBlank() || destinationFolderName.isBlank()) {
      System.out.println("File name and destination folder name cannot be blank");
      return;
    }

    // Tìm file cần sao chép
    File fileToCopy = findFileByName(fileName, currentFolder);
    if (fileToCopy == null) {
      System.out.println("File not found");
      return;
    }

    // Tìm folder đích
    Folder destinationFolder = findFolderByName(destinationFolderName, currentFolder);
    if (destinationFolder == null) {
      System.out.println("Destination folder not found");
      return;
    }

    // Thực hiện sao chép file
    File copiedFile = new File(fileToCopy.getName(), fileToCopy.getContent()); // Giả sử File có thuộc tính content
    destinationFolder.getFiles().add(copiedFile);
    System.out.println("File has been copied to " + destinationFolderName);
  }

  private static void copyFolderToFolder() throws IOException {
    //khởi tạo console prompt và prompt builder
    // dùng để lấy nội dung người dùng nhập trong java từ giao diện console

    ConsolePrompt prompt = new ConsolePrompt();
    PromptBuilder promptBuilder = prompt.getPromptBuilder();


    // Tạo prompt để nhập tên folder nguồn cần sao chép
    promptBuilder.createInputPrompt()
            .name("sourceFolderName")
            .message("Enter the name of the source folder to copy")
            .addPrompt();

    // Tạo prompt để nhập tên folder đích
    promptBuilder.createInputPrompt()
            .name("destinationFolderName")
            .message("Enter the name of the destination folder")
            .addPrompt();
    //Hiển thị các prompt trên console và lấy kết quả nhập vào từ người dùng.
    HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
    //Trích xuất tên của folder nguồn và folder đích từ kết quả người dùng nhập vào.
    String sourceFolderName = ((InputResult) result.get("sourceFolderName")).getInput();
    String destinationFolderName = ((InputResult) result.get("destinationFolderName")).getInput();

    //Kiểm tra để đảm bảo rằng cả hai tên folder không được để trống.
    if (sourceFolderName.isBlank() || destinationFolderName.isBlank()) {
      System.out.println("Folder names cannot be blank");
      return;
    }

    // Tìm folder nguồn và folder đích
    Folder sourceFolder = findFolderByName(sourceFolderName, currentFolder);
    Folder destinationFolder = findFolderByName(destinationFolderName, currentFolder);
    //kiểm tra xem folder đích có tồn tại hay không
    if (sourceFolder == null) {
      System.out.println("Source folder not found");
      return;
    }

    //kiểm tra xem folder nguồncó tồn tại hay không
    if (destinationFolder == null) {
      System.out.println("Destination folder not found");
      return;
    }

    // Thực hiện sao chép folder bằng cách gọi hàm copyFolder
    copyFolder(sourceFolder, destinationFolder);
    System.out.println("Folder has been copied to " + destinationFolderName);

  }

  private static File findFileByName(String fileName, Folder folder) {
    return folder.getFiles()
            .stream()//gọi hàm stream để xử lý LinkedList
            .filter(file -> file.getName().equals(fileName))
            //duyệt theo list lọc các phần tử theo điều kiện nếu giống thì trả về
            //nếu khác thì tìm kiếm Node tiếp theo : folder -> folder.getName().equals(fileName)
            .findFirst()//lay file dau tien bang voi cai ten minh nhap
            .orElse(null);// neu duyet het khong giong ten thi tra ve null
  }
  private static Folder findFolderByName(String folderName, Folder parentFolder) {
    // Tìm folder trong parentFolder
    return parentFolder.getFolders()
            .stream()//gọi hàm stream để xử lý LinkedList
            .filter(folder -> folder.getName().equals(folderName))
            //duyệt theo list lọc các phần tử theo điều kiện nếu giống thì trả về
            //nếu khác thì tìm kiếm Node tiếp theo : folder -> folder.getName().equals(folderName)
            .findFirst()//lay file dau tien bang voi cai ten minh nhap
            .orElse(null);// neu duyet het khong giong ten thi tra ve null
  }

  private static void copyFolder(Folder sourceFolder, Folder destinationFolder) {
    // Tạo một bản sao của sourceFolder trong destinationFolder
    Folder newFolder = new Folder(sourceFolder.getName()); // Giả sử có constructor Folder(String name)
    destinationFolder.getFolders().add(newFolder);
   // Thêm folder mới vào danh sách các folder con của folder đích.
    // Sao chép nội dung từ sourceFolder sang newFolder
    }
  private static void backToPreviousFolder() {
    if (currentFolder != null && currentFolder.getParent() != null) {
      currentFolder = currentFolder.getParent();
      System.out.println("You are now back in folder: " + currentFolder.getName());
    } else {
      System.out.println("You are already at the root folder or no parent folder available.");
    }
  }




}
