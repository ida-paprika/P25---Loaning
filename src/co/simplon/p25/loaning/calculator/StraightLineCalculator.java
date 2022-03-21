package co.simplon.p25.loaning.calculator;

final class StraightLineCalculator extends AbstractCalculator {

    public StraightLineCalculator(Request request) {
	super(request);
    }

    @Override
    public Payment.Builder firstPeriod​(Payment.Builder builder) {
	double amount = this.amount();
	int periods = this.periods();
	double periodicRate = this.decimalPeriodicRate();

	double principal = amount / periods;
	double interest = amount * periodicRate / 100;
	double total = principal + interest;
	double remaining = amount - principal;

	builder.interests(interest);
	builder.principal(principal);
	builder.remaining(remaining);
	builder.total(total);

	return builder;
    }

    @Override
    public Payment.Builder nextPeriod​(Payment previous, Payment.Builder builder) {
	double principal = previous.getPrincipal();
	double interest = previous.getRemaining() * this.decimalPeriodicRate() / 100;
	double total = principal + interest;
	double remaining = previous.getRemaining() - principal;

	builder.interests(interest);
	builder.principal(principal);
	builder.remaining(remaining);
	builder.total(total);

	return builder;
    }

}
