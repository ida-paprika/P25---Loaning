package co.simplon.p25.loaning.calculator;

public enum ScheduleMethod {
    STRAIGHT_LINE {
	@Override
	public Calculator calculator​(Request request) {
	    return new StraightLineCalculator(request);
	}

    },
    ANNUITY {
	@Override
	public Calculator calculator​(Request request) {
	    return new AnnuityCalculator(request);
	}

    };

    public abstract Calculator calculator​(Request request);

    public static ScheduleMethod valueOf​(String name) {
	for (ScheduleMethod c : ScheduleMethod.values()) {
	    if (name == c.toString()) {
		return c;
	    } else if (name == null) {
		throw new NullPointerException();
	    } else {
		throw new IllegalArgumentException();
	    }
	}
	return null;
    }

}
