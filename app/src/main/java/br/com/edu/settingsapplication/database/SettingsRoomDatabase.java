package br.com.edu.settingsapplication.database;

import static br.com.edu.settingsapplication.domain.QuickAction.Type.*;

import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.com.edu.settingsapplication.R;
import br.com.edu.settingsapplication.database.converter.QuickActionsConverter;
import br.com.edu.settingsapplication.database.dao.SettingsDAO;
import br.com.edu.settingsapplication.database.entities.SettingEntity;
import br.com.edu.settingsapplication.domain.QuickAction;

@Database(entities = {SettingEntity.class}, version = 1, exportSchema = false)
@TypeConverters({QuickActionsConverter.class})
public abstract class SettingsRoomDatabase extends RoomDatabase {

    public abstract SettingsDAO settingDAO();

    private static volatile SettingsRoomDatabase INSTANCE;
    private static final int THREADS = 4;

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(THREADS);

    public static SettingsRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (SettingsRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), SettingsRoomDatabase.class, "settings_database").fallbackToDestructiveMigration().addMigrations(new Migration(1, 2) {
                                @Override
                                public void migrate(@NonNull SupportSQLiteDatabase database) {
                                    databaseWriteExecutor.execute(() -> {
                                        INSTANCE.settingDAO().deleteAll();
                                    });
                                }
                            }).addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    databaseWriteExecutor.execute(() -> {
                                        SettingsDAO settingsDAO = INSTANCE.settingDAO();

                                        settingsDAO.deleteAll();

                                        // Default Settings
                                        settingsDAO.insert(newSetting(context, R.drawable.twotone_aod_24, R.color.sea_blue, R.color.sea_blue_200, "About phone", "This section provides detailed information about the device, such as model, operating system version, battery status, and other device-related settings.", "MIUI Global 14.0.23", quickActions(action("System Version", "Android 12", INFORMATIONAL), action("Device Name", "Redmi Note 11S", INFORMATIONAL))));
                                        settingsDAO.insert(newSetting(context, R.drawable.twotone_arrow_circle_up_24, R.color.noon_sky, R.color.noon_sky_200, "System app updater", "Allows checking and updating system apps to ensure the device is running the latest software version.", null, quickActions(action("Last Update", "Check Now", BUTTON))));
                                        settingsDAO.insert(newSetting(context, R.drawable.twotone_beenhere_24, R.color.green_fields, R.color.green_fields_200, "Security status", "Displays the device's security status, including security updates, malware scans, and other settings related to device protection.", null, quickActions(action("Security update", "2023-10-01", INFORMATIONAL), action("Find Device", "Currently unavailable", SWITCH))));

                                        settingsDAO.insert(newSetting(context, R.drawable.twotone_auto_awesome_motion_24, R.color.sunrise_sky, R.color.sunrise_sky_200, "SIM cards & mobile networks", "Manages settings related to SIM cards, such as mobile network information, roaming settings, and network preferences.", null, quickActions(action("SIM Card 1", "Available Network", INFORMATIONAL), action("SIM Card 2", "OFF", INFORMATIONAL))));
                                        settingsDAO.insert(newSetting(context, R.drawable.twotone_album_24, R.color.sea_blue, R.color.sea_blue_200, "Wi-Fi", "Enables the configuration and management of Wi-Fi connections, including adding networks, advanced settings, and connection details.", "MIOffice-5G", quickActions(action("Wi-Fi connection", "MIOffice-5G", SWITCH), action("Networks ", "Select a new network to connect", INFORMATIONAL))));
                                        settingsDAO.insert(newSetting(context, R.drawable.twotone_bluetooth_24, R.color.sea_blue, R.color.sea_blue_200, "Bluetooth", "Controls Bluetooth settings, including device pairing, visibility, and other options related to wireless connectivity.", "Off", quickActions(action("Available devices", "Search for near devices with bluetooth", INFORMATIONAL), action("Bluetooth", "OFF", SWITCH))));
                                        settingsDAO.insert(newSetting(context, R.drawable.twotone_brightness_auto_24, R.color.noon_sky, R.color.noon_sky_200, "Connection & sharing", "Groups settings related to connectivity, such as VPN, internet sharing, printing, and other connection options.", null, quickActions(action("Manage Connections", "VPN", INFORMATIONAL), action("Interconnectivity", "Mi Share", INFORMATIONAL), action("Airplane mode", "OFF", SWITCH))));

                                        settingsDAO.insert(newSetting(context, R.drawable.twotone_bathroom_24, R.color.grayish_night, R.color.grayish_night_200, "Always-on display & Lock screen", "Manages settings related to the always-on display, lock screen, and security options like PIN, password, or facial recognition.", null, quickActions(action("Sleep", "After 5 minutes of inactivity", INFORMATIONAL), action("Always on display", "OFF", SWITCH), action("Raise to wake", "OFF", SWITCH))));
                                        settingsDAO.insert(newSetting(context, R.drawable.twotone_brightness_7_24, R.color.sunrise_sky, R.color.sunrise_sky_200, "Display", "Controls display settings such as brightness, night mode, font size, and color calibration.", null, quickActions(action("Brightness level", "AUTO", INFORMATIONAL), action("Dark Mode", "Unknown", SWITCH), action("Font", "Select a font for the System UI", INFORMATIONAL))));
                                        settingsDAO.insert(newSetting(context, R.drawable.twotone_audiotrack_24, R.color.green_fields, R.color.green_fields, "Sound & vibration", "Allows customization of audio settings, including volume, ringtones, notifications, and vibration patterns.", null, quickActions(action("Notifications", "Fade in", INFORMATIONAL), action("Ringtone", "Mi (Remix)", INFORMATIONAL), action("Alarm", "Nature alarm", INFORMATIONAL), action("Sound options", "Silence incoming callas and notifications", SWITCH))));
                                        settingsDAO.insert(newSetting(context, R.drawable.twotone_add_alert_24, R.color.sea_blue, R.color.sea_blue_200, "Notifications & Control center", "Configures options related to notifications, such as managing priority apps, sound settings, and customizing the control center.", null, quickActions(action("Notifications", "App notifications", INFORMATIONAL), action("Control center", "Control center style", INFORMATIONAL), action("Status bar", "Configure status bar theme and colors", INFORMATIONAL))));
                                        settingsDAO.insert(newSetting(context, R.drawable.twotone_bedroom_baby_24, R.color.sunrise_sky, R.color.sunrise_sky_200, "Home screen", "Provides customization options for the home screen, including layout, widgets, and appearance settings.", null, quickActions(action("System navigation", "Buttons", INFORMATIONAL), action("Home screen settings", "Fill cells of uninstalled apps", SWITCH), action("Suggestions", "Show suggestions", SWITCH), action("Recents", "Arrange items in Recents", INFORMATIONAL), action("Blur app previews", "Bluer app previews in Recents", INFORMATIONAL))));
                                        settingsDAO.insert(newSetting(context, R.drawable.twotone_add_photo_alternate_24, R.color.noon_sky, R.color.noon_sky_200, "Wallpaper", "Allows the selection and configuration of wallpapers for the home and lock screens.", null, quickActions(action("Online wallpapers", "Choose from thousands of amazing wallpapers", INFORMATIONAL))));
                                        settingsDAO.insert(newSetting(context, R.drawable.twotone_app_shortcut_24, R.color.grayish_night, R.color.grayish_night_200, "Themes", "Offers theme customization options to modify the overall system appearance, including icons, colors, and styles.", null, quickActions(action("Theme", "Select a theme for your mobile device", INFORMATIONAL))));

                                        settingsDAO.insert(newSetting(context, R.drawable.twotone_badge_24, R.color.green_fields, R.color.green_fields_200, "Security & passwords", "Manages security settings, such as screen lock, passwords, and unlock methods.", null, quickActions(action("Screen lock", "ON", INFORMATIONAL), action("Fingerprint unlock", "ON", INFORMATIONAL), action("Face unlock", "OFF", INFORMATIONAL), action("Unlock with bluetooth", "OFF", INFORMATIONAL), action("Authorization & revocation", "Grant or revoke authorization of individual apps", INFORMATIONAL))));
                                        settingsDAO.insert(newSetting(context, R.drawable.twotone_assignment_late_24, R.color.sunrise_sky, R.color.sunrise_sky_200, "Safety & emergency", "Includes security settings and emergency contact options for critical situations.", null, quickActions(action("Medical information", "Name, blood type and more", INFORMATIONAL), action("Emergency Location Service", "ON", INFORMATIONAL))));
                                        settingsDAO.insert(newSetting(context, R.drawable.twotone_beenhere_24, R.color.sea_blue, R.color.sea_blue_200, "Privacy protection", "Settings related to user privacy, including app permissions, location settings, and other privacy options.", null, quickActions(action("Sensitive actions", "Manage apps that request sensitive permissions", INFORMATIONAL), action("Special permissions", "Monitor how apps use sensitive permissions", INFORMATIONAL))));
                                        settingsDAO.insert(newSetting(context, R.drawable.twotone_battery_charging_full_24, R.color.green_fields, R.color.green_fields_200, "Battery", "Provides information on battery consumption, power optimizations, and energy-saving options.", null, quickActions(action("Battery saver", "OFF", SWITCH), action("Energy Optimization", "Optimize battery usage in 2 scenarios", INFORMATIONAL))));
                                        settingsDAO.insert(newSetting(context, R.drawable.twotone_apps_24, R.color.noon_sky, R.color.noon_sky_200, "Apps", "Manages installed applications, including permissions, notifications, and specific app settings.", null, quickActions(action("Apps", "Manage apps", INFORMATIONAL), action("Uninstalled apps", "Manage uninstalled apps that are in trash", INFORMATIONAL))));
                                        settingsDAO.insert(newSetting(context, R.drawable.twotone_add_box_24, R.color.grayish_night, R.color.grayish_night_200, "Additional settings", "Groups various additional settings, such as language, date and time, accessibility, and other advanced system options.", null, quickActions(action("Special features", "Second space", INFORMATIONAL), action("Developer options", "Use with careful", INFORMATIONAL))));

                                    });
                                }

                                @Override
                                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                                    super.onOpen(db);
                                    onCreate(db);
                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static SettingEntity newSetting(Context context, int iconRes, int primaryColor, int secondaryColor, String title, String description, String additionalInfo, List<QuickAction> quickActions) {

        Resources resources = context.getResources();

        return new SettingEntity.Builder(title, description, iconRes)
                .additionalInfo(additionalInfo)
                .primaryColor(resources.getColor(primaryColor, null))
                .secondaryColor(resources.getColor(secondaryColor, null))
                .withQuickAction(quickActions)
                .build();
    }

    private static List<QuickAction> quickActions(QuickAction... quickActions) {
        return new ArrayList<>(Arrays.asList(quickActions));
    }

    private static QuickAction action(String actionTitle, String actionName, QuickAction.Type type) {
        return new QuickAction(actionTitle, actionName, type);
    }

    private static QuickAction action(String actionTitle, QuickAction.Type type) {
        return new QuickAction(actionTitle, type);
    }

}
