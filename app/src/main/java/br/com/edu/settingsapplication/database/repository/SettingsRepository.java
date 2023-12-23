package br.com.edu.settingsapplication.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.edu.settingsapplication.database.SettingsRoomDatabase;
import br.com.edu.settingsapplication.database.dao.SettingsDAO;
import br.com.edu.settingsapplication.database.entities.SettingEntity;

public class SettingsRepository {

    private SettingsDAO settingsDAO;
    private LiveData<List<SettingEntity>> allSettings;

    public SettingsRepository(Application application) {
        SettingsRoomDatabase database = SettingsRoomDatabase.getDatabase(application);
        this.settingsDAO = database.settingDAO();
        this.allSettings = settingsDAO.getAll();
    }

    public LiveData<List<SettingEntity>> getAllSettings() {
        return allSettings;
    }

    public void insert(SettingEntity setting) {
        SettingsRoomDatabase.databaseWriteExecutor.execute(() -> settingsDAO.insert(setting));
    }

    public LiveData<List<SettingEntity>> findByName(String settingName) {
        return settingsDAO.findByName(settingName);
    }

    public void delete(SettingEntity setting) {
        SettingsRoomDatabase.databaseWriteExecutor.execute(() -> settingsDAO.delete(setting));
    }

    public void deleteAll() {
        SettingsRoomDatabase.databaseWriteExecutor.execute(() -> settingsDAO.deleteAll());
    }
}
