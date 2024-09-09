package com.sanusbrain.Controllers.Primary.Home;

import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.utils.ScrollUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private MFXScrollPane mfxScrollPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ScrollUtils.addSmoothScrolling(mfxScrollPane);
    }
}
