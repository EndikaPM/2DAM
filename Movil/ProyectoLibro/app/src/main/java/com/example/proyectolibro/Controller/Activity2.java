package com.example.proyectolibro.Controller;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectolibro.Controller.DB.LibrosSQLite;
import com.example.proyectolibro.Model.Libro;
import com.example.proyectolibro.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity{
    private final static int REQUEST_CODE_ADD = 10;
    private final static int REQUEST_CODE_Edit = 20;
    private ListView lista;
    private ArrayList<Libro> datosLibros;
    private LibroAdaptador adaptador;
    private int elementoSelecionado;
    private FloatingActionButton botonFlotante;
    private Toolbar toolbar;
    private LayoutInflater inflaterToast;
    private View layoutToast;
    private ImageView imageToast;
    private TextView textToast;
    private Toast toastEliminar;
    private LibrosSQLite librosSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        inflaterToast = getLayoutInflater();
        layoutToast = inflaterToast.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.persnality_toast));
        imageToast = (ImageView) layoutToast.findViewById(R.id.imagen_toast);
        textToast = (TextView) layoutToast.findViewById(R.id.text_toast);
        toastEliminar = new Toast(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lista = findViewById(R.id.principa_lista_view);

        librosSQLite = new LibrosSQLite(this, "libros", null, 1);

        datosLibros = new ArrayList<>();

        adaptador = new LibroAdaptador(this, R.layout.libro_each, datosLibros);
        lista.setAdapter(adaptador);

        /*datosLibros.add(new Libro(R.drawable.ima1, "La mano izquierda de dios",
                "Thomas Cale ha sido criado en un monasterio donde se entrena a niños " +
                        "para convertirse en guerreros sin piedad. Cuando escapa, descubre un mundo " +
                        "que nunca imaginó y se ve envuelto en una guerra que podría cambiar el destino " +
                        "de la humanidad.", "fecha publicación:\n2010",true));

        datosLibros.add(new Libro(R.drawable.ima2, "Las cuatro Postrimeras",
                "Segunda parte de la trilogía iniciada con 'La mano izquierda de Dios'." +
                        " Thomas Cale continúa su viaje enfrentándose a enemigos poderosos mientras " +
                        "intenta comprender su destino y el papel que debe jugar en una guerra " +
                        "apocalíptica.","fecha publicación:\n2011" ,false));

        datosLibros.add(new Libro(R.drawable.ima3, "Batir de alas",
                "Última entrega de la trilogía de Paul Hoffman. Thomas Cale debe " +
                        "enfrentar su destino final en una batalla épica que determinará el futuro " +
                        "del mundo. Una conclusión intensa y emotiva de una saga oscura y fascinante."
                ,"fecha publicación:\n2013" ,true));

        datosLibros.add(new Libro(R.drawable.ima4, "La niña del bosque",
                "Novela independiente de Paul Hoffman ambientada en un mundo de fantasía " +
                        "donde una joven con habilidades especiales debe sobrevivir en un bosque misterioso" +
                        " mientras descubre secretos sobre su origen y su poder.","fecha " +
                "publicación:\n2015" ,false));

        datosLibros.add(new Libro(R.drawable.ima5, "La sombra del viento",
                "Barcelona, 1945. Daniel Sempere descubre un libro maldito que cambiará" +
                        " su vida para siempre. Una historia de amor, misterio y tragedias del pasado" +
                        " en la Barcelona de posguerra. Primera novela de la saga del Cementerio de " +
                        "los Libros Olvidados de Carlos Ruiz Zafón.","fecha publicación:\n2001" ,false));

        datosLibros.add(new Libro(R.drawable.ima6, "El corazón con que vivo",
                "Novela de Erich Maria Remarque que explora temas de amor, " +
                        "pérdida y supervivencia en la Europa de posguerra. Una historia " +
                        "profundamente emotiva sobre la búsqueda de significado y conexión humana " +
                        "en tiempos difíciles.","fecha publicación:\n1952" ,true));

        datosLibros.add(new Libro(R.drawable.ima7, "La Odisea",
                "Poema épico griego atribuido a Homero. Narra las aventuras de Odiseo" +
                        " en su viaje de regreso a Ítaca tras la Guerra de Troya. Una obra " +
                        "fundamental de la literatura universal que ha influenciado la cultura " +
                        "occidental durante milenios.","fecha publicación: Siglo\nVII a.c." ,false));


*/
        //inicioDB();
        cargarDatosLibros();

        registerForContextMenu(lista);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                elementoSelecionado = position;
                //Toast.makeText(Activity2.this, "elemeto seleccionado "+ elementoSelecionado, Toast.LENGTH_SHORT).show();
            }
        });

        botonFlotante = findViewById(R.id.floatButton);
        botonFlotante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int imagen = datosLibros.get(elementoSelecionado).getImagen();
                String titulo = datosLibros.get(elementoSelecionado).getTitulo();
                String contenido = datosLibros.get(elementoSelecionado).getTexto();
                String fecha = datosLibros.get(elementoSelecionado).getFecha();
                boolean favorito = datosLibros.get(elementoSelecionado).isDato1();

                Intent enviarDetalles = new Intent(Activity2.this, PantallaDetalles.class);
                enviarDetalles.putExtra("imagen", imagen);
                enviarDetalles.putExtra("titulo", titulo);
                enviarDetalles.putExtra("contenido", contenido);
                enviarDetalles.putExtra("fecha", fecha);
                enviarDetalles.putExtra("favorito", favorito);

                startActivity(enviarDetalles);
            }
        });
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contex_delete, menu);
        menu.setHeaderTitle("Opciones");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (item.getItemId() == R.id.menu_eliminar) {
            int positionElimi = info.position;

            Libro libroEliminado = datosLibros.get(positionElimi);
            new AlertDialog.Builder(this).setTitle("¡¡Eliminar!!")
                    .setMessage("¿Estas seguro que deseas eliminar el libro?")
                    .setPositiveButton("Aceptar",(dialog, which) -> {
                        datosLibros.remove(positionElimi);

                         toastEliminar.setDuration(Toast.LENGTH_LONG);
                         toastEliminar.setGravity(Gravity.CENTER, 0,0);
                         textToast.setText("Libro eliminado: \n" + libroEliminado.getTitulo());
                         textToast.setTextColor(Color.GREEN);
                         toastEliminar.setView(layoutToast);
                         toastEliminar.show();
                        //Toast.makeText(this, "Libro eliminado: " + libroEliminado.getTitulo(), Toast.LENGTH_SHORT).show();

                        adaptador.notifyDataSetChanged();
                    }).setNegativeButton("Cancelar",(dialog, which) -> {
                        toastEliminar.setDuration(Toast.LENGTH_LONG);
                        toastEliminar.setGravity(Gravity.CENTER, 0,0);
                        textToast.setText("¡¡Accion Cancelada!! ");
                        textToast.setTextColor(Color.RED);
                        toastEliminar.setView(layoutToast);
                        toastEliminar.show();
                        //Toast.makeText(this, "Accion cancelada.",Toast.LENGTH_SHORT).show();
                    }).show();


            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent recibir){
        super.onActivityResult(requestCode, resultCode, recibir);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_ADD) {
                Bundle datosAdd = recibir.getExtras();
                if (datosAdd != null) {

                    datosLibros.add(new Libro(R.drawable.ic_launcher_foreground, datosAdd.getString("Title"), datosAdd.getString("Descri"), datosAdd.getString("datePublis"), true));

                    Toast.makeText(Activity2.this, "Libro añadido: " + datosAdd.getString("Title"), Toast.LENGTH_SHORT).show();
                    adaptador.notifyDataSetChanged();
                }
            } else if (requestCode == REQUEST_CODE_Edit) {
                Bundle datosEdit = recibir.getExtras();
                if (datosEdit != null) {
                    int posicion = datosEdit.getInt("indexEdit", -1);

                    if (posicion != -1 && posicion < datosLibros.size()) {
                        Libro libro = datosLibros.get(posicion);

                        int nuevaImagen = datosEdit.getInt("imgId", libro.getImagen());
                        libro.setImagen(nuevaImagen);
                        libro.setTitulo(datosEdit.getString("Title"));
                        libro.setTexto(datosEdit.getString("Descri"));
                        libro.setFecha(datosEdit.getString("datePublis"));

                        adaptador.notifyDataSetChanged();
                        Toast.makeText(Activity2.this, "Libro actualizado", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            // Manda a la pantalla de añadir nuevo juego
            Intent addLibro = new Intent(Activity2.this, AddLibro.class);
            startActivityForResult(addLibro, REQUEST_CODE_ADD);

            return true;
        }
        else if(item.getItemId() == R.id.modificar){
            // Manda a la pantalla de editar, pero primero comprueba si se ha selecionado un juego
            if (elementoSelecionado != -1) {
                Intent datoEditar = new Intent(Activity2.this, Update.class);

                int positionEdit = elementoSelecionado;

                datoEditar.putExtra("indexEdit", positionEdit);
                datoEditar.putExtra("img", datosLibros.get(positionEdit).getImagen());
                datoEditar.putExtra("titleEdit", datosLibros.get(positionEdit).getTitulo());
                datoEditar.putExtra("textEdit", datosLibros.get(positionEdit).getTexto());
                datoEditar.putExtra("dateEdit", datosLibros.get(positionEdit).getFecha());

                startActivityForResult(datoEditar, REQUEST_CODE_Edit);
            }
            else {
                Toast.makeText(Activity2.this, "Selecciona un libro primero", Toast.LENGTH_SHORT).show();
            }

            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void cargarDatosLibros(){
        Log.d("DEBUG_DB", "Intentando cargar libros desde BD...");
        ArrayList<Libro> librosDB = librosSQLite.consultLibros(librosSQLite.getReadableDatabase());
        Log.d("DEBUG_DB", "Libros cargados: " + datosLibros.size());
        datosLibros.clear();
        datosLibros.addAll(librosDB);
        if (datosLibros.isEmpty()) {
            Log.e("DEBUG_DB", "¡¡¡La BD está vacía!!!");
        } else {
            for (Libro libro : datosLibros) {
                Log.d("DEBUG_DB", "Libro: " + libro.getTitulo());
            }
        }
        adaptador.notifyDataSetChanged();
    }
    public void inicioDB (){
        librosSQLite = new LibrosSQLite(this, "libros", null, 1);
        Log.d("DEBUG_DB","Iniciando BD - total de libros a insertar: " + datosLibros.size());
        for (Libro libro: datosLibros) {
            librosSQLite.addLibro(librosSQLite.getWritableDatabase(), libro);
            Log.d("DEBUG_DB", "Insertado libro: " + libro.getTitulo());
        }
        Log.d("DEBUG_DB", "Inserción completada");
    }

}