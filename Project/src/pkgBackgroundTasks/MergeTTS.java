package pkgBackgroundTasks;

import javax.swing.SwingWorker;

import pgkGUI.InputText;
import pgkGUI.MediaPlayer;
import pkgBashProcesses.ProcessConvert;
import pkgBashProcesses.ProcessMerge;
import pkgBashProcesses.ProcessSaveText;
import pkgBashProcesses.ProcessText2Wave;

public class MergeTTS extends SwingWorker<Void, Void> {

	String textInput = InputText.getTextInput();

	// Multiple process builders with festival, text2wave and ffmpeg
	@Override
	protected Void doInBackground() throws Exception {

		try {

			// Save the text from user input to text file
			ProcessSaveText saveText = new ProcessSaveText();
			saveText.execute();

			// Convert text to wave file
			ProcessText2Wave txt2wav = new ProcessText2Wave();
			txt2wav.execute();

			// Convert wave to mp3 file
			ProcessConvert convert = new ProcessConvert();
			convert.execute();

			// Merge selected video and mp3 to mp4 file
			ProcessMerge merge = new ProcessMerge();
			merge.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}

		String outputLocation = ProcessMerge.getOutputLocation();
		MediaPlayer.setVideoLocation(outputLocation);

		return null;
	}

	// Play the new video once the process is done
	@Override
	protected void done() {
		String videoLocation = MediaPlayer.getVideoLocation();
		MediaPlayer.getMediaPlayerComponent().getMediaPlayer().playMedia(videoLocation);
	}

}