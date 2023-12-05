package me.aartikov.alligator.listeners;


import androidx.annotation.NonNull;

import me.aartikov.alligator.Screen;

/**
 * Interface for listening of dialog showing.
 */
public interface DialogShowingListener {
	/**
	 * Is called when a dialog was shown.
	 *
	 * @param screenClass class of a screen that represents a dialog.
	 */
	void onDialogShown(@NonNull Class<? extends Screen> screenClass);
}
