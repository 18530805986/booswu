package com.vijaya.speechtotext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView mVoiceInputTv;
    private ImageButton mSpeakBtn;
    TextToSpeech tts;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVoiceInputTv = (TextView) findViewById(R.id.voiceInput);
        mSpeakBtn = (ImageButton) findViewById(R.id.btnSpeak);
        mSpeakBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startVoiceInput();
            }
        });

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.US);
                    tts.speak("Hello", TextToSpeech.QUEUE_FLUSH, null);
                    mVoiceInputTv.setText(Html.fromHtml("<p> Hello!</p>"));
                }
            }
        });
        mSpeakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startVoiceInput();
            }
        });
    }

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    if (result != null && result.size() > 0) {
                        mVoiceInputTv.append(Html.fromHtml("<p> You: " + result.get(0) + "</p>"));

                        if (result.get(0).equalsIgnoreCase("hello")) {
                            tts.speak("What is your name", TextToSpeech.QUEUE_FLUSH, null);
                            mVoiceInputTv.append(Html.fromHtml("<p> What is your name?</p>"));

                        }
                        else if (result.get(0).contains("name")){
                            String name = result.get(0).substring(result.get(0).lastIndexOf(' ') + 1);
                            editor = preferences.edit();
                            editor.putString("name", name).apply();
                            tts.speak("Hello, " + name, TextToSpeech.QUEUE_FLUSH, null);
                            mVoiceInputTv.append(Html.fromHtml("<p>Hello, " + name + "</p>"));


                        }
                        else if(result.get(0).contains("not feeling good")){
                            tts.speak("I can understand. Please tell your symptoms in short", TextToSpeech.QUEUE_FLUSH, null);
                            mVoiceInputTv.append(Html.fromHtml("<p>I can understand. Please tell your symptoms in short</p>"));
                        }
                        else if(result.get(0).contains("medicine")){
                            tts.speak("I think you have fever. Please take this medicine.", TextToSpeech.QUEUE_FLUSH, null);
                            mVoiceInputTv.append(Html.fromHtml("<p>I think you have fever. Please take this medicine.</p>"));
                        }
                        else if(result.get(0).contains("thank you")){
                            tts.speak("Thank you too, " + preferences.getString("name","") + " Take care.", TextToSpeech.QUEUE_FLUSH, null);
                            mVoiceInputTv.append(Html.fromHtml("<p>Thank you too, " + preferences.getString("name","") + ". Take care.</p>"));
                        }

                    }
                }
                break;
            }

        }
    }
}