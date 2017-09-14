package kvuong_EcoRe;

//Updates: Added constructor
public class Item {
	//itemType represents the type of recyclable...ie. "plastic bottle" or "aluminum can"
	//Assume each Item of itemType "plastic bottle" has a fixed weight and fixed price (for simplicity)
	private String itemType;
	private double itemWeight;
	private double price;
	

	public Item(String itemType, double itemWeight, double price){
		this.itemType = itemType;
		this.itemWeight = itemWeight;
		this.price = price;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public double getItemWeight() {
		return itemWeight;
	}
	public void setItemWeight(double itemWeight) {
		this.itemWeight = itemWeight;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public boolean equals(Item item){
		return(this.itemType == item.itemType);
	}
}
