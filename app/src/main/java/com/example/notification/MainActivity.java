package com.example.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int NOTIFICATION_ID = 4562;
    private EditText editTitle;
    private EditText editText;
    private TextView viewTitle;
    private TextView viewText;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTitle = findViewById(R.id.am_title_edtext);
        editText = findViewById(R.id.am_text_edtext);
        viewTitle = findViewById(R.id.am_title_view);
        viewText = findViewById(R.id.am_text_view);
        Button notificationBtn = findViewById(R.id.ma_notification_btn);
        notificationBtn.setOnClickListener(view -> {
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), "default");

            String s1 = editTitle.getText().toString();
            String s2 = editText.getText().toString();

            Bundle bundle = new Bundle();
            bundle.putString("Title", s1);
            bundle.putString("Text", s2);

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtras(bundle);


            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT, bundle);


            notificationBuilder.setContentTitle(editTitle.getText())
                    .setContentText(editText.getText())
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setSmallIcon(R.mipmap.ic_launcher_round);

            Notification notification = notificationBuilder.build();
            NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

            notificationManager.notify(NOTIFICATION_ID, notification);


        });
        Intent intent2 = getIntent();
        String title = intent2.getStringExtra("Title");
        String text = intent2.getStringExtra("Text");
        viewTitle.setText(title);
        viewText.setText(text);
    }
}
