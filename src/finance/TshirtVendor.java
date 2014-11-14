package finance;

import java.util.ArrayList;

import events.eventutils.Constants;
import participatingbody.participants.HostelRep;


public class TshirtVendor {
	
	private static long costPrice = Constants.TSHIRT_CP;
	private static long sellingPrice;
	
	public static long getCP(){
		return costPrice;
	}
	
	public static void setSP(long sellingPrice){
		TshirtVendor.sellingPrice = sellingPrice;
	}
	
	public static long getSP(){
		return sellingPrice;
	}
	
	public void getGoodies(){
		
	}
	
	public void takePayments(){
		
	}
	
	public void supplyTshirts(ArrayList<HostelRep> hostelReps){
		if(hostelReps != null){
			for(HostelRep hostelRep : hostelReps){
				hostelRep.distributeTees();
			}
		}
	}
}
