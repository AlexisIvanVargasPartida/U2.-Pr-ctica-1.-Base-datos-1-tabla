package mx.edu.ittepic.tpdm_u2_practica1_alexisvargas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {
EditText dias,descripcion,calorias;
Button eliminar,modificar;
int ID=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    dias=findViewById(R.id.diasme);
    descripcion=findViewById(R.id.descripcionme);
    calorias=findViewById(R.id.caloriasme);
    eliminar=findViewById(R.id.eliminarme);
    modificar=findViewById(R.id.modificarme);

    ID=getIntent().getExtras().getInt("id");
    dias.setText(getIntent().getExtras().getString("dias"));
    descripcion.setText(getIntent().getExtras().getString("descripcion"));
    calorias.setText(""+getIntent().getExtras().getString("calorias"));
    eliminar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Rutina rutina=new Rutina(Main4Activity.this);
            boolean res=rutina.eliminar(new Rutina(ID,"","",0));
           if(res){
               Toast.makeText(Main4Activity.this,"Se borro con exito",
                       Toast.LENGTH_LONG).show();
               dias.setEnabled(false);
               descripcion.setEnabled(false);
               calorias.setEnabled(false);
           }else{
               Toast.makeText(Main4Activity.this,"Error no se pudo eliminar",
                       Toast.LENGTH_LONG).show();
           }
        }
    });

    modificar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Rutina rutina = new Rutina(Main4Activity.this);
            boolean res = rutina.actualizar(new Rutina(ID, dias.getText().toString(),
                    descripcion.getText().toString(), Integer.parseInt(calorias.getText().toString())));
            if (res) {
                Toast.makeText(Main4Activity.this, "Se modifico con exito",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(Main4Activity.this, "Error al modificar",
                        Toast.LENGTH_LONG).show();
            }
        }

    });
    }
}
