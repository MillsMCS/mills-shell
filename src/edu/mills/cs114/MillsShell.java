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
        while (true) {
            System.out.print("$M$ ");
            String command = scanner.nextLine();
            if (command.equals("exit")) {
                break;
            }
            try {
                ProcessBuilder pb = new ProcessBuilder(command.split("\\s+"));
                Process process = pb.start();
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                process.waitFor();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
