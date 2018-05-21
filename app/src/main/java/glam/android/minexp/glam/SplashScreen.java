package glam.android.minexp.glam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

public class SplashScreen extends Activity {
    private boolean isRunning;

    class C01501 implements Runnable {

        class C01491 implements Runnable {
            C01491() {
            }

            public void run() {
                SplashScreen.this.doFinish();
            }
        }

        C01501() {
        }

        public void run() {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                SplashScreen.this.runOnUiThread(new C01491());
            }
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        this.isRunning = true;
        startSplash();
    }

    private void startSplash() {
        new Thread(new C01501()).start();
    }

    private synchronized void doFinish() {
        if (this.isRunning) {
            this.isRunning = false;
            Intent i = new Intent(this, Home.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return super.onKeyDown(keyCode, event);
        }
        this.isRunning = false;
        finish();
        return true;
    }
}
