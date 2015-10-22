package pkgBashProcesses;

import java.io.IOException;

import javax.swing.SwingWorker;

import pgkGUI.MediaPlayer;

public class ProcessMerge extends SwingWorker<Void, Void> {

	private static final String OUTPUTLOCATION = ".outputTTS.mp4";

	public static String getOutputLocation() {
		return OUTPUTLOCATION;
	}

	@Override
	protected Void doInBackground() throws Exception {

		String videoLocation = MediaPlayer.getVideoLocation();
		String convertLocation = ProcessConvert.getConvertLocation();

		// BASH command to merge the current video with the converted mp3 file
		// to output an mp4
		String cmd = "ffmpeg -y -i " + videoLocation + " -i " + convertLocation + " -filter_complex amix=inputs=2 "
				+ OUTPUTLOCATION;
		ProcessBuilder builderAdd = new ProcessBuilder("/bin/bash", "-c", cmd);
		try {
			Process process = builderAdd.start();
			process.waitFor();
		} catch (IOException | InterruptedException e1) {
			e1.printStackTrace();
		}
		return null;
	}
}
