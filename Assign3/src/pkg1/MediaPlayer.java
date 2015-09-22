package pkg1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class MediaPlayer {
    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;

    public static void main(final String[] args) {
    	        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MediaPlayer(args);
            }
        });
    }

    private MediaPlayer(String[] args) {
        JFrame frame = new JFrame("VIDIVOX PROTOTYPE");
        
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        
        final EmbeddedMediaPlayer video = mediaPlayerComponent.getMediaPlayer();
        
        JPanel panel = new JPanel();
        
        JButton btnMute = new JButton("Mute");
        btnMute.setBounds(458, 538, 88, 23);
        btnMute.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				video.mute();
			}
		});
        panel.setLayout(null);
        panel.add(btnMute);
        
        JButton btnDecreaseVolume = new JButton("-Volume");
        btnDecreaseVolume.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		video.setVolume(video.getVolume() - 10);
        	}
        });
        btnDecreaseVolume.setBounds(556, 538, 88, 23);
        panel.add(btnDecreaseVolume);
        
        
        JButton btnIncreaseVolume = new JButton("+Volume");
        btnIncreaseVolume.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (video.getVolume() >= 190) { // Limits the volume to 200 to prevent audio distortion.
        			video.setVolume(200);
        		} else {
        			video.setVolume(video.getVolume() + 10);
        		}
        	}
        });
        btnIncreaseVolume.setBounds(654, 538, 89, 23);
        panel.add(btnIncreaseVolume);

        JButton btnFord = new JButton(">>");
        btnFord.setBounds(371, 538, 75, 23);
        btnFord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				video.setRate(2);
			}
		});
        panel.add(btnFord);

        JButton btnBack = new JButton("<<");
        btnBack.setBounds(139, 538, 75, 23);
        btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				video.skip(-5000);
			}
		});
        panel.add(btnBack);

        final JLabel timeLabel = new JLabel("0 seconds");
        timeLabel.setBounds(10, 538, 111, 23);
        panel.add(timeLabel);
        
        Timer timer = new Timer(200, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println(video.getTime()+ " seconds");
				timeLabel.setText((video.getTime()/1000)+ " seconds");
			}
		}); 
        timer.start();
        
        frame.setContentPane(panel);
        
        JButton btnPlaypause = new JButton("Play/Pause");
        btnPlaypause.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		video.pause();
        	}
        });
        btnPlaypause.setBounds(231, 538, 128, 23);
        panel.add(btnPlaypause);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 0, 1034, 527);
        panel.add(panel_1);
        panel_1.setLayout(new BorderLayout(0, 0));
        panel_1.add(mediaPlayerComponent, BorderLayout.CENTER);
        
        
        frame.setLocation(100, 100);
        frame.setSize(1050, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        mediaPlayerComponent.getMediaPlayer().playMedia("sample_bigbuckbunny.mp4");
    }
}
 
