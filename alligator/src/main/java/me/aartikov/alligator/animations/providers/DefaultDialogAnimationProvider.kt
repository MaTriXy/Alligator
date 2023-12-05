package me.aartikov.alligator.animations.providers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import me.aartikov.alligator.Screen;
import me.aartikov.alligator.animations.AnimationData;
import me.aartikov.alligator.animations.DialogAnimation;


/**
 * Default implementation of {@link DialogAnimationProvider}. Always returns {@code DialogAnimation.DEFAULT}.
 */
public class DefaultDialogAnimationProvider implements DialogAnimationProvider {
	@Override
	@NonNull
	public DialogAnimation getAnimation(@NonNull Class<? extends Screen> screenClass,
										@Nullable AnimationData animationData) {
		return DialogAnimation.DEFAULT;
	}
}
