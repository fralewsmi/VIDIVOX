package pkgBackgroundTasks;

import javax.swing.SwingWorker;

import pkgBashProcesses.ProcessSaveMp3;
import pkgBashProcesses.ProcessSaveText;
import pkgBashProcesses.ProcessText2Wave;

public class SaveTTS extends SwingWorker<Void, Void> {

	@Override
	protected Void doInBackground() throws Exception {

		// Save the text from user input to text file
		ProcessSaveText saveText = new ProcessSaveText();
		saveText.run();

		// Convert text to wave file
		ProcessText2Wave txt2wav = new ProcessText2Wave();
		txt2wav.run();

		// Save wave to mp3 file
		ProcessSaveMp3 saveMp3 = new ProcessSaveMp3();
		saveMp3.run();

		return null;
	}

}
