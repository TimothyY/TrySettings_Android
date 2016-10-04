package timothyyudi.trysettings_android;

import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MySettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedStateInstanceState) {
        super.onCreate(savedStateInstanceState);

        // Display fragment as main content
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
    }

    public static class SettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.my_settings);  //load preference screen from xml directory.
        }
    }
}
