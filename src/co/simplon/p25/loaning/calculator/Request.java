package co.simplon.p25.loaning.calculator;

public final class Request {

    private final double amount;
    private final int duration;
    private final double rate;

    private Request(Builder builder) {
	this.amount = builder.amount;
	this.duration = builder.duration;
	this.rate = builder.rate;
    }

    public static class Builder {

	private double amount;
	private int duration;
	private double rate;

	public Builder(double amount, int duration, double rate) {
	    this.amount = amount;
	    this.duration = duration;
	    this.rate = rate;
	}

	public void amount(double amount) {
	    this.amount = amount;
	}

	public void duration(int duration) {
	    this.duration = duration;
	}

	public void rate(double rate) {
	    this.rate = rate;
	}

	public Request build() {
	    return new Request(this);
	}
    }

    public double getAmount() {
	return amount;
    }

    public int getDuration() {
	return duration;
    }

    public double getRate() {
	return rate;
    }

    @Override
    public String toString() {
	return String.format("Amount= %.2f | Duration= %d | Rate= %.2f", amount, duration, rate);
    }
}
