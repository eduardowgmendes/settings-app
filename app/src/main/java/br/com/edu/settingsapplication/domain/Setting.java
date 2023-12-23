package br.com.edu.settingsapplication.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Objects;

public class Setting implements Parcelable {

    private int iconRes;
    private String title;
    private String description;
    private String additionalInfo;
    private int colorRes;
    private int backdropColor;

    private List<QuickAction> quickActions;

    public Setting(int iconRes, int colorRes, int backdropColor, String title, String description, String additionalInfo) {
        this.iconRes = iconRes;
        this.colorRes = colorRes;
        this.backdropColor = backdropColor;
        this.title = title;
        this.description = description;
        this.additionalInfo = additionalInfo;
    }

    public Setting(int iconRes, int colorRes, int backdropColor, String title, String description, String additionalInfo, List<QuickAction> quickActions) {
        this.iconRes = iconRes;
        this.colorRes = colorRes;
        this.backdropColor = backdropColor;
        this.title = title;
        this.description = description;
        this.additionalInfo = additionalInfo;
        this.quickActions = quickActions;
    }

    protected Setting(Parcel in) {
        iconRes = in.readInt();
        title = in.readString();
        description = in.readString();
        additionalInfo = in.readString();
        colorRes = in.readInt();
        backdropColor = in.readInt();
        in.readList(quickActions, QuickAction.class.getClassLoader());
    }

    public static final Creator<Setting> CREATOR = new Creator<Setting>() {
        @Override
        public Setting createFromParcel(Parcel in) {
            return new Setting(in);
        }

        @Override
        public Setting[] newArray(int size) {
            return new Setting[size];
        }
    };

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }

    public int getColorRes() {
        return colorRes;
    }

    public void setColorRes(int colorRes) {
        this.colorRes = colorRes;
    }

    public int getBackdropColor() {
        return backdropColor;
    }

    public void setBackdropColor(int backdropColor) {
        this.backdropColor = backdropColor;
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

    public boolean hasExtraInfo() {
        return additionalInfo != null;
    }

    public boolean hasQuickActions() {
        return quickActions != null;
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
        Setting setting = (Setting) o;
        return iconRes == setting.iconRes && Objects.equals(title, setting.title) && Objects.equals(additionalInfo, setting.additionalInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iconRes, title, additionalInfo);
    }

    @Override
    public String toString() {
        return "Setting{" +
                "iconRes=" + iconRes +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                ", colorRes=" + colorRes + '\'' +
                ", backdropColor=" + '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(iconRes);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(additionalInfo);
        parcel.writeInt(colorRes);
        parcel.writeInt(backdropColor);
        parcel.writeList(quickActions);
    }
}
