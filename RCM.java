package kvuong_EcoRe;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

/*Changes so far
 * Added ItemTypesAccepted
 * Added a bunch of private variables
 * Added getters and add/remove functions for ArrayList items
 * Added locate Item function
 */

public class RCM {
	// RCM information
	private int rcmID;
	private String location;
	private ArrayList<Item> items; //List of accepted Items in this RCM
	private int currentItemCount = 0; //Number of current items
	private int itemsInserted = 0; //Total items inserted into this machine -> might be useful for parts of the GUI and RMOS
	private double currWeight = 0.0; // Initial money of RCM: $100, initial weight of RCM: 0lbs
	private double monthlyMoney = 0.0;
	private double monthlyWeight = 0.0;
	private int sessCoupons = 0;
	private double sessWeight = 0.0;
	private double sessMoney = 0.0;
	private double totalMoney = 100.0; 
	private boolean activeRCM = false;
	private static final double CAPACITY = 100; //Total weight RCMs can hold is 100lbs
	private Date lastEmptied; // Time of last removal
	private int timesEmptied;
	
	//getters and setters
	public int getrcmID() { return rcmID; }
	public void setrcmID(int rcmID) { this.rcmID = rcmID; }
	
	public String getLocation() { return location; }
	public void setLocation(String location) { this.location = location; }
	
	public ArrayList<Item> getItems(){ return items;}	//returns items arrayList
	public Item getItem(int index){return items.get(index);}	//returns item at index
	public void addItem(Item newItem){ this.items.add(newItem);}
	public void removeItem(Item newItem){ this.items.remove(newItem);}
	
	public int getCurrentItemCount(){ return currentItemCount;}
	public void setCurrentItemCount(int count){ this.currentItemCount = count;}
	
	public int getItemsInserted(){ return itemsInserted;}
	public void setItemsInserted(int itemsInserted){ this.itemsInserted = itemsInserted;}
	
	public double getcurrWeight() { return currWeight;}
	public void setcurrWeight(double currWeight) { this.currWeight = currWeight;}
	
	public int getSessCoupons() {return sessCoupons;}
	public void incrementCoupon() {sessCoupons++;}
	
	public double getSessWeight() {return sessWeight;}
	public void addtoSessWeight(double Weight) {sessWeight += Weight;}
	
	public double getSessMoney() {return sessMoney;}
	public void addtoSessMoney(double Money) {sessMoney += Money;}
	
	public double getTotalMoney(){ return totalMoney;}
	public void setTotalMoney(double money){ this.totalMoney = money;}
	
	public Date getLastEmptied(){ return lastEmptied;}
	public void setLastEmptied(Date time){ this.lastEmptied = time;}
	
	public double getCapacity() {return CAPACITY;}
	
	public int getTimesEmptied() {return timesEmptied;}
	
	//Constructor -> initializes ID and location
	public RCM(int rcmID, String location){
		this.rcmID = rcmID;
		this.location = location;
		this.items = new ArrayList<Item>();
		this.activeRCM = true;
	}
	
	//create string array of accepted item types
	public String[] itemTypesAccepted(){
		String[] itemTypesAccepted = new String[items.size()];
		
		for(int i = 0; i < items.size(); i++){
			itemTypesAccepted[i] = items.get(i).getItemType();
		}
		
		return itemTypesAccepted;
	}
	
	//empties the machine and records the date of emptying
	public void empty(){
		currWeight = 0.0;
		currentItemCount = 0;
		lastEmptied = new Date();
		timesEmptied++;
	}
	
	//updates the current money in the system. if true the user 
	//gets money. if false the user will get a coupon
	public boolean updateMoney(double moneyGiven){
		if(totalMoney - moneyGiven < 0){
			//display error message??
			return false;
		}
		totalMoney -= moneyGiven;
		return true;
	}
	
	//Restocks the RCM back to 100 dollars
	public void restockMoney(){
		totalMoney = 100.0;
	}
	
	//checks to see if Item is correctly inserted into the machine
	//If true then the item was inserted correctly and the appropriate type, weight, and price are shown
	//If false then the item did not get inserted and an error is displayed
	public boolean insertItem(Item recyclable){
		//If capacity is exceeded RCM does not allow insertion
		if(currWeight + recyclable.getItemWeight() > CAPACITY){
			//display error message??
			return false;
		}
		
		//Go through all accepted items in ArrayList items
		//If one matches the argument recyclable it is an accepted item
		//Insert the item -> updates currWeight and totalMoney
		for(int i = 0; i < items.size(); i++){
			BufferedWriter bw = null;
			FileWriter fw = null;
			if(recyclable.getItemType() == items.get(i).getItemType()){
				currWeight += items.get(i).getItemWeight();
				boolean moneyTest = updateMoney(items.get(i).getPrice()); //GUI will have to distinguish whether money or coupon is returned
				itemsInserted++;
				currentItemCount++;
				this.addtoSessWeight(items.get(i).getItemWeight());
				if(moneyTest == true){
					this.addtoSessMoney(items.get(i).getPrice());
				}
				try{
					String path = "C:" + File.separator + "Users" + File.separator + "Kevin Vuong" + File.separator + "coen160" + File.separator + "COEN160Project" + File.separator + "src" + File.separator + "dateItemCount" + this.rcmID + ".txt"; 
					File file = new File(path);
					fw = new FileWriter(file.getAbsoluteFile(), true);
					bw = new BufferedWriter(fw);
					bw.write((LocalDateTime.now().getMonth().toString()));
					bw.write(":");
					bw.close();
					fw.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
				return true;
			}
		}
		return false;
	}
	
	public int locateItem(String itemType){
		int index = -1;
		for(int i = 0; i < items.size(); i++){
			if(items.get(i).getItemType() == itemType){
				index = i;
			}
		}
		return index;
	}
	
	public void resetSession(){
		this.sessCoupons = 0;
		this.sessWeight = 0.0;
		this.sessMoney = 0.0;
	}
	
	public boolean itemSearch(Item item){
		boolean found = false;
		for(int i = 0; i < items.size(); i++){
			if(items.get(i).equals(item))
				found = true;
		}
		return found;
	}
	
	public static void main(String[] args){
		Item glass = new Item("Glass Bottle", 1.0, 101.00);
		Item plastic = new Item("Plastic Cup", 10.5, 2.00);
		Item aluminum = new Item("Aluminum Can", 20.0, 3.00);
		Item paper = new Item("Newspaper", 0.5, 1.00);
		RCM rcm1 = new RCM(1, "Dallas");
		rcm1.addItem(glass);
		rcm1.addItem(plastic);
		rcm1.addItem(aluminum);
		rcm1.addItem(paper);
		rcm1.empty();
		rcm1.insertItem(glass);
		rcm1.insertItem(plastic);
		rcm1.insertItem(aluminum);
		rcm1.insertItem(paper);
	}
}
