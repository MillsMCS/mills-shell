package edu.mills.cs114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		Runtime runtime = Runtime.getRuntime();
		while (true) {
			System.out.print("$ ");
			String command = keyboard.readLine();
			Process p = runtime.exec(command);
			p.waitFor();
		}
	}
}