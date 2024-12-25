package com.cursosant.patternsbasics

import com.cursosant.patternsbasics.eventBus.Result
import com.cursosant.patternsbasics.eventBus.SportEvent

fun getEventsInRealTime() =  listOf(
    Result(1, "Fútbol", listOf("Italia", "Perú", "Corea del Sur")),
    Result(2, "Levantamiento Pesas", listOf("Mongoli", "Alemania", "Turquia")),
    Result(3, "Gimansio Rítmica", listOf("Rusia", "Usa", "Francia")),
    Result(4, "Polo Acuático", listOf("España", "Vietnam", "USA")),
    Result(5, "Beisbol", null, true),
    Result(6, "Rugby", listOf("Sudáfrica", "Quatar", "Rumanía")),
    Result(7, "Tenis", listOf("España", "México", "Colombia"))
)

fun getResultEventsInRealTime() = listOf(
    SportEvent.ResultSuccess(1, "Futból", listOf("Italia", "Perú", "Corea del Sur")),
    SportEvent.ResultSuccess(2, "Levantamiento de Pesas", listOf("Mongolia", "Alemania", "Turquía")),
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