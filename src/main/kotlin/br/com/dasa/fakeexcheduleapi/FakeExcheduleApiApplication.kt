package br.com.dasa.fakeexcheduleapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients(basePackages = ["br.com.dasa.fakeexcheduleapi.clients"])
@SpringBootApplication
class FakeExcheduleApiApplication

fun main(args: Array<String>) {
	runApplication<FakeExcheduleApiApplication>(*args)
}
