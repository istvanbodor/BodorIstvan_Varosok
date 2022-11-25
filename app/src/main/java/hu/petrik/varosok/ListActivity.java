package hu.petrik.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ArrowKeyMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class ListActivity extends AppCompatActivity {


    private Button visszagomb;
    private TextView lista;
    private String base_url = "https://retoolapi.dev/LkbtQ7/varosok";

    private class RequestTask extends AsyncTask<Void, Void, Response> {
        private String requestUrl;
        private String requestMethod;


        public RequestTask(String requestUrl) {
            this.requestUrl = requestUrl;
            this.requestMethod = "GET";
        }

        public RequestTask(String requestUrl, String requestMethod) {
            this.requestUrl = requestUrl;
            this.requestMethod = requestMethod;
        }

        @Override
        protected Response doInBackground(Void... voids) {
            Response response = null;
            try {
                switch (requestMethod) {
                    case "GET":
                        response = RequestHandler.get(requestUrl);
                        break;

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Response response) {
            super.onPostExecute(response);
            switch (requestMethod){
                case "GET":
                    String varosok = response.getContent();
                    lista.setText(varosok);
                    break;
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        init();
        RequestTask task = new RequestTask(base_url, "GET");
        task.execute();
        lista.setMovementMethod(new ScrollingMovementMethod());
        visszagomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListActivity.this, MainActivity.class));
            }
        });
    }

    private void init() {

        visszagomb = findViewById(R.id.visszagomb);
        lista = findViewById(R.id.lista);


    }



}