package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import roundedComponents.RoundButton;
import java.awt.Cursor;

public class ProductPanel extends RoundButton {

	/**
	 * Create the panel.
	 */
	public ProductPanel(String productName) {
		super("", 10, 10);
		setPreferredSize(new Dimension(450, 80)); // Fixed size for scrolling
        setLayout(null);
		
		JLabel name = new JLabel("New label");
		name.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		name.setForeground(new Color(255, 255, 255));
		name.setFont(new Font("Inter 24pt Medium", Font.PLAIN, 20));
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setBounds(10, 23, 430, 34);
		name.setText(productName);
		add(name);
		
	}
}
