package kz.yerke.yandexContest
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel


class DataJson : ArrayList<DataJsonItem>()

data class DataJsonItem(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("forwardPE")
    val forwardPE: Double,
    @SerializedName("longName")
    val longName: String,
    @SerializedName("regularMarketChangePercent")
    val regularMarketChangePercent: Double,
    @SerializedName("regularMarketChange")
    val regularMarketChange: Double,
    @SerializedName("regularMarketPrice")
    val regularMarketPrice: Double,
    @SerializedName("symbol")
    val symbol: String
){
    @IgnoredOnParcel
    var isFav = false
}
 