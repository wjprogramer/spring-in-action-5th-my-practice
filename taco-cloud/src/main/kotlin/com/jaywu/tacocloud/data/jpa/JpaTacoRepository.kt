package com.jaywu.tacocloud.data.jpa

import com.jaywu.tacocloud.Taco
import org.springframework.data.repository.CrudRepository

interface JpaTacoRepository: CrudRepository<Taco, Long> {
}