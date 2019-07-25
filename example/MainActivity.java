package de.hs_kl.xmlincode;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams;
import androidx.constraintlayout.widget.ConstraintSet;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createScreen();
    }

    private void createScreen() {
        ConstraintLayout screen = new ConstraintLayout(this);
        screen.setLayoutParams(new LayoutParams(
               LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));

        EditText et = createEditText();
        // EditText
        //app:layout_constraintEnd_toEndOf = "parent"
        //app:layout_constraintStart_toStartOf = "parent"
        //app:layout_constraintTop_toTopOf = "parent"
        ConstraintSet cs_et = new ConstraintSet();
        cs_et.connect(et.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
        cs_et.connect(et.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        cs_et.connect(et.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);
        cs_et.applyTo(screen);
        screen.addView(et);

        TextView tv = createTextView();
        //app:layout_constraintEnd_toEndOf="parent"
        //app:layout_constraintStart_toStartOf="parent"
        //app:layout_constraintTop_toBottomOf="@+id/editText"
        ConstraintSet cs_tv = new ConstraintSet();
        cs_tv.connect(tv.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
        cs_tv.connect(tv.getId(), ConstraintSet.TOP, et.getId(), ConstraintSet.TOP);
        cs_tv.connect(tv.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);
        cs_tv.applyTo(screen);
        screen.addView(tv);


        screen.addView(createRadioGroup());
        screen.addView(createButton());
        setContentView(screen);
    }


    private Button createButton() {
        /*
        <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="24dp"
        android:layout_weight="1"
        android:text="Suchen"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.498"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/radioGrp" />
        */
        return null;
    }

    private RadioGroup createRadioGroup() {
        /*
        <RadioGroup
        android:id="@+id/radioGrp"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="32dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView">

        <RadioButton
            android:id="@+id/radioButton3"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_margin="8dp"
            android:layout_weight="1"
            android:text="Artist" />

        <RadioButton
            android:id="@+id/radioButton2"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_margin="8dp"
            android:layout_weight="1"
            android:text="Stadt" />

        <RadioButton
            android:id="@+id/radioButton"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_margin="8dp"
            android:layout_weight="1"
            android:text="Datum" />

    </RadioGroup>

        */
        return null;
    }

    private TextView createTextView() {
        TextView tv = new TextView(this);

        // android:id="@+id/textView"
        tv.setId(R.id.text_view);

        // android:layout_width="wrap_content"
        // android:layout_height="wrap_content"
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

        // android:layout_marginStart="24dp"
        // android:layout_marginTop="32dp"
        // android:layout_marginEnd="313dp"
        params.setMargins(24, 32, 313, 0);
        tv.setLayoutParams(params);

        // android:text="Suche nach"
        tv.setText("Suche nach");

        return tv;
    }

    private EditText createEditText() {
        EditText et = new EditText(this);

        // android:id="@+id/editText" inklusive ids.xml unter res/values
        et.setId(R.id.edit_text);

        // android:layout_width="0dp"
        // android:layout_height="wrap_content"
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(0, ConstraintLayout.LayoutParams.WRAP_CONTENT);

        // android:layout_marginStart = "24dp"
        // android:layout_marginTop = "24dp"
        // android:layout_marginEnd = "24dp"
        params.setMargins(24, 24, 24, 0);
        et.setLayoutParams(params);

        //android:ems = "10"
        et.setEms(10);

        //android:hint = "Name"
        et.setHint("name");

        //android:inputType = "textPersonName"
        et.setInputType(0);

        return et;
    }

}