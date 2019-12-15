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
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class InsertionSortAnimate extends JPanel implements ChangeListener {
	private Color[] colorList = { Color.GREEN, Color.BLUE, Color.MAGENTA, Color.ORANGE, Color.RED, Color.YELLOW,
			Color.DARK_GRAY, Color.BLUE, Color.CYAN, Color.GRAY };
	private static final int NUM_OF_ITEMS = 30;
	private static final int DIM_W = 1600;
	private static final int DIM_H = 800;
	private static final int HORIZON = 800;
	private static final int VERT_INC = 25;
	private static final int HOR_INC = DIM_W / NUM_OF_ITEMS;

	private JButton startButton;
	private Timer timer = null;
	private JButton resetButton;
	private static JSlider slider;

	Integer[] list;
	int currentIndex = 0;
	int swappedIndex = 0;
	int oldIndex = 0;
	static int delay = 1150;
	int sortedInt = 0;

	public InsertionSortAnimate() {
		list = initList();

		slider = new JSlider(JSlider.HORIZONTAL, 100, 2000, 1150);
		slider.addChangeListener(this);

		
		timer = new Timer(delay, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isSortingDone() || isSorted(list)) {
					if (!increaseDone()) {
						repaint();
						return;
					} else {
						((Timer) e.getSource()).stop();
						startButton.setEnabled(false);
					}

				} else {
					sortOnlyOneItem();
				}
				repaint();
			}
		});
		startButton = new JButton("Play/Pause");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (timer.isRunning()) {
					timer.stop();
				} else
					timer.start();
			}
		});
		resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list = initList();
				currentIndex = 0;
				repaint();
				startButton.setEnabled(true);
				slider.setValue(1150);
				sortedInt = 0;
				timer.stop();
			}
		});

		add(startButton);
		add(resetButton);
		add(slider);
	}

	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();
		delay = (int) source.getValue();
		timer.setDelay(delay);
	}

	public boolean increaseDone() {
		return sortedInt == currentIndex;
	}

	public boolean isSortingDone() {
		return currentIndex == NUM_OF_ITEMS;
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
		slider.setValue(50);
		return true;
	}

	public void sortOnlyOneItem() {

		int index = list[currentIndex];
		int j = currentIndex;
		while (j > 0 && list[j - 1] > index) {
			swappedIndex = j - 1;
			list[j] = list[j - 1];
			j--;
		}

		oldIndex = index + 1;
		list[j] = index;
		currentIndex++;

		for (int i : list) {
			System.out.print(i + ", ");
		}
		System.out.println();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (isSorted(list)) {
			drawItem(g, list[0], 0, Color.BLUE);
			for (int i = 0; i < sortedInt; i++)
				drawItem(g, list[i], i, Color.BLUE);
			for (int i = sortedInt; i < currentIndex; i++)
				drawItem(g, list[i], i, Color.DARK_GRAY);
			sortedInt++;

		} else {
			drawItem(g, list[0], 0, Color.DARK_GRAY);

			for (int i = 0; i < list.length; i++) {
				drawItem(g, list[i], i, Color.RED);
				if (currentIndex > 0 && currentIndex != list.length) {
					drawItem(g, list[currentIndex], currentIndex, Color.GREEN);
					drawItem(g, list[swappedIndex], swappedIndex, Color.DARK_GRAY);
				}
			}

			if (currentIndex == 0) {
				for (int i = 0; i < list.length; i++) {
					drawItem(g, list[i], i, Color.DARK_GRAY);
					drawItem(g, list[0], 0, Color.DARK_GRAY);
				}
			}
		}
	}

	public Dimension getPreferredSize() {
		return new Dimension(DIM_W, DIM_H);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Insertion Sort");
				frame.add(new InsertionSortAnimate());
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
				JFrame frame = new JFrame("Insertion Sort");
				frame.add(new InsertionSortAnimate());
				frame.pack();
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
}