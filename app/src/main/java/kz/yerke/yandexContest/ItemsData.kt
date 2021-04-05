package kz.yerke.yandexContest

sealed class ItemsData {

    data class items(val data: DataJsonItem):ItemsData()
    data class favourite(val data: DataJsonItem):ItemsData()

}
