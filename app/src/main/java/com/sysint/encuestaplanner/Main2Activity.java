package com.sysint.encuestaplanner;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.LinearLayout;

public class Main2Activity extends Activity implements OnClickListener {
    String[] mobileArray = {"Seleccione una actividad:"};

    private Button mostrarPreguntas;
    // Progress Dialog
    private ProgressDialog pDialog;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*ScrollView sv = new ScrollView(this);
        ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        sv.addView(ll);

        TextView tv = new TextView(this);
        tv.setText("Disenio Dinamico:");
        ll.addView(tv);
        EditText et = new EditText(this);

        et.setText("Ingrese su texto aca:");

        ll.addView(et);

        Button b = new Button(this);
        b.setText("Added Button");
        ll.addView(b);

        ListView listView = new ListView(this);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,et,mobileArray);
        listView.setAdapter(adapter);

        ll.addView(listView);
        //leerDB();
        //baseDeDatos db = new baseDeDatos();

        /*ResultSet rs = db.query();
        try {
            while (rs.next()) {
                RadioButton rb = new RadioButton(Main2Activity.this);
                rb.setText(rs.getString(2));
                System.out.println(rs.getString(2));
                ll.addView(rb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //db.start();
        sqlThread.start();*/
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);
        //this.setContentView(sv);
    }

    Thread sqlThread = new Thread(){
        public void run(){
                try {
                    Class.forName("org.postgresql.Driver");
                    Connection conn = DriverManager.getConnection(
                            "jdbc:postgresql://104.219.52.195:5432/encuesta", "remoteuser", "test123");
                    String stsql = "Select * from parametrizacion";
                    Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery(stsql);
                    while (rs.next()){

                    }
                    conn.close();
                } catch (SQLException se) {
                    System.out.println("No se puede conectar. Error: " + se.toString());
                } catch (ClassNotFoundException e) {
                    System.out.println("No se encuentra la clase. Error: " + e.getMessage());
                }
            }
    };


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, listadoPreguntas.class);
        startActivity(intent);
    }

    class LoadAllProducts extends AsyncTask<String, String, String> {

        /**
         * Antes de empezar el background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Main2Activity.this);
            pDialog.setMessage("Cargando comercios. Por favor espere...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * obteniendo todos los productos
         */
        protected String doInBackground(String... args) {
            // Building Parameters

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */

                }
            });
        }
    }

}

class baseDeDatos {
    public final baseDeDatos INSTANCE = new baseDeDatos();

    public baseDeDatos() {

    }


    public ResultSet query() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:postgresql://104.219.52.195:5432/encuesta", "remoteuser", "test123");
            String stsql = "Select * from parametrizacion";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(stsql);
            conn.close();
            return rs;
        } catch (SQLException se) {
            System.out.println("No se puede conectar. Error: " + se.toString());
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("No se encuentra la clase. Error: " + e.getMessage());
            return null;
        }
    }

}