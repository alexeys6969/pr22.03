package com.example.animation_shashin.presentations;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.animation_shashin.R;

public class ActivityContact extends AppCompatActivity {

    ImageView ivPhoto;
    TextView tvName;
    TextView tvPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact);

        ivPhoto = findViewById(R.id.ivPhoto);
        tvName = findViewById(R.id.tvName);
        tvPhone = findViewById(R.id.tvPhone);

        String name = getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");
        String photoUri = getIntent().getStringExtra("photoUri");

        tvName.setText(name);
        tvPhone.setText(phone);

        if(photoUri != null && !photoUri.isEmpty()) {
            ivPhoto.setImageURI(Uri.parse(photoUri));
        }
    }
}