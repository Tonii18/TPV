package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import roundedComponents.RoundButtonImage;
import roundedComponents.RoundPanel;
import java.awt.Cursor;

public class ListProductPanel extends RoundPanel {

	/**
	 * Create the panel.
	 */
	public ListProductPanel(String name) {
		super(10, 10);
		setBackground(new Color(228, 228, 228));
		setBorder(new LineBorder(Color.BLACK, 1));
		setPreferredSize(new Dimension(390, 80)); // Fixed size for scrolling
		
        setLayout(null);
		
		JLabel product = new JLabel("New label");
		product.setFont(new Font("Inter 18pt Medium", Font.PLAIN, 20));
		product.setHorizontalAlignment(SwingConstants.LEFT);
		product.setBounds(10, 16, 360, 48);
		product.setText(name);
		add(product);
		
		RoundButtonImage delete = new RoundButtonImage("", 10, 10);
		delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		delete.setBackground(new Color(255, 108, 100));
		delete.setBorder(null);
		delete.setBounds(330, 16, 50, 48);
		delete.setIcon(new ImageIcon(getClass().getResource("/delete.png")));
		add(delete);
	}
}
