package com.example.vamshi.Curious.Model.Retrofit

data class JsonPlacePics(val meta: PMeta,
                         val response: PResponse)

data class PMeta(val code: Int = 0,
                val requestId: String = "")

data class PResponse(val photos: Photos)

data class Photos(val count: Int = 0,
                  val dupesRemoved: Int = 0,
                  val items: List<ItemsItem>?)

data class ItemsItem(val createdAt: Int = 0,
                     val checkin: Checkin,
                     val visibility: String = "",
                     val prefix: String = "",
                     val width: Int = 0,
                     val id: String = "",
                     val source: Source,
                     val suffix: String = "",
                     val user: User,
                     val height: Int = 0)

data class Checkin(val createdAt: Int = 0,
                   val timeZoneOffset: Int = 0,
                   val id: String = "",
                   val type: String = "")

data class Source(val name: String = "",
                  val url: String = "")

data class User(val firstName: String = "",
                val lastName: String = "",
                val gender: String = "",
                val photo: Photo,
                val id: String = "")

data class Photo(val prefix: String = "",
                 val suffix: String = "")