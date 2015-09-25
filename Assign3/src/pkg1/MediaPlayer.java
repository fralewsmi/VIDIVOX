package pkg1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class MediaPlayer {
	
    protected static String videoLocation = "";
	static EmbeddedMediaPlayerComponent mediaPlayerComponent;
    
    public static void main(final String[] args) {
    	        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MediaPlayer(args);
            }
        });
    }

    private MediaPlayer(String[] args) {

    	String [] toDelete = {"convert.mp3", "out.mp4","text.txt", "wave.wav", "out1.mp4"};
    	for (int i=0; i<toDelete.length; i++) {
    		File file = new File(toDelete[i]);
    		file.delete();
    	}
    	
    	// Creates frame for VIDIVOX player prototype
        JFrame frame = new JFrame("VIDIVOX PROTOTYPE");
        frame.setLocation(100, 100);
        frame.setSize(1174, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        final EmbeddedMediaPlayer video = mediaPlayerComponent.getMediaPlayer();
        
        // Initialises the FileChoosers to select the video and audio files to play
        JFileChooser videoChooser = new JFileChooser();
        JFileChooser audioChooser = new JFileChooser();
        
        // Creates panel which contains the video panel and the buttons for the player controls
        JPanel panel = new JPanel();
        panel.setLayout(null);        
        frame.setContentPane(panel);

        // Creates panel for the video to play
        JPanel videoPanel = new JPanel();
        videoPanel.setBounds(0, 0, 1158, 527);
        panel.add(videoPanel);
        videoPanel.setLayout(new BorderLayout(0, 0));
        videoPanel.add(mediaPlayerComponent, BorderLayout.CENTER);
        
        // Initialises and adds time label to show the current time of the video file
        final JLabel timeLabel = new JLabel("0 seconds");
        timeLabel.setBounds(10, 538, 111, 23);
        panel.add(timeLabel);
        
        // Initialises Volume Control buttons
        JButton btnMute = new JButton("Mute"); // Mute the sound
        JButton btnDecreaseVolume = new JButton("-Vol"); // Decrease the sound
        JButton btnIncreaseVolume = new JButton("+Vol"); // Increase the sound
        
        // Initialises playback control buttons
        JButton btnPlaypause = new JButton("Play/Pause"); // Play or Pause
        JButton btnBack = new JButton("<<"); // Rewind
        JButton btnFord = new JButton(">>"); // Fast-Forward

        // Initialises other buttons to select video, audio and add synthetic speech
        JButton btnVideo = new JButton("Select Video"); // Choose a video file to play
        JButton btnAudio = new JButton("Select Audio"); // Choose and audio file to play
        JButton btnVoice = new JButton("Add Voice"); // Add synthetic speech to the video

        // Sets the location of each of the buttons
        btnMute.setBounds(458, 538, 88, 23);
        btnDecreaseVolume.setBounds(556, 538, 88, 23);
        btnIncreaseVolume.setBounds(654, 538, 89, 23);
        
        btnPlaypause.setBounds(231, 538, 128, 23);
        btnBack.setBounds(139, 538, 75, 23);
        btnFord.setBounds(371, 538, 75, 23);

        btnVideo.setBounds(888, 538, 125, 23);
        btnAudio.setBounds(753, 538, 125, 23);
        btnVoice.setBounds(1023, 538, 125, 23);

        // Adds all the buttons to the button panel
        panel.add(btnMute);
        panel.add(btnDecreaseVolume);
        panel.add(btnIncreaseVolume);
        
        panel.add(btnPlaypause);
        panel.add(btnBack);
        panel.add(btnFord);
        
        panel.add(btnVideo);
        panel.add(btnAudio);
        panel.add(btnVoice);


        btnMute.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				video.mute();
			}
		});
        
        btnDecreaseVolume.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		video.setVolume(video.getVolume() - 10);
        	}
        });
        
        btnIncreaseVolume.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (video.getVolume() >= 190) { // Limits the volume to 200 to prevent audio distortion.
        			video.setVolume(200);
        		} else {
        			video.setVolume(video.getVolume() + 10);
        		}
        	}
        });
        
        btnPlaypause.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		video.pause();
        	}
        });

        final Timer skipBackTimer = new Timer(100 , new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               video.skip(-300);
            }
         });
         final ButtonModel backModel = btnBack.getModel();
         backModel.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent cEvt) {
               if (backModel.isPressed() && !skipBackTimer.isRunning()) {
            	   skipBackTimer.start();
               } else if (!backModel.isPressed() && skipBackTimer.isRunning()) {
            	   skipBackTimer.stop();
               }
            }
         });
        
        final Timer skipFordTimer = new Timer(100 , new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
              video.skip(300);
           }
        });
        final ButtonModel fordModel = btnFord.getModel();
        fordModel.addChangeListener(new ChangeListener() {
           @Override
           public void stateChanged(ChangeEvent cEvt) {
              if (fordModel.isPressed() && !skipFordTimer.isRunning()) {
            	  skipFordTimer.start();
              } else if (!fordModel.isPressed() && skipFordTimer.isRunning()) {
            	  skipFordTimer.stop();
              }
           }
        });
        
        Timer timer = new Timer(200, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timeLabel.setText((video.getTime()/1000)+ " seconds");
			}
		}); 
        timer.start();
    
        btnVideo.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		int returnVal = videoChooser.showOpenDialog(panel);
        		
        		if (returnVal == JFileChooser.APPROVE_OPTION) {
        			videoLocation = videoChooser.getSelectedFile().toString();
        			mediaPlayerComponent.getMediaPlayer().playMedia(videoLocation);
        		}
        	}
        });
        
        btnAudio.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
                if (!videoLocation.equals("")) {
	        		int returnVal = audioChooser.showOpenDialog(panel);
	        		if (returnVal == JFileChooser.APPROVE_OPTION) {
	        			String audioLocation = audioChooser.getSelectedFile().toString();
	        			changeAudio(audioLocation);
	        		}
                } else {
        			JOptionPane.showMessageDialog(panel, "Please select a video file first!", "Warning: No video selected", JOptionPane.WARNING_MESSAGE);
                }
        	}

			private void changeAudio(String audioLocation) {
				String outputLocation = "out.mp4";
				String cmd = "ffmpeg -i "+videoLocation+" -i "+audioLocation+" -map 0:v -map 1:a "+outputLocation;
				ProcessBuilder builderAudio = new ProcessBuilder("/bin/bash", "-c", cmd);
				try {
					Process process = builderAudio.start();
					process.waitFor();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				videoLocation = outputLocation;
				mediaPlayerComponent.getMediaPlayer().playMedia(videoLocation);
			}
        });
        
        btnVoice.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (!videoLocation.equals("")) {
        			InputText inputText = new InputText();
        			inputText.setVisible(true);
        		} else {
        			JOptionPane.showMessageDialog(panel, "Please select a video file first!", "Warning: No video selected", JOptionPane.WARNING_MESSAGE);
        		}
        	}
        });
    }

}
  
