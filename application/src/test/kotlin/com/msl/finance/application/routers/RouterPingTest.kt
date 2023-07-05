package com.msl.finance.application.routers

import com.msl.finance.integration.IntegrationTest
import org.junit.jupiter.api.Test

private const val PONG = "pong"

internal class RouterPingTest: IntegrationTest() {

    @Test
    fun `should return pong when ping endpoint is called`() {
        webTestClient.get()
            .uri("/ping")
            .exchange()
            .expectStatus()
            .isOk
            .expectBody(String::class.java)
            .isEqualTo(PONG)
    }

    @Test
    fun `should return not found error`() {
        webTestClient.get()
            .uri("/pingg")
            .exchange()
            .expectStatus()
            .is4xxClientError
    }
}