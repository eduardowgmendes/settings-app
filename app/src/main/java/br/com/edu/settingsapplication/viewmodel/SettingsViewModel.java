package br.com.edu.settingsapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.edu.settingsapplication.R;
import br.com.edu.settingsapplication.database.repository.SettingsRepository;
import br.com.edu.settingsapplication.database.entities.SettingEntity;

public class SettingsViewModel extends AndroidViewModel {

    private SettingsRepository settingsRepository;

    private final LiveData<List<SettingEntity>> allSettings;

    public SettingsViewModel(@NonNull Application application) {
        super(application);
        this.settingsRepository = new SettingsRepository(application);
        this.allSettings = settingsRepository.getAllSettings();
    }

    public LiveData<List<SettingEntity>> getAllSettings() {
        return this.allSettings;
    }

    public void insert(SettingEntity setting) {
        this.settingsRepository.insert(setting);
    }

    public LiveData<List<SettingEntity>> findByName(String settingName) {
        return this.settingsRepository.findByName(settingName);
    }

    public void deleteAll() {
        this.settingsRepository.deleteAll();
    }

    public void delete(SettingEntity setting) {
        this.settingsRepository.delete(setting);
    }

}
