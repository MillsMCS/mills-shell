package edu.mills.cs114;

import java.io.IOException;
import java.util.Scanner;

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
	 * @throws IOException          if unable to get input from user
	 * @throws InterruptedException if the command's execution is interrupted
	 */
	public static void main(String args[]) throws IOException, InterruptedException {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("$M$ ");
			String command = scanner.nextLine();
			try {
				ProcessBuilder pb = new ProcessBuilder(command);
				Process p = pb.start();
				p.waitFor();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
	}
}
