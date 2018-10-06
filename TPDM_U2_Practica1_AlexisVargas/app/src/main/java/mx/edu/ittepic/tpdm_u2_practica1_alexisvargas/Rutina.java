package mx.edu.ittepic.tpdm_u2_practica1_alexisvargas;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class Rutina {
   int numero;
   String dias,descripcion;
   int calorias;
   BaseDatos baseDatos;


public Rutina(int nc,String d, String des,int cal){

    numero=nc;
    dias=d;
    descripcion=des;
    calorias=cal;
}

public Rutina(Activity activity){baseDatos=new BaseDatos(activity,"ejercicio",null,1);}

public  boolean insertar(Rutina rutina){
    try{
        SQLiteDatabase tabla=baseDatos.getWritableDatabase();
        ContentValues data=new ContentValues();
        data.put("DIAS",rutina.dias);
        data.put("DESCRIPCION",rutina.descripcion);
        data.put("CALORIAS",rutina.calorias);
        long res=tabla.insert("RUTINA","ID",data);
        tabla.close();
        if(res<0){
            return false;
        }

    }catch(SQLiteException e){
        Log.e("ERROR: ",e.getMessage());
        return false;
    }
    return true;
}

 public Rutina consultar(String descripcion) {
     Rutina ru = null;
     try {
         SQLiteDatabase tabla = baseDatos.getReadableDatabase();
         String SQL = "SELECT*FROM RUTINA WHERE DESCRIPCION=?";
         String claves[] = {descripcion};

         Cursor c = tabla.rawQuery(SQL, claves);
         if (c.moveToFirst()) {
             ru = new Rutina(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3));
         }
         tabla.close();
     } catch (SQLiteException e) {
         return null;
     }
     return ru;
 }
  public Rutina[]consulta(){
    Rutina[] resultado=null;
    try {
        SQLiteDatabase tabla=baseDatos.getReadableDatabase();
        String SQL="SELECT * FROM RUTINA";
        Cursor c = tabla.rawQuery(SQL,null);
        if(c.moveToFirst()){
            resultado = new Rutina[c.getCount()];
            int i=0;
            do {
                resultado[i++] = new Rutina(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3));
            }while(c.moveToNext());
        }
        tabla.close();
    }catch (SQLiteException e){
        return null;
    }
      return resultado;
  }

    public boolean eliminar(Rutina rutina){
        try{
            SQLiteDatabase tabla = baseDatos.getWritableDatabase();
            String[] data = {""+rutina.numero};
            long res = tabla.delete("RUTINA","ID=?",data);
            tabla.close();
            if(res==0){
                return false;
            }
        }catch (SQLiteException e){
            return false;
        }
        return true;
    }

    public boolean actualizar(Rutina rutina){
        try{
            SQLiteDatabase tabla = baseDatos.getWritableDatabase();
            ContentValues data = new ContentValues();
            data.put("DIAS",rutina.dias);
            data.put("DESCRIPCION",rutina.descripcion);
            data.put("CALORIAS",rutina.calorias);
            String[] clave = {""+rutina.numero};
            long res = tabla.update("RUTINA",data,"ID=?",clave);
            tabla.close();
            if(res<0){
                return false;
            }
        }catch (SQLiteException e){
            return false;
        }
        return true;
    }
}
