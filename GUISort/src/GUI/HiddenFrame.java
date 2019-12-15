package GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class HiddenFrame extends JFrame {

	
	private PancakeSortAnimate panimate;
	private MergeSortAnimate mergeSort;
	private JButton panBtn;
	private JButton mergeBtn;
	private JLabel title;
	
	public HiddenFrame() {
		super("Smello World");
		
		
		setSize(300, 150);
		setMinimumSize(new Dimension(300, 150));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		
		
		panimate = new PancakeSortAnimate();
		mergeSort = new MergeSortAnimate();
		panBtn = new JButton("Pancake Sort");
		mergeBtn = new JButton("Merge Sort");
		title = new JLabel("Welcome to the hidden panel\n (these may break)");
		
		panBtn.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				panimate.start();
			}
		});
		
		mergeBtn.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				mergeSort.start();
			}
		});
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.gridy = 0;
		
		add(title,gc);
		gc.weighty = 1;
		gc.gridy++;
		
		add(panBtn,gc);
		
		gc.gridy++;
		
		add(mergeBtn,gc);
	}
	
	/*
	public int getX() {
		return WIDTH;
	}
	
	public int getY() {
		return HEIGHT;
	}
	*/
}
