package br.com.trioparadadura.agendatelefonica;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ContatoItemTable {
    private static String TABLE = "PAIS_ITEM";

    private static String COLUMN_ID = "ID";
    private static String COLUMN_NOME = "NOME";
    private static String COLUMN_TELEFONEFIXO = "TELEFONE FIXO";
    private static String COLUMN_TELEFONECELULAR = "TELEFONE CELULAR";

    private static String[] ALL_COLUMNS = {COLUMN_ID,COLUMN_NOME,COLUMN_TELEFONEFIXO,COLUMN_TELEFONECELULAR};

    public static String CREATE_TABLE = "CREATE TABLE " + TABLE + "("
            + COLUMN_ID               + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NOME             + " TEXT, "
            + COLUMN_TELEFONEFIXO       + " TEXT, "
            + COLUMN_TELEFONECELULAR  + " TEXT "
            +");";

    private SQLiteDatabase mDataBase;
    private DataBaseHelper mDataHelper;

    public ContatoItemTable(Context context) throws SQLException {
        mDataHelper = DataBaseHelper.getInstance(context);
        open();
    }

    private void open() throws SQLException {
        mDataBase = mDataHelper.getWritableDatabase();
    }

    public long inserir(ContatoItem ContatoItem){
        return mDataBase.insert(TABLE, null, toContent(ContatoItem));
    }

    public List<ContatoItem> obterTodos(){
        Cursor cursor = null;
        List<ContatoItem> list = new ArrayList<>();
        try {
            cursor = mDataBase.query(true,TABLE,ALL_COLUMNS,null,null,null,null,null,null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    list.add(toObject(cursor));
                } while (cursor.moveToNext());
            }
        }finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return list;
    }

    private ContatoItem toObject(Cursor cursor) {
        ContatoItem u = new ContatoItem();
        u.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        u.setNome(cursor.getString(cursor.getColumnIndex(COLUMN_NOME)));
        u.setTelefoneFixo(cursor.getString(cursor.getColumnIndex(COLUMN_TELEFONEFIXO)));
        u.setTelefoneCelular(cursor.getString(cursor.getColumnIndex(COLUMN_TELEFONECELULAR)));
        return u;
    }

    private ContentValues toContent(ContatoItem item) {
        ContentValues content = new ContentValues();
        content.put(COLUMN_NOME, item.getNome());
        content.put(COLUMN_TELEFONEFIXO, item.getTelefoneFixo());
        content.put(COLUMN_TELEFONECELULAR, item.getTelefoneCelular());
        return content;
    }
}
