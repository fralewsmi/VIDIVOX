package pkgBackgroundTasks;

import java.io.IOException;

import javax.swing.SwingWorker;

import pgkGUI.MediaPlayer;

public class AudioChanger extends SwingWorker<Void, Void> {

	private static final String OUTPUTLOCATION = ".outputAudio.mp4";

	public static String getOutputLocation() {
		return OUTPUTLOCATION;
	}

	// Use a process builder with fmmpeg to add the selected audio to the video
	@Override
	protected Void doInBackground() throws Exception {

		String videoLocation = MediaPlayer.getVideoLocation();
		String audioLocation = MediaPlayer.getAudioLocation();

		MediaPlayer.getProgressBar().setIndeterminate(true);

		// BASH command to replace the existing audio from the video to a new
		// audio
		String cmd = "ffmpeg -y -i " + videoLocation + " -i " + audioLocation + " -map 0:v -map 1:a " + OUTPUTLOCATION;
		ProcessBuilder builderAudio = new ProcessBuilder("/bin/bash", "-c", cmd);
		try {
			Process process = builderAudio.start();
			process.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MediaPlayer.setVideoLocation(OUTPUTLOCATION);

		return null;
	}

	// Play the new video once the process is finished
	@Override
	protected void done() {
		MediaPlayer.getProgressBar().setIndeterminate(false);
		String videoLocation = MediaPlayer.getVideoLocation();
		MediaPlayer.getMediaPlayerComponent().getMediaPlayer().playMedia(videoLocation);
	}

}
