package timothyyudi.trysettings_android;

import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MySettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedStateInstanceState) {
        super.onCreate(savedStateInstanceState);

        // Display fragment as main content
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
    }

    public static class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener{
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.my_settings);  //load preference screen from xml directory.
            //set the sharedPreferences with the default values from the xml for the very first time
            PreferenceManager.setDefaultValues(this.getActivity(), R.xml.my_settings, false);
            // show the current value in the settings screen
            for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); i++) {
                crawlPreferenceObject(getPreferenceScreen().getPreference(i));
            }
        }

        public static final String KEY_P_EXAMPLE = "preference_example";
        public static final String KEY_SP_EXAMPLE = "switchPreference_example";
        public static final String KEY_ETP_EXAMPLE = "editTextPreference_example";
        public static final String KEY_LP_EXAMPLE = "listPreference_example";

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            //key-value object are saved automatically to default SharedPreferences.
            //But we still have to handle the widget's summary manually.
            if (key.equals(KEY_LP_EXAMPLE)) {
                ListPreference listPref = (ListPreference) findPreference(key);
                listPref.setSummary("This is an example of List Preference. \nUse this for a predefined-String value option. \nCurrently set into the " + sharedPreferences.getString(key, "") + ".");
            }
        }

        @Override
        public void onResume() {
            super.onResume();
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            super.onPause();
            getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        }

        private void crawlPreferenceObject(Preference p) {
            if (p instanceof PreferenceCategory) {
                PreferenceCategory prefCat = (PreferenceCategory) p;
                for (int i = 0; i < prefCat.getPreferenceCount(); i++) {
                    crawlPreferenceObject(prefCat.getPreference(i));
                }
            } else {
                syncSummary(p);
            }
        }

        private void syncSummary(Preference p) {
            if(p.getKey().compareTo(KEY_LP_EXAMPLE)==0){
                p.setSummary("This is an example of List Preference. \nUse this for a predefined-String value option. \nCurrently set into the "+
                        PreferenceManager.getDefaultSharedPreferences(this.getActivity()).getString(KEY_LP_EXAMPLE,"")+".");
            }
        }
    }
}
