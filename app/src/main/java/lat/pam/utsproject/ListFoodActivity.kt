package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFoodActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FoodAdapter
    private lateinit var foodList: List<Food>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_food)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Menyiapkan data makanan
        foodList = listOf(
            Food("Batagor", "Batagor asli enak dari Bandung", R.drawable.batagor),
            Food("Salad Hitam", "Salad segar yang dibuat secara langsung", R.drawable.black_salad),
            Food("Cappucino", "Kopi cappucino asli yang dibuat dari Kopi Arabica", R.drawable.cappuchino),
            Food("Cheesecake", "Kue Keju enakeun", R.drawable.cheesecake),
            Food("Cireng", "Teu ngenah tapi ludes", R.drawable.cireng),
            Food("Donut", "Tidak boleh (bhs.inggris)", R.drawable.donut),
            Food("Kopi Hitam", "Kapal api diseduh", R.drawable.kopi_hitam),
            Food("Mie Goreng", "Aku Jelek", R.drawable.mie_goreng),
            Food("Nasi Goreng", "Naa si goreng", R.drawable.nasigoreng),
            Food("Teh Gelembung", "Teh dikobok", R.drawable.sparkling_tea)
        )

        adapter = FoodAdapter(foodList) { selectedFood ->
            // Kirim kembali makanan yang dipilih ke OrderActivity
            val resultIntent = Intent()
            resultIntent.putExtra(OrderActivity.EXTRA_FOOD_NAME, selectedFood.name)
            setResult(RESULT_OK, resultIntent)
            finish() // Tutup ListFoodActivity setelah memilih
        }
        recyclerView.adapter = adapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
