package com.art.screenresultsample.screens;

import android.net.Uri;

import com.art.alligator.Screen;
import com.art.alligator.ScreenResult;

/**
 * Date: 3/17/2017
 * Time: 10:33
 *
 * @author Artur Artikov
 */
public class ImagePickerScreen implements Screen {

	// It is convenient to declare a screen result as static inner class of the screen
	public static class Result implements ScreenResult {
		private Uri mUri;

		public Result(Uri uri) {
			mUri = uri;
		}

		public Uri getUri() {
			return mUri;
		}
	}
}