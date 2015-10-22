package pkgBashProcesses;

import java.io.IOException;

import javax.swing.SwingWorker;

public class ProcessText2Wave extends SwingWorker<Void, Void> {

	private static final String WAVELOCATION = ".wave.wav";

	public static String getWaveLocation() {
		return WAVELOCATION;
	}

	@Override
	protected Void doInBackground() throws Exception {

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
		return null;
	}
}
