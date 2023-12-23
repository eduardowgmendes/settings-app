package br.com.edu.settingsapplication.database.converter;

import java.util.List;
import java.util.stream.Collectors;

import br.com.edu.settingsapplication.database.entities.SettingEntity;
import br.com.edu.settingsapplication.domain.Setting;

public class SettingConverter {
    public static SettingEntity toSettingEntity(Setting setting) {
        return new SettingEntity.Builder(setting.getTitle(), setting.getDescription(), setting.getIconRes())
                .additionalInfo(setting.getAdditionalInfo())
                .primaryColor(setting.getColorRes())
                .secondaryColor(setting.getBackdropColor())
                .withQuickAction(setting.getQuickActions())
                .build();
    }

    public static Setting toSetting(SettingEntity settingEntity) {
        return new Setting(settingEntity.getIconRes(),
                settingEntity.getPrimaryColor(),
                settingEntity.getSecondaryColor(),
                settingEntity.getTitle(),
                settingEntity.getDescription(),
                settingEntity.getAdditionalInfo(),
                settingEntity.getQuickActions());
    }

    public static List<SettingEntity> toSettingEntityList(List<Setting> settings) {
        return settings.stream()
                .map(SettingConverter::toSettingEntity)
                .collect(Collectors.toList());
    }

    public static List<Setting> toSettingList(List<SettingEntity> settings) {
        return settings.stream()
                .map(SettingConverter::toSetting)
                .collect(Collectors.toList());
    }

}
