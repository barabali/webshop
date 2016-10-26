package model;

public class Category {

	private Long id;
	private String name;
	private Discount discount;

	public Category(String name) {
		this.name = name;
		discount=new Discount("0.0");
	}

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

}
