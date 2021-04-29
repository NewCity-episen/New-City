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
	
	public Offer(int off_id) {
		this.offer_id = off_id;
	}
	
	
	public int getOffer_id() {
		return offer_id;
	}

	public void setOffer_id(int offer_id) {
		this.offer_id = offer_id;
	}

	public String getOffer_type() {
		return offer_type;
	}

	public void setOffer_type(String offer_type) {
		this.offer_type = offer_type;
	}

	public String getOffer_name() {
		return offer_name;
	}

	public void setOffer_name(String offer_name) {
		this.offer_name = offer_name;
	}

	public int getOffer_floor() {
		return offer_floor;
	}

	public void setOffer_floor(int offer_floor) {
		this.offer_floor = offer_floor;
	}

	public int getOffer_building() {
		return offer_building;
	}

	public void setOffer_building(int offer_building) {
		this.offer_building = offer_building;
	}

	public int getOffer_price() {
		return offer_price;
	}

	public void setOffer_price(int offer_price) {
		this.offer_price = offer_price;
	}

	public int getOffer_area() {
		return offer_area;
	}

	public void setOffer_area(int offer_area) {
		this.offer_area = offer_area;
	}
}
