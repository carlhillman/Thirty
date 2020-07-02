package se.umu.carl.thirty.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Die implements Parcelable {
    public Boolean selected ;
    public int value;
    String color;
    String number;
    public Die(Boolean selected, int value) {
        this.selected = selected;
        this.value = value;
    }

    protected Die(Parcel in) {
        byte tmpSelected = in.readByte();
        selected = tmpSelected == 0 ? null : tmpSelected == 1;
        value = in.readInt();
        color = in.readString();
        number = in.readString();
    }

    public static final Creator<Die> CREATOR = new Creator<Die>() {
        @Override
        public Die createFromParcel(Parcel in) {
            return new Die(in);
        }

        @Override
        public Die[] newArray(int size) {
            return new Die[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeByte((byte) (selected == null ? 0 : selected ? 1 : 2));
        out.writeInt(value);
        out.writeString(color);
        out.writeString(number);
    }

}
