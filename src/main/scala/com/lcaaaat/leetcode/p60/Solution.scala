package com.lcaaaat.leetcode.p60

import scala.collection.mutable.ArrayBuffer

object Solution {
  def factory(n: Int): Int = {
    (1 to n).product
  }

  def getPermutation(n: Int, k: Int): String = {
    val numbers = ArrayBuffer[Int]()
    (1 to n).foreach { i =>
      numbers += i
    }
    val ret = ArrayBuffer[String]()
    var current = 1
    var kk = k
    while (current <= n) {
      val rest = n - current
      if (rest == 0) {
        ret += numbers(0).toString
      } else {
        val fac = factory(rest)
        val div = (kk - 1) / fac
        val mod = kk - fac * div
        ret += numbers(div).toString
        numbers.remove(div)
        kk = mod
      }
      current += 1
    }
    ret.mkString
  }

  def main(args: Array[String]): Unit = {
    println(getPermutation(3, 2))
  }
}
