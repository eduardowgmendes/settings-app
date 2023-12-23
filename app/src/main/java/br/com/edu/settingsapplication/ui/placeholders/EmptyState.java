package br.com.edu.settingsapplication.ui.placeholders;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Objects;

public class EmptyState implements Parcelable {
    private int backdropRes;

    private int backdropTintRes;
    private String title;
    private String description;

    public EmptyState(int backdropRes, int backdropTintRes, String title, String description) {
        this.backdropRes = backdropRes;
        this.backdropTintRes = backdropTintRes;
        this.title = title;
        this.description = description;
    }

    protected EmptyState(Parcel in) {
        backdropRes = in.readInt();
        backdropTintRes = in.readInt();
        title = in.readString();
        description = in.readString();
    }

    public static final Creator<EmptyState> CREATOR = new Creator<EmptyState>() {
        @Override
        public EmptyState createFromParcel(Parcel in) {
            return new EmptyState(in);
        }

        @Override
        public EmptyState[] newArray(int size) {
            return new EmptyState[size];
        }
    };

    public int getBackdropRes() {
        return backdropRes;
    }

    public void setBackdropRes(int backdropRes) {
        this.backdropRes = backdropRes;
    }

    public int getBackdropTintRes() {
        return backdropTintRes;
    }

    public void setBackdropTintRes(int backdropTintRes) {
        this.backdropTintRes = backdropTintRes;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmptyState that = (EmptyState) o;
        return backdropRes == that.backdropRes && Objects.equals(title, that.title) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(backdropRes, title, description);
    }

    @Override
    public String toString() {
        return "EmptyState{" +
                "backdropRes=" + backdropRes +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(backdropRes);
        dest.writeInt(backdropTintRes);
        dest.writeString(title);
        dest.writeString(description);
    }
}
