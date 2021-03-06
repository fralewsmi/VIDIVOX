package pkgBashProcesses;

import java.io.IOException;

public class ProcessText2Wave {

	private static final String WAVELOCATION = ".wave.wav";

	public static String getWaveLocation() {
		return WAVELOCATION;
	}

	public void run() {

		String textLocation = ProcessSaveText.getTextLocation();

		// BASH command to convert the text file into a wave file
		String cmd = "text2wave -o " + WAVELOCATION + " " + textLocation;
		ProcessBuilder builderWave = new ProcessBuilder("/bin/bash", "-c", cmd);
		try {
			@SuppressWarnings("unused")
			Process process = builderWave.start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return;
	}
}
