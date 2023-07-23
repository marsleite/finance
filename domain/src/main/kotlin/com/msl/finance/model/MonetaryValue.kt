package com.msl.finance.model

import java.math.BigDecimal
import java.math.RoundingMode

data class MonetaryValue(val value: BigDecimal) {
  companion object {
    private const val SCALE = 2
    private val ROUNDING_MODE = RoundingMode.HALF_EVEN

    fun fromDouble(valor: Double): MonetaryValue {
      val bigDecimalValor = BigDecimal(valor).setScale(SCALE, ROUNDING_MODE)
      return MonetaryValue(bigDecimalValor)
    }
  }

  operator fun plus(outro: MonetaryValue): MonetaryValue {
    val sum = value.add(outro.value)
    return MonetaryValue(sum)
  }

  operator fun minus(outro: MonetaryValue): MonetaryValue {
    val sub = value.subtract(outro.value)
    return MonetaryValue(sub)
  }

  operator fun times(valor: Double): MonetaryValue {
    val multiply = valor.toBigDecimal().setScale(SCALE, ROUNDING_MODE)
      .multiply(value).setScale(SCALE, ROUNDING_MODE)
    return MonetaryValue(multiply)
  }
}