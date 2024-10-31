// FoodAdapter.kt
package lat.pam.utsproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(
    private val foodList: List<Food>,
    private val onFoodSelected: (Food) -> Unit // Tambahkan parameter untuk listener
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_food, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foodList[position]
        holder.foodName.text = food.name
        holder.foodDescription.text = food.description
        holder.foodImage.setImageResource(food.imageResourceId)

        // Atur click listener untuk tombol "Select" di setiap item
        holder.btnSelectFood.setOnClickListener {
            onFoodSelected(food)
        }
    }

    override fun getItemCount(): Int = foodList.size

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodImage: ImageView = itemView.findViewById(R.id.foodImage)
        val foodName: TextView = itemView.findViewById(R.id.foodName)
        val foodDescription: TextView = itemView.findViewById(R.id.foodDescription)
        val btnSelectFood: Button = itemView.findViewById(R.id.btnSelectFood) // Inisialisasi tombol "Select"
    }
}
