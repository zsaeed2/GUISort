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

public class BubbleSortAnimate extends JPanel implements ChangeListener {

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
	int currentIndex = NUM_OF_ITEMS - 1;
	int swappedIndex = currentIndex;
	int delay = 1150;
	int sortedInt = 0;
	public BubbleSortAnimate() {
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
				currentIndex = NUM_OF_ITEMS - 1;
				repaint();
				currentIndex = NUM_OF_ITEMS - 1;
				swappedIndex = currentIndex;
				delay = 1150;
				sortedInt = 0;
				slider.setValue(1150);
				startButton.setEnabled(true);
				timer.stop();
			}
		});
		add(slider);
		add(startButton);
		add(resetButton);
	}

	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();
		delay = (int) source.getValue();
		timer.setDelay(delay);
	}

	public boolean increaseDone() {
		return sortedInt == NUM_OF_ITEMS - 1;
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
		slider.setValue(50);
		return true;
	}

	public void sortOnlyOneItem() {

		for (int j = 1; j <= currentIndex; j++) {
			if (list[j - 1] > list[j]) {
				int temp = list[j - 1];
				list[j - 1] = list[j];
				list[j] = temp;
				swappedIndex = j;
			}
		}
		System.out.println(swappedIndex);

		currentIndex--;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (isSorted(list)) {
			drawItem(g, list[0], 0, Color.BLUE);
			for (int i = 0; i < sortedInt; i++)
				drawItem(g, list[i], i, Color.BLUE);
			for (int i = sortedInt; i < NUM_OF_ITEMS; i++)
				drawItem(g, list[i], i, Color.DARK_GRAY);
			sortedInt++;

		} else {

			drawItem(g, list[0], 0, Color.DARK_GRAY);
			for (int i = 0; i < list.length; i++) {
				drawItem(g, list[i], i, Color.RED);
				drawItem(g, list[swappedIndex], swappedIndex, Color.DARK_GRAY);
			}
		}
	}

	public Dimension getPreferredSize() {
		return new Dimension(DIM_W, DIM_H);
	}

	public static void main(String[] args) {
		start();
	}

	public static void start() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Bubble Sort");
				frame.add(new BubbleSortAnimate());
				frame.pack();
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
}