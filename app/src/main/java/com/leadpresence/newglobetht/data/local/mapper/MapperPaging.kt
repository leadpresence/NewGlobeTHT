package com.leadpresence.newglobetht.data.local.mapper



interface MapperPaging<F,T>{
    fun  map(from:F):T
}

fun <F,T> MapperPaging<F,T>.mapAll(list: List<F>)=list.map { map(it) }