package pkgBashProcesses;

import java.io.IOException;

import javax.swing.SwingWorker;

public class ProcessConvert  extends SwingWorker<Void, Void> {
	
	@Override
	protected Void doInBackground() throws Exception {
		
		String cmd = "ffmpeg -y -i .wave.wav -codec:a libmp3lame -qscale:a 2 .convert.mp3";
		ProcessBuilder builderConvert = new ProcessBuilder("/bin/bash", "-c", cmd);
		try {
			Process process = builderConvert.start();
			process.waitFor();
		} catch (IOException | InterruptedException e1) {
			e1.printStackTrace();
		}
		return null;
	}
}
