package model;

public class Offer {
	
	private int offer_id;
	private String offer_type;
	private String offer_name;
	private int offer_floor;
	private int offer_building;
	private int offer_price;
	private int offer_area;
	
	public Offer(int off_id, String off_ty, String off_na, int off_fl, int off_bui, int off_pr,  int off_ar) {
		this.offer_id = off_id;
		this.offer_type = off_ty;
		this.offer_name = off_na;
		this.offer_floor = off_fl;
		this.offer_building = off_bui;
		this.offer_price = off_pr;
		this.offer_area = off_ar;
	}
	
	public Offer(int off_id, String off_na) {
		this.offer_id = off_id;
		this.offer_name = off_na;
	}
	
	public int getOfferId() {
		return offer_id;
	}

	public void setOfferId(int offer_id) {
		this.offer_id = offer_id;
	}

	public String getOfferType() {
		return offer_type;
	}

	public void setOfferType(String offer_type) {
		this.offer_type = offer_type;
	}

	public String getOfferName() {
		return offer_name;
	}

	public void setOfferName(String offer_name) {
		this.offer_name = offer_name;
	}

	public int getOfferFloor() {
		return offer_floor;
	}

	public void setOfferFloor(int offer_floor) {
		this.offer_floor = offer_floor;
	}

	public int getOfferBuilding() {
		return offer_building;
	}

	public void setOfferBuilding(int offer_building) {
		this.offer_building = offer_building;
	}

	public int getOfferPrice() {
		return offer_price;
	}

	public void setOfferPrice(int offer_price) {
		this.offer_price = offer_price;
	}

	public int getOfferArea() {
		return offer_area;
	}

	public void setOfferArea(int offer_area) {
		this.offer_area = offer_area;
	}
}
