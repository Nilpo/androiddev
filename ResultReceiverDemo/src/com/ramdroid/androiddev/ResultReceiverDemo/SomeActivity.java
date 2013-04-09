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

    public void getSomeText(View v) {
        Intent i = new Intent(this, SomeService.class);
        i.putExtra(SomeService.REQUEST_RECEIVER_EXTRA, new ResultReceiver(null) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                if (resultCode == SomeService.RESULT_ID_QUOTE) {
                    String result = resultData.getString("result");
                    updateText(result);
                }
            }
        });
        startService(i);
    }

    public void getAnotherOne(View v) {
        setProgressBarIndeterminateVisibility(true);
        Intent i = new Intent(this, LongService.class);
        i.putExtra(LongService.REQUEST_RECEIVER_EXTRA, new ResultReceiver(null) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                if (resultCode == LongService.RESULT_ID_QUOTE) {
                    final String result = resultData.getString("result");

                    // the result is still in the other thread, so we have to make sure
                    // that the text is updated back in the UI thread!
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            setProgressBarIndeterminateVisibility(false);
                            updateText(result);
                        }
                    });
                }
            }
        });
        startService(i);
    }

}
