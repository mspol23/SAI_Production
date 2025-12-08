package main.entities;

public class DataToChart implements FormatToChart {

	private String category;
	private Number amount;

	@Override
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public Number getAmount() {
		return amount;
	}

	public void setAmount(Number amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "DataToChart [category=" + category + ", amount=" + amount + "]";
	}

}
