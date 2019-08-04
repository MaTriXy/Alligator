package me.aartikov.alligator.commands;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import me.aartikov.alligator.NavigationContext;
import me.aartikov.alligator.Screen;
import me.aartikov.alligator.animations.AnimationData;
import me.aartikov.alligator.destinations.ActivityDestination;
import me.aartikov.alligator.destinations.DialogFragmentDestination;
import me.aartikov.alligator.destinations.FragmentDestination;
import me.aartikov.alligator.exceptions.MissingFragmentNavigatorException;
import me.aartikov.alligator.exceptions.NavigationException;


/**
 * Command implementation for {@code replace} method of {@link me.aartikov.alligator.AndroidNavigator}.
 */
public class ReplaceCommand extends BaseCommand {
	private Screen mScreen;
	@Nullable
	private AnimationData mAnimationData;

	public ReplaceCommand(@NonNull Screen screen, @Nullable AnimationData animationData) {
		super(screen.getClass());
		mScreen = screen;
		mAnimationData = animationData;
	}

	@Override
	public boolean execute(@NonNull ActivityDestination destination, @NonNull NavigationContext navigationContext) throws NavigationException {
		navigationContext.getActivityNavigator().replace(mScreen, destination, mAnimationData);
		return false;
	}

	@Override
	public boolean execute(@NonNull FragmentDestination destination, @NonNull NavigationContext navigationContext) throws NavigationException {
		if (navigationContext.getFragmentNavigator() == null) {
			throw new MissingFragmentNavigatorException();
		}
		navigationContext.getFragmentNavigator().replace(mScreen, destination, mAnimationData);
		return true;
	}

	@Override
	public boolean execute(@NonNull DialogFragmentDestination destination, @NonNull NavigationContext navigationContext) throws NavigationException {
		navigationContext.getDialogFragmentNavigator().replace(mScreen, destination, mAnimationData);
		return true;
	}
}