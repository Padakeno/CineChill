package domain.exampleandroidproject.padakeno.cinechill;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
                try {
                    URI uri = new URI("http://votre.site.org/getrequest.php?name=David");
                    Toast.makeText(this, getHttpResponse(uri), Toast.LENGTH_LONG).show();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static String getHttpResponse(URI url_serveur, URI url_argument) {
        StringBuilder response = new StringBuilder();
        try {
            HttpGet get = new HttpGet();
            get.setURI(url_serveur + "/search.php?titre=" + url_argument);
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpResponse httpResponse = httpClient.execute(get);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                Log.d("[GET REQUEST]", "HTTP Get succeeded");

                HttpEntity messageEntity = httpResponse.getEntity();
                InputStream is = messageEntity.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
            }
        } catch (Exception e) {
            Log.e("[GET REQUEST]", e.getMessage());
        }
        Log.d("[GET REQUEST]", "Done with HTTP getting");
        return response.toString();
    }
}

