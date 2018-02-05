package lucidastar.com.androidart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import lucidastar.com.androidart.chapter_2.Chapter2Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void startNewActivity(Class clazz){
        startActivity(new Intent(this,clazz));
    }

    public void btnChapter_1(View view){

    }
    public void btnChapter_2(View view){
        startNewActivity(Chapter2Activity.class);
    }
}
