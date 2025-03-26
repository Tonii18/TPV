package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import models.Product;
import operations.Operaciones;
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
	private JScrollPane scrollPane;
	private JPanel productsPanel;
	private JPanel listProductsPanel;
	
	private static List<RoundButton> buttonGroup = new ArrayList();
	
	private static String filePath = "files/products.json";
	private static Gson gson = new Gson();
	private JTextField result;
	private RoundButton pay;
	private RoundButton clean;
	
	private static float totalPrice = 0.00F;

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
		setBounds(100, 100, 1300, 802);
		setLocationRelativeTo(null);
		setResizable(false);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/tpv.png"));
		setIconImage(icon.getImage());
		
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
		calculator.setBorder(null);
		calculator.setBackground(new Color(255, 255, 255));
		calculator.setBounds(10, 11, 439, 599);
		calculatorBorder.add(calculator);
		calculator.setLayout(null);
		
		result = new JTextField();
		result.setBounds(10, 302, 419, 50);
		result.setFocusable(false);
		result.setHorizontalAlignment(SwingConstants.RIGHT);
		result.setFont(new Font("Inter 28pt ExtraBold", Font.PLAIN, 30));
		result.setBorder(new LineBorder(new Color(155, 155, 155)));
		result.setText(String.valueOf(totalPrice));
		calculator.add(result);
		result.setColumns(10);
		
		pay = new RoundButton("Pagar", 10, 10);
		pay.setBounds(10, 363, 207, 35);
		pay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pay.setForeground(new Color(255, 255, 255));
		pay.setFont(new Font("Inter 24pt Black", Font.PLAIN, 20));
		pay.setBackground(new Color(26, 205, 80));
		pay.setBorder(null);
		calculator.add(pay);
		
		clean = new RoundButton("Limpiar", 10, 10);
		clean.setBounds(222, 363, 207, 35);
		clean.setForeground(new Color(255, 255, 255));
		clean.setFont(new Font("Inter 24pt Black", Font.PLAIN, 20));
		clean.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		clean.setBackground(new Color(255, 59, 48));
		clean.setBorder(null);
		calculator.add(clean);
		
		JScrollPane listViewer = new JScrollPane();
		listViewer.setBounds(10, 11, 419, 280);
		listViewer.setBorder(null);
		listViewer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		listViewer.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		calculator.add(listViewer);
		
		listProductsPanel = new JPanel();
		listProductsPanel.setBackground(new Color(255, 255, 255));
		listProductsPanel.setLayout(new BoxLayout(listProductsPanel, BoxLayout.Y_AXIS)); // Vertical layout
		listViewer.setViewportView(listProductsPanel);  // Attach products panel to scroll
		
		/*
		 * Code for the calculator
		 */
		
		CalculatorPanel keyboard = new CalculatorPanel(this);
		keyboard.setBounds(10, 409, 419, 179);
		calculator.add(keyboard);
		keyboard.setLayout(null);
		
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
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(153, 11, 562, 599);
		menu.add(scrollPane);
		
		productsPanel = new JPanel();
		productsPanel.setLayout(new BoxLayout(productsPanel, BoxLayout.Y_AXIS)); // Vertical layout
		scrollPane.setViewportView(productsPanel);  // Attach products panel to scroll
		
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddProduct adding = new AddProduct();
				adding.setVisible(true);
			}
			
		});
		
		buttonsHover();
		
		for(RoundButton rb: buttonGroup) {
			rb.addActionListener(new ActionListener() {
				
				Color bck = rb.getBackground();
				
				@Override
				public void actionPerformed(ActionEvent e) {
					showProducts(rb.getText(), bck);
				}
				
			});
		}
		
		clean.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				totalPrice = 0.00F;
				result.setText(String.format("%.2f", totalPrice));
				listProductsPanel.removeAll();

				listProductsPanel.setPreferredSize(new Dimension(listProductsPanel.getWidth(), 0));

				listProductsPanel.revalidate();
				listProductsPanel.repaint();
			}

		});
		
		pay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Has realizado el pago correctamente!", "Pago exitoso", JOptionPane.INFORMATION_MESSAGE, getIcon("/comprobado.png", 40, 40));
				totalPrice = 0.00F;
				result.setText(String.format("%.2f", totalPrice));
				listProductsPanel.removeAll();

				listProductsPanel.setPreferredSize(new Dimension(listProductsPanel.getWidth(), 0));

				listProductsPanel.revalidate();
				listProductsPanel.repaint();
			}

		});
		
		
	}
	
	// Private class for buttons
	
	// External methods
	
	public Icon getIcon(String path, int w, int h) {
		return new ImageIcon(new ImageIcon(getClass().getResource(path)).getImage().getScaledInstance(w, h, 0));
	}
	
	public void showProducts(String section, Color backgorund) {
		String content = Operaciones.readFile(filePath);
		List<Product> newList;
		if(content == null) {
			newList = new ArrayList<>();
		}else {
			newList = gson.fromJson(content, new TypeToken<List<Product>>() {}.getType());
			if (newList == null) {
				newList = new ArrayList<>();
		    }
		}
		
		productsPanel.removeAll();
		productsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Espaciado entre productos
		
		listProductsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
	    
		for(Product p: newList) {
			if(p.getType().equalsIgnoreCase(section)) {
				ProductPanel pp = new ProductPanel(p.getName());
				pp.setBackground(backgorund);
				productsPanel.add(pp);
				
				pp.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						totalPrice += p.getPrice();
				        result.setText(String.format("%.2f", totalPrice));

				        ListProductPanel lpp = new ListProductPanel(p.getName(), p.getPrice());
				        listProductsPanel.add(lpp);

				        listProductsPanel.setPreferredSize(new Dimension(listProductsPanel.getWidth(), listProductsPanel.getComponentCount() * 90));

				        listProductsPanel.revalidate();
				        listProductsPanel.repaint();
					}
					
				});
			}
		}
		
		int panelHeight = Math.max(newList.size() * 90, scrollPane.getHeight());
		productsPanel.setPreferredSize(new Dimension(scrollPane.getWidth() - 20, panelHeight));
		
		productsPanel.revalidate();
		productsPanel.repaint();
	}
	
	public void buttonsHover() {
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

	public static float getTotalPrice() {
		return totalPrice;
	}

	public static void setTotalPrice(float totalPrice) {
		Main.totalPrice = totalPrice;
	}

	public JTextField getResult() {
		return result;
	}

	public void setResult(JTextField result) {
		this.result = result;
	}

	public JPanel getListProductsPanel() {
		return listProductsPanel;
	}

	public void setListProductsPanel(JPanel listProductsPanel) {
		this.listProductsPanel = listProductsPanel;
	}
	
	public JScrollPane getListViewer() {
	    return scrollPane; // Este es el JScrollPane que contiene `listProductsPanel`
	}
	
}
