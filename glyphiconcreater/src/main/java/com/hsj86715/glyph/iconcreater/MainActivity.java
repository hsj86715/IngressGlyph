package com.hsj86715.glyph.iconcreater;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View view = findViewById(R.id.app_icon_view);
            view.setDrawingCacheEnabled(true);
            view.buildDrawingCache();
            // this is the important code :)
            // Without it the view will have a dimension of 0,0 and the bitmap will be null
//            view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
//                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
            Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            view.draw(canvas);
            String strPath = "/testSaveView/" + UUID.randomUUID().toString() + ".png";
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File sdCardDir = Environment.getExternalStorageDirectory();
                FileOutputStream fos = null;
                try {
                    File file = new File(sdCardDir, strPath);
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    fos = new FileOutputStream(file);

                    //当指定压缩格式为PNG时保存下来的图片显示正常
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);

                    //当指定压缩格式为JPEG时保存下来的图片背景为黑色
//				 bitmap.compress(CompressFormat.JPEG, 100, fos);
                    fos.flush();
                    Toast.makeText(MainActivity.this, "Save icon success，Path：" + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return super.onTouchEvent(event);
    }
}
