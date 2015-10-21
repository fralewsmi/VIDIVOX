package pkgBackgroundTasks;

import java.io.File;

import javax.swing.SwingWorker;

import pgkGUI.InputText;
import pgkGUI.NameFile;
import pkgBashProcesses.ProcessSaveText;
import pkgBashProcesses.ProcessText2Wave;

public class SaveTTS extends SwingWorker<Void, Void> {
	
	String textInput = InputText.getTextInput();
	
	@Override
	protected Void doInBackground() throws Exception {
		
		File file = new File(".text.txt");
		file.delete();
		ProcessSaveText saveText = new ProcessSaveText();
		saveText.execute();
		
		file = new File(".wave.wav");
		file.delete();
		ProcessText2Wave txt2wav = new ProcessText2Wave();
		txt2wav.execute();
		
		NameFile nameFile = new NameFile();
		nameFile.setVisible(true);
		
		return null;
	}
	
}
