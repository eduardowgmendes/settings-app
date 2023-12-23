package br.com.edu.settingsapplication.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.edu.settingsapplication.R;
import br.com.edu.settingsapplication.preferences.SharedPreferencesHelper;
import br.com.edu.settingsapplication.ui.adapters.viewholders.enums.VisualisationMode;

public class SettingsActivity extends AppCompatActivity {

    private SharedPreferences visualisationModeSharedPreferences;
    private SharedPreferences nightModeSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        visualisationModeSharedPreferences = getSharedPreferences("visualisation_mode_preferences", Context.MODE_PRIVATE);
        nightModeSharedPreferences = getSharedPreferences("night_mode_shared_preferences", Context.MODE_PRIVATE);

        setSupportActionBar(findViewById(R.id.settings_toolbar));
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        RadioGroup visualisationModesRadioGroup = findViewById(R.id.visualisation_modes_radio_group);

        TextInputLayout organisationModeSelector = findViewById(R.id.organisation_mode_menu);
        AutoCompleteTextView organisationModeSelectorAutoComplete = (AutoCompleteTextView) organisationModeSelector.getEditText();
        ArrayAdapter<String> organisationModeArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, provideOrganisationModesOptions());
        organisationModeSelectorAutoComplete.setAdapter(organisationModeArrayAdapter);

        checkSavedVisualisationMode(visualisationModesRadioGroup);

        visualisationModesRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            saveVisualisationMode(checkedId);
        });


        MaterialSwitch nightModeSwitch = findViewById(R.id.night_mode_switch);
        nightModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> saveNightMode(isChecked));

        if (isAtNightMode())
            nightModeSwitch.setChecked(true);

    }

    private void checkSavedVisualisationMode(RadioGroup visualisationModesRadioGroup) {
        if (retrieveVisualisationMode().isListMode())
            visualisationModesRadioGroup.check(R.id.list_mode_radio_button);

        if (retrieveVisualisationMode().isGridMode())
            visualisationModesRadioGroup.check(R.id.grid_mode_radio_button);

        if (retrieveVisualisationMode().isStaggeredGridMode())
            visualisationModesRadioGroup.check(R.id.staggered_grid_mode_radio_button);
    }

    private void saveVisualisationMode(int checkedId) {
        if (checkedId == R.id.list_mode_radio_button) {
            setVisualisationMode(VisualisationMode.LIST_MODE);
        } else if (checkedId == R.id.grid_mode_radio_button) {
            setVisualisationMode(VisualisationMode.GRID_MODE);
        } else if (checkedId == R.id.staggered_grid_mode_radio_button) {
            setVisualisationMode(VisualisationMode.STAGGERED_GRID_MODE);
        }
    }

    private List<String> provideOrganisationModesOptions() {
        List<String> options = new ArrayList<>();
        options.add("Alphabetically");
        options.add("Random Ordering");
        options.add("Grouped by Similarity");
        return options;
    }

    private void saveNightMode(boolean checked) {
        setNightMode(checked);
        changeToNightMode(checked);
    }

    private void setNightMode(boolean enabled) {
        SharedPreferences.Editor editor = nightModeSharedPreferences.edit();
        editor.putBoolean(SharedPreferencesHelper.Preference.NIGHT_MODE_VALUE, enabled);
        editor.apply();
    }

    private void changeToNightMode(boolean enabled) {
        if (enabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

    }

    private void setVisualisationMode(VisualisationMode visualisationMode) {
        SharedPreferences.Editor editor = visualisationModeSharedPreferences.edit();
        editor.putString("visualisation_mode", visualisationMode.name());
        editor.apply();
    }

    private VisualisationMode retrieveVisualisationMode() {
        String visualisationMode = visualisationModeSharedPreferences.getString("visualisation_mode", VisualisationMode.LIST_MODE.name());
        return VisualisationMode.valueOf(VisualisationMode.class, visualisationMode);
    }

    private boolean isAtNightMode() {
        return nightModeSharedPreferences.getBoolean("night_mode", false);
    }

}