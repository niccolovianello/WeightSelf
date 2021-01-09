package com.example.materialexamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    var data = listOf(
        Item(
        System.currentTimeMillis() - 100000,
        "Esso",
        45.0,
        50
         ),
        Item(
        System.currentTimeMillis()-5000000000,
        "Q8",
        50.0,
        50000
        ),
        Item(
        System.currentTimeMillis() - 1000000000000,
        "Agip",
        15.0,
        90
        ),
        Item(
        System.currentTimeMillis()-70000000000,
        "Eni",
        12.0,
        9000
    )
    )

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.sort_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.sort_data -> adapter.sortData() ;
            R.id.sort_prezzo -> adapter.sortPrezzo();
            R.id.sort_stazione -> adapter.sortStazione();
        }
        return super.onOptionsItemSelected(item)
    }

    val adapter = ItemAdapter(data)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
    }
}

data class Item(val date: Long, val station: String, val price: Double, val km: Int)

class ItemAdapter(var data:List<Item>):RecyclerView.Adapter<ItemAdapter.ViewHolder>(){

    class ViewHolder(v: View):RecyclerView.ViewHolder(v){
        val price:TextView = v.findViewById(R.id.price)
        val date:TextView = v.findViewById(R.id.date)
        val station:TextView = v.findViewById(R.id.station)
        fun bind(item:Item){
            date.text = DateFormat.
            getDateInstance(DateFormat.SHORT).
            format(Date(item.date))

            price.text = String.format("%5.2f", item.price)
            station.text = item.station
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        println("Grandezza lista ----->" + data.size)
        return data.size
    }

    fun sortData(){
        val newData = data.sortedBy { it.date }
        //No animazione
        data = newData
        notifyDataSetChanged()
    }
    fun sortStazione(){
        val newData = data.sortedBy { it.station }
        //No animazione
        data = newData
        notifyDataSetChanged()
    }
    fun sortPrezzo(){
        val newData = data.sortedBy { it.price }
        //No animazione
        data = newData
        notifyDataSetChanged()
    }
}