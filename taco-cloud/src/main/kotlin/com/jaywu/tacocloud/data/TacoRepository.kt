package com.jaywu.tacocloud.data

import com.jaywu.tacocloud.Taco

interface TacoRepository {
    fun save(taco: Taco): Taco
}