package cookie.thaumaturgy.api;

public class DunamisStack {
	protected Dunamis dunamis;
	public int amount;

	public DunamisStack(Dunamis dunamis, int amount) {
		this.dunamis = dunamis;
		this.amount = amount;
	}

	public DunamisStack(Dunamis dunamis) {
		this(dunamis, 1);
	}

	public Dunamis getDunamis() {
		return dunamis;
	}
}
