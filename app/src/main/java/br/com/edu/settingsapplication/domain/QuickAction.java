package br.com.edu.settingsapplication.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class QuickAction implements Parcelable {
    private String title;
    private String actionName;
    private Type type;

    public QuickAction(String title, Type type) {
        this.title = title;
        this.type = type;
    }

    public QuickAction(String title, String actionName, Type type) {
        this.title = title;
        this.actionName = actionName;
        this.type = type;
    }

    protected QuickAction(Parcel in) {
        title = in.readString();
        actionName = in.readString();
    }

    public static final Creator<QuickAction> CREATOR = new Creator<QuickAction>() {
        @Override
        public QuickAction createFromParcel(Parcel in) {
            return new QuickAction(in);
        }

        @Override
        public QuickAction[] newArray(int size) {
            return new QuickAction[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(actionName);
    }

    public enum Type {
        INFORMATIONAL, SWITCH, CHECKBOX, BUTTON;

        public boolean isInformational() {
            return this == INFORMATIONAL;
        }

        public boolean isSwitch() {
            return this == SWITCH;
        }

        public boolean isCheckbox() {
            return this == CHECKBOX;
        }

        public boolean isButton() {
            return this == BUTTON;
        }

    }
}
