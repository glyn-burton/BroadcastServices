package com.example.broadcastservices
import android.app.AlarmManager
import android.content.Intent
import android.content.res.AssetManager
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.jojo_item.view.*

//, var alarmManager: AlarmManager

class JojoRVAdapter(val jojoList: ArrayList<Jojo>):RecyclerView.Adapter<JojoRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JojoRVAdapter.ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.jojo_item,parent, false)
        val viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun getItemCount() = jojoList.size


    override fun onBindViewHolder(holder: JojoRVAdapter.ViewHolder, position: Int) {

        holder.populateItem(jojoList[position])
    }

    fun update (){

        notifyDataSetChanged()

    }

    inner class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

        fun populateItem (jojo:Jojo) {
            val pic = jojo.pic
            itemView.rvName.text = jojo.name
            itemView.rvStand.text = jojo.stand
            itemView.rvCatchphrase.text = jojo.catchphrase
            itemView.rvPart.text = jojo.part

            when(pic){

                "giorno" -> itemView.rvPic.setImageResource(R.drawable.giorno)
                "joseph" -> itemView.rvPic.setImageResource(R.drawable.joesph)
                "johnny" -> itemView.rvPic.setImageResource(R.drawable.johnny)
                "jolyne" -> itemView.rvPic.setImageResource(R.drawable.jolene)
                "jonathon" -> itemView.rvPic.setImageResource(R.drawable.jonathon)
                "josuke" -> itemView.rvPic.setImageResource(R.drawable.josuke)
                "jotaro" -> itemView.rvPic.setImageResource(R.drawable.jotaro)
                "josefumi" -> itemView.rvPic.setImageResource(R.drawable.josefumi)

            }

            itemView.setOnClickListener(View.OnClickListener {

                //alarmManager.set()

            })

        }
    }
}