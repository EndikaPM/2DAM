package com.example.gestionhoteles.Controller;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;

public class WebViewController {
    @FXML
    private WebView webView;

    @FXML
    private void initialize() {
        webView.getEngine().load("file:///home/endika/2DAM/DIU/1trimestre/GestionHoteles/JavaDoc/com.example.gestionhoteles/module-summary.html");
    }
}
