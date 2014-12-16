package glazer.vendingmachine;

public class Money {

	private int numDollars;
	private int numQuarters;
	private int numDimes;
	private int numNickles;

	public Money() {

	}

	public Money(int numDollars, int numQuarters, int numDimes, int numNickles) {
		this.numDollars = numDollars;
		this.numQuarters = numQuarters;
		this.numDimes = numDimes;
		this.numNickles = numNickles;
	}

	public void add(Money money) {
		this.numDollars += money.getNumDollars();
		this.numQuarters += money.getNumQuarters();
		this.numDimes += money.getNumDimes();
		this.numNickles += money.getNumNickles();
	}

	public Money remove(double amount) throws NotEnoughChangeException {
		double extra = amount;
		if (getTotal() >= extra) {
			int dollar = (int) Math.min(getNumDollars(),
					Math.round((extra / 1.0) * 100.0) / 100.0);
			extra -= Math.round(dollar * 100.0) / 100.0;
			extra = Math.round(extra * 100.0) / 100.0;
			int quarter = (int) Math.min(getNumQuarters(),
					Math.round((extra / .25) * 100.0 )/ 100.0);
			extra -= Math.round(quarter * .25 * 100.0) / 100.0;
			extra = Math.round(extra * 100.0) / 100.0;
			int dime = (int) Math.min(getNumDimes(),
					Math.round((extra / .1) * 100.0 )/ 100.0);
			extra -= Math.round(dime * .1 * 100.0) / 100.0;
			extra = Math.round(extra * 100.0) / 100.0;
			int nickle = (int) Math.min(getNumNickles(),
					Math.round((extra / .05) * 100.0 )/ 100.0);
			extra -= Math.round(nickle * .05 * 100.0) / 100.0;
			extra = Math.round(extra * 100.0) / 100.0;
			Money change = new Money(dollar, quarter, dime, nickle);
			if (change.getTotal() == amount) {
				setNumDollars(numDollars - change.getNumDollars());
				setNumQuarters(numQuarters - change.getNumQuarters());
				setNumDimes(numDimes - change.getNumDimes());
				setNumNickles(numNickles - change.getNumNickles());
				return change;
			} else {
				throw new NotEnoughChangeException();
			}
		} else {
			throw new NotEnoughChangeException();
		}
	}

	public double getTotal() {
		double total = numDollars + (numQuarters * .25) + (numDimes * .1)
				+ (numNickles * .05);
		return Math.round(total * 100.0) / 100.0;
	}

	public int getNumDollars() {
		return numDollars;
	}

	public void setNumDollars(int numDollars) {
		this.numDollars = numDollars;
	}

	public int getNumQuarters() {
		return numQuarters;
	}

	public void setNumQuarters(int numQuarters) {
		this.numQuarters = numQuarters;
	}

	public int getNumNickles() {
		return numNickles;
	}

	public void setNumNickles(int numNickles) {
		this.numNickles = numNickles;
	}

	public int getNumDimes() {
		return numDimes;
	}

	public void setNumDimes(int numDimes) {
		this.numDimes = numDimes;
	}

}
