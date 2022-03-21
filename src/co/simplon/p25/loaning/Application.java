package co.simplon.p25.loaning;

import co.simplon.p25.loaning.ui.Cli;
import co.simplon.p25.loaning.ui.CliException;

public class Application {
// a=100000 d=1 r=1.2 m=STRAIGHT_LINE
// a=100000 d=1 r=1.2 m=ANNUITY
    public static void main(String[] args) {

	Cli cli = Cli.getInstance();

	try {
	    cli.start​(args[0]);
	} catch (CliException e) {
	    System.err.println(e.getMessage());
	}

	cli.stop();

    }

}
