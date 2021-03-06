package timothyyudi.trysettings_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    int loginClickCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginClickCount=0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.settings:
                Toast.makeText(this, "Settings menu clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, MySettingsActivity.class);
                startActivity(i);
                return true;
            case R.id.login:
                if(loginClickCount%2==0){
                    Toast.makeText(this, "logging in", Toast.LENGTH_SHORT).show();
                    item.setTitle("logout");
                }else{
                    Toast.makeText(this, "logging out ", Toast.LENGTH_SHORT).show();
                    item.setTitle("login");
                }
                loginClickCount++;
                return true;
            case R.id.subMenu1:
                Toast.makeText(this, "(sub)Menu 01 clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subMenu2:
                Toast.makeText(this, "(sub)Menu 02 clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
