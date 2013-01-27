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
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: ramdroid
 * Date: 1/27/13
 * Time: 7:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class YetSomeActivity extends Activity {

    private SomeData someData = new SomeData();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yetsome);

        someData = getIntent().getParcelableExtra("Something");
        ((TextView)findViewById(R.id.someText)).setText(someData.toString());
    }
}
