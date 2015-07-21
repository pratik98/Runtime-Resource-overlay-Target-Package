/*
 * Copyright (c) 2014, Sony Mobile Communications AB
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.example.android.rrotarget;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.android.rrotarget.R;

import java.util.Locale;

public class Main extends Activity {
    private static final int COLOR_ROW_0 = Color.rgb(250, 250, 250);
    private static final int COLOR_ROW_1 = Color.rgb(240, 240, 240);

    private void setConfiguration(String which) {
        Resources r = getResources();
        Configuration c = new Configuration();
        c.setToDefaults();
        if ("English".equals(which)) {
            Log.d("lang","en_US");
            c.locale = new Locale("en_US");
        }
        if ("Spanish".equals(which)) {
            Log.d("lang","es_ES");
            c.locale = new Locale("es_ES");
        }
        if ("French".equals(which)) {
            Log.d("lang","fr_FR");
            c.locale = new Locale("fr_FR");
        }
        if ("Hindi".equals(which)) {
            Log.d("lang","hi_IN");
            c.locale = new Locale("hi_IN");
        }
        if ("Japanese".equals(which)) {
            Log.d("lang","ja_JA");
            c.locale = new Locale("ja_JA");
        }
        r.updateConfiguration(c, r.getDisplayMetrics());

        /*
         * Explicitly refresh displayed resources since setting a spinner value
         * obviously doesn't trigger onConfigurationChanged.
         */
        drawResources();
    }

    private TextView createTextView(String s) {
        LayoutParams lp = new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f);
        TextView tv = new TextView(this);
        tv.setLayoutParams(lp);
        tv.setTextColor(Color.BLACK);
        tv.setText(s);
        return tv;
    }

    private void addRow(TableLayout table, String key, View value) {
        TableRow row = new TableRow(this);
        row.setBackgroundColor(table.getChildCount() % 2 == 0 ? COLOR_ROW_0 : COLOR_ROW_1);
        row.setPadding(2, 0, 2, 0);

        TextView tv = createTextView(key);

        value.setPadding(50, 50, 2, 4);
      //  row.addView(tv);

        row.addView(value);

        table.addView(row);
    }



    private void addString(TableLayout table, String key, String value) {
        TextView tv = createTextView(value);
        addRow(table, key, tv);
    }



    private void drawResources() {
        Resources r = getResources();
        Log.i("resources",""+r);
        TableLayout table = (TableLayout)findViewById(R.id.table);
        Configuration c = r.getConfiguration();
        table.removeAllViews();
        //addString(table, "Locale", c.locale.toString());

            addString(table, "", getString(R.string.hello));


    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.main);

        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.config_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView parent, View view,
                int position, long id) {
                TextView tv = (TextView)view;
                Log.d("spinner",tv.getText().toString());
                setConfiguration(tv.getText().toString());
            }

            public void onNothingSelected(AdapterView parent) {}
        });
    }
}
