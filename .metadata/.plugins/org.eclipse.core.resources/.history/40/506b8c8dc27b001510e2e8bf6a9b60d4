package pgkGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/*
 * Simple list of instructions on how to use this program 
 * Presented in a JTextPane 
 */
@SuppressWarnings("serial")
public class Help extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Help frame = new Help();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Help() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel lblHelp = new JLabel("Help");
		lblHelp.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelp.setFont(new Font("Tahoma", Font.PLAIN, 32));
		contentPane.add(lblHelp, BorderLayout.NORTH);

		// simple text pane to show the help info
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		String helpText = "1) Load a video:\n\tA video can be loaded up by selecting 'Load Video' from the file menu.\n"
				+ "2) Add an audio track:\n\tAn audio track can be added to the video by selecting 'Add Audio' from the file menu after selecting a video.\n"
				+ "3) Add a text to speech voice:\n\tSelecting 'Add Voice' from the file menu after selecting a video brings up the text to speech window.\n\t"
				+ "User input can be typed in the text box. Enter a single sentance only\n"
				+ "3.1) Preview the text to speech:\n\tEnter your text and press 'Play'.\n"
				+ "3.2) Save the text to speech to an mp3 file:\n\tPress 'Save to File', a window will pop up promping you to specify the save location\n"
				+ "3.2) Add the text to speech to the video:\n\tPress 'Add to Video'.\n"
				+ "4) Save the video:\n\tSelect 'Save Video' to save the current video, a window will pop up promping you to specify the save location\n";

		textPane.setText(helpText);
		contentPane.add(textPane, BorderLayout.CENTER);
	}

}
