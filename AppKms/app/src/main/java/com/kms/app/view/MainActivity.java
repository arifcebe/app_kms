package com.kms.app.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.kms.app.R;
import com.kms.app.model.BalitaModel;
import com.kms.app.model.BalitaSingleton;
import com.kms.app.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.edNama)
    EditText edNama; // edittext nama
    @Bind(R.id.rdoGrup)
    RadioGroup rdoGrup; // radio grup
    @Bind(R.id.rdoLaki)
    RadioButton rdoLaki; // radio button laki
    @Bind(R.id.rdoPrp)
    RadioButton rdoPrp; // radio button perempuan
    @Bind(R.id.edBerat)
    EditText edBerat; // edittext berat
    @Bind(R.id.spinBln)
    Spinner spinBln;
    @Bind(R.id.spinTgl)
    Spinner spinTgl;
    @Bind(R.id.spinTahun)
    Spinner spinThn;


    private ArrayAdapter<String> tglAdapter, tahunAdapter,blnAdapter;
    private String checkKelamin = "";
    private EventBus eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        eventBus = EventBus.getDefault();
        eventBus.register(this);

        // tgl adapter
        tglAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, Utils.arrTgl());
        spinTgl.setAdapter(tglAdapter);

        // tahun spinner adapter
        tahunAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, Utils.arrTahun());
        spinThn.setAdapter(tahunAdapter);

        // bulan spinner adapter
        blnAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,
                        getResources().getStringArray(R.array.arrBulan));
        spinBln.setAdapter(blnAdapter);

        rdoLaki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkKelamin = "laki";
            }
        });

        rdoPrp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkKelamin = "perempuan";
            }
        });


    }

    @OnClick(R.id.btnProses)
    public void onClickBtnProses(){
        Toast.makeText(this,"kelamin "+checkKelamin,Toast.LENGTH_LONG).show();
        Utils.TRACE(TAG, "bulan value " + spinBln.getSelectedItemPosition());

        BalitaModel balita = new BalitaModel();
        balita.setNama(edNama.getText().toString());
        balita.setJenisKelamin(checkKelamin);
        balita.setBerat(Float.valueOf(edBerat.getText().toString()));

        eventBus.post(balita);
        BalitaSingleton.getInstance().setBalita(balita);
        startActivity(new Intent(this, HasilActivity.class));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        eventBus.unregister(this);
        super.onDestroy();

    }

    @Subscribe
    public void onEvent(BalitaModel balita){

    }
}
