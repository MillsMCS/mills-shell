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
        Map<String, String> env = getMutableEnvironment();
        while (true) {
            System.out.print("$M$ ");
            String command = scanner.nextLine();
            if (command.equals("exit")) {
                break;
            }
            String[] commandWords = parseCommand(command, env);
            // set VAR=value
            if (commandWords.length >= 4 && commandWords[0].equals("set")
                    && commandWords[2].equals("=")) {
                env.put(commandWords[1], commandWords[3]);
                continue;
            }
            try {
                ProcessBuilder pb = new ProcessBuilder(commandWords);
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

    private static String[] parseCommand(String command, Map<String, String> env) {
        String[] commandWords = command.split("\\s+");
        for (int i = 0; i < commandWords.length; i++) {
            if (commandWords[i].startsWith("$")) {
                commandWords[i] = env.getOrDefault(commandWords[i].substring(1), "");
            }
        }
        return commandWords;
    }

    private static Map<String, String> getMutableEnvironment() {
        Map<String, String> env0 = System.getenv();
        Map<String, String> env = new HashMap<>(env0);
        return env;
    }
}
