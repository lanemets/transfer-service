package mt.app;


import com.google.inject.Injector;

public class InjectorHolder {
	private Injector injector;
	private static final InjectorHolder me = new InjectorHolder();

	private InjectorHolder() {
	}

	public static void setInjector(Injector injector) {
		me.injector = injector;
	}

	public static Injector getInstance() {
		return me.injector;
	}
}
