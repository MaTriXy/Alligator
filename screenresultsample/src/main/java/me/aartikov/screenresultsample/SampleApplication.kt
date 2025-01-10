package me.aartikov.screenresultsample;

import android.app.Application;

import me.aartikov.alligator.ActivityResultHandler;
import me.aartikov.alligator.AndroidNavigator;
import me.aartikov.alligator.NavigationContextBinder;
import me.aartikov.alligator.Navigator;
import me.aartikov.alligator.navigationfactories.NavigationFactory;


public class SampleApplication extends Application {
	private static AndroidNavigator sAndroidNavigator;

	@Override
	public void onCreate() {
		super.onCreate();
		sAndroidNavigator = new AndroidNavigator(new SampleNavigationFactory());
	}

	public static Navigator getNavigator() {
		return sAndroidNavigator;
	}

	public static NavigationFactory getNavigationFactory() {
		return sAndroidNavigator.getNavigationFactory();
	}

	public static NavigationContextBinder getNavigationContextBinder() {
		return sAndroidNavigator;
	}

	public static ActivityResultHandler getActivityResultHandler() {
		return sAndroidNavigator.getActivityResultHandler();
	}
}
