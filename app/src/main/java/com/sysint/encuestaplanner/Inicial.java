package com.sysint.encuestaplanner;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Inicial extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] datos = {"1", "2", "3","4", "5", "6","7", "8", "9","10"};
    Vector<String> total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
        Spinner spinner = (Spinner) findViewById(R.id.planets_spinner);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, datos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        //sqlThread.start();
        run2();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void sendMessage(View view) {
        Context context = getApplicationContext();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void run2() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:postgresql://104.219.52.195:5432/encuesta", "remoteuser", "test123");
            String stsql = "Select * from parametrizacion";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(stsql);
            total = new Vector<String>();
            while (rs.next()) {
                total.add(rs.getString(2));
                System.out.println(rs.getString(2));
            }
            conn.close();
        } catch (SQLException se) {
            System.out.println("No se puede conectar. Error: " + se.toString());
        } catch (ClassNotFoundException e) {
            System.out.println("No se encuentra la clase. Error: " + e.getMessage());
        }
    }

    Thread sqlThread = new Thread() {
        public void run() {
            try {
                Class.forName("org.postgresql.Driver");
                Connection conn = DriverManager.getConnection(
                        "jdbc:postgresql://104.219.52.195:5432/encuesta", "remoteuser", "test123");
                String stsql = "Select * from parametrizacion";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(stsql);
                total = new Vector<String>();
                for(int i=0;i<10;i++) {
                    rs.next();
                    total.add(rs.getString(2));
                }
                conn.close();
                datos=total.toArray(new String[total.size()]);
            } catch (SQLException se) {
                System.out.println("No se puede conectar. Error: " + se.toString());
            } catch (ClassNotFoundException e) {
                System.out.println("No se encuentra la clase. Error: " + e.getMessage());
            }
        }
    };

}
