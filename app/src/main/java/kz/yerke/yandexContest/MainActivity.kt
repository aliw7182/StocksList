package kz.yerke.yandexContest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kz.yerke.yandexContest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val adapter = ItemsAdapter()
    private val dataApiService = DataApiService.create()
    private val disposable = CompositeDisposable()
    private val key = "8NR9ddOklRa7SPQxvg3YIgCdGqAGCQxKQCIQB04QLOB1FqJaKGQUTbgnRWYP"
    private lateinit var binding: ActivityMainBinding
    val items = ArrayList<ItemsData>()
    val fav = ArrayList<ItemsData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val llm = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
        binding.uiRecyclerView.layoutManager = llm
        binding.uiRecyclerView.adapter = adapter
        adapter.listener = object :ItemsAdapter.ItemsAdapterListener{
            override fun favClicked(data: DataJsonItem, fava: Boolean) {
                if(fava){
                    fav.add(ItemsData.favourite(data))
                }
                else{
                    fav.remove(ItemsData.favourite(data))
                }
            }

        }
        binding.favouriteText.setOnClickListener {
            adapter.items = fav
            binding.favouriteText.textSize = 28F
            binding.stocksText.textSize = 18F

        }
        binding.stocksText.setOnClickListener {
            adapter.items = items
            binding.favouriteText.textSize = 18F
            binding.stocksText.textSize = 28F
        }
        getData()
    }

    private fun getData(){
        dataApiService.getDataList("AAPL,YNDX,GOOGL,AMZN,BAC,MSFT,TSLA",key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("Json",it.toString())
                    it.forEach {
                        items.add(ItemsData.items(it))
                    }
                    adapter.items = items

                },
                {
                    Log.e("ERROR",it.message.toString())
                }
            ).also { disposable }

    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }




}