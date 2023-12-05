package me.aartikov.alligator;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


/**
 * Interface for binding and unbinding of a {@link NavigationContext}.
 */
public interface NavigationContextBinder {

	/**
	 * Returns bound navigation context
	 *
	 * @return navigation context if it is bound or null otherwise
	 */
	@Nullable
	NavigationContext getNavigationContext();

	/**
	 * Returns if a navigation context is bound.
	 *
	 * @return true if a navigation context is bound
	 */
	boolean isBound();

	/**
	 * Bind a navigation context if no context is bound or a context with the same activity is bound. Do nothing otherwise.
	 * This method should be called from {@code onResumeFragments} of an activity.
	 *
	 * @param navigationContext navigation context
	 */
	void bind(@NonNull NavigationContext navigationContext);

	/**
	 * Unbind a currently bound navigation context if it has the same activity as a passed one. Do nothing otherwise.
	 * This method should be called from {@code onPause} of an activity.
	 *
	 * @param activity activity that initiates unbinding
	 */
	void unbind(AppCompatActivity activity);
}
