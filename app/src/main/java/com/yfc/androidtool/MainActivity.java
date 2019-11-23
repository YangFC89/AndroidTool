package com.yfc.androidtool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yfc.androidu.address.AddressManager;
import com.yfc.androidu.address.SelectAddressPop;

import org.xutils.x;


public class MainActivity extends AppCompatActivity {
    private String p = "", c = "", a = "", t = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tvTextName).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x.view().inject(MainActivity.this);
                SelectAddressPop pop = new SelectAddressPop();
                pop.setAddressName(p, c, a, t);
                pop.show(getFragmentManager(), "address");
                pop.setOnSelectAddress(new SelectAddressPop.SelectAddressFinish() {
                    @Override
                    public void finish(String provinceCode, String cityCode, String areaCode, String townCode) {
                        String[] strings = AddressManager.newInstance(MainActivity.this).getAddressName(provinceCode, cityCode, areaCode, townCode);
                        p = strings[0];
                        c = strings[1];
                        a = strings[2];
                        t = strings[3];
                    }
                });
            }
        });

    }
}
