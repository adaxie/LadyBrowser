package com.mobile.ladybrowser;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class BrowserActivity extends Activity {

    private Controller mController;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mController = createController();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private Controller createController() {
        Controller controller = new Controller(this);
        controller.setUi(new BaseUi(this, controller));
        return controller;
    }
}
