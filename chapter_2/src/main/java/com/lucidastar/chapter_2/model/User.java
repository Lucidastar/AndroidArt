package com.lucidastar.chapter_2.model;

import android.os.Parcel;
import android.os.Parcelable;


import java.io.Serializable;


/**
 * Created by qiuyouzone on 2018/2/5.
 */

public class User implements Parcelable,Serializable {

    public int userId;
    public String userName;
    public boolean isMale;

    public Book mBook;
    public User(Parcel in) {
        userId = in.readInt();
        userName = in.readString();
        isMale = in.readInt() == 1 ;
        mBook = in.readParcelable(Thread.currentThread().getContextClassLoader());
    }

    public User() {
    }

    public User(int userId, String userName, boolean isMale) {
        this.userId = userId;
        this.userName = userName;
        this.isMale = isMale;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(userId);
        out.writeString(userName);
        out.writeInt(isMale ? 0 : 1);
        out.writeParcelable(mBook,0);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", isMale=" + isMale +
                ", mBook=" + mBook +
                '}';
    }
}
