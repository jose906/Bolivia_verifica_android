package com.bolivia.verifica.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.bolivia.verifica.R;

public class Transparencia extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparencia);
        String a = getText();
        webView = (WebView) findViewById(R.id.web_trans);
        webView.loadData(a,"text/html","UTF-8");
        webView.setBackgroundColor(Color.TRANSPARENT);
    }
    public String getText(){

        String a = "<div class=\"entry-content-wrap\">\n" +
                "\t\n" +
                "\t<div class=\"entry-content\">\n" +
                "\t\t<p style=\"text-align: justify;\"><span style=\"font-size: 18px;\">La Fundación para el Periodismo es una organización sin fines de lucro que fue creada por periodistas bolivianos el año 2008 y autorizada a funcionar como tal por Resolución Prefectural N° 603 del Gobierno Autónomo Departamental de&nbsp; La Paz.&nbsp;</span></p>\n" +
                "<p style=\"text-align: justify;\"><span style=\"font-size: 18px;\">Puedes encontrar nuestros estatutos y nuestra personería jurídica<a href=\"https://fundacionperiodismo.org/estatutos/\" target=\"_blank\" rel=\"noopener noreferrer\"> aquí</a>.&nbsp;</span></p>\n" +
                "<p style=\"text-align: justify;\"><span style=\"font-size: 18px;\">Para consultar nuestra política de fondos puedes revisar <a href=\"https://boliviaverifica.bo/politica-de-fondos/\" target=\"_blank\" rel=\"noopener noreferrer\">aquí</a>.</span></p>\n" +
                "<p>&nbsp;</p>\n" +
                "\t</div><!-- .entry-content -->\n" +
                "\t</div>";
        return a;



    }
}