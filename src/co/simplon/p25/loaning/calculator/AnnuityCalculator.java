package co.simplon.p25.loaning.calculator;

final class AnnuityCalculator extends AbstractCalculator {

    public AnnuityCalculator(Request request) {
	super(request);
    }

    @Override
    public Payment.Builder firstPeriod​(Payment.Builder builder) {
	double amount = this.amount();
	double periodicRate = this.decimalPeriodicRate() / 100;

	double total = total(amount);
	double interest = amount * periodicRate;
	double principal = total - interest;
	double remaining = amount - principal;

	builder.interests(interest);
	builder.principal(principal);
	builder.remaining(remaining);
	builder.total(total);

	return builder;
    }

    @Override
    public Payment.Builder nextPeriod​(Payment previous, Payment.Builder builder) {
	double periodicRate = this.decimalPeriodicRate() / 100;

	double total = previous.getTotal();
	double interest = previous.getRemaining() * periodicRate;
	double principal = total - interest;
	double remaining = previous.getRemaining() - principal;

	builder.interests(interest);
	builder.principal(principal);
	builder.remaining(remaining);
	builder.total(total);

	return builder;
    }

    private double total(double num) {

	double periodicRate = this.decimalPeriodicRate() / 100;
	int periods = this.periods();

	double a = periodicRate * Math.pow((1 + periodicRate), periods);
	double b = Math.pow((1 + periodicRate), periods) - 1;
	double total = num * (a / b);

	return total;

    }

}
