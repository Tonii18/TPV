package models;

public class Product {
	
	private String type;
	private String name;
	private float price;
	
	public Product() {
		
	}

	public Product(String type, String name, float price) {
		super();
		this.type = type;
		this.name = name;
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [type=" + type + ", name=" + name + ", price=" + price + "]";
	}
	
	

}
