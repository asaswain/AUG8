package asa.scps.nyu.edu.nycmaps;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    boolean firstTime[] = {true};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // listener for map type view
        AdapterView.OnItemSelectedListener cbxListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getId() == (R.id.mapType)) {
                    if (firstTime[0]) {
                        firstTime[0] = false;
                        return;
                    }
                    String type = (String) parent.getItemAtPosition(position);
                    try {
                        MapSettings.setMapType(type);
                    } catch (IllegalArgumentException exc) {
                        // do nothing
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };

        // map type view
        Spinner spinnerMapType = (Spinner) findViewById(R.id.mapType);

        // build a list of dates for this week
        String[] typeList = {"Normal", "Hybrid", "Satellite", "Terrain"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                typeList
        );

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMapType.setAdapter(arrayAdapter);
        spinnerMapType.setOnItemSelectedListener(cbxListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void viewMap(View v) {
        Intent intent = new Intent(SettingsActivity.this, MapsActivity.class);

        EditText angleText = (EditText) findViewById(R.id.cameraAngle);
        Float cameraTilt = Float.valueOf(angleText.getText().toString());
        try {
            MapSettings.setCameraTilt(cameraTilt);

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException activityNotFoundException) {
                Toast toast = Toast.makeText(SettingsActivity.this, activityNotFoundException.toString(), Toast.LENGTH_LONG);
                toast.show();
            }
        } catch (IllegalArgumentException e) {
            Toast toast = Toast.makeText(SettingsActivity.this, e.toString(), Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
