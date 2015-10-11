import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.SwingConstants;


public class Help extends JFrame {
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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

	/**
	 * Create the frame.
	 */
	public Help() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblHelp = new JLabel("Help");
		lblHelp.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelp.setFont(new Font("Tahoma", Font.PLAIN, 32));
		contentPane.add(lblHelp, BorderLayout.NORTH);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		String t = "1) Load a video:\nA video can be loaded up by selecting 'Load Video' from the file menu.\n"
				+ "2) Add an audio track:\nAn audio track can be added to the video by selecting 'Load Audio' from the file menu after selecting a video.\n"
				+ "3) Add a text to speech voice:\nSelecting 'Add Voice' from the file menu brings up the text to speech window.\n"
				+ "3.1) Preview the text to speech:\nEnter your text and press 'Preview'.\n"
				+ "3.2) Save the text to speech to an mp3 file:\nPress 'Save', a window will pop up promping you to enter the file name\n"
				+ "3.2) Add the text to speech to the video:\nPress 'Add to Video'.\n";
		
		textPane.setText(t);
		contentPane.add(textPane, BorderLayout.CENTER);
	}

}
