package com.daiane.distanciaentredoispontos;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_NETWORK_STATE;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {
Ponto p1,p2;
String PROVIDER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PROVIDER = LocationManager.NETWORK_PROVIDER;//LocationManager.GPS_PROVIDER;
    }

    public void reset(View v){
        p1 = new Ponto();
        p2 = new Ponto();
         EditText edtPto = findViewById(R.id.edtPtoA);
         edtPto.setText("");
         edtPto = findViewById(R.id.edtPtoB);
        edtPto.setText("");
    }

    public void mostrarGoogleMaps(double latitude, double longitude){
        WebView wv = findViewById(R.id.webv);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl("https://www.google.com/maps/search/api=1&query="+latitude+","+longitude);
    }

    public void verPontoA(View v){mostrarGoogleMaps(p1.getLatitude(),
            p1.getLongitude());}
    public void verPontoB(View v){mostrarGoogleMaps(p2.getLatitude(), p2.getLongitude());}

    public void lerPontoA(View v){
        p1 = this.getPonto();
        EditText edtPto = findViewById(R.id.edtPtoA);
        edtPto.setText(p1.Imprimir2());
}
    public void lerPontoB(View v){
        p2 = this.getPonto();
        EditText edtPto = findViewById(R.id.edtPtoB);
        edtPto.setText(p2.Imprimir2());
    }

    public Ponto getPonto() {
        LocationManager mLocManager =(LocationManager) getSystemService(LOCATION_SERVICE);
        LocationListener mLocListener = new MinhaLocalizacaoListener();


        if (ActivityCompat.checkSelfPermission(this,
                ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{
                            ACCESS_FINE_LOCATION
                    }, 1);
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{
                            ACCESS_NETWORK_STATE
                    }, 1);
            return null;
        }


        mLocManager.requestLocationUpdates(PROVIDER, 0, 0, mLocListener);
        Location localAtual = mLocManager.getLastKnownLocation(PROVIDER);
        if (mLocManager.isProviderEnabled(PROVIDER)) {
            Toast.makeText(MainActivity.this, "GPS DESABILITADO.", Toast.LENGTH_LONG).show();
        }

       return new Ponto(localAtual.getLatitude(),localAtual.getLongitude(),localAtual.getAltitude());
    }

    public void CalcularDistancia(View v){
        LocationManager  mlocManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        float [] resultado = new float[1];
        Location.distanceBetween(p1.getLatitude(),p1.getLongitude(),p2.getLatitude(),p2.getLongitude(), resultado);
         if(mlocManager.isProviderEnabled(PROVIDER)){
             String texto = "Distancia " + resultado[0] + "\n" ;
             Toast.makeText(MainActivity.this, texto, Toast.LENGTH_LONG).show();
         } else {
             Toast.makeText(MainActivity.this, "GPS DESABILITADO.", Toast.LENGTH_LONG).show();
         }

    }


}