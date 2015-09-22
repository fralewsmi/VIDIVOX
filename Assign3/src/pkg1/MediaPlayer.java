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
    	
        NativeLibrary.addSearchPath(
            RuntimeUtil.getLibVlcLibraryName(), "/Applications/vlc-2.0.0/VLC.app/Contents/MacOS/lib"
        );
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        
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
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(mediaPlayerComponent, BorderLayout.CENTER);
        
        JButton btnMute = new JButton("Mute");
        btnMute.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				video.mute();
			}
		});
        panel.add(btnMute, BorderLayout.NORTH);

        JButton btnFord = new JButton(">>");
        btnFord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				video.skip(5000);
			}
		});
        panel.add(btnFord, BorderLayout.EAST);

        JButton btnBack = new JButton("<<");
        btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				video.skip(-5000);
			}
		});
        panel.add(btnBack, BorderLayout.WEST);

        final JLabel timeLabel = new JLabel("0 seconds");
        panel.add(timeLabel, BorderLayout.SOUTH);
        
        Timer timer = new Timer(200, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println(video.getTime()+ " seconds");
				timeLabel.setText((video.getTime()/1000)+ " seconds");
			}
		}); 
        timer.start();
        
        frame.setContentPane(panel);

        frame.setLocation(100, 100);
        frame.setSize(1050, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        mediaPlayerComponent.getMediaPlayer().playMedia("sample_bigbuckbunny.mp4");
    }
}
 