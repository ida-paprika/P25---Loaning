package co.simplon.p25.loaning.ui;

import java.io.FileReader;
import java.util.Properties;
import java.util.Scanner;

import co.simplon.p25.loaning.calculator.Schedule;
import co.simplon.p25.loaning.calculator.ScheduleMethod;

public final class Cli {

    private static final Cli INSTANCE = new Cli();
    private static Scanner scanner;

    private Cli() {
	//
    }

    public static Cli getInstance() {
	return INSTANCE;
    }

    public void start​(String propertiesPath) throws CliException {
	try {
	    if (scanner != null) {
		throw new CliException("Scanner already exists");
	    }
	    scanner = new Scanner(System.in);
	    Properties properties = getProperties(propertiesPath);
	    System.out.println(properties.getProperty("cli.welcome"));
	    System.out.println(properties.getProperty("cli.request"));

	    CliInputs inputs = CliUtil.toCliInputs​(scanner.nextLine());

	    ScheduleMethod method = inputs.getMethod();

	    Schedule schedule = method.calculator​(inputs.getRequest()).calculate();

	    CliUtil.printSchedule​(properties, schedule);

	} catch (Exception e) {
	    e.getMessage();
	}
    }

    public void stop() {
	try {
	    if (scanner == null) {
		throw new NullPointerException();
	    } else {
		scanner.close();
	    }
	} catch (NullPointerException e) {
	    e.getMessage();
	}
    }

    private static Properties getProperties(String path) throws CliException {
	try {
	    FileReader reader = new FileReader(path);
	    Properties prop = new Properties();
	    prop.load(reader);
	    return prop;
	} catch (Exception e) {
	    throw new CliException("Properties file not found");
	}
    }

}
