package de.pfeufferweb.android.whereru;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class MainPreferenceActivity extends PreferenceActivity {
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	}
}
