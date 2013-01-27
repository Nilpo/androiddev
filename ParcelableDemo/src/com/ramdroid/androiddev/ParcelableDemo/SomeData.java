package com.ramdroid.androiddev.ParcelableDemo;

/**
 *    Copyright 2013 by Ronald Ammann (ramdroid)

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

import android.os.Parcel;
import android.os.Parcelable;

public class SomeData implements Parcelable {

    public int someInteger;
    public String someString;

    public SomeData() {
    }

    public SomeData(int someInteger, String someString) {
        this.someInteger = someInteger;
        this.someString = someString;
    }

    public String toString() {
        return someInteger + " " + someString;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(someInteger);
        out.writeString(someString);
    }

    public static final Parcelable.Creator<SomeData> CREATOR = new Parcelable.Creator<SomeData>() {
        public SomeData createFromParcel(Parcel in) {
            return new SomeData(in);
        }

        public SomeData[] newArray(int size) {
            return new SomeData[size];
        }
    };

    private SomeData(Parcel in) {
        this.someInteger = in.readInt();
        this.someString = in.readString();
    }
}