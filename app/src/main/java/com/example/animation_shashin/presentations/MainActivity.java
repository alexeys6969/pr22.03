package com.example.animation_shashin.presentations;

import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.animation_shashin.R;
import com.example.animation_shashin.domains.PermissionManager;

public class MainActivity extends AppCompatActivity {

    LinearLayout llContacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionManager.GetPermision(this);
        llContacts = findViewById(R.id.llContacts);

        getContacts();
    }
    public void getContacts() {
        Cursor cursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                null
        );
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String photoUri = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));

            addContact(name, number, photoUri);
        }
        cursor.close();
    }
    public void addContact(String name, String phone, String photoUri) {
        View viewContact = LayoutInflater.from(this).inflate(R.layout.item_contact, llContacts, false);
        TextView tvName = viewContact.findViewById(R.id.tvName);
        TextView tvPhone = viewContact.findViewById(R.id.tvPhone);
        ImageView ivPhoto = viewContact.findViewById(R.id.ivPhoto);
        CardView cvPhoto = viewContact.findViewById(R.id.cvPhoto);
        ImageView bthCall = viewContact.findViewById(R.id.bthCall);

        tvName.setText(name);
        tvPhone.setText(phone);

        if(photoUri != null)
            ivPhoto.setImageURI(Uri.parse(photoUri));

        viewContact.setOnClickListener(v -> {
            Intent intent = new Intent(this, ActivityContact.class);
            intent.putExtra("name", name);
            intent.putExtra("phone", phone);
            intent.putExtra("photoUri", photoUri);

            Bundle bundle = null;

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                        this, cvPhoto, "photoContact"
                );
                bundle = options.toBundle();
            }

            if(bundle == null) {
                startActivity(intent);
            } else {
                startActivity(intent, bundle);
            }
        });

        bthCall.setOnClickListener(v -> {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phone));
            startActivity(callIntent);
        });

        llContacts.addView(viewContact);
    }
}