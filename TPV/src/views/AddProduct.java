package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import models.Product;
import operations.Operaciones;
import roundedComponents.RoundButton;
import roundedComponents.RoundPanel;
import roundedComponents.RoundTextField;

public class AddProduct extends JFrame {

	private JPanel contentPane;
	
	private RoundButton add;
	private RoundTextField type;
	private RoundTextField name;
	private RoundTextField price;
	
	private static List<Product> list;
	private static Gson gson = new Gson();
	private static String filePath = "files/products.json";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddProduct frame = new AddProduct();
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
	public AddProduct() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setFont(new Font("Inter 18pt Medium", Font.PLAIN, 15));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String content = Operaciones.readFile(filePath);
		list = gson.fromJson(content, new TypeToken<List<Product>>() {}.getType());
		
		if(list == null) {
			list = new ArrayList<>();
		}
		
		add = new RoundButton("Añadir producto", 10, 10);
		add.setBackground(new Color(11, 47, 86));
		add.setForeground(new Color(255, 255, 255));
		add.setFont(new Font("Inter 28pt Black", Font.PLAIN, 20));
		add.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add.setBorder(null);
		add.setBounds(38, 500, 324, 53);
		contentPane.add(add);
		
		type = new RoundTextField(10, 10, 10);
		type.setHorizontalAlignment(SwingConstants.CENTER);
		type.setForeground(new Color(11, 47, 86));
		type.setFont(new Font("Inter 24pt Medium", Font.PLAIN, 20));
		type.setBorder(null);
		type.setCustomBorderColor(new Color(11, 47, 86));
		type.setBounds(35, 83, 324, 43);
		contentPane.add(type);
		type.setColumns(10);
		
		name = new RoundTextField(10, 10, 10);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setForeground(new Color(11, 47, 86));
		name.setFont(new Font("Inter 24pt Medium", Font.PLAIN, 20));
		name.setCustomBorderColor(new Color(11, 47, 86));
		name.setColumns(10);
		name.setBorder(null);
		name.setBounds(35, 209, 324, 43);
		contentPane.add(name);
		
		price = new RoundTextField(10, 10, 10);
		price.setHorizontalAlignment(SwingConstants.CENTER);
		price.setForeground(new Color(11, 47, 86));
		price.setFont(new Font("Inter 24pt Medium", Font.PLAIN, 20));
		price.setCustomBorderColor(new Color(11, 47, 86));
		price.setColumns(10);
		price.setBorder(null);
		price.setBounds(35, 335, 324, 43);
		contentPane.add(price);
		
		JLabel typeLbl = new JLabel("Tipo de producto");
		typeLbl.setBackground(new Color(11, 47, 86));
		typeLbl.setForeground(new Color(11, 47, 86));
		typeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		typeLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		typeLbl.setFont(new Font("Inter 24pt ExtraBold", Font.PLAIN, 15));
		typeLbl.setBounds(38, 53, 136, 19);
		contentPane.add(typeLbl);
		
		JLabel nameLbl = new JLabel("Nombre");
		nameLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		nameLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nameLbl.setForeground(new Color(11, 47, 86));
		nameLbl.setFont(new Font("Inter 24pt ExtraBold", Font.PLAIN, 15));
		nameLbl.setBounds(35, 179, 71, 19);
		contentPane.add(nameLbl);
		
		JLabel priceLbl = new JLabel("Precio");
		priceLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		priceLbl.setHorizontalAlignment(SwingConstants.CENTER);
		priceLbl.setForeground(new Color(11, 47, 86));
		priceLbl.setFont(new Font("Inter 24pt ExtraBold", Font.PLAIN, 15));
		priceLbl.setBounds(38, 305, 60, 19);
		contentPane.add(priceLbl);
		
		JButton close = new JButton("");
		close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		close.setFocusable(false);
		close.setContentAreaFilled(false);
		close.setBorderPainted(false);
		close.setOpaque(false);
		close.setBorder(null);
		close.setBounds(350, 11, 40, 40);
		close.setIcon(new ImageIcon(getClass().getResource("/close.png")));
		contentPane.add(close);
		
		// ActionListener to close the window
		
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});
		
		// ActionListener to save the product
		
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addProduct();
			}
			
		});
	}
	
	// External methods
	
	public void addProduct() {
		String typeProduct = type.getText();
		String nameProduct = name.getText();
		float priceProduct = Float.parseFloat(price.getText());
		
		Product p = new Product(typeProduct, nameProduct, priceProduct);
		list.add(p);
		
		String content = gson.toJson(list);
		Operaciones.writeFile(content, filePath);
		
		JOptionPane.showMessageDialog(null, "Producto añadido correctamente");
		dispose();
	}
}
