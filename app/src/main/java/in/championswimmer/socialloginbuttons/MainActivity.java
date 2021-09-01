package in.championswimmer.socialloginbuttons;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import in.championswimmer.libsocialbuttons.BtnSocial;

public class MainActivity extends AppCompatActivity {

    BtnSocial btnSocial;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = MainActivity.this;

        btnSocial = new BtnSocial(mContext, null);


    }

}
