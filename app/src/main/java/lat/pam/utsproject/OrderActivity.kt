package lat.pam.utsproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class OrderActivity : AppCompatActivity() {
    private lateinit var etFoodName: TextView
    private lateinit var etServings: EditText
    private lateinit var etName: EditText
    private lateinit var etAddress: EditText
    private lateinit var etNotes: EditText
    private lateinit var btnOrder: Button
    private lateinit var btnViewMenu: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        // Initialize views
        etFoodName = findViewById(R.id.etFoodName)
        etServings = findViewById(R.id.etServings)
        etName = findViewById(R.id.etName)
        etAddress = findViewById(R.id.etAddress)
        etNotes = findViewById(R.id.etNotes)
        btnOrder = findViewById(R.id.btnOrder)
        btnViewMenu = findViewById(R.id.btnViewMenu)

        // Set click listener for the "View Menu" button
        btnViewMenu.setOnClickListener {
            val intent = Intent(this, ListFoodActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_FOOD_LIST)
        }

        // Set click listener for Place Order button
        btnOrder.setOnClickListener {
            // Check if required fields are filled
            val servings = etServings.text.toString().trim()
            val name = etName.text.toString().trim()
            val address = etAddress.text.toString().trim()

            if (servings.isEmpty() || name.isEmpty() || address.isEmpty()) {
                // Show a message for each empty field
                val missingFields = mutableListOf<String>()
                if (servings.isEmpty()) missingFields.add("Jumlah")
                if (name.isEmpty()) missingFields.add("Nama Costumer")
                if (address.isEmpty()) missingFields.add("Alamat")

                val message = "Please fill in: ${missingFields.joinToString(", ")}"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            } else {
                // Create an intent to open ConfirmationActivity
                val intent = Intent(this, ConfirmationActivity::class.java)

                // Pass the data to ConfirmationActivity
                intent.putExtra(EXTRA_FOOD_NAME, etFoodName.text.toString())
                intent.putExtra(EXTRA_SERVINGS, servings)
                intent.putExtra(EXTRA_ORDER_NAME, name)
                intent.putExtra(EXTRA_ADDRESS, address)
                intent.putExtra(EXTRA_NOTES, etNotes.text.toString())

                // Start ConfirmationActivity
                startActivity(intent)
            }
        }
    }

    // Handle the result from ListFoodActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_FOOD_LIST && resultCode == Activity.RESULT_OK) {
            val selectedFood = data?.getStringExtra(EXTRA_FOOD_NAME)
            etFoodName.text = selectedFood ?: "No food selected"
        }
    }

    companion object {
        const val REQUEST_CODE_FOOD_LIST = 1001
        const val EXTRA_FOOD_NAME = "extra_food_name"
        const val EXTRA_SERVINGS = "extra_servings"
        const val EXTRA_ORDER_NAME = "extra_order_name"
        const val EXTRA_ADDRESS = "extra_address"
        const val EXTRA_NOTES = "extra_notes"
    }
}
