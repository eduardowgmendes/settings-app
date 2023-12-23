package br.com.edu.settingsapplication.database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.edu.settingsapplication.database.converter.QuickActionsConverter;
import br.com.edu.settingsapplication.domain.QuickAction;

@Entity(tableName = "settings_table")
public class SettingEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private long id;
    private int iconRes;
    private int primaryColor;
    private int secondaryColor;
    private String title;
    private String description;
    private String additionalInfo;
    private List<QuickAction> quickActions;

    public static class Builder {
        private final int iconRes;
        private final String title;
        private final String description;
        private String additionalInfo;
        private int primaryColor;
        private int secondaryColor;
        private List<QuickAction> quickActions;

        public Builder(String title, String description, int iconRes) {
            this.title = title;
            this.description = description;
            this.iconRes = iconRes;
        }

        public Builder additionalInfo(String additionalInfo) {
            this.additionalInfo = additionalInfo;
            return this;
        }

        public Builder primaryColor(int color) {
            this.primaryColor = color;
            return this;
        }

        public Builder secondaryColor(int color) {
            this.secondaryColor = color;
            return this;
        }

        public Builder withQuickAction(List<QuickAction> quickActions) {
            this.quickActions = quickActions;
            return this;
        }

        public SettingEntity build() {
            return new SettingEntity(this);
        }

    }

    public SettingEntity(Builder builder) {
        this.iconRes = builder.iconRes;
        this.primaryColor = builder.primaryColor;
        this.secondaryColor = builder.secondaryColor;
        this.title = builder.title;
        this.description = builder.description;
        this.additionalInfo = builder.additionalInfo;
        this.quickActions = builder.quickActions;
    }

    public SettingEntity() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }

    public int getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(int primaryColor) {
        this.primaryColor = primaryColor;
    }

    public int getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(int secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public List<QuickAction> getQuickActions() {
        return quickActions;
    }

    public void setQuickActions(List<QuickAction> quickActions) {
        this.quickActions = quickActions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SettingEntity that = (SettingEntity) o;
        return id == that.id && iconRes == that.iconRes && primaryColor == that.primaryColor && secondaryColor == that.secondaryColor && Objects.equals(title, that.title) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, iconRes, primaryColor, secondaryColor, title, description);
    }

    @Override
    public String toString() {
        return "SettingEntity{" +
                "id=" + id +
                ", iconRes=" + iconRes +
                ", primaryColor=" + primaryColor +
                ", secondaryColor=" + secondaryColor +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", quickActions='" + quickActions + '\'' +
                '}';
    }
}
