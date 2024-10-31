package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var etNewUsername: EditText
    private lateinit var etNewPassword: EditText
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize views
        etNewUsername = findViewById(R.id.etNewUsername)
        etNewPassword = findViewById(R.id.etNewPassword)
        btnRegister = findViewById(R.id.btnRegister)

        // Set click listener for register button
        btnRegister.setOnClickListener {
            val newUsername = etNewUsername.text.toString()
            val newPassword = etNewPassword.text.toString()

            if (newUsername.isNotEmpty() && newPassword.isNotEmpty()) {
                // Pass data back to MainActivity
                val intent = Intent()
                intent.putExtra("username", newUsername)
                intent.putExtra("password", newPassword)
                setResult(RESULT_OK, intent)
                finish() // Close the RegisterActivity
            } else {
                Toast.makeText(this, "Please enter a username and password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
