package com.e.bandbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;

import java.io.File;

public class SongActivity extends AppCompatActivity {

    @BindView(R.id.pdfView)
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        ButterKnife.bind(this);

        System.out.println();
        pdfView.fromFile((File)getIntent().getExtras().get("file"))
                .defaultPage(0)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .onPageError(new OnPageErrorListener() {
                    @Override
                    public void onPageError(int page, Throwable t) {
                        Toast.makeText(SongActivity.this, "", Toast.LENGTH_SHORT).show();
                    }
                })
                .enableAnnotationRendering(true)
                .nightMode(true)
                .load();
    }
}
