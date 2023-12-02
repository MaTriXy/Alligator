package me.aartikov.navigationmethodssample.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Random;

import me.aartikov.alligator.Navigator;
import me.aartikov.alligator.annotations.RegisterScreen;
import me.aartikov.navigationmethodssample.R;
import me.aartikov.navigationmethodssample.SampleApplication;
import me.aartikov.navigationmethodssample.screens.TestSmallScreen;


@RegisterScreen(TestSmallScreen.class)
public class TestFragment extends Fragment {

	View mRootView;
	TextView mCounterTextView;
	Button mForwardButton;
	Button mReplaceButton;
	Button mResetButton;
	Button mBackButton;
	Button mDoubleBackButton;

	private final Navigator mNavigator = SampleApplication.getNavigator();

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_test, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRootView = view.findViewById(R.id.root_view);
        mCounterTextView = view.findViewById(R.id.counter_text_view);
        mForwardButton = view.findViewById(R.id.forward_button);
        mReplaceButton = view.findViewById(R.id.replace_button);
        mResetButton = view.findViewById(R.id.reset_button);
        mBackButton = view.findViewById(R.id.back_button);
        mDoubleBackButton = view.findViewById(R.id.double_back_button);

        TestSmallScreen screen = SampleApplication.getScreenResolver().getScreen(this);
        int counter = screen.getCounter();
        mCounterTextView.setText(getString(R.string.counter_template, counter));

        mForwardButton.setOnClickListener(v -> mNavigator.goForward(new TestSmallScreen(counter + 1)));
        mReplaceButton.setOnClickListener(v -> mNavigator.replace(new TestSmallScreen(counter)));
        mResetButton.setOnClickListener(v -> mNavigator.reset(new TestSmallScreen(1)));
        mBackButton.setOnClickListener(v -> mNavigator.goBack());
        mDoubleBackButton.setOnClickListener(v -> {
			mNavigator.goBack();        // Due to a command queue in AndroidNavigator you can combine navigation methods arbitrarily.
			mNavigator.goBack();
		});

		mRootView.setBackgroundColor(getRandomColor());
	}

	private static int getRandomColor() {
		Random random = new Random();
		return Color.HSVToColor(new float[]{random.nextInt(360), 0.1f, 0.8f});
	}
}
