package com.example.estudiante.retodeezer_renjifo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.deezer.sdk.model.Album;
import com.deezer.sdk.model.Playlist;
import com.deezer.sdk.network.connect.DeezerConnect;
import com.deezer.sdk.network.request.DeezerRequest;
import com.deezer.sdk.network.request.DeezerRequestFactory;
import com.deezer.sdk.network.request.event.JsonRequestListener;
import com.deezer.sdk.network.request.event.RequestListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv_listas;
    ListaAdapter customAdapter;
    private List<Playlist> listas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_listas=findViewById(R.id.lv_listas);
        customAdapter= new ListaAdapter(this);
        lv_listas.setAdapter(customAdapter);

        DeezerRequest req=DeezerRequestFactory.requestPlaylist(34554);

        Lista lista1= new Lista("Musica Pop","marenjifo","10");
        customAdapter.agregarLista(lista1);

        // replace with your own Application ID
        String applicationID = "298304";
        DeezerConnect deezerConnect = new DeezerConnect(this, applicationID);

        // the request listener
        RequestListener listener = new JsonRequestListener() {

            public void onResult(Object result, Object requestId) {
              //  List<Album> albums = (List<Album>) result;
              //  List<Playlist> playlists=(List<Playlist>) result;
                listas.addAll((List<Playlist>) result);


//                Log.e("DEEZER","Listas: "+playlists.size());

                // do something with the albums

            }

            public void onUnparsedResult(String requestResponse, Object requestId) {}

            public void onException(Exception e, Object requestId) {}
        };

// create the request
        long artistId = 11472;
        DeezerRequest request = DeezerRequestFactory.requestArtistAlbums(artistId);


// set a requestId, that will be passed on the listener's callback methods
        request.setId("myRequest");

// launch the request asynchronously
        deezerConnect.requestAsync(request, listener);

    }
}
