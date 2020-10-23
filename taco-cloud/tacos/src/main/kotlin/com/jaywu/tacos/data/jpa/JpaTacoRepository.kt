package com.jaywu.tacos.data.jpa

import com.jaywu.tacos.Taco
import org.springframework.data.repository.CrudRepository

interface JpaTacoRepository: CrudRepository<Taco, Long> {
}