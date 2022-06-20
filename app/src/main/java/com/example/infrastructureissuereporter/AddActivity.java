package com.example.infrastructureissuereporter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import at.lukle.clickableareasimage.OnClickableAreaClickedListener;

public class AddActivity extends AppCompatActivity implements View.OnTouchListener {

    public static final String APPLICATION_ID = "7E0F4527-3F78-6834-FF1F-4317066E7300";
    public static final String API_KEY = "257BE987-6F3F-4164-B0C6-9EB5835FF1D9";
    public static final String SERVER_URL = "https://api.backendless.com";

    private Button dateButton;
    private Button submitButton;
    private TextView dateTextView;
    private EditText inputIssue;
    private EditText inputDescription;
    private ImageView mapImageView;

    String longDate;
    String Issue;
    String Description;

    int buttonId;
    int sectionOn;

    boolean onMap = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Backendless
        Backendless.initApp(getApplicationContext(), APPLICATION_ID, API_KEY);
        Backendless.setUrl(SERVER_URL);

        SectionIssues issue = new SectionIssues();

        // Wiring
        dateButton = findViewById(R.id.button_date_add);
        dateTextView = findViewById(R.id.textview_date_add);
        inputIssue = findViewById(R.id.textEdit_issue_add);
        inputDescription = findViewById(R.id.textEdit_description_add);
        submitButton = findViewById(R.id.button_submit_add);
        mapImageView = findViewById(R.id.imageView_allMap_add);

        // Listeners
        System.out.println("DEBUG: Shit");
        if (mapImageView != null) {
            System.out.println("DEBUG: Poop");
            mapImageView.setOnTouchListener (this);
        };

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate();
            }
        });

            submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onMap)
                {
                    System.out.println("hello");
                    Issue = inputIssue.getText().toString();
                    Description = inputDescription.getText().toString();
                    //System.out.println("DEBUG1" + Issue + " " + Description);

                    // Backendless shenanigans
                    issue.setIssue(Issue);
                    issue.setDescription(Description);
                    issue.setInputDate(longDate);
                    issue.setSection(sectionOn);
                    // In Progress
                    //issue.setXPosition(1);
                    //issue.setYPosition(1);

                    Backendless.Data.of(SectionIssues.class).save(issue, new AsyncCallback<SectionIssues>() {
                        @Override
                        public void handleResponse(SectionIssues response) {
                            Toast.makeText(getApplicationContext(),
                                    "Success! Now, place the radio button where the incident occurred.", Toast.LENGTH_LONG).show();
                            inputIssue.getText().clear();
                            inputDescription.getText().clear();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(getApplicationContext(),
                                    "Error occurred: " + fault.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    // Radio Button Creation
                    ConstraintLayout layout = (ConstraintLayout)findViewById(R.id.constraint_place_add);
                    ConstraintSet set = new ConstraintSet();
                    // System.out.println("DEBUG: 1");

                    RadioButton radioButton = new RadioButton(getApplicationContext());
                    buttonId = View.generateViewId();
                    radioButton.setId(buttonId);  // cannot set id after add
                    layout.addView(radioButton,0);
                    set.clone(layout);
                    set.connect(radioButton.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP, 60);
                    set.applyTo(layout);
                    // System.out.println("DEBUG: " + radioButton.getId());
                }
                else
                {
                    toast("Error occurred: Select a section first");
                }
            }
        });

    }

    public boolean onTouch (View v, MotionEvent ev){
        System.out.println("DEBUG: Hello XD");
        final int action = ev.getAction();

        final int evX = (int) ev.getX();
        final int evY = (int) ev.getY();

        int touchColor = getHotspotColor (R.id.imageView_colorMap_add, evX, evY);
        ColorTool ct = new ColorTool ();
        int tolerance = 25;
        // Put which one is clicked here
        if (ct.closeMatch (Color.RED, touchColor, tolerance)) onSectionClick(1);
        else if (ct.closeMatch (Color.BLUE, touchColor, tolerance)) onSectionClick(2);
        else if (ct.closeMatch (Color.YELLOW, touchColor, tolerance)) onSectionClick(3);
        else if (ct.closeMatch (Color.GREEN, touchColor, tolerance)) onSectionClick(4);
        else if (ct.closeMatch (Color.MAGENTA, touchColor, tolerance)) onSectionClick(5);
        else if (ct.closeMatch (Color.CYAN, touchColor, tolerance)) onSectionClick(6);

        return false;
    }

    public int getHotspotColor (int hotspotId, int x, int y) {
        ImageView img = (ImageView) findViewById (hotspotId);
        img.setDrawingCacheEnabled(true);
        Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
        img.setDrawingCacheEnabled(false);
        return hotspots.getPixel(x, y);
    }

    public void onSectionClick (int sectionId)
    {
        if (sectionId == 1)
        {
            mapImageView.setImageResource(R.drawable.section_one_map);
        }
        else if (sectionId == 2)
        {
            mapImageView.setImageResource(R.drawable.section_two_map);
        }
        else if (sectionId == 3)
        {
            mapImageView.setImageResource(R.drawable.section_three_map);
        }
        else if (sectionId == 4)
        {
            mapImageView.setImageResource(R.drawable.section_four_map);
        }
        else if (sectionId == 5)
        {
            mapImageView.setImageResource(R.drawable.section_five_map);
        }
        else if (sectionId == 6)
        {
            mapImageView.setImageResource(R.drawable.section_six_map);
        }
        sectionOn = sectionId;
        onMap = true;
    }

    private void setDate() {
        Calendar calendar = Calendar.getInstance();

        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int date) {
                longDate = month + "/" + date + "/" + year;
                dateTextView.setText(longDate);
            }
        }, YEAR, MONTH, DATE);

        datePickerDialog.show();
        //System.out.println("DEBUG2" + longDate);
        // Backendless shenanigans
    }

    public void toast (String msg)
    {
        Toast.makeText (getApplicationContext(), msg, Toast.LENGTH_LONG).show ();
    }
}