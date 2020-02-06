package pt.ulisboa.tecnico.learnjava.sibs.status;

public class Completed extends State {

	public static State instance = null;

	private Completed() {

	}

	public static State getInstance() {
		if (instance == null) {
			instance = new Completed();
		}
		return instance;
	}

}
