package the.primer.samples;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPatterns {
  private static String[][] patternData = {{"\\d{3}-\\d{2}-\\d{4}", "Social Security Number", "The pattern matches a string of 9 digits separated by hyphens into groups of 3-2-4 digits."}, {"\\w+@[a-z]+\\.[a-z]+", "Email address", "The pattern matches a string that starts with one or more word characters, followed by the @ symbol, then one or more lowercase letters, a dot, and finally one or more lowercase letters."}, {"(\\d{2}:){2}\\d{2}", "24-hour time", "The pattern matches a string of 2 digits separated by colons, repeated twice."}, {"^\\w+", "Word at the start of string", "The pattern matches a word at the start of the string."}, {".*[\\.!?]$", "End of string with punctuation", "The pattern matches a string that ends with a dot, an exclamation mark, or a question mark."}, {"[0-9]+", "Number", "The pattern matches one or more digits."}, {"\\b(the|an?|and)\\b", "Articles", "The pattern matches the word 'the', 'an' or 'and' when they appear as standalone words."}, {"(Mr|Ms|Dr)\\.", "Titles", "The pattern matches the abbreviations for Mr., Ms., and Dr., followed by a dot."}};

  public static void printPatterns() {
    Scanner sc = new Scanner(System.in);
    for (String[] data : patternData) {
      String pattern = data[0];
      String purpose = data[1];
      String description = data[2];
      Pattern compiledPattern = Pattern.compile(pattern);

      System.out.println("\n*********");
      System.out.println("Pattern: " + pattern);
      System.out.println("Purpose: " + purpose);
      System.out.println("Description: " + description);
      System.out.println();

      for (String text : sampleTexts) {
        Matcher matcher = compiledPattern.matcher(text);
        while (matcher.find()) {
          System.out.println("\nSample text: " + text);
          System.out.print("Matching text: ");
          System.out.println(matcher.group());
        }
      }
      System.out.print("Press 'q' to exit or any other key to continue... ");
      try {
        char c = (char) System.in.read();
        if (c == 'q') {
          System.exit(0);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private static String[] sampleTexts = {"My social security number is 123-45-6789.", "Please use the id user@example.com to communicate with the office.", "The time then was 12:34:56 and he was still asleep.", "The cat was playing with the ball.", "Is this the end of the road for us?"};
}
