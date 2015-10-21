package pgkGUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pkgBackgroundTasks.AudioChanger;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class MediaPlayer {

	private static String videoLocation = "";
	private static String audioLocation = "";
	private static EmbeddedMediaPlayerComponent mediaPlayerComponent;

	MediaPlayer(String[] args) {

		// Creates frame for VIDIVOX player prototype
		JFrame frame = new JFrame("VIDIVOX PROTOTYPE");
		frame.setLocation(100, 100);
		frame.setSize(1174, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		// Initialize the video player
		mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
		final EmbeddedMediaPlayer video = mediaPlayerComponent.getMediaPlayer();

		// Initialize the file choosers
		JFileChooser videoChooser = new JFileChooser();
		JFileChooser audioChooser = new JFileChooser();

		// Initialize the menu bar
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		// Base panel
		JPanel panel = new JPanel();
		panel.setLayout(null);
		frame.setContentPane(panel);
		
		// Initialize the progress bar
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(770, 514, 388, 23);
		panel.add(progressBar);
		
		// Open a video file
		JMenuItem mntmOpenVideo = new JMenuItem("Open Video");
		mntmOpenVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = videoChooser.showOpenDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					// Use file chooser to select the video file and play it
					videoLocation = videoChooser.getSelectedFile().toString();
					mediaPlayerComponent.getMediaPlayer().playMedia(videoLocation);
				}
			}
		});
		mnFile.add(mntmOpenVideo);
		
		// Add a new audio track
		JMenuItem mntmOpenAudio = new JMenuItem("Open Audio");
		mntmOpenAudio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!videoLocation.equals("")) {
					int returnVal = audioChooser.showOpenDialog(frame);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						// Use file chooser to select the audio file, task processed in background
						audioLocation = audioChooser.getSelectedFile().toString();
						progressBar.setIndeterminate(true);
						AudioChanger audioChanger = new AudioChanger();
						audioChanger.execute();
						progressBar.setIndeterminate(false);
					}
				} else {
					// Warning if a video is not selected first
					JOptionPane.showMessageDialog(frame, "Please select a video file first!",
							"Warning: No video selected", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		mnFile.add(mntmOpenAudio);
		
		// Open up the tts window
		JMenuItem mntmAddVoice = new JMenuItem("Add Voice");
		mntmAddVoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!videoLocation.equals("")) {
					InputText inputText = new InputText();
					inputText.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(frame, "Please select a video file first!",
							"Warning: No video selected", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		mnFile.add(mntmAddVoice);
		
		// Open the help window
		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Help help = new Help();
				help.setVisible(true);
			}
		});
		mnFile.add(mntmHelp);

		// Creates the video panel
		JPanel videoPanel = new JPanel();
		videoPanel.setBounds(0, 0, 1158, 511);
		panel.add(videoPanel);
		videoPanel.setLayout(new BorderLayout(0, 0));
		videoPanel.add(mediaPlayerComponent, BorderLayout.CENTER);

		// Initializes the video timer
		final JLabel timeLabel = new JLabel("0 seconds");
		timeLabel.setBounds(618, 514, 134, 23);
		panel.add(timeLabel);

		// Initializes Volume Control buttons
		JButton btnMute = new JButton("Mute");
		JButton btnDecreaseVolume = new JButton("-Vol");
		JButton btnIncreaseVolume = new JButton("+Vol");

		// Initializes playback control buttons
		JButton btnPlaypause = new JButton("Play/Pause");
		JButton btnBack = new JButton("<<");
		JButton btnFord = new JButton(">>");

		btnMute.setBounds(416, 514, 88, 23);
		btnDecreaseVolume.setBounds(318, 514, 88, 23);
		btnIncreaseVolume.setBounds(514, 514, 89, 23);
		btnPlaypause.setBounds(95, 514, 128, 23);
		btnBack.setBounds(10, 514, 75, 23);
		btnFord.setBounds(233, 514, 75, 23);

		panel.add(btnMute);
		panel.add(btnDecreaseVolume);
		panel.add(btnIncreaseVolume);
		panel.add(btnPlaypause);
		panel.add(btnBack);
		panel.add(btnFord);

		// Mute function
		btnMute.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				video.mute();
			}
		});

		// Decrease volume function
		btnDecreaseVolume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				video.setVolume(video.getVolume() - 10);
			}
		});
		
		// Increase volume function
		btnIncreaseVolume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (video.getVolume() >= 190) { // Limits the volume to 200 to prevent audio distortion.
					video.setVolume(200);
				} else {
					video.setVolume(video.getVolume() + 10);
				}
			}
		});

		// Pause function
		btnPlaypause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				video.pause();
			}
		});
		
		// Timer for the rewind function
		final Timer skipBackTimer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				video.skip(-200);
			}
		});
		// Rewind function
		final ButtonModel backModel = btnBack.getModel();
		backModel.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent chgeEvent) {
				if (backModel.isPressed() && !skipBackTimer.isRunning()) {
					skipBackTimer.start();
				} else if (!backModel.isPressed() && skipBackTimer.isRunning()) {
					skipBackTimer.stop();
				}
			}
		});
		
		// Fast forward function
		final ButtonModel fordModel = btnFord.getModel();
		fordModel.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent chgeEvent) {
				if (fordModel.isPressed()) {
					video.setRate(2);
				} else if (!fordModel.isPressed()) {
					video.setRate(1);
				}
			}
		});
		
		// Timer function
		Timer timer = new Timer(200, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timeLabel.setText((video.getTime() / 1000) + " seconds");
			}
		});
		timer.start();
		
	}
	
	// Getter methods
	
	public static String getVideoLocation() {
		return videoLocation;
	}

	public static String getAudioLocation() {
		return audioLocation;
	}

	public static EmbeddedMediaPlayerComponent getMediaPlayerComponent() {
		return mediaPlayerComponent;
	}
	
}