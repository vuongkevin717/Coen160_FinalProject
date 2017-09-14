package kvuong_EcoRe;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class RMOS {
	public RMOS(){
	}
	//Admin credentials
	private String username = "admin";
	private String password = "pw";
	
	private int totalMachines = 0;
	private ArrayList<RCM> RCMDirectory = new ArrayList<RCM>();
	
	/*
	 *  *****************ADMIN LOGIN*******************
	 */
	public boolean login(String username, String password){
		boolean attempt = false;
		if((this.username.equals(username)) && (this.password.equals(password))){
			attempt = true;
		}
		return attempt;
	}
	
	/*
	 *  ***************RCM MANIPULATION*******************
	 */
	public String[] rcmIDs(){
		String[] rcmIDs = new String[RCMDirectory.size()];
		for(int i = 0; i < RCMDirectory.size(); i++){
			rcmIDs[i] = Integer.toString(RCMDirectory.get(i).getrcmID());
		}
		return rcmIDs;
	}
	
	//returns rcmID of the busiestRCM -> GUI will display ID and location of this machine
	public int busiestRCM(){
		int temp = 0;
		int index = 0;
		for(int i = 0; i < RCMDirectory.size(); i++){
			if(RCMDirectory.get(i).getItemsInserted() > temp){
				temp = RCMDirectory.get(i).getItemsInserted();
				index = i;
			}
		}
		return index;
	}
	
	//Change the price of an item
	public boolean changePrice(int rcmID, String itemType, double newPrice){
		int index = RCMDirectory.get(locateRCM(rcmID)).locateItem(itemType);
		if(index != -1){
			RCMDirectory.get(locateRCM(rcmID)).getItem(index).setPrice(newPrice);
			return true;
		}
		return false;
	}
	
	//Check amount of money in a specific RCM
	public double checkMoney(int rcmID){
		return(RCMDirectory.get(locateRCM(rcmID)).getTotalMoney());
	}
	
	//Check amount of weight in a current RCM -> GUI will determine if it is close to full and needs to be emptied
	public double checkWeight(int rcmID){
		return(RCMDirectory.get(locateRCM(rcmID)).getcurrWeight());
	}
	
	//Convert from lbs to Kilograms -> returns a String -> only use for display so its fine
	public String lbsToKilograms(int rcmID){
		double conversionFactor = 2.2046226218;
		double weight = RCMDirectory.get(locateRCM(rcmID)).getcurrWeight();
		double temp = (weight / conversionFactor);
		DecimalFormat df = new DecimalFormat("0.00");
		return(df.format(temp));
	}
	
	public void restockMoney(int rcmID){
		RCMDirectory.get(locateRCM(rcmID)).restockMoney();
	}
	
	public void empty(int rcmID){
		RCMDirectory.get(locateRCM(rcmID)).empty();
	}
	
	public int itemsReturnedMonth(int rcmID, String month){
		int count = 0;
		String path = "C:" + File.separator + "Users" + File.separator + "Kevin Vuong" + File.separator + "coen160" + File.separator + "COEN160Project" + File.separator + "src" + File.separator + "dateItemCount" + rcmID + ".txt"; 
		File file = new File(path);
		try{	
			Scanner inFile = new Scanner(file);
			if(inFile.hasNextLine()) {
				String currentLine = inFile.nextLine();
				String[] tokens = currentLine.split("[:]+");
				for(int i = 0; i < tokens.length; i++){
					if(tokens[i].equals(month))
						count++;
				}
			}
			inFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int itemsReturnedMonth(String month){
		int count = 0;
		for(int i = 1; i <= totalMachines; i++){
			String path = "C:" + File.separator + "Users" + File.separator + "Kevin Vuong" + File.separator + "coen160" + File.separator + "COEN160Project" + File.separator + "src" + File.separator + "dateItemCount" + i + ".txt"; 
			File file = new File(path);
			try{	
				Scanner inFile = new Scanner(file);
				if(inFile.hasNextLine()) {
					String currentLine = inFile.nextLine();
					String[] tokens = currentLine.split("[:]+");
					for(int j = 0; j < tokens.length; j++){
						if(tokens[j].equals(month))
							count++;
					}
				}
				inFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public Date lastTimeEmptied(int rcmID){
		return(RCMDirectory.get(locateRCM(rcmID)).getLastEmptied());
	}
	
	public String[] itemTypes(int rcmID){
		return(RCMDirectory.get(locateRCM(rcmID)).itemTypesAccepted());
	}
	
	public ArrayList<RCM> getRCMDirectory(){
		return RCMDirectory;
	}
	
	//finds the ArrayList index given the rcmID. if -1 is returned then the rcmID is invalid
	public int locateRCM(int rcmID){
		int index = -1;
		for(int i = 0; i < RCMDirectory.size(); i++){
			if(RCMDirectory.get(i).getrcmID() == rcmID){
				index = i;
			}
		}
		return index;
	}
	
	public void addRCM(String location){
		RCM rcm = new RCM(++totalMachines, location);
		RCMDirectory.add(rcm);
		try{
			//necessary because if a new RCM is created we need to create a new file to keep track of its inserts
			String path = "C:" + File.separator + "Users" + File.separator + "Kevin Vuong" + File.separator + "coen160" + File.separator + "COEN160Project" + File.separator + "src" + File.separator + "dateItemCount" + totalMachines + ".txt"; 
			File file = new File(path);
			boolean fvar = file.createNewFile();
			if(!fvar){	//if the file exists already then erase the current contents -> file will exist once the RCM is created
				PrintWriter writer = new PrintWriter(file);
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void removeRCM(int rcmID){
		if(!RCMDirectory.isEmpty()){
			RCM temp = null;
			for(RCM rcm : RCMDirectory){
				if(rcm.getrcmID() == rcmID){
					temp = rcm;
				}
			}
			if(temp != null){
				RCMDirectory.remove(temp);
				totalMachines--;
			}
		}
	}
	
	public String displayRCM(){
		String allRCM = "";
		for (RCM rcm : RCMDirectory){
			allRCM += "Machine ID: " + rcm.getrcmID() + " Location: " + rcm.getLocation() + " \n";
		}
		return allRCM;
	}
	
	/*
	 *  ***************ITEM MANIPULATION*******************
	 */	
	//Adds an accepted item to the specified RCM
	public boolean addAcceptedItem(int rcmID, Item newItem){
		if(RCMDirectory.get(this.locateRCM(rcmID)).itemSearch(newItem)){
			return false;
		}	//Display error message in GUI -> "Item already included"
		RCMDirectory.get(this.locateRCM(rcmID)).addItem(newItem);
		return true;
	}
	
	//Removes an accepted item from the specified RCM
	public boolean removeAcceptedItem(int rcmID, Item deletedItem){
		if(RCMDirectory.get(this.locateRCM(rcmID)).itemSearch(deletedItem)){
			RCMDirectory.get(this.locateRCM(rcmID)).removeItem(deletedItem);
			return true;
		}
		return false; //Error: "Item not found!"
	}
	
	public int getTotalMachines(){
		return totalMachines;
	}
	
	public static void main(String[] args){
		RMOS rmos = new RMOS();
		Item glass = new Item("Glass Bottle", 1.0, 101.00);
		Item plastic = new Item("Plastic Cup", 10.5, 2.00);
		Item aluminum = new Item("Aluminum Can", 20.0, 3.00);
		Item paper = new Item("Newspaper", 0.5, 1.00);
		rmos.addRCM("Dallas");
		rmos.addRCM("Santa Clara");
		rmos.RCMDirectory.get(0).addItem(glass);
		rmos.RCMDirectory.get(0).addItem(plastic);
		rmos.RCMDirectory.get(0).insertItem(glass);
		rmos.RCMDirectory.get(0).insertItem(glass);
		rmos.RCMDirectory.get(0).insertItem(glass);
		rmos.RCMDirectory.get(1).addItem(glass);
		rmos.RCMDirectory.get(1).addItem(plastic);
		rmos.RCMDirectory.get(1).insertItem(glass);
		rmos.RCMDirectory.get(1).insertItem(glass);
		rmos.RCMDirectory.get(1).insertItem(glass);
		System.out.println(rmos.itemsReturnedMonth(1, "MARCH"));
		System.out.println(rmos.itemsReturnedMonth("MARCH"));
	}
}
