package cookie.thaumaturgy.api;

public class AspectStack {
	protected Aspect aspect;
	public int amount;

	public AspectStack(Aspect aspect, int amount) {
		this.aspect = aspect;
		this.amount = amount;
	}

	public AspectStack(Aspect aspect) {
		this(aspect, 1);
	}

	public Aspect getAspect() {
		return aspect;
	}
}
