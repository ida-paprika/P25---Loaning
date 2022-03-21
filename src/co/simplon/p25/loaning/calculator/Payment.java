package co.simplon.p25.loaning.calculator;

public final class Payment {

    private final double interests;
    private final double principal;
    private final double remaining;
    private final double total;

    private Payment(Builder builder) {
	this.interests = builder.interests;
	this.principal = builder.principal;
	this.remaining = builder.remaining;
	this.total = builder.total;
    }

    public static class Builder {
	private double interests;
	private double principal;
	private double remaining;
	private double total;

	public Builder() {
	    //
	}

	public void interests(double interests) {
	    this.interests = interests;
	}

	public void principal(double principal) {
	    this.principal = principal;
	}

	public void remaining(double remaining) {
	    this.remaining = remaining;
	}

	public void total(double total) {
	    this.total = total;
	}

	public Payment build() {
	    return new Payment(this);
	}

    }

    public double getInterests() {
	return interests;
    }

    public double getPrincipal() {
	return principal;
    }

    public double getRemaining() {
	return remaining;
    }

    public double getTotal() {
	return total;
    }

    @Override
    public String toString() {
	return String.format("{interests=%s, principal=%s, remaining=%s, total=%s}", interests, principal, remaining,
		total);
    }

}
