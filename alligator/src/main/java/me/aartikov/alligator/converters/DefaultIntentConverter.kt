package me.aartikov.alligator.converters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;

import me.aartikov.alligator.Screen;


/**
 * Creates an intent that starts an activity of the given class. It also puts a screen to the intent's extra if {@code ScreenT} is {@code Serializable} or {@code Parcelable}.
 *
 * @param <ScreenT> screen type
 */
public class DefaultIntentConverter<ScreenT extends Screen> implements IntentConverter<ScreenT> {
	private static final String KEY_SCREEN = "me.aartikov.alligator.KEY_SCREEN";

	private Class<ScreenT> mScreenClass;
	private Class<? extends Activity> mActivityClass;

	public DefaultIntentConverter(Class<ScreenT> screenClass, Class<? extends Activity> activityClass) {
		mScreenClass = screenClass;
		mActivityClass = activityClass;
	}

	@Override
	@NonNull
	public Intent createIntent(@NonNull Context context, @NonNull ScreenT screen) {
		Intent intent = new Intent(context, mActivityClass);
		if (screen instanceof Serializable) {
			intent.putExtra(KEY_SCREEN, (Serializable) screen);
		} else if (screen instanceof Parcelable) {
			intent.putExtra(KEY_SCREEN, (Parcelable) screen);
		}
		return intent;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Nullable
	public ScreenT getScreen(@NonNull Intent intent) {
		if (Serializable.class.isAssignableFrom(mScreenClass)) {
			return (ScreenT) intent.getSerializableExtra(KEY_SCREEN);
		} else if (Parcelable.class.isAssignableFrom(mScreenClass)) {
			return (ScreenT) intent.getParcelableExtra(KEY_SCREEN);
		} else {
			throw new IllegalArgumentException("Screen " + mScreenClass.getSimpleName() + " should be Serializable or Parcelable.");
		}
	}
}