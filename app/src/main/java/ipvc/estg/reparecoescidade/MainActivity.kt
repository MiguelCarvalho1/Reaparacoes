package ipvc.estg.reparecoescidade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.login_menu -> {
                Toast.makeText(this, "@string/login", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.notes_menu -> {
                Toast.makeText(this, "@string/notes", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.repairs_menu -> {
                Toast.makeText(this, "@string/repairs", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        }
    }


