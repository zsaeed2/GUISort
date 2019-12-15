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

public class MergeSortAnimate extends JPanel {

	private static final int NUM_OF_ITEMS = 30;
	private static final int DIM_W = 800;
	private static final int DIM_H = 400;
	private static final int HORIZON = 350;
	private static final int VERT_INC = 10;
	private static final int HOR_INC = DIM_W / NUM_OF_ITEMS;

	private JButton startButton;
	private Timer timer = null;
	private JButton resetButton;

	static Integer[] list;
	static int currentIndex = 0;

	public MergeSortAnimate() {
		list = initList();

		timer = new Timer(200, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isSortingDone() || isSorted(list)) {
					((Timer) e.getSource()).stop();
					startButton.setEnabled(false);
					for (int i : list) {
						System.out.print(i);
					}
				} else {
					System.out.println("enter one");
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

	void mergesort(Integer[] list2, int low, int high)  { 
	    if(low == high)  
	         return; 
	    int length = high-low+1; 
	    int pivot = (low+high) / 2; 
	    mergesort(list2, low, pivot); 
	    mergesort(list2, pivot+1, high); 
	    int working[] = new int[length]; 
	    for(int i = 0; i < length; i++)  
	        working[i] = list2[low+i]; 
	    int m1 = 0;  
	    int m2 = pivot-low+1; 
	    for(int i = 0; i < length; i++) { 
	      if(m2 <= high-low)  
	          if(m1 <= pivot-low)  
	              if(working[m1] > working[m2])  
	                  list2[i+low] = working[m2++];   
	              else  
	                  list2[i+low] = working[m1++]; 
	          else  
	              list2[i+low] = working[m2++]; 
	      else  
	          list2[i+low] = working[m1++]; 
	    } 
	  } 
	
	public void sortOnlyOneItem() {

		int low = 0;
		int high = list.length - 1;
		
		if(low == high)  
	         return; 
	    int length = high-low+1; 
	    int pivot = (low+high) / 2; 
	    mergesort(list, low, pivot); 
	    mergesort(list, pivot+1, high); 
	    int working[] = new int[length]; 
	    for(int i = 0; i < length; i++)  
	        working[i] = list[low+i]; 
	    int m1 = 0;  
	    int m2 = pivot-low+1; 
	    for(int i = 0; i < length; i++) { 
	      if(m2 <= high-low)  
	          if(m1 <= pivot-low)  
	              if(working[m1] > working[m2])  
	                  list[i+low] = working[m2++];   
	              else  
	                  list[i+low] = working[m1++]; 
	          else  
	              list[i+low] = working[m2++]; 
	      else  
	          list[i+low] = working[m1++]; 
	    } 
		currentIndex++;

		/*
		 * int N = list.length; if (N <= 1) return list; double[] a = new double[N/2];
		 * double[] b = new double[N - N/2]; for (int i = 0; i < a.length; i++) a[i] =
		 * list[i]; for (int i = 0; i < b.length; i++) b[i] = list[i + N/2]; return
		 * merge(mergesort(a), mergesort(b));
		 * 
		 * 
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

	@Override
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
				JFrame frame = new JFrame("Merge Sort");
				frame.add(new MergeSortAnimate());
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
				JFrame frame = new JFrame("Merge Sort");
				frame.add(new MergeSortAnimate());
				frame.pack();
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
	
}