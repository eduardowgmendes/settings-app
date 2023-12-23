package br.com.edu.settingsapplication.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;
import java.util.Objects;

import br.com.edu.settingsapplication.R;
import br.com.edu.settingsapplication.database.converter.SettingConverter;
import br.com.edu.settingsapplication.database.entities.SettingEntity;
import br.com.edu.settingsapplication.domain.Setting;
import br.com.edu.settingsapplication.preferences.SharedPreferencesHelper;
import br.com.edu.settingsapplication.ui.adapters.viewholders.enums.VisualisationMode;
import br.com.edu.settingsapplication.ui.fragments.ListFragment;
import br.com.edu.settingsapplication.viewmodel.SettingsViewModel;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences nightModeSharedPreferences;
    private SharedPreferences visualisationModeSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nightModeSharedPreferences = SharedPreferencesHelper.Preference.getNightMode(this);

        setUpNightMode(nightModeSharedPreferences.getBoolean(SharedPreferencesHelper.Preference.NIGHT_MODE_VALUE, true));

        visualisationModeSharedPreferences = SharedPreferencesHelper.Preference.getVisualisationMode(this);

        MaterialToolbar mainToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        Objects.requireNonNull(getSupportActionBar())
                .setTitle(getResources().getString(R.string.settings_title));

        SettingsViewModel settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        settingsViewModel.getAllSettings().observe(this, settingEntities -> replaceMainContent(R.id.main_content, ListFragment.newInstance(SettingConverter.toSettingList(settingEntities), retrieveVisualisationMode())));
    }

    private void setUpNightMode(boolean enable) {
        if (enable) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private VisualisationMode retrieveVisualisationMode() {
        String visualisationMode = visualisationModeSharedPreferences.getString(SharedPreferencesHelper.Preference.VISUALISATION_MODE_VALUE, VisualisationMode.LIST_MODE.name());
        return VisualisationMode.valueOf(VisualisationMode.class, visualisationMode);
    }

    private void replaceMainContent(int placeholderLayout, Fragment contentFragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(placeholderLayout, contentFragment)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.settings) {
            openActivity(MainActivity.this, SettingsActivity.class);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void openActivity(Context source, Class<? extends AppCompatActivity> target) {
        startActivity(new Intent(source, target));
    }

    private void toggleNightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        saveNightModeSettings(true);
    }

    private void saveNightModeSettings(boolean enabled) {
        nightModeSharedPreferences
                .edit()
                .putBoolean(SharedPreferencesHelper.Preference.NIGHT_MODE_VALUE, enabled)
                .apply();
    }

    private boolean isAtNightMode() {
        return nightModeSharedPreferences.getBoolean(SharedPreferencesHelper.Preference.NIGHT_MODE_VALUE, false);
    }

}