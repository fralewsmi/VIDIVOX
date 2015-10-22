package pgkGUI;

import javax.swing.SwingUtilities;

import pkgBackgroundTasks.DeleteHidden;

public class Main {

	public static void main(final String[] args) {

		// Delete hidden files to avoid conflicts
		DeleteHidden delete = new DeleteHidden();
		delete.execute();

		// Run MediaPlayer
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MediaPlayer(args);
			}
		});
	}
}
