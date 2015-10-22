package pkgBackgroundTasks;

import java.io.File;

import javax.swing.SwingWorker;

import pkgBashProcesses.ProcessConvert;
import pkgBashProcesses.ProcessMerge;
import pkgBashProcesses.ProcessSaveText;
import pkgBashProcesses.ProcessText2Wave;

public class DeleteHidden extends SwingWorker<Void, Void> {

	// delete the temporary hidden files that are created during the running of
	// the program
	@Override
	protected Void doInBackground() throws Exception {

		// deletes the following files

		String textLocation = ProcessSaveText.getTextLocation();
		File file = new File(textLocation);
		file.delete();

		String waveLocation = ProcessText2Wave.getWaveLocation();
		file = new File(waveLocation);
		file.delete();

		String convertLocation = ProcessConvert.getConvertLocation();
		file = new File(convertLocation);
		file.delete();

		String outputLocationTTS = ProcessMerge.getOutputLocation();
		file = new File(outputLocationTTS);
		file.delete();

		String outputLocationAudio = AudioChanger.getOutputLocation();
		file = new File(outputLocationAudio);
		file.delete();

		return null;
	}

}
