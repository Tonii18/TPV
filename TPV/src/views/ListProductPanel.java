package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import roundedComponents.RoundButtonImage;
import roundedComponents.RoundPanel;

public class ListProductPanel extends RoundPanel {
	
	private float price;

	/**
	 * Create the panel.
	 */
	public ListProductPanel(String name, float price) {
		super(10, 10);
		this.price = price;
		setBackground(new Color(228, 228, 228));
		setBorder(new LineBorder(Color.BLACK, 1));
		setPreferredSize(new Dimension(390, 80)); // Fixed size for scrolling
		
        setLayout(null);
		
		JLabel product = new JLabel("New label");
		product.setFont(new Font("Inter 18pt Medium", Font.PLAIN, 20));
		product.setHorizontalAlignment(SwingConstants.LEFT);
		product.setBounds(10, 16, 310, 48);
		product.setText(name);
		add(product);
		
		RoundButtonImage delete = new RoundButtonImage("", 10, 10);
		delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		delete.setBackground(new Color(255, 108, 100));
		delete.setBorder(null);
		delete.setBounds(330, 16, 50, 48);
		delete.setIcon(new ImageIcon(getClass().getResource("/delete.png")));
		add(delete);
		
		delete.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (getParent() != null) {
		            Main mainFrame = (Main) getTopLevelAncestor();
		            if (mainFrame != null) {
		                // Eliminar este producto del panel de lista
		                mainFrame.getListProductsPanel().remove(ListProductPanel.this);

		                // Recalcular el precio total sumando los precios de los productos restantes
		                float newTotal = 0.00F;
		                for (java.awt.Component comp : mainFrame.getListProductsPanel().getComponents()) {
		                    if (comp instanceof ListProductPanel) {
		                        newTotal += ((ListProductPanel) comp).price;
		                    }
		                }
		                Main.setTotalPrice(newTotal);
		                mainFrame.getResult().setText(String.format("%.2f", newTotal));

		                // Ajustar el tamaño del panel
		                mainFrame.getListProductsPanel().setPreferredSize(new Dimension(
		                        mainFrame.getListProductsPanel().getWidth(),
		                        mainFrame.getListProductsPanel().getComponentCount() * 90));

		                mainFrame.getListProductsPanel().revalidate();
		                mainFrame.getListProductsPanel().repaint();
		                mainFrame.getListViewer().revalidate();
		                mainFrame.getListViewer().repaint();
		            }
		        }
		    }
		});


	}
	
}
