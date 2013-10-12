package de.imnue.GoEuro_Autocompletion;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import de.imnue.GoEuro_Autocompletion.Entities.Position;
import java.util.ArrayList;

/**
 * Copyright (c) 2013, Brian Nuernberg
 * All rights reserved.

 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the
 * following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name of Brian Nuernberg nor the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
 * AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

public class FormActivity extends Activity implements AutoCompleteResponse, DateSet {
    /**
     * Called when the activity is first created.
     */
    final private static String dateDivider = ".";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        AutoCompleteTextView autoCompleteTextViewStartingPoint = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextViewStartingPoint);
        PositionAdapter adapterStartingPoint = new PositionAdapter(this, R.layout.position_row, null);
        AutoCompleteTextWatcher startingPointTextWatcher = new AutoCompleteTextWatcher(autoCompleteTextViewStartingPoint, this);
        autoCompleteTextViewStartingPoint.addTextChangedListener(startingPointTextWatcher);
        autoCompleteTextViewStartingPoint.setAdapter(adapterStartingPoint);

        AutoCompleteTextView autoCompleteTextViewDestination = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextViewDestination);
        PositionAdapter adapterDestination = new PositionAdapter(this, R.layout.position_row, null);
        AutoCompleteTextWatcher destinationTextWatcher = new AutoCompleteTextWatcher(autoCompleteTextViewDestination, this);
        autoCompleteTextViewDestination.addTextChangedListener(destinationTextWatcher);
        autoCompleteTextViewDestination.setAdapter(adapterDestination);

        Button searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setText(R.string.btn_search_title);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), R.string.missing_feature, 5);
                toast.show();
            }
        });
        TextView dateTextView = (TextView)findViewById(R.id.dateView);
        dateTextView.setText(SimpleDateFormatter.currentDate());
    }

    public void backendReponseFinish(ArrayList<Position> response, AutoCompleteTextView textView) {
        if (response != null) {
            PositionAdapter adapter = new PositionAdapter(this, R.layout.position_row, response);
            textView.setAdapter(adapter);

            adapter.notifyDataSetChanged();
        }
    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        ((DatePickerFragment)newFragment).delegate = this;
        newFragment.show(getFragmentManager(), "datePicker");
    }


    @Override
    public void dateSet(int year, int monthOfYear, int dayOfMonth) {
        EditText dateView = (EditText)findViewById(R.id.dateView);

       dateView.setText(String.valueOf(dayOfMonth) + dateDivider + String.valueOf(monthOfYear) + dateDivider + String.valueOf(year));
    }
}
