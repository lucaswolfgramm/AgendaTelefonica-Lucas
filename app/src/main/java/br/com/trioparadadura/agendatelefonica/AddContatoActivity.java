package br.com.trioparadadura.agendatelefonica;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class AddContatoActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add);
            android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar)
                    findViewById(R.id.toolbar);
            toolbar.setTitle("Adicionar Contato");
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);

            }
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    onBackPressed();
                    break;
                case R.id.menu_editar:
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);

                    builder.setTitle("Atenção");
                    builder.setMessage("Deseja Realmente Salvar?");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(AddContatoActivity.this, "Contato Salvo", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("Cancelar", null);
                    builder.show();
                    break;
            }
            return super.onOptionsItemSelected(item);
        }
}
