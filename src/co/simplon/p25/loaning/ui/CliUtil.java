package co.simplon.p25.loaning.ui;

import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.simplon.p25.loaning.calculator.Payment;
import co.simplon.p25.loaning.calculator.Request;
import co.simplon.p25.loaning.calculator.Schedule;
import co.simplon.p25.loaning.calculator.ScheduleMethod;

final class CliUtil {

    public CliUtil() {
	//
    }

    public static CliInputs toCliInputs​(String inputLine) throws CliInputsException {
	ArrayList<String> inputs = extractData(inputLine);

	int amount = validateAmount(inputs.get(0));
	double duration = validateDuration(inputs.get(1));
	double rate = validateRate(inputs.get(2));
	ScheduleMethod method = isMethod(inputs.get(3));

	CliInputs cliInputs = new CliInputs.Builder(new Request.Builder((double) amount, (int) duration, rate).build(),
		method).build();

	return cliInputs;
    }

    static void printSchedule​(Properties properties, Schedule schedule) {

	int period = 1;

	System.out.println(String.format("%-10s%-20s%-20s%-20s%s", properties.getProperty("cli.period.period"),
		properties.getProperty("cli.period.principal"), properties.getProperty("cli.period.interest"),
		properties.getProperty("cli.period.total"), properties.getProperty("cli.period.remaining")));

	for (Payment payment : schedule.getPayments()) {
	    System.out.println(String.format("%-10d%,-20.2f%,-20.2f%,-20.2f%,.2f", period++, payment.getPrincipal(),
		    payment.getInterests(), payment.getTotal(), payment.getRemaining()));
	}

	System.out.println(
		String.format("%-10s%-20s%,-20.2f%,-20.2f", " ", " ", schedule.getInterests(), schedule.getTotal()));

    }

    private static ArrayList<String> extractData(String inputLine) throws CliInputsException {
	ArrayList<String> inputs = new ArrayList<String>();
	String regex = "([a-z]{1}=)([\\d|._|[A-Z]]*)";
	Pattern pattern = Pattern.compile(regex);
	Matcher matcher = pattern.matcher(inputLine);
	while (matcher.find()) {
	    inputs.add(matcher.group(2));
	}

	if (inputs.size() == 4) {
	    return inputs;
	}
	throw new CliInputsException();
    }

    private static int validateAmount(String input) throws CliInputsException {
	int amount = Integer.parseInt(input);
	if (isInteger(amount) && amount >= 100 && amount <= 1000000) {
	    return amount;
	} else {
	    throw new CliInputsException();
	}
    }

    private static double validateDuration(String input) throws CliInputsException {
	double duration = Double.parseDouble(input);
	if (isDouble(duration) && duration >= 1 && duration <= 30) {
	    return duration;
	} else {
	    throw new CliInputsException();
	}
    }

    private static double validateRate(String input) throws CliInputsException {
	double duration = Double.parseDouble(input);
	if (isDouble(duration) && duration >= 0.05 && duration <= 20.0) {
	    return duration;
	} else {
	    throw new CliInputsException();
	}
    }

    private static ScheduleMethod isMethod(String input) {

	try {
	    ScheduleMethod.valueOf(input);
	} catch (Exception e) {
	    System.err.println(new CliInputsException().getMessage());
	}

	return ScheduleMethod.valueOf(input);
    }

    private static boolean isInteger(int input) {
	if (input == (int) input) {
	    return true;
	} else
	    return false;
    }

    private static boolean isDouble(double input) {
	if (input == (double) input)
	    return true;
	else
	    return false;
    }
}
