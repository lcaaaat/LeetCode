package com.lcaaaat.leetcode.P72

object Solution {
  def minDistance(word1: String, word2: String): Int = {
    val length1 = word1.length + 1
    val length2 = word2.length + 1
    val f = Array.ofDim[Int](length1, length2)
    f(0)(0) = 0
    for (i <- 1 until length1) {
      f(i)(0) = i
    }
    for (j <- 1 until length2) {
      f(0)(j) = j
    }
    for (i <- 1 until length1) {
      for (j <- 1 until length2) {
        if (word1(i - 1) == word2(j - 1)) {
          f(i)(j) = f(i - 1)(j - 1)
        } else {
          f(i)(j) = f(i - 1)(j - 1) + 1
        }
        f(i)(j) = Math.min(f(i)(j), f(i - 1)(j) + 1)
        f(i)(j) = Math.min(f(i)(j), f(i)(j - 1) + 1)
      }
    }
    f(length1 - 1)(length2 - 1)

  }

  def main(args: Array[String]): Unit = {
    val word1 = "horse"
    val word2 = "ros"
    println(minDistance(word1, word2))
  }
}
