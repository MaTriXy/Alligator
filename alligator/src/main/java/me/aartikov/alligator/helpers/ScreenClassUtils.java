package me.aartikov.alligator.helpers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import me.aartikov.alligator.NavigationFactory;
import me.aartikov.alligator.Screen;

/**
 * Date: 19.03.2017
 * Time: 9:51
 *
 * @author Artur Artikov
 */

/**
 * Utils class for storing and retrieving screen class information.
 */
public class ScreenClassUtils {
	private static final String KEY_SCREEN_CLASS_NAME = "me.aartikov.alligator.KEY_SCREEN_CLASS_NAME";
	private static final String KEY_PREVIOUS_SCREEN_CLASS_NAME = "me.aartikov.alligator.KEY_PREVIOUS_SCREEN_CLASS_NAME";

	private ScreenClassUtils() {
	}

	public static void putScreenClass(Intent intent, Class<? extends Screen> screenClass) {
		intent.putExtra(KEY_SCREEN_CLASS_NAME, screenClass.getName());
	}

	@SuppressWarnings("unchecked")
	public static Class<? extends Screen> getScreenClass(Activity activity, NavigationFactory navigationFactory) {
		String className = activity.getIntent().getStringExtra(KEY_SCREEN_CLASS_NAME);
		return getClassByName(className);
	}

	public static void putScreenClass(Fragment fragment, Class<? extends Screen> screenClass) {
		Bundle arguments = fragment.getArguments();
		if (arguments == null) {
			arguments = new Bundle();
			fragment.setArguments(arguments);
		}
		arguments.putString(KEY_SCREEN_CLASS_NAME, screenClass.getName());
	}

	@SuppressWarnings("unchecked")
	public static Class<? extends Screen> getScreenClass(Fragment fragment) {
		if (fragment.getArguments() == null) {
			return null;
		}

		String className = fragment.getArguments().getString(KEY_SCREEN_CLASS_NAME);
		return (Class<? extends Screen>) getClassByName(className);
	}

	public static void putPreviousScreenClass(Intent intent, Class<? extends Screen> screenClass) {
		intent.putExtra(KEY_PREVIOUS_SCREEN_CLASS_NAME, screenClass.getName());
	}

	@SuppressWarnings("unchecked")
	public static Class<? extends Screen> getPreviousScreenClass(Activity activity) {
		String className = activity.getIntent().getStringExtra(KEY_PREVIOUS_SCREEN_CLASS_NAME);
		return getClassByName(className);
	}

	private static Class getClassByName(String className) {
		if (className == null || className.isEmpty()) {
			return null;
		}

		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
