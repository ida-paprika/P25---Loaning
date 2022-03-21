package co.simplon.p25.loaning.ui;

import co.simplon.p25.loaning.calculator.Request;
import co.simplon.p25.loaning.calculator.ScheduleMethod;

public final class CliInputs {

    private final Request request;
    private final ScheduleMethod method;

    private CliInputs(Builder builder) {
	this.request = builder.request;
	this.method = builder.method;
    }

    public static class Builder {

	private Request request;
	private ScheduleMethod method;

	public Builder(Request request, ScheduleMethod method) {
	    this.request = request;
	    this.method = method;
	}

	public void request(Request request) throws NullPointerException {
	    this.request = request;
	}

	public void method(ScheduleMethod method) throws NullPointerException {
	    this.method = method;
	}

	public CliInputs build() {
	    return new CliInputs(this);
	}

    }

    public Request getRequest() {
	return request;
    }

    public ScheduleMethod getMethod() {
	return method;
    }

    @Override
    public String toString() {
	return String.format("%s | Method= %s", request, method);
    }
}
