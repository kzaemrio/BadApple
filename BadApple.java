import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.applet.Applet;
import java.applet.AudioClip;

class BadApple extends JFrame {
	private static AudioClip music;
	private static javax.swing.Timer timer;
	private Listener listener;

	public BadApple() {
		music = Applet
				.newAudioClip(this.getClass().getResource("BadApple.wav"));
		listener = new Listener();
		getContentPane().add(listener);
		timer = new javax.swing.Timer(32, listener);

		setTitle("BadApple");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(650, 510);
		setVisible(true);
	}

	public static void main(String[] args) {
		new BadApple();
		music.play();
		timer.start();
	}

	class Listener extends JPanel implements ActionListener {
		private String fileName;
		private int i = 1;

		public void actionPerformed(ActionEvent e) {
			repaint();
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			if (i == 6569) {
				System.exit(0);
			}
			else {
				i++;
			}

			fileName = "data/out (" + i + ").txt";
			FileReader fr = null;
			BufferedReader br = null;
			try {
				fr = new FileReader(fileName);
				br = new BufferedReader(fr);
				String temp;

				int j = 0;

				while ((temp = br.readLine()) != null) {
					StringTokenizer st = new StringTokenizer(temp);
					while (st.hasMoreTokens()) {
						int start = Integer.parseInt(st.nextToken());
						int end = Integer.parseInt(st.nextToken());
						g.drawLine(start, j, end, j);
					}
					j++;
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				try {
					fr.close();
					br.close();
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
	}
}