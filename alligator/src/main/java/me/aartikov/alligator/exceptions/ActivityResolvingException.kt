package me.aartikov.alligator.exceptions;

import androidx.annotation.NonNull;

import me.aartikov.alligator.Screen;


/**
 * Exception thrown when an implicit intent can't be resolved.
 */
public class ActivityResolvingException extends NavigationException {
	private Screen mScreen;

	public ActivityResolvingException(@NonNull Screen screen) {
		super("Failed to resolve an activity for a screen " + screen.getClass().getSimpleName());
		mScreen = screen;
	}

	@NonNull
	public Screen getScreen() {
		return mScreen;
	}
}
