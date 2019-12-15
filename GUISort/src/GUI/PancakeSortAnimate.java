
package GUI;

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

public class PancakeSortAnimate extends JPanel {

	private static final int NUM_OF_ITEMS = 30;
	private static final int DIM_W = 800;
	private static final int DIM_H = 400;
	private static final int HORIZON = 350;
	private static final int VERT_INC = 10;
	private static final int HOR_INC = DIM_W / NUM_OF_ITEMS;

	private JButton startButton;
	private Timer timer = null;
	private JButton resetButton;

	Integer[] list;
	int currentIndex = 0;

	public PancakeSortAnimate() {
		list = initList();

		timer = new Timer(200, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isSortingDone() || isSorted(list)) {
					((Timer) e.getSource()).stop();
					startButton.setEnabled(false);
					for (int i : list) {
						System.out.print(i + ", ");

					}
					System.out.println();
				} else {
					sortOnlyOneItem();
				}
				repaint();
			}
		});
		startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				timer.stop();
			}
		});
		add(startButton);
		add(resetButton);
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

	public void drawItem(Graphics g, int item, int index) {
		int height = item * VERT_INC;
		int y = HORIZON - height;
		int x = index * HOR_INC;
		g.fillRect(x, y, HOR_INC, height);
	}

	private static boolean isSorted(Integer[] a) {
		for (int i = 1; i < a.length; i++)
			if (a[i] < a[i - 1])
				return false;
		return true;
	}

	public int[] minmax(int n) {
		int xm, xM;
		xm = xM = list[0];
		int posm = 0, posM = 0;

		for (int i = 1; i < n; ++i) {
			if (list[i] < xm) {
				xm = list[i];
				posm = i;
			} else if (list[i] > xM) {
				xM = list[i];
				posM = i;
			}
		}
		// System.out.println("in minmax");
		return new int[] { posm, posM };
	}

	public void flip(int n) {
		for (int i = 0; i < (n + 1) / 2; ++i) {
			int tmp = list[i];
			list[i] = list[n - i];
			list[n - i] = tmp;
			timer = new Timer(20, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					repaint();
				}
			});
		}

	// System.out.println("flip(0.." + n + "): " + toString());
	}

	public void sort(int n, int dir) {
		if (n == 0)
			return;

		int[] mM = minmax(n);
		int bestXPos = mM[dir];
		int altXPos = mM[1 - dir];
		boolean flipped = false;
		// System.out.println("in sort");

		if (bestXPos == n - 1) {
			--n;
		} else if (bestXPos == 0) {
			flip(n - 1);
			--n;
		} else if (altXPos == n - 1) {
			dir = 1 - dir;
			--n;
			flipped = true;
		} else {
			flip(bestXPos);
		}
		sort(n, dir);

		if (flipped) {
			flip(n);
		}
	}

	public void sortOnlyOneItem() {

		sort(list.length, 1);
		/*
		 * int n = currentIndex; int dir = 1; int[] mM = minmax(n); int bestXPos =
		 * mM[dir]; int altXPos = mM[1 - dir]; boolean flipped = false;
		 * 
		 * if (bestXPos == n - 1) { --n; } else if (bestXPos == 0) { flip(n - 1); --n; }
		 * else if (altXPos == n - 1) { dir = 1 - dir; --n; flipped = true; } else {
		 * flip(bestXPos); } sort(n, dir);
		 * 
		 * if (flipped) { flip(n); }
		 * 
		 * /* int index = list[currentIndex]; int j = currentIndex; while (j > 0 &&
		 * list[j - 1] > index) { list[j] = list[j - 1]; j--; } list[j] = index;
		 * currentIndex++;
		 * 
		 * for(int i:list) { System.out.print(i +", "); }
		 * 
		 * 
		 * /* int currentMax = list[0]; int currentMaxIndex = 0;
		 * 
		 * for (int j = 1; j <= currentIndex; j++) { if (currentMax < list[j]) {
		 * currentMax = list[j]; currentMaxIndex = j; } }
		 * 
		 * if (currentMaxIndex != currentIndex) { list[currentMaxIndex] =
		 * list[currentIndex]; list[currentIndex] = currentMax; } currentIndex++;
		 */
	}


	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < list.length; i++) {
			drawItem(g, list[i], i);
		}
	}


	public Dimension getPreferredSize() {
		return new Dimension(DIM_W, DIM_H);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Pancake Sort");
				frame.add(new PancakeSortAnimate());
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
				JFrame frame = new JFrame("Pancake Sort");
				frame.add(new PancakeSortAnimate());
				frame.pack();
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
}