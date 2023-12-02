package me.aartikov.alligator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import me.aartikov.alligator.animations.AnimationData;

/**
 * Interface with navigation methods.
 */
public interface Navigator {

	/**
	 * Returns if a navigator can execute a command immediately
	 *
	 * @return true if a navigator can execute a command immediately
	 */
	boolean canExecuteCommandImmediately();

	/**
	 * Returns if a navigator has pending commands
	 *
	 * @return true if a navigator has pending commands
	 */
	boolean hasPendingCommands();

	/**
	 * Adds a new screen and goes to it.
	 *
	 * @param screen new screen
	 */
	void goForward(@NonNull Screen screen);

	/**
	 * {@code goForward} with an animation data.
	 *
	 * @param screen        new screen
	 * @param animationData animation data for an additional animation configuring
	 */
	void goForward(@NonNull Screen screen, @Nullable AnimationData animationData);

	/**
	 * Finishes a current screen and goes back to the previous screen.
	 */
	void goBack();

	/**
	 * {@code goBack} with an animation data.
	 *
	 * @param animationData animation data for an additional animation configuring
	 */
	void goBack(@Nullable AnimationData animationData);

	/**
	 * Finishes a current screen and goes back to the previous screen with result.
	 *
	 * @param screenResult screen result that will be returned
	 */
	void goBackWithResult(@NonNull ScreenResult screenResult);

	/**
	 * {@code goBackWithResult} with an animation data.
	 *
	 * @param screenResult  screen result that will be returned
	 * @param animationData animation data for an additional animation configuring
	 */
	void goBackWithResult(@NonNull ScreenResult screenResult, @Nullable AnimationData animationData);

	/**
	 * Goes back to a screen with the given class.
	 *
	 * @param screenClass screen class for going back
	 */
	void goBackTo(@NonNull Class<? extends Screen> screenClass);

	/**
	 * Goes back to a screen with the given screen object.
	 *
	 * @param screen screen class for going back
	 */
	void goBackTo(@NonNull Screen screen);

	/**
	 * {@code goBackTo} with an animation data.
	 *
	 * @param screenClass   screen class for going back
	 * @param animationData animation data for an additional animation configuring
	 */
	void goBackTo(@NonNull Class<? extends Screen> screenClass, @Nullable AnimationData animationData);

	/**
	 * {@code goBackTo} with an animation data.
	 *
	 * @param screen        screen class for going back
	 * @param animationData animation data for an additional animation configuring
	 */
	void goBackTo(@NonNull Screen screen, @Nullable AnimationData animationData);

	/**
	 * Goes back to a screen with the given class and returns result to it.
	 *
	 * @param screenClass  screen class for going back
	 * @param screenResult screen result that will be returned
	 */
	void goBackToWithResult(@NonNull Class<? extends Screen> screenClass, @NonNull ScreenResult screenResult);

	/**
	 * Goes back to a screen with the given class and returns result to it.
	 *
	 * @param screen       screen object for going back
	 * @param screenResult screen result that will be returned
	 */
	void goBackToWithResult(@NonNull Screen screen, @NonNull ScreenResult screenResult);

	/**
	 * {@code goBackToWithResult} with an animation data.
	 *
	 * @param screenClass   screen class for going back
	 * @param screenResult  screen result that will be returned
	 * @param animationData animation data for an additional animation configuring
	 */
	void goBackToWithResult(@NonNull Class<? extends Screen> screenClass, @NonNull ScreenResult screenResult, @Nullable AnimationData animationData);

	/**
	 * {@code goBackToWithResult} with an animation data.
	 *
	 * @param screen        screen object for going back
	 * @param screenResult  screen result that will be returned
	 * @param animationData animation data for an additional animation configuring
	 */
	void goBackToWithResult(@NonNull Screen screen, @NonNull ScreenResult screenResult, @Nullable AnimationData animationData);

	/**
	 * Replaces the last screen with a new screen.
	 *
	 * @param screen new screen
	 */
	void replace(@NonNull Screen screen);

	/**
	 * {@code replace} with an animation data.
	 *
	 * @param screen        new screen
	 * @param animationData animation data for an additional animation configuring
	 */
	void replace(@NonNull Screen screen, @Nullable AnimationData animationData);

	/**
	 * Removes all other screens and adds a new screen.
	 *
	 * @param screen new screen
	 */
	void reset(@NonNull Screen screen);

	/**
	 * {@code reset} with an animation data.
	 *
	 * @param screen        new screen
	 * @param animationData animation data for an additional animation configuring
	 */
	void reset(@NonNull Screen screen, @Nullable AnimationData animationData);

	/**
	 * Finishes a current flow or a current top-level screen.
	 */
	void finish();

	/**
	 * {@code finish} with an animation data.
	 *
	 * @param animationData animation data for an additional animation configuring
	 */
	void finish(@Nullable AnimationData animationData);

	/**
	 * Finishes a current flow or a current top-level screen and returns a screen result.
	 *
	 * @param screenResult screen result that will be returned
	 */
	void finishWithResult(@NonNull ScreenResult screenResult);

	/**
	 * {@code finishWithResult} with an animation data.
	 *
	 * @param screenResult  screen result that will be returned
	 * @param animationData animation data for an additional animation configuring
	 */
	void finishWithResult(@NonNull ScreenResult screenResult, @Nullable AnimationData animationData);

	/**
	 * Finishes a current top-level screen.
	 */
	void finishTopLevel();

	/**
	 * {@code finishTopLevel} with an animation data.
	 *
	 * @param animationData animation data for an additional animation configuring
	 */
	void finishTopLevel(@Nullable AnimationData animationData);

	/**
	 * Finishes a current top-level screen and returns a screen result.
	 *
	 * @param screenResult screen result that will be returned
	 */
	void finishTopLevelWithResult(@NonNull ScreenResult screenResult);

	/**
	 * {@code finishTopLevelWithResult} with an animation data.
	 *
	 * @param screenResult  screen result that will be returned
	 * @param animationData animation data for an additional animation configuring
	 */
	void finishTopLevelWithResult(@NonNull ScreenResult screenResult, @Nullable AnimationData animationData);

	/**
	 * Switches screens.
	 *
	 * @param screen screen for switching to
	 */
	void switchTo(@NonNull Screen screen);

	/**
	 * {@code switchTo} with an animation data.
	 *
	 * @param screen        screen for switching to
	 * @param animationData animation data for an additional animation configuring
	 */
	void switchTo(@NonNull Screen screen, @Nullable AnimationData animationData);
}
