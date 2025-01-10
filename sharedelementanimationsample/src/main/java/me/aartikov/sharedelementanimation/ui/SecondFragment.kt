package me.aartikov.sharedelementanimation.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import me.aartikov.alligator.animations.AnimationData;
import me.aartikov.alligator.annotations.RegisterScreen;
import me.aartikov.sharedelementanimation.R;
import me.aartikov.sharedelementanimation.SampleApplication;
import me.aartikov.sharedelementanimation.screens.SecondScreen;


@RegisterScreen(SecondScreen.class)
public class SecondFragment extends Fragment implements SharedElementProvider {

	ImageView mKittenImageView;

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_second, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mKittenImageView = view.findViewById(R.id.kitten_image_view);

		SecondScreen screen = SampleApplication.getScreenResolver().getScreen(this);
		mKittenImageView.setImageResource(screen.getKittenIndex() == 0 ? R.drawable.kitten_0 : R.drawable.kitten_1);
		mKittenImageView.setOnClickListener(v -> SampleApplication.getNavigator().goBack());
	}

	@Override
	public View getSharedElement(AnimationData animationData) {
		return mKittenImageView;
	}

	@Override
	public String getSharedElementName(AnimationData animationData) {
		SecondScreen screen = SampleApplication.getScreenResolver().getScreen(this);
		return "kitten_" + screen.getKittenIndex();
	}
}
