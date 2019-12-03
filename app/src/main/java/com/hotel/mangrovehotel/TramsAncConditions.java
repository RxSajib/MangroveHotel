package com.hotel.mangrovehotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;

public class TramsAncConditions extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trams_anc_conditions);


        toolbar = findViewById(R.id.TramConditionIDToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon);
        getSupportActionBar().setTitle("Why Mangrove");


/*
        toString(getExternalCacheDir()hfjgng ggtkk5 kv otrmk)
yomd kmv jb
                kvtrevlo
                finishAffinity(cjhnhb kc jhwojob
                        rh j wkk hbv,dismissKeyboardShortcutsHelper();hv jlv
        v,getExternalCacheDirs()vrwcehur
                vrknwf
        tgggejljr
                vrknkl',,w
    vvrtrlh
            fttllkner vvrtrlh4l
        .nvh jhtrih \onWindowAttributesChanged(ygr

        ;ltjhn oow
        vvboihf kjt   /k user unregisterActivityLifecycleCallbacks(unregisterActivityLifecycleCallbacks(););
        cjokjhw iuy8w

        mvtl[mw]kjt);h
                cha
        lhvbw lhjvrcuggu ojq  kf
                );

        triggerSearch(i);\ojve
                jw

*/


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}




