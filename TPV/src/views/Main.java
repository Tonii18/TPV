package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import roundedComponents.RoundButton;
import roundedComponents.RoundPanel;

public class Main extends JFrame {

	private JPanel contentPane;
	
	private RoundButton entrys;
	private RoundButton meets;
	private RoundButton fishes;
	private RoundButton desserts;
	private RoundButton drinks;
	private RoundButton add;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 820);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel navBar = new JPanel();
		navBar.setBackground(new Color(11, 47, 86));
		navBar.setBounds(0, 0, 1284, 100);
		contentPane.add(navBar);
		navBar.setLayout(null);
		
		JLabel logoLbl = new JLabel("");
		logoLbl.setBounds(24, 5, 90, 90);
		logoLbl.setIcon(new ImageIcon(getClass().getResource("/logo.png")));
		navBar.add(logoLbl);
		
		JLabel title = new JLabel("Tap&Go");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Inter 24pt ExtraBold", Font.PLAIN, 30));
		title.setForeground(new Color(255, 255, 255));
		title.setBounds(117, 34, 124, 31);
		navBar.add(title);
		
		JLabel tpv = new JLabel("TPV");
		tpv.setHorizontalAlignment(SwingConstants.CENTER);
		tpv.setForeground(Color.WHITE);
		tpv.setFont(new Font("Inter 24pt ExtraBold", Font.PLAIN, 30));
		tpv.setBounds(1169, 34, 105, 31);
		navBar.add(tpv);
		
		RoundPanel calculatorBorder = new RoundPanel(10, 10);
		calculatorBorder.setBackground(new Color(217, 217, 217));
		calculatorBorder.setBounds(30, 130, 459, 621);
		contentPane.add(calculatorBorder);
		calculatorBorder.setLayout(null);
		
		RoundPanel calculator = new RoundPanel(10, 10);
		calculator.setBackground(new Color(255, 255, 255));
		calculator.setBounds(10, 11, 439, 599);
		calculatorBorder.add(calculator);
		calculator.setLayout(null);
		
		RoundPanel menu = new RoundPanel(10, 10);
		menu.setBounds(528, 130, 725, 621);
		contentPane.add(menu);
		menu.setLayout(null);
		
		RoundPanel sideBar = new RoundPanel(10, 10);
		sideBar.setBackground(new Color(11, 47, 86));
		sideBar.setBounds(0, 0, 143, 621);
		menu.add(sideBar);
		sideBar.setLayout(null);
		
		entrys = new RoundButton("Entrantes", 10, 10);
		entrys.setBackground(new Color(255, 204, 0));
		entrys.setHorizontalTextPosition(SwingConstants.CENTER);
		entrys.setForeground(new Color(255, 255, 255));
		entrys.setFont(new Font("Inter 18pt Medium", Font.PLAIN, 15));
		entrys.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		entrys.setBorder(null);
		entrys.setBounds(21, 15, 100, 85);
		sideBar.add(entrys);
		
		meets = new RoundButton("Carnes", 10, 10);
		meets.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		meets.setHorizontalTextPosition(SwingConstants.CENTER);
		meets.setForeground(Color.WHITE);
		meets.setFont(new Font("Inter 18pt Medium", Font.PLAIN, 15));
		meets.setBorder(null);
		meets.setBackground(new Color(255, 59, 48));
		meets.setBounds(21, 115, 100, 85);
		sideBar.add(meets);
		
		fishes = new RoundButton("Pescados", 10, 10);
		fishes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fishes.setHorizontalTextPosition(SwingConstants.CENTER);
		fishes.setForeground(Color.WHITE);
		fishes.setFont(new Font("Inter 18pt Medium", Font.PLAIN, 15));
		fishes.setBorder(null);
		fishes.setBackground(new Color(0, 122, 255));
		fishes.setBounds(21, 215, 100, 85);
		sideBar.add(fishes);
		
		desserts = new RoundButton("Postres", 10, 10);
		desserts.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		desserts.setHorizontalTextPosition(SwingConstants.CENTER);
		desserts.setForeground(Color.WHITE);
		desserts.setFont(new Font("Inter 18pt Medium", Font.PLAIN, 15));
		desserts.setBorder(null);
		desserts.setBackground(new Color(255, 149, 0));
		desserts.setBounds(21, 315, 100, 85);
		sideBar.add(desserts);
		
		drinks = new RoundButton("Bebidas", 10, 10);
		drinks.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		drinks.setHorizontalTextPosition(SwingConstants.CENTER);
		drinks.setForeground(Color.WHITE);
		drinks.setFont(new Font("Inter 18pt Medium", Font.PLAIN, 15));
		drinks.setBorder(null);
		drinks.setBackground(new Color(0, 199, 190));
		drinks.setBounds(21, 415, 100, 85);
		sideBar.add(drinks);
		
		add = new RoundButton("Añadir\r\n", 10, 10);
		add.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add.setHorizontalTextPosition(SwingConstants.CENTER);
		add.setForeground(new Color(11, 47, 86));
		add.setFont(new Font("Inter 18pt Medium", Font.PLAIN, 15));
		add.setBorder(null);
		add.setBackground(new Color(255, 255, 255));
		add.setBounds(21, 515, 100, 85);
		sideBar.add(add);
		
		buttonsHover();
		
	}
	
	// Private class for buttons
	
	// External methods
	
	public void buttonsHover() {
		List<RoundButton> buttonGroup = new ArrayList();
		buttonGroup.add(entrys);
		buttonGroup.add(meets);
		buttonGroup.add(fishes);
		buttonGroup.add(desserts);
		buttonGroup.add(drinks);
		buttonGroup.add(add);
		
		for(RoundButton rb: buttonGroup) {
			rb.addMouseListener(new MouseAdapter() {
				
				Color originalColor = rb.getBackground();

				@Override
				public void mousePressed(MouseEvent e) {
					rb.setBackground(new Color(168, 168, 168));
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					rb.setBackground(originalColor);
				}

				
				
			});
		}
	}
}
