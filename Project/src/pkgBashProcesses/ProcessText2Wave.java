package pkgBashProcesses;

import java.io.IOException;

import javax.swing.SwingWorker;

public class ProcessText2Wave  extends SwingWorker<Void, Void> {
	
	@Override
	protected Void doInBackground() throws Exception {
		String cmd = "text2wave -o .wave.wav .text.txt";
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
