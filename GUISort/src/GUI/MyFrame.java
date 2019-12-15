package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MyFrame extends JFrame {

	private Preferences prefs;
	private InsertionSortAnimate insSort;
	private SelectionSortAnimate selSort;
	private BubbleSortAnimate bubSort;
	private BOGOSORTANIMATE bogoSort;
	private JLabel title;
	private JLabel hidden;
	private JButton intBtn;
	private JButton selBtn;
	private JButton bubBtn;
	private JButton bogoBtn;
	private HiddenFrame hFrame;
	

	public MyFrame() {
		super("Sorting Algorithms");
		setLayout(new GridBagLayout());
		setBounds(400, 400, getWidth(), getHeight());

		
		hFrame = new HiddenFrame();
		hFrame.setVisible(false);
		hFrame.setBounds(400, 400, hFrame.getWidth(), hFrame.getHeight());
		/*
		hFrame.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
					hFrame.setVisible(false);
			}
			public void keyReleased(KeyEvent e) {
			}
			public void keyTyped(KeyEvent e) {
			}		
		});
	*/
		insSort = new InsertionSortAnimate();
		selSort = new SelectionSortAnimate();
		bubSort = new BubbleSortAnimate();
		bogoSort = new BOGOSORTANIMATE();
		// bubSort.start();
		title = new JLabel("Choose a sorting type");
		hidden = new JLabel("  ");
		hidden.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				hFrame.setVisible(true);
			}	
			public void mouseEntered(MouseEvent e) {		
			}
			public void mouseExited(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
			}			
		});
		GraphicsEnvironment ge = GraphicsEnvironment.
                getLocalGraphicsEnvironment();
		String[] fonts = ge.getAvailableFontFamilyNames();
		JComboBox fontChooser = new JComboBox(fonts);
		title.setFont(new Font("Comic Sans MS", 20, 20));
		intBtn = new JButton("Insertion Sort");
		intBtn.setFont(new Font("Comic Sans MS", 12, 12));
		selBtn = new JButton("Selection Sort");
		selBtn.setFont(new Font("Comic Sans MS", 12, 12));
		bubBtn = new JButton("Bubble Sort");
		bubBtn.setFont(new Font("Comic Sans MS", 12, 12));
		bogoBtn = new JButton("Bogo Sort");
		bogoBtn.setFont(new Font("Comic Sans MS", 12, 12));

		intBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insSort.start();
			}
		});
		selBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selSort.start();
			}
		});
		bubBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bubSort.start();
			}
		});
		bogoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bogoSort.start();
			}
		});
		
		

		setSize(300, 350);
		setMinimumSize(new Dimension(300, 350));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		GridBagConstraints gc = new GridBagConstraints();

		gc.weighty = 1;

		gc.gridy = 0;
		gc.gridx = 0;
		
		
		//add(fontChooser,gc);
		add(title,gc);
		// row 1
		
		gc.gridy++;
		gc.fill = GridBagConstraints.NONE;
		// specifies the alginment
		gc.anchor = GridBagConstraints.CENTER;
		// insets adds spacing between objects
		gc.insets = new Insets(0, 0, 0, 5);

		add(intBtn, gc);
		/// row 2
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);

		add(selBtn, gc);
		// row 3
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);

		add(bubBtn, gc);
		// row 4
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);

		add(bogoBtn, gc);
		
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = .1;
		
		add(hidden,gc);

	}
	
}