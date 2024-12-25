package com.cursosant.clean.common

import kotlin.random.Random


fun getResultEventsInRealTime() = listOf(
    SportEvent.ResultSuccess(1, "Futból", listOf("Italia", "Perú", "Corea del Sur")),
    SportEvent.ResultSuccess(
        2,
        "Levantamiento de Pesas",
        listOf("Mongolia", "Alemania", "Turquía")
    ),
    SportEvent.ResultError(10, "Error de Red"),
    SportEvent.ResultSuccess(3, "Gimanasia Rítmica", listOf("Rusia", "USA", "Francia")),
    SportEvent.ResultSuccess(4, "Polo Acuático", listOf("España", "Vietnam", "USA")),
    SportEvent.ResultSuccess(5, "Beisból", null, true),
    SportEvent.ResultError(20, "Error Permisos"),
    SportEvent.ResultSuccess(6, "Rugby", listOf("Sudáfrica", "Quatar", "Rumania")),
    SportEvent.ResultSuccess(7, "Tenis", listOf("España", "México", "Colombia"))
)

fun getAdEventsInRealTime() =  listOf(
    SportEvent.AdEvent,
    SportEvent.AdEvent
)

fun someTime(): Long = Random.nextLong(500, 2_000)