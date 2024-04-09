package cookie.thaumaturgy.api;

import cookie.thaumaturgy.Thaumaturgy;

import java.util.List;

public final class DunamisBuilder {
	private final String name;
	private int color;
	private int texture;
	private List<Dunamis> composition;

	public DunamisBuilder(String name) {
		this.name = name;
	}

	public DunamisBuilder setColor(int color) {
		this.color = color;
		return this;
	}

	public DunamisBuilder setTexture(int texture) {
		this.texture = texture;
		return this;
	}

	public DunamisBuilder setComposition(List<Dunamis> composition) {
		this.composition = composition;
		return this;
	}

	public Dunamis build() {
		Dunamis dunamis = new Dunamis(name, color, texture, composition);
		Dunami.DUNAMI.add(dunamis);
		return dunamis;
	}
}
