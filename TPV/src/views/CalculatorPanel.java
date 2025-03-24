package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import roundedComponents.RoundButton;
import roundedComponents.RoundButtonImage;

public class CalculatorPanel extends JPanel {
	
	private RoundButton equals;
	private RoundButtonImage erase;
	
	private RoundButton one;
	private RoundButton two;
	private RoundButton three;
	private RoundButton four;
	private RoundButton five;
	private RoundButton six;
	private RoundButton seven;
	private RoundButton eight;
	private RoundButton nine;
	private RoundButton zero;
	private RoundButton sum;
	private RoundButton minus;
	private RoundButton mult;
	private RoundButton div;
	private RoundButton comma;
	private RoundButton percent;
	
	private static List<RoundButton> list = new ArrayList<>();

	/**
	 * Create the panel.
	 */
	public CalculatorPanel() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);

		equals = new RoundButton("", 10, 10);
		equals.setForeground(new Color(255, 255, 255));
		equals.setFont(new Font("Inter 28pt ExtraBold", Font.PLAIN, 20));
		equals.setText("=");
		equals.setBackground(new Color(0, 122, 255));
		equals.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		equals.setBounds(354, 9, 55, 75);
		add(equals);

		erase = new RoundButtonImage("", 10, 10);
		erase.setBackground(new Color(255, 166, 0));
		erase.setBounds(354, 93, 55, 75);
		erase.setIcon(new ImageIcon(getClass().getResource("/eraser.png")));
		erase.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(erase);

		one = new RoundButton("1", 10, 10);
		one.setBounds(10, 7, 75, 35);
		add(one);

		two = new RoundButton("2", 10, 10);
		two.setBounds(95, 7, 75, 35);
		add(two);

		three = new RoundButton("3", 10, 10);
		three.setBounds(180, 7, 75, 35);
		add(three);

		sum = new RoundButton("+", 10, 10);
		sum.setBounds(265, 7, 75, 35);
		add(sum);

		four = new RoundButton("4", 10, 10);
		four.setBounds(10, 49, 75, 35);
		add(four);

		five = new RoundButton("5", 10, 10);
		five.setBounds(95, 49, 75, 35);
		add(five);

		six = new RoundButton("6", 10, 10);
		six.setBounds(180, 49, 75, 35);
		add(six);

		minus = new RoundButton("-", 10, 10);
		minus.setBounds(265, 49, 75, 35);
		add(minus);

		seven = new RoundButton("7", 10, 10);
		seven.setBounds(10, 91, 75, 35);
		add(seven);

		eight = new RoundButton("8", 10, 10);
		eight.setBounds(95, 91, 75, 35);
		add(eight);

		nine = new RoundButton("9", 10, 10);
		nine.setBounds(180, 91, 75, 35);
		add(nine);

		mult = new RoundButton("X", 10, 10);
		mult.setBounds(265, 91, 75, 35);
		add(mult);

		percent = new RoundButton("%", 10, 10);
		percent.setBounds(10, 133, 75, 35);
		add(percent);

		zero = new RoundButton("0", 10, 10);
		zero.setBounds(95, 133, 75, 35);
		add(zero);

		comma = new RoundButton(",", 10, 10);
		comma.setBounds(180, 133, 75, 35);
		add(comma);

		div = new RoundButton("/", 10, 10);
		div.setBounds(265, 133, 75, 35);
		add(div);
		
		addButtons();
		configurations();
		

	}
	
	public void configurations() {
		for(RoundButton rb: list) {
			rb.setCustomBorderColor(new Color(166, 166, 166));
			rb.setBackground(new Color(217, 217, 217));
			rb.setFont(new Font("Inter 24pt ExtraBold", Font.PLAIN, 20));
			rb.setForeground(Color.BLACK);
			rb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
	}
	
	public void addButtons() {
		list.add(one);
		list.add(two);
		list.add(three);
		list.add(four);
		list.add(five);
		list.add(six);
		list.add(seven);
		list.add(eight);
		list.add(nine);
		list.add(zero);
		list.add(comma);
		list.add(percent);
		list.add(sum);
		list.add(minus);
		list.add(mult);
		list.add(div);
	}
}
