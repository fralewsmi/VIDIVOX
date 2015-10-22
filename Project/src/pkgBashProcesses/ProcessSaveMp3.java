package pkgBashProcesses;

import java.io.IOException;

import javax.swing.SwingWorker;

import pgkGUI.InputText;

public class ProcessSaveMp3 extends SwingWorker<Void, Void> {

	String filename = InputText.getAudioSaveLocation();

	@Override
	protected Void doInBackground() throws Exception {
		
		// Bash process to convert wave file into user-specified mp3
		String cmd = "ffmpeg -y -i .wave.wav -codec:a libmp3lame -qscale:a 2 " + filename + ".mp3";
		ProcessBuilder builderWave = new ProcessBuilder("/bin/bash", "-c", cmd);
		try {
			@SuppressWarnings("unused")
			Process process = builderWave.start();
		} catch (IOException e1) {
			System.out.print("failed");
			e1.printStackTrace();
		}
		return null;
	}

}
