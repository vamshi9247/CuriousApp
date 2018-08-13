package com.example.vamshi.Curious

 data class Pics( var count: Int?) {

     var photo:ArrayList<PhotoEntity>? = null


        init {
            photo = ArrayList(count!!.plus( 1))
        }


        fun addPhoto(p: PhotoEntity) {
            photo!!.add(p)
        }
}