package com.e.bandbook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.*;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.e.bandbook.Helpers.FireBaseClient;
import com.e.bandbook.Helpers.TextDirectory;

import java.io.IOException;
import java.net.URISyntaxException;


public class SettingsActivity extends AppCompatActivity {

    private static final int PICK_PDF_CODE = 301;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @BindView(R.id.file_aktualization_button)
    Button aktualisationButton;
    @BindView(R.id.choose_folder_button)
    Button chooseFileButton;
    @BindView(R.id.path_edit_text)
    EditText pathEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data!=null){
            pathEditText.setText(data.getData().getPath());

                saveToPreferences(TextDirectory.getPath(data.getData().getPath()), "dir");
                saveToPreferences(data.getData().getLastPathSegment().split(":")[1], "dirName");
        }
        else{
            pathEditText.setText("Something wrong!");
        }
    }

    void saveToPreferences(String value, String tag){
        sharedPref = this.getSharedPreferences("BANDbook",Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        editor.putString(tag, value);
        editor.apply();
        System.out.println("======================" + tag + value);
    }


    @OnClick(R.id.choose_folder_button)
    void chooseFile(){
        Intent browserPDF = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        startActivityForResult(Intent.createChooser(browserPDF, "Wybierz tekst"), PICK_PDF_CODE);
    }

    @OnClick(R.id.file_aktualization_button)
    void actualization() {
        FireBaseClient fireBaseClient = new FireBaseClient();
        try {
            fireBaseClient.downloadTexts(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
