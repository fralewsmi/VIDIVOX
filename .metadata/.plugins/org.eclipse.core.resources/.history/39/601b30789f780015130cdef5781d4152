package pkgBackgroundTasks;

import java.io.IOException;

import javax.swing.SwingWorker;

import pgkGUI.NameFile;

public class SaveFile extends SwingWorker<Void, Void> {
	
	String fileName = NameFile.getTextInput();
	@Override
	protected Void doInBackground() throws Exception {
		String cmd = "ffmpeg -y -i .wave.wav -codec:a libmp3lame -qscale:a 2 " + fileName + ".mp3";
		ProcessBuilder builderWave = new ProcessBuilder("/bin/bash", "-c", cmd);
		try {
			Process process = builderWave.start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}

}
