package pkgBashProcesses;

import java.io.IOException;

import pgkGUI.InputText;

public class ProcessSaveText {

	private static final String TEXTLOCATION = ".text.txt";

	public static String getTextLocation() {
		return TEXTLOCATION;
	}

	public void run() {

		String textInput = InputText.getTextInput();

		// BASH command to output the contents of the user input into a text
		// file
		String cmd = "echo " + textInput + " > " + TEXTLOCATION;
		ProcessBuilder builderText = new ProcessBuilder("/bin/bash", "-c", cmd);
		try {
			@SuppressWarnings("unused")
			Process process = builderText.start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return;
	}
}
