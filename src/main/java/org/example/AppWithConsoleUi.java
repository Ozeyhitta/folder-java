package org.example;

import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.InputResult;
import de.codeshelf.consoleui.prompt.ListResult;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class AppWithConsoleUi {

  public static final String EXIT = "exit"; //định nghĩa 1 chuỗi để thoát khỏi chương trình
  public static final Folder root = new Folder("root"); //tạo 1 list va folder dau đặt tên là root.
  public static final String CREATE_FOLDER = "createFolder";
  public static final String CREATE_FILE = "createFile";
  public static final String LIST_FILES = "listFiles";
  public static Folder currentFolder = root; //tạo 1 biến currentFolder hiện tại là thư mục gốc

  public static void main(String[] args) throws IOException {

    String command = "";
    while (!command.equals(EXIT)) {
      ConsolePrompt prompt = new ConsolePrompt();
      PromptBuilder promptBuilder = prompt.getPromptBuilder();
      promptBuilder.createListPrompt()
              .name("command")
              .message("Enter Command, when you want to exit type ‘exit’")
              .newItem(CREATE_FOLDER).text("Create folder").add()
              .newItem(CREATE_FILE).text("Create file").add()
              .newItem(LIST_FILES).text("List all file and folder").add()
              .newItem(EXIT).text(EXIT).add()
              .addPrompt();

      HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());

      command = ((ListResult) result.get("command")).getSelectedId();
      switch (command) {
        case CREATE_FOLDER -> createFolder(currentFolder);
        case CREATE_FILE -> createFile(currentFolder);
        case LIST_FILES -> listFoldersAndFiles(currentFolder);
      }
    }

  }

  // create folder
  public static void createFolder(Folder actualFolder) throws IOException {
    ConsolePrompt prompt = new ConsolePrompt();
    PromptBuilder promptBuilder = prompt.getPromptBuilder();
    promptBuilder.createInputPrompt()
            .name("folderName")
            .message("Give a folder name")
            .addPrompt();
    HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
    String folderName = ((InputResult) result.get("folderName")).getInput();

    if (folderName.isBlank()) {
      System.out.println("Folder name cannot be blank");
    } else {
      Folder folder = new Folder(folderName); //
      Objects.requireNonNullElse(actualFolder, root).getFolders().add(folder);
    }
  }

  public static void createFile(Folder actualFolder) throws IOException {
    ConsolePrompt prompt = new ConsolePrompt();
    PromptBuilder promptBuilder = prompt.getPromptBuilder();
    promptBuilder.createInputPrompt()
            .name("fileName")
            .message("Give a File name")
            .addPrompt();
    HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
    String fileName = ((InputResult) result.get("fileName")).getInput();
    if (fileName.isBlank()) {
      System.out.println("File name cannot be blank");
    } else {
      File file = new File();
      file.setName(fileName);
      Objects.requireNonNullElse(actualFolder, root).getFiles().add(file);
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
}
