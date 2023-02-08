package the.primer.samples;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatch {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter 1 to match text with a pattern");
    System.out.println("Enter 2 to display common patterns and their information");
    System.out.print("Enter your choice: ");
    int choice = sc.nextInt();
    sc.nextLine(); // consume the line after the integer input

    if (choice == 1) {
      System.out.print("Enter text: ");
      String text = sc.nextLine();
      System.out.print("Enter pattern: ");
      String pattern = sc.nextLine();

      Pattern compiledPattern = Pattern.compile(pattern);
      Matcher matcher = compiledPattern.matcher(text);

      System.out.println("Matching text: ");
      while (matcher.find()) {
        System.out.println(matcher.group());
      }
    } else if (choice == 2) {
      RegexPatterns.printPatterns();
    } else {
      System.out.println("Invalid choice. Please enter either 1 or 2.");
    }
  }
}
