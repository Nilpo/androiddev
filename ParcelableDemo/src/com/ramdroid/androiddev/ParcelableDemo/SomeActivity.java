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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SomeActivity extends Activity
{
    private SomeData someData1 = new SomeData(42, "Something1");
    private SomeData someData2 = new SomeData(43, "Something2");
    private MoreData moreData = new MoreData();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        moreData.add(someData1);
        moreData.add(someData2);

        setContentView(R.layout.some);
        ((TextView)findViewById(R.id.someText)).setText(moreData.toString());
    }

    public void doSomething(View v) {
        startActivity(new Intent(this, YetSomeActivity.class)
                .putExtra("Something", someData1)
                .putExtra("SomeMore", moreData));
    }
}
