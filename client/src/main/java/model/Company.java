package model;

public class Company {

	private int id_entreprise;
	private String entreprise_name;
	private String entreprise_phone_number;
	private String entreprise_mail;
	public int getId_entreprise() {
		return id_entreprise;
	}
	public void setId_entreprise(int id_entreprise) {
		this.id_entreprise = id_entreprise;
	}
	public String getEntreprise_name() {
		return entreprise_name;
	}
	public void setEntreprise_name(String entreprise_name) {
		this.entreprise_name = entreprise_name;
	}
	public String getEntreprise_phone_number() {
		return entreprise_phone_number;
	}
	public void setEntreprise_phone_number(String entreprise_phone_number) {
		this.entreprise_phone_number = entreprise_phone_number;
	}
	public String getEntreprise_mail() {
		return entreprise_mail;
	}
	public void setEntreprise_mail(String entreprise_mail) {
		this.entreprise_mail = entreprise_mail;
	}
	public String toString() {
		return entreprise_name;
		
	}
	public void update(Company cp) {
		this.entreprise_mail=cp.getEntreprise_mail();
		this.entreprise_name=cp.getEntreprise_name();
		this.entreprise_phone_number=cp.getEntreprise_phone_number();
		this.id_entreprise=cp.getId_entreprise();
	}
	
}
