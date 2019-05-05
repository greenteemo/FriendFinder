package com.group.friendfinder.View.my;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.group.friendfinder.R;


public class EditProfile extends AppCompatActivity {

    private EditText et[] = new EditText[11];
    private enum mET{
        profile_edit_first_name,
        profile_edit_surname,
        profile_edit_birthday,
        profile_edit_course,
        profile_edit_address,
        profile_edit_suburb,
        profile_edit_native_language,
        profile_edit_favourite_sport,
        profile_edit_favourite_movie,
        profile_edit_favourite_unit,
        profile_edit_current_job
    }
    private Spinner sp[] = new Spinner[3];
    private enum mSP {
        profile_edit_gender,
        profile_edit_study_mode,
        profile_edit_nationality
    }
    private Button mbtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        this.et[mET.profile_edit_first_name.ordinal()] = findViewById(R.id.profile_edit_first_name);
        this.et[mET.profile_edit_surname.ordinal()] = findViewById(R.id.profile_edit_surname);
        this.et[mET.profile_edit_birthday.ordinal()] = findViewById(R.id.profile_edit_birthday);
        this.et[mET.profile_edit_course.ordinal()] = findViewById(R.id.profile_edit_course);
        this.et[mET.profile_edit_address.ordinal()] = findViewById(R.id.profile_edit_address);
        this.et[mET.profile_edit_suburb.ordinal()] = findViewById(R.id.profile_edit_suburb);
        this.et[mET.profile_edit_native_language.ordinal()] = findViewById(R.id.profile_edit_native_language);
        this.et[mET.profile_edit_favourite_sport.ordinal()] = findViewById(R.id.profile_edit_favourite_sport);
        this.et[mET.profile_edit_favourite_movie.ordinal()] = findViewById(R.id.profile_edit_favourite_movie);
        this.et[mET.profile_edit_favourite_unit.ordinal()] = findViewById(R.id.profile_edit_favourite_unit);
        this.et[mET.profile_edit_current_job.ordinal()] = findViewById(R.id.profile_edit_current_job);
        this.sp[mSP.profile_edit_gender.ordinal()] = findViewById(R.id.profile_edit_gender);
        this.sp[mSP.profile_edit_study_mode.ordinal()] = findViewById(R.id.profile_edit_study_mode);
        this.sp[mSP.profile_edit_nationality.ordinal()] = findViewById(R.id.profile_edit_nationality);
        this.mbtn1 = findViewById(R.id.profile_button);

        mbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et[2].setHintTextColor(Color.RED);
            }
        });
    }
}
