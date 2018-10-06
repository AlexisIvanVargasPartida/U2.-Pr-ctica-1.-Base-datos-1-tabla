package mx.edu.ittepic.tpdm_u2_practica1_alexisvargas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    ListView liston;
    Rutina ru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        liston = findViewById(R.id.listarutinas);
        ru = new Rutina(this);
        liston.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int fi, long l) {
                final Rutina[] s = ru.consulta();
                final int i = fi;
                AlertDialog.Builder alerta = new AlertDialog.Builder(Main3Activity.this);
                alerta.setTitle("Detalle de "+s[i].dias)
                        .setMessage("id: "+s[i].dias+"\nDescripción: "+s[i].descripcion+"\nCalorias: "
                                +s[i].calorias+"\n¿Deseas modificar/Eliminar registro?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int ift) {
                                Toast.makeText(Main3Activity.this,""+s[i].numero+" "+s[i].dias+" "+s[i].descripcion,
                                        Toast.LENGTH_LONG).show();
                                Intent io = new Intent(Main3Activity.this, Main4Activity.class);
                                io.putExtra("id",s[i].numero);
                                io.putExtra("dias",s[i].dias);
                                io.putExtra("descripcion",s[i].descripcion);
                                io.putExtra("calorias",s[i].calorias);
                                startActivity(io);
                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
            }
        });
    }

    protected void onStart(){
        super.onStart();
        try {
            String dias[] = {"No hay dias capturados aun"};
            Rutina[] s = ru.consulta();
            if (s!=null) {
                dias = new String[s.length];
                for (int i = 0; i < dias.length; i++) {
                    dias[i] = s[i].dias;
                }
            }
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                    this, android.R.layout.simple_list_item_1, dias);
            liston.setAdapter(adaptador);
        } catch (Exception e){

        }
    }
}
