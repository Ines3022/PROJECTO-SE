package pt.ulisboa.tecnico.learnjava.sibs.status;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.ServicesException;
import pt.ulisboa.tecnico.learnjava.sibs.domain.Sibs;
import pt.ulisboa.tecnico.learnjava.sibs.domain.TransferOperation;

public class Retry extends State {
	private static String antigoEstado;
	private static TransferOperation transferOperation;

	public Retry(String antigoEstado, TransferOperation transferOperation, Sibs sibs)
			throws AccountException, ServicesException {
		this.antigoEstado = antigoEstado;
		this.transferOperation = transferOperation;
		if (transferOperation.getNumberOfRetries() == 0) {
			transferOperation.getState().cancel(transferOperation, sibs);
			transferOperation.setState(StateError.getInstance());
		} else {
			transferOperation.setNumberOfRetries();
			getStateFromString(antigoEstado, transferOperation);

		}
	}

	public void getStateFromString(String antigoEstado, TransferOperation transferOperation) {
		switch (antigoEstado) {
		case "Deposited":
			transferOperation.setState(Deposited.getInstance());
			break;
		case "Withdrawn":
			transferOperation.setState(Withdrawn.getInstance());
			break;
		case "Registered":
			transferOperation.setState(Registered.getInstance());
			break;
		}

	}

	@Override
	public void process(TransferOperation transferOperation, Sibs sibs) throws AccountException, ServicesException {

	}

	@Override
	public void cancel(TransferOperation transferOperation, Sibs sibs) throws AccountException, ServicesException {

	}

}
