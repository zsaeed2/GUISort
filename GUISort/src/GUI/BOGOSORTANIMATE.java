
package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class BOGOSORTANIMATE extends JPanel {

	private Color[] colorList = { Color.GREEN, Color.BLUE, Color.GRAY, Color.DARK_GRAY, Color.MAGENTA, Color.ORANGE, Color.RED, Color.YELLOW,
			 Color.BLUE, Color.CYAN};
	private static final int NUM_OF_ITEMS = 4;
	private static final int DIM_W = 1600;
	private static final int DIM_H = 800;
	private static final int HORIZON = 800;
	private static final int VERT_INC = 40;
	private static final int HOR_INC = DIM_W / NUM_OF_ITEMS;

	private JButton startButton;
	private Timer timer = null;
	private JButton resetButton;

	Integer[] list;
	int currentIndex = NUM_OF_ITEMS - 1;

	int trials = 0;
	int count = 0;

	public BOGOSORTANIMATE() {
		list = initList();

		timer = new Timer(0, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isSortingDone() || isSorted(list)) {
					((Timer) e.getSource()).stop();
					startButton.setEnabled(false);
					System.out.println(count);

					count = 0;
				} else {
					sortOnlyOneItem();
				}
				repaint();
			}
		});
		startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (timer.isRunning())
					timer.stop();
				else
					timer.start();
			}
		});
		resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list = initList();
				timer.stop();
				count = 0;
				trials = 0;
				currentIndex = NUM_OF_ITEMS - 1;
				repaint();
				startButton.setEnabled(true);
			}
		});
		add(startButton);
		add(resetButton);
	}

	public boolean isSortingDone() {
		return currentIndex == 0;
	}

	public Integer[] initList() {
		Integer[] nums = new Integer[NUM_OF_ITEMS];
		for (int i = 1; i <= nums.length; i++) {
			nums[i - 1] = i;
		}
		Collections.shuffle(Arrays.asList(nums));
		return nums;
	}

	public void drawItem(Graphics g, int item, int index, Color c) {
		int height = item * VERT_INC;
		int y = HORIZON - height;
		int x = index * HOR_INC;
		g.fillRect(x, y, HOR_INC, height);
		g.setColor(c);
	}

	private static boolean isSorted(Integer[] a) {
		for (int i = 1; i < a.length; i++)
			if (a[i] < a[i - 1])
				return false;
		return true;
	}

	public void sortOnlyOneItem() {
		Collections.shuffle(Arrays.asList(list));
		count++;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < list.length; i++) {
			drawItem(g, list[i], i, colorList[(int)(Math.random() * 4)]);
		}
	}

	public Dimension getPreferredSize() {
		return new Dimension(DIM_W, DIM_H);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("BOGO Sort");
				frame.add(new BOGOSORTANIMATE());
				frame.pack();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
	
	
	public static void start() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("BOGO Sort");
				frame.add(new BOGOSORTANIMATE());
				frame.pack();
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
}
