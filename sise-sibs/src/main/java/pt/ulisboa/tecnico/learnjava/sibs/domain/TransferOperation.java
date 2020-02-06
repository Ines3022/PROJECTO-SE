package pt.ulisboa.tecnico.learnjava.sibs.domain;

import pt.ulisboa.tecnico.learnjava.sibs.exceptions.OperationException;
import pt.ulisboa.tecnico.learnjava.sibs.status.Registered;
import pt.ulisboa.tecnico.learnjava.sibs.status.State;

public class TransferOperation extends Operation {
	private final String sourceIban;
	private final String targetIban;
	private State state;
	private int numberOfRetries;

	public TransferOperation(String sourceIban, String targetIban, int value) throws OperationException {
		super(Operation.OPERATION_TRANSFER, value);

		if (this.invalidString(sourceIban) || this.invalidString(targetIban)) {
			throw new OperationException();
		}

		this.sourceIban = sourceIban;
		this.targetIban = targetIban;
		this.state = Registered.getInstance();
		this.numberOfRetries = 3;
	}

	public int getNumberOfRetries() {
		return numberOfRetries;
	}

	public void setNumberOfRetries() {
		this.numberOfRetries--;
	}

	private boolean invalidString(String name) {
		return name == null || name.length() == 0;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Override
	public int commission() {
		if (this.sourceIban.substring(0, 3).equals(this.targetIban.substring(0, 3))) {
			return 0;
		} else {
			return (int) Math.round(super.commission() + this.getValue() * 0.05);
		}
	}

	public String getSourceIban() {
		return this.sourceIban;
	}

	public String getTargetIban() {
		return this.targetIban;
	}

}
