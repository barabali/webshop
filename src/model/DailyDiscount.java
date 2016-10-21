package model;

public enum DailyDiscount {
	
	Monday,Thuesday,Wednesday,Thursday,Friday,Saturday,Sunday;
	
	private int discount;
	
	public void setDiscount(int d){
		this.discount=d;
	}

	public int getDiscount() {
		return discount;
	}

}
