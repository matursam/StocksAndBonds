package holdings;

public abstract class Holding {

	private int value;
	private String name;

	public Holding(int initialValue, String name) {
		this.value = initialValue;
		this.name = name;
	}
	
	public int getValue() {
		return this.value;
	}
	public String getName() {
		return this.name;
	}
	public void updateValue(boolean bearMarket) {
		this.value += this.getPriceChange(doubleDieRoll(), bearMarket);
	}
	private static int doubleDieRoll() {
		return (int) (Math.round(((Math.random() * 5) + 1)) + Math.round((Math.random() * 5) + 1));
	}
	protected abstract int getPriceChange(int dieRoll, boolean bearMarket);
}
