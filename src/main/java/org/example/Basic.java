package org.example;

import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.ListResult;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import org.fusesource.jansi.AnsiConsole;

import java.io.IOException;
import java.util.HashMap;

import static org.fusesource.jansi.Ansi.ansi;

/**
 * User: Andreas Wegmann
 * Date: 29.11.15
 */
public class Basic {
  public static void createCommand() {
    try {
      ConsolePrompt prompt = new ConsolePrompt();
      PromptBuilder promptBuilder = prompt.getPromptBuilder();

      promptBuilder.createListPrompt()
              .name("projectType")
              .message("What kind of project do you want to create? (java, javascript, python) ")
              .newItem().text("java").add()
              .newItem("javascript").text("javascript").add().newItem("python").text("python")
              .add().addPrompt();

      HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());

      String projectType = ((ListResult) result.get("projectType")).getSelectedId();
      System.out.println("result = " + ((ListResult) result.get("projectType")).getSelectedId());
      System.out.println("Building a new project: " + projectType);

      if (projectType == "java") {
        prompt = new ConsolePrompt();
        promptBuilder = prompt.getPromptBuilder();
        promptBuilder.createCheckboxPrompt()
                .name("features")
                .message("Please select features to add:")
                .newSeparator("Databases:")
                .add()
                .newItem().name("mongo").text("MongoDB").add()
                .newItem("mysql").text("MySQL").add()
                .newItem("db2").text("DB2").disabledText("Sorry, discontinued.").add()

                .newSeparator().text("Rest API").add()
                .newItem("spring-rest").text("Spring REST").check().add()
                .newItem("micronaut").text("Micronaut").add()
                .addPrompt();

        result = prompt.prompt(promptBuilder.build());
        System.out.println("result = " + result);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        TerminalFactory.get().restore();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    createCommand();
  }
}
