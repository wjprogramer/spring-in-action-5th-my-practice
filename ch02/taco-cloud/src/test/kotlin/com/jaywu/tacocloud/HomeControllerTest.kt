package com.jaywu.tacocloud

//import org.hamcrest.core.StringContains
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
//import org.springframework.test.web.servlet.MockMvc
//import org.springframework.test.web.servlet.get
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers
//
//@WebMvcTest(HomeController::class)
//class HomeControllerTest {
//
//    @Autowired
//    private lateinit var mockMvc: MockMvc
//
//    @Test
//    fun testHomePage() {
//        val result = mockMvc.get("/")
//                .andDo {
//                    MockMvcResultHandlers.print()
//                }
//                .andExpect {
//                    status { isOk }
//                    view { name("Home") }
//                    content { string( StringContains("Welcome to...") ) }
//                }
//                .andReturn()
//
//        val content = result.response.contentAsString
//    }
//
//}