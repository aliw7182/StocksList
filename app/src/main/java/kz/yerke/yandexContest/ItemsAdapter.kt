package kz.yerke.yandexContest

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kz.yerke.yandexContest.databinding.ItemAdapterBinding
import java.text.DecimalFormat

class ItemsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items = ArrayList<ItemsData>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    val ITEM_TYPE = 1
    val FAV_TYPE = 2
    var listener: ItemsAdapterListener? = null

    override fun getItemViewType(position: Int): Int {
        when (items[position]) {
            is ItemsData.items -> {
                return ITEM_TYPE
            }
            else -> {
                return FAV_TYPE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            ITEM_TYPE ->return ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_adapter, parent, false)
            )

            else ->return ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_adapter, parent, false)
            )
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (holder is ViewHolder) {
            if (item is ItemsData.items) {
                holder.bind(item, null, listener,position)
            } else if (item is ItemsData.favourite) {
                holder.bind(null, item, listener,position)
            }
        }

    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        val binding = ItemAdapterBinding.bind(containerView)
        @SuppressLint("SetTextI18n")
        fun bind(
            item: ItemsData.items?,
            fav: ItemsData.favourite?,
            listener: ItemsAdapterListener?,
            position: Int
        ) {
            if((position+1)%2 == 0){
                binding.root.background = ContextCompat.getDrawable(containerView.context,R.drawable.white_bg_radius_16)
            }
            else{
                binding.root.background = ContextCompat.getDrawable(containerView.context,R.drawable.gray_bg_radius_16)
            }

            item?.let {
                if(it.data.isFav){
                    binding.uiStar.setColorFilter(ContextCompat.getColor(containerView.context,R.color.yellow),
                        PorterDuff.Mode.MULTIPLY);
                }
                else{
                    binding.uiStar.setColorFilter(ContextCompat.getColor(containerView.context,R.color.grey),
                        PorterDuff.Mode.MULTIPLY);
                }
                if(it.data.regularMarketChange > 0){
                    binding.uiSubPrice.setTextColor(ContextCompat.getColor(containerView.context,R.color.green))
                }else{
                    binding.uiSubPrice.setTextColor(ContextCompat.getColor(containerView.context,R.color.red))
                }
                binding.uiSubName.text = it.data.longName
                binding.uiName.text = it.data.symbol
                binding.uiPrice.text = it.data.regularMarketPrice.toString()+ " "+it.data.currency
                binding.uiSubPrice.text = DecimalFormat("##.##").format(it.data.regularMarketChange) +" "+ it.data.currency+"("+ DecimalFormat("##.##").format(it.data.regularMarketChangePercent)+"%)"
                if(it.data.symbol == "AAPL"){
                    binding.uiIcon.setImageResource(R.drawable.aapl)
                }
                else if(it.data.symbol == "GOOGL"){
                    binding.uiIcon.setImageResource(R.drawable.googl)
                }
                else if(it.data.symbol == "AMZN"){
                    binding.uiIcon.setImageResource(R.drawable.amzn)
                }
                else if(it.data.symbol == "BAC"){
                    binding.uiIcon.setImageResource(R.drawable.bac)
                }
                else if(it.data.symbol == "MSFT"){
                    binding.uiIcon.setImageResource(R.drawable.msft)
                }
                else if(it.data.symbol == "YNDX") binding.uiIcon.setImageResource(R.drawable.yndx)
                else if(it.data.symbol == "TSLA") binding.uiIcon.setImageResource(R.drawable.tsla)
                binding.uiStar.setOnClickListener { view->
                    it.data.isFav =!it.data.isFav
                    if(it.data.isFav){
                        binding.uiStar.setColorFilter(ContextCompat.getColor(containerView.context,R.color.yellow),
                            PorterDuff.Mode.MULTIPLY);
                    }
                    else{
                        binding.uiStar.setColorFilter(ContextCompat.getColor(containerView.context,R.color.grey),
                            PorterDuff.Mode.MULTIPLY);
                    }
                    listener?.favClicked(it.data,it.data.isFav)


                }


            }
            fav?.let {
                if(it.data.isFav){
                    binding.uiStar.setColorFilter(ContextCompat.getColor(containerView.context,R.color.yellow),
                        PorterDuff.Mode.MULTIPLY);
                }
                else{
                    binding.uiStar.setColorFilter(ContextCompat.getColor(containerView.context,R.color.grey),
                        PorterDuff.Mode.MULTIPLY);
                }
                binding.uiName.text = it.data.symbol
                binding.uiSubName.text = it.data.longName
                binding.uiPrice.text = it.data.regularMarketPrice.toString() + " "+ it.data.currency
                binding.uiSubPrice.text = DecimalFormat("##.##").format(it.data.regularMarketChange) +" "+ it.data.currency+"("+ DecimalFormat("##.##").format(it.data.regularMarketChangePercent)+"%)"
                if(it.data.symbol == "AAPL"){
                    binding.uiIcon.setImageResource(R.drawable.aapl)
                }
                else if(it.data.symbol == "GOOGL"){
                    binding.uiIcon.setImageResource(R.drawable.googl)
                }
                else if(it.data.symbol == "AMZN"){
                    binding.uiIcon.setImageResource(R.drawable.amzn)
                }
                else if(it.data.symbol == "BAC"){
                    binding.uiIcon.setImageResource(R.drawable.bac)
                }
                else if(it.data.symbol == "MSFT"){
                    binding.uiIcon.setImageResource(R.drawable.msft)
                }
                else if(it.data.symbol == "YNDX") binding.uiIcon.setImageResource(R.drawable.yndx)
                else if(it.data.symbol == "TSLA") binding.uiIcon.setImageResource(R.drawable.tsla)
                binding.uiStar.setOnClickListener { view->
                    it.data.isFav =!it.data.isFav
                    if(it.data.isFav){
                        binding.uiStar.setColorFilter(ContextCompat.getColor(containerView.context,R.color.yellow),
                            PorterDuff.Mode.MULTIPLY);
                    }
                    else{
                        binding.uiStar.setColorFilter(ContextCompat.getColor(containerView.context,R.color.grey),
                            PorterDuff.Mode.MULTIPLY);
                    }
                    listener?.favClicked(it.data,it.data.isFav)

                }
            }


        }
    }

    interface ItemsAdapterListener {
        fun favClicked(data: DataJsonItem, fav: Boolean)

    }

}
