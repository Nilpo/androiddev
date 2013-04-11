package com.ramdroid.androiddev.ResultReceiverDemo;

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
import android.os.Handler;
import android.os.ResultReceiver;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class SomeActivity extends Activity
{
    Handler handler = new Handler();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.some);
    }

    private void updateText(String text) {
        ((TextView)findViewById(R.id.someText)).setText(text);
    }

    public void onClickedStartService(View v) {
        Intent i = new Intent(this, SomeService.class);
        i.putExtra(SomeService.RECEIVER_EXTRA, someResultReceiver);
        startService(i);
        onServiceStarted();
    }

    public void onClickedNextNumber(View v) {
        Intent i = new Intent(SomeService.ACTION_SEND_SOMETHING);
        sendBroadcast(i);
    }

    private void onServiceStarted() {
        setProgressBarIndeterminateVisibility(true);
        findViewById(R.id.buttonStartService).setEnabled(false);
        findViewById(R.id.buttonNextNumber).setEnabled(true);
    }

    private void onServiceFinished() {
        setProgressBarIndeterminateVisibility(false);
        findViewById(R.id.buttonStartService).setEnabled(true);
        findViewById(R.id.buttonNextNumber).setEnabled(false);
    }

    final ResultReceiver someResultReceiver = new ResultReceiver(handler) {
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            if (resultCode == SomeService.DATA) {
                String result = resultData.getString("result");
                updateText(result);
            }
            else if (resultCode == SomeService.FINISHED) {
                String result = resultData.getString("result");
                updateText(result);
                onServiceFinished();
            }
        }
    };
}
