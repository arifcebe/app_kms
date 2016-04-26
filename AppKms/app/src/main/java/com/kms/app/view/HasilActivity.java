package com.kms.app.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.kms.app.R;
import com.kms.app.model.BalitaModel;
import com.kms.app.model.BalitaSingleton;
import com.kms.app.utils.DatabaseHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.Years;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HasilActivity extends AppCompatActivity {

    private static final String TAG = HasilActivity.class.getSimpleName();
    @Bind(R.id.hasil_umur)
    TextView tvUmur;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.hasil_nama)
    TextView tvNama;
    @Bind(R.id.hasil_berat)
    TextView tvBerat;

    private EventBus eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hasil_activity);
        ButterKnife.bind(this);

        eventBus = EventBus.getDefault();
        eventBus.register(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Hasil Perhitungan");
        BalitaModel balita = BalitaSingleton.getInstance().getBalita();

        LocalDate birthdate = new LocalDate(2011, 1, 20);
        LocalDate now = new LocalDate();
        Years age = Years.yearsBetween(birthdate, now);
        Months bulan = Months.monthsBetween(birthdate,now);

        tvUmur.setText(String.valueOf(age.getYears())+" | "+String.valueOf(bulan.getMonths()));
        Log.d(TAG,"status gizi "+ DatabaseHelper.getInstance(this)
            .getStatusGizi("20",balita.getBerat(),balita.getJenisKelamin()));
        /*tvNama.setText(balita.getNama());
        tvBerat.setText(String.valueOf(balita.getBerat()));*/

    }

    @Override
    protected void onDestroy() {
        eventBus.unregister(this);
        super.onDestroy();

    }

    @Subscribe
    public void onEvent(BalitaModel balita){

        /*if(balita != null){
            tvNama.setText(balita.getNama());
            tvBerat.setText(String.valueOf(balita.getBerat()));
        }*/
    }
}
