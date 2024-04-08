package cookie.thaumaturgy.api;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Aspect {
	protected final String name;
	protected final int color;
	protected final int texture;
	protected final List<Aspect> composition;

	public Aspect(String name, int color, int texture, @Nullable List<Aspect> composition) {
		this.name = name;
		this.color = color;
		this.texture = texture;
		this.composition = composition != null ? composition : new ArrayList<>();
	}

	public Aspect(String name, int color, int texture) {
		this(name, color, texture, null);
	}

	public String getName() {
		return name;
	}

	public int getColor() {
		return color;
	}

	public int getTexture() {
		return texture;
	}

	public List<Aspect> getComposition() {
		return composition;
	}
}
