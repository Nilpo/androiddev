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

import java.util.ArrayList;
import java.util.List;

public class MoreData implements Parcelable {

    public List<SomeData> someDataList = new ArrayList<SomeData>();

    public MoreData() {
    }

    public void add(SomeData someData) {
        someDataList.add(someData);
    }

    public String toString() {
        String result = "";
        for (SomeData someData : someDataList) {
            if (result.length() > 0) {
                result += ", ";
            }
            result += someData.toString();
        }
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeTypedList(someDataList);
    }

    public static final Creator<MoreData> CREATOR = new Creator<MoreData>() {
        public MoreData createFromParcel(Parcel in) {
            return new MoreData(in);
        }

        public MoreData[] newArray(int size) {
            return new MoreData[size];
        }
    };

    private MoreData(Parcel in) {
        in.readTypedList(someDataList, SomeData.CREATOR);
    }
}