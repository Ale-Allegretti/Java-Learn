package conMyLambdaCombo;

import java.util.function.Function;

public class NamedFunction<T,R> implements Function<T, R> {

	private Function<T, R> f;
	private String name;
	
	public NamedFunction(Function<T,R> f, String name) {
		this.f=f; this.name=name;
	}

	@Override
	public R apply(T t) {
		return f.apply(t);
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		return getName();
	}
	
}
