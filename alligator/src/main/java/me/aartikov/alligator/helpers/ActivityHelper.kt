package me.aartikov.alligator.helpers;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import me.aartikov.alligator.animations.TransitionAnimation;


/**
 * Helper class for starting and finishing an activity with animation.
 */
public class ActivityHelper {
	private AppCompatActivity mActivity;

	public ActivityHelper(@NonNull AppCompatActivity activity) {
		mActivity = activity;
	}

	public boolean resolve(@NonNull Intent intent) {
		return intent.resolveActivity(mActivity.getPackageManager()) != null;
	}

	public void start(@NonNull Intent intent, @NonNull TransitionAnimation animation) {
		Bundle optionsBundle = animation.getActivityOptionsBundle(mActivity);
		animation.applyBeforeActivityStarted(mActivity, intent);
		ActivityCompat.startActivity(mActivity, intent, optionsBundle);
		animation.applyAfterActivityStarted(mActivity);
	}

	public void startForResult(@NonNull Intent intent, int requestCode, @NonNull TransitionAnimation animation) {
		Bundle optionsBundle = animation.getActivityOptionsBundle(mActivity);
		animation.applyBeforeActivityStarted(mActivity, intent);
		ActivityCompat.startActivityForResult(mActivity, intent, requestCode, optionsBundle);
		animation.applyAfterActivityStarted(mActivity);
	}

	public void finish(@NonNull TransitionAnimation animation) {
		animation.applyBeforeActivityFinished(mActivity);
		if (animation.needDelayActivityFinish()) {
			mActivity.supportFinishAfterTransition();
		} else {
			mActivity.finish();
		}
		animation.applyAfterActivityFinished(mActivity);
	}
}
