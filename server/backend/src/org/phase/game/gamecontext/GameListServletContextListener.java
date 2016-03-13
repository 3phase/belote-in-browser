package org.phase.game.gamecontext;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

public class GameListServletContextListener extends GuiceServletContextListener {

	public static Injector injector;
	
	@Override
	protected Injector getInjector() {
		if (injector == null) {
			injector = Guice.createInjector(new ServletModule() {
				@Override
				protected void configureServlets() {
					bind(GameServices.class);
				}
			});
		}

		return injector;
	}
	
}
