package com.art.alligator.implementation.commands;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.art.alligator.AnimationData;
import com.art.alligator.Command;
import com.art.alligator.CommandExecutionException;
import com.art.alligator.NavigationContext;
import com.art.alligator.NavigationFactory;
import com.art.alligator.Screen;
import com.art.alligator.TransitionAnimation;
import com.art.alligator.TransitionType;
import com.art.alligator.implementation.CommandUtils;
import com.art.alligator.implementation.FailedResolveActivityException;
import com.art.alligator.implementation.ScreenClassUtils;

/**
 * Date: 29.12.2016
 * Time: 10:22
 *
 * @author Artur Artikov
 */
public class ForwardCommand implements Command {
	private Screen mScreen;
	private boolean mForResult;
	private AnimationData mAnimationData;

	public ForwardCommand(Screen screen, boolean forResult, AnimationData animationData) {
		mScreen = screen;
		mForResult = forResult;
		mAnimationData = animationData;
	}

	@Override
	public boolean execute(NavigationContext navigationContext, NavigationFactory navigationFactory) throws CommandExecutionException {
		if (navigationFactory.isActivityScreen(mScreen.getClass())) {
			Activity activity = navigationContext.getActivity();
			Intent intent = navigationFactory.createIntent(activity, mScreen);
			ScreenClassUtils.putScreenClass(intent, mScreen.getClass());
			ScreenClassUtils.putPreviousScreenClass(intent, ScreenClassUtils.getScreenClass(activity, navigationFactory));

			if(intent.resolveActivity(activity.getPackageManager()) == null) {
				throw new FailedResolveActivityException(this, mScreen);
			}

			if(mForResult) {
				if (!navigationFactory.isScreenForResult(mScreen.getClass())) {
					throw new CommandExecutionException(this, "Screen " + mScreen.getClass().getSimpleName() + " is not registered as screen for result.");
				}
				int requestCode = navigationFactory.getRequestCode(mScreen.getClass());
				activity.startActivityForResult(intent, requestCode);
			} else {
				activity.startActivity(intent);
			}
			CommandUtils.applyActivityAnimation(activity, getActivityAnimation(navigationContext, navigationFactory));
			return false;

		} else if (navigationFactory.isFragmentScreen(mScreen.getClass())) {
			FragmentManager fragmentManager = navigationContext.getFragmentManager();
			if (fragmentManager == null) {
				throw new CommandExecutionException(this, "FragmentManager is not bound.");
			}
			if(mForResult) {
				throw new CommandExecutionException(this, "goForwardForResult is not supported for fragment screens.");
			}

			Fragment fragment = navigationFactory.createFragment(mScreen);
			FragmentTransaction transaction = fragmentManager.beginTransaction();
			Fragment currentFragment = CommandUtils.getCurrentFragment(navigationContext);
			if(currentFragment != null) {
				CommandUtils.applyFragmentAnimation(transaction, getFragmentAnimation(navigationContext, currentFragment));
				transaction.detach(currentFragment);
			}

			ScreenClassUtils.putScreenClass(fragment, mScreen.getClass());
			int index = CommandUtils.getFragmentCount(navigationContext);
			String tag = CommandUtils.getFragmentTag(navigationContext, index);
			transaction.add(navigationContext.getContainerId(), fragment, tag);
			transaction.commitNow();
			return true;

		} else {
			throw new CommandExecutionException(this, "Screen " + mScreen.getClass().getSimpleName() + " is not registered.");
		}
	}

	private TransitionAnimation getActivityAnimation(NavigationContext navigationContext, NavigationFactory navigationFactory) {
		Class<? extends Screen> screenClassFrom = ScreenClassUtils.getScreenClass(navigationContext.getActivity(), navigationFactory);
		Class<? extends Screen> screenClassTo = mScreen.getClass();
		return navigationContext.getAnimationProvider().getAnimation(TransitionType.FORWARD, screenClassFrom, screenClassTo, true, mAnimationData);
	}

	private TransitionAnimation getFragmentAnimation(NavigationContext navigationContext, Fragment currentFragment) {
		Class<? extends Screen> screenClassFrom = ScreenClassUtils.getScreenClass(currentFragment);
		Class<? extends Screen> screenClassTo = mScreen.getClass();
		return navigationContext.getAnimationProvider().getAnimation(TransitionType.FORWARD, screenClassFrom, screenClassTo, false, mAnimationData);
	}
}
