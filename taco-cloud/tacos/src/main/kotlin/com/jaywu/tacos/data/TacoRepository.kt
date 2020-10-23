package com.jaywu.tacos.data

import com.jaywu.tacos.Taco

interface TacoRepository {
    fun save(taco: Taco): Taco
}