package edu.mills.cs114;

import java.io.*;
import java.util.*;

/**
 * A simple shell created for CS 114/214 Lecture 12.
 * 
 * @author Ellen Spertus (spertus@mills.edu)
 */
public class MillsShell {
    /**
     * Prompts user for shell commands, which are executed.
     *
     * @param args ignored
     * @throws IOException if unable to get input from user
     * @throws InterruptedException if the command's execution is interrupted
     */
    public static void main(String args[]) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> env = System.getenv();
        while (true) {
            System.out.print("$M$ ");
            String[] commandWords = parseCommand(env, scanner.nextLine());
            ProcessBuilder pb = new ProcessBuilder(commandWords);
            try {
                Process p = pb.start();
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                p.waitFor();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static String[] parseCommand(Map<String, String> env, String command) {
        String[] commandWords = command.split("\\s+");
        for (int i = 0; i < commandWords.length; i++) {
            if (commandWords[i].startsWith("$")) {
                commandWords[i] = env.getOrDefault(commandWords[i].substring(1), "");
            }
        }
        return commandWords;
    }
}
