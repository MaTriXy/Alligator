package com.art.advancedscreenswitchersample.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.art.alligator.NavigationContext;
import com.art.alligator.NavigationContextBinder;
import com.art.alligator.NavigationFactory;
import com.art.alligator.Navigator;
import com.art.alligator.TransitionAnimation;
import com.art.alligator.implementation.FragmentScreenSwitcher;
import com.art.advancedscreenswitchersample.R;
import com.art.advancedscreenswitchersample.SampleAnimationProvider;
import com.art.advancedscreenswitchersample.SampleApplication;
import com.art.advancedscreenswitchersample.screens.TabScreen;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Date: 21.01.2016
 * Time: 23:30
 *
 * @author Artur Artikov
 */
public class MainActivity extends AppCompatActivity implements OnTabSelectListener {
	private static final String ANDROID_SCREEN_NAME = "ANDROID";
	private static final String BUG_SCREEN_NAME = "BUG";
	private static final String DOG_SCREEN_NAME = "DOG";

	private Navigator mNavigator;
	private NavigationContextBinder mNavigationContextBinder;
	private TabsInfo mTabsInfo;
	private FragmentScreenSwitcher mScreenSwitcher;

	@BindView(R.id.activity_main_bottom_bar)
	BottomBar mBottomBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mNavigator = SampleApplication.getNavigator();
		mNavigationContextBinder = SampleApplication.getNavigationContextBinder();
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		mBottomBar.setOnTabSelectListener(this, false);
		initTabsInfo();
		initScreenSwitcher();

		if (savedInstanceState == null) {
			mNavigator.switchTo(ANDROID_SCREEN_NAME);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		bindNavigationContext();
	}

	@Override
	protected void onPause() {
		mNavigationContextBinder.unbind();
		super.onPause();
	}

	@Override
	public void onBackPressed() {
		mNavigator.goBack();
	}

	@Override
	public void onTabSelected(@IdRes int tabId) {
		mNavigator.switchTo(mTabsInfo.getScreenName(tabId));
	}

	private void initTabsInfo() {
		mTabsInfo = new TabsInfo();
		mTabsInfo.add(ANDROID_SCREEN_NAME, R.id.tab_android, new TabScreen(getString(R.string.tab_android)));
		mTabsInfo.add(BUG_SCREEN_NAME, R.id.tab_bug, new TabScreen(getString(R.string.tab_bug)));
		mTabsInfo.add(DOG_SCREEN_NAME, R.id.tab_dog, new TabScreen(getString(R.string.tab_dog)));
	}

	private void initScreenSwitcher() {
		mScreenSwitcher = new FragmentScreenSwitcher(getSupportFragmentManager(), R.id.activity_main_container) {
			@Override
			protected Fragment createFragment(String screenName) {
				NavigationFactory navigationFactory = SampleApplication.getNavigationFactory();
				return navigationFactory.createFragment(mTabsInfo.getScreen(screenName));
			}

			@Override
			protected TransitionAnimation getAnimation(String screenNameFrom, String screenNameTo) {
				int indexFrom = mTabsInfo.getTabIndex(screenNameFrom);
				int indexTo = mTabsInfo.getTabIndex(screenNameTo);
				if(indexTo > indexFrom) {
					return new TransitionAnimation(R.anim.slide_in_right, R.anim.slide_out_left);
				} else {
					return new TransitionAnimation(R.anim.slide_in_left, R.anim.slide_out_right);
				}
			}

			@Override
			protected void onScreenSwitched(String screenName) {
				bindNavigationContext();
				selectTab(mTabsInfo.getTabId(screenName));
			}
		};
	}

	private void bindNavigationContext() {
		Fragment fragment = mScreenSwitcher.getCurrentFragment();
		NavigationContext.Builder builder = new NavigationContext.Builder(this)
				.screenSwitcher(mScreenSwitcher)
				.animationProvider(new SampleAnimationProvider());

		if (fragment != null && fragment instanceof ContainerIdProvider) {
			int containerId = ((ContainerIdProvider) fragment).getContainerId();
			builder.fragmentManagerAndContainerId(fragment.getChildFragmentManager(), containerId);
		}
		mNavigationContextBinder.bind(builder.build());
	}

	private void selectTab(@IdRes int tabId) {
		mBottomBar.setOnTabSelectListener(null);    // workaround to don't call listener
		mBottomBar.selectTabWithId(tabId);
		mBottomBar.setOnTabSelectListener(this, false);
	}
}