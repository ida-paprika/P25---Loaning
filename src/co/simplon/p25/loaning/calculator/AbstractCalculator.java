package co.simplon.p25.loaning.calculator;

public abstract class AbstractCalculator implements Calculator {

    private final Request request;

    public AbstractCalculator(Request request) {
	this.request = request;
    }

    @Override
    public final Schedule calculate() {
	System.out.println(request);
	Payment.Builder payment = new Payment.Builder();
	Schedule.Builder schedule = new Schedule.Builder();
	Payment previousPayment;

	this.firstPeriod​(payment);

	schedule.add​(payment.build());

	double interests = payment.build().getInterests();
	double total = payment.build().getTotal();

	while (Math.round(payment.build().getRemaining()) > 0) {
	    previousPayment = payment.build();

	    payment = this.nextPeriod​(previousPayment, payment);
	    schedule.add​(payment.build());

	    interests += payment.build().getInterests();
	    total += payment.build().getTotal();
	}

	schedule.interests(interests);
	schedule.total(total);

	return schedule.build();

    }

    public final double amount() {
	return this.request.getAmount();
    }

    public final double decimalPeriodicRate() {
	return this.request.getRate() / this.periods();
    }

    public final int periods() {
	return this.request.getDuration() * 12;
    }

    public abstract Payment.Builder firstPeriod​(Payment.Builder builder);

    public abstract Payment.Builder nextPeriod​(Payment previous, Payment.Builder builder);

}
