package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ConfirmationActivity : AppCompatActivity() {
    private lateinit var tvFoodName: TextView
    private lateinit var tvServings: TextView
    private lateinit var tvOrderName: TextView
    private lateinit var tvAddress: TextView
    private lateinit var tvNotes: TextView
    private lateinit var backToMenuButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_confirmation)

        // Setup window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize views
        tvFoodName = findViewById(R.id.tvFoodName)
        tvServings = findViewById(R.id.tvServings)
        tvOrderName = findViewById(R.id.tvOrderName)
        tvAddress = findViewById(R.id.tvAddress)
        tvNotes = findViewById(R.id.tvNotes)
        backToMenuButton = findViewById(R.id.backtoMenu)

        // Get data from Intent
        val foodName = intent.getStringExtra(OrderActivity.EXTRA_FOOD_NAME) ?: "Unknown"
        val servings = intent.getStringExtra(OrderActivity.EXTRA_SERVINGS) ?: "Unknown"
        val orderName = intent.getStringExtra(OrderActivity.EXTRA_ORDER_NAME) ?: "Unknown"
        val address = intent.getStringExtra(OrderActivity.EXTRA_ADDRESS) ?: "Unknown"
        val notes = intent.getStringExtra(OrderActivity.EXTRA_NOTES) ?: "No additional notes"

        // Set text views with received data
        tvFoodName.text = "Nama Makanan: $foodName"
        tvServings.text = "Jumlah Pesanan: $servings"
        tvOrderName.text = "Nama: $orderName"
        tvAddress.text = "Alamat: $address"
        tvNotes.text = "Catatan: $notes"

        // Set click listener for back to menu button
        backToMenuButton.setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }
}
