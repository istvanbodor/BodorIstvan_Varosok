package hu.petrik.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class InsertActivity extends AppCompatActivity {
    private EditText nev, orszag, lakossag;
    private Button insertfelvevogomb, insertvisszagomb;
    private String base_url = "https://retoolapi.dev/LkbtQ7/varosok";

    private class RequestTask extends AsyncTask<Void, Void, Response> {
        private String requestUrl;
        private String requestMethod;
        private String requestBody;

        public RequestTask(String requestUrl) {
            this.requestUrl = requestUrl;
            this.requestMethod = "GET";
        }

        public RequestTask(String requestUrl, String requestMethod) {
            this.requestUrl = requestUrl;
            this.requestMethod = requestMethod;
        }

        public RequestTask(String requestUrl, String requestMethod, String requestBody) {
            this.requestUrl = requestUrl;
            this.requestMethod = requestMethod;
            this.requestBody = requestBody;
        }

        @Override
        protected Response doInBackground(Void... voids) {
            Response response = null;
            try {
                switch (requestMethod) {
                    case "POST":
                        response = RequestHandler.post(requestUrl, requestBody);
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
                case "POST":
                    if (response.getResponseCode() == 201) {
                        RequestTask task = new RequestTask(base_url);
                        task.execute();
                    }
                    break;
            }
        }
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        init();
        insertfelvevogomb.setOnClickListener(view -> {
            String nevmezo = nev.getText().toString().trim();
            String orszagmezo = orszag.getText().toString().trim();
            String lakossagmezo = lakossag.getText().toString().trim();

            if (nevmezo.isEmpty() || orszagmezo.isEmpty() || lakossagmezo.isEmpty())
            {
                Toast.makeText(this, "Sikertelen felvétel, töltsön ki minden mezőt!", Toast.LENGTH_SHORT).show();
            }
            else {
            String json = String.format("{\"nev\": \"%s\", \"orszag\": \"%s\", \"lakossag\": \"%s\"}",
                    nevmezo, orszagmezo, lakossagmezo);
            RequestTask task = new RequestTask(base_url, "POST", json);
            task.execute();
            nev.setText("");
            orszag.setText("");
            lakossag.setText("");
                Toast.makeText(this, "Sikeres felvétel", Toast.LENGTH_SHORT).show();
            }






        });


        insertvisszagomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InsertActivity.this, MainActivity.class));
            }
        });


    }


    private void init() {

        nev = findViewById(R.id.nev);
        orszag = findViewById(R.id.orszag);
        lakossag = findViewById(R.id.lakossag);
        insertfelvevogomb = findViewById(R.id.insertfelvevogomb);
        insertvisszagomb = findViewById(R.id.insertvisszagomb);



    }
}