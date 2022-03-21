package co.simplon.p25.loaning.calculator;

import java.util.ArrayList;
import java.util.List;

public final class Schedule {

    private final List<Payment> payments;
    private final double interests;
    private final double total;

    private Schedule(Builder builder) {
	this.payments = builder.payments;
	this.interests = builder.interests;
	this.total = builder.total;
    }

    public static class Builder {

	private List<Payment> payments = new ArrayList<>();
	private double interests;
	private double total;

	public Builder() {
	    //
	}

	public Schedule.Builder add​(Payment payment) {
	    this.payments.add(payment);
	    return this;
	}

	public void payments(List<Payment> payments) {
	    this.payments = payments;
	}

	public void interests(double interests) {
	    this.interests = interests;
	}

	public void total(double total) {
	    this.total = total;
	}

	public Schedule build() {
	    return new Schedule(this);
	}

    }

    public List<Payment> getPayments() {
	return payments;
    }

    public double getInterests() {
	return interests;
    }

    public double getTotal() {
	return total;
    }

    @Override
    public String toString() {
	int i = 1;
	for (Payment p : this.payments) {
	    System.out.println(i++ + "	" + p);
	}

	return "		" + interests + "		" + total;
    }

}
