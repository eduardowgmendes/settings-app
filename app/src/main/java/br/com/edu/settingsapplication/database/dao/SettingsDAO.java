package br.com.edu.settingsapplication.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import br.com.edu.settingsapplication.database.SettingsRoomDatabase;
import br.com.edu.settingsapplication.database.entities.SettingEntity;

@Dao
public interface SettingsDAO {

    @Query("SELECT * FROM settings_table")
    LiveData<List<SettingEntity>> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(SettingEntity setting);

    @Delete
    void delete(SettingEntity setting);

    @Query("SELECT * FROM settings_table WHERE title LIKE :settingName")
    LiveData<List<SettingEntity>> findByName(String settingName);

    @Query("DELETE FROM settings_table")
    void deleteAll();

}
