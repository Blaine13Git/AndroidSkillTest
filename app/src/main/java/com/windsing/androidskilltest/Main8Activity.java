package com.windsing.androidskilltest;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class Main8Activity extends AppCompatActivity {

    private EditText editText;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        editText = (EditText) findViewById(R.id.editText2);
        checkBox = (CheckBox) findViewById(R.id.checkBox);

        loadSavePreference();
    }

    private void loadSavePreference(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean checkBox_value = sharedPreferences.getBoolean("CheckBox_Value", false);
        String name = sharedPreferences.getString("storedName","YourName");
        if(checkBox_value){
            checkBox.setChecked(true);
        }else {
            checkBox.setChecked(false);
        }

        editText.setText(name);
    }

    private void savePreferences(String key,boolean value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }
    private void savePreferences(String key,String value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public void toSave(View view){
        savePreferences("CheckBox_Value", checkBox.isChecked());
        if(checkBox.isChecked()){
            //save
            savePreferences("storedName", editText.getText().toString());
        }
        finish();
    }
}
