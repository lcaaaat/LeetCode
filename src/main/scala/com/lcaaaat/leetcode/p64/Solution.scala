package com.lcaaaat.leetcode.p64

import scala.collection.mutable.ArrayBuffer

object Solution {
  def minPathSum(grid: Array[Array[Int]]): Int = {
    var last = ArrayBuffer[Int]()
    var current = ArrayBuffer[Int]()
    val n = grid.length
    val m = grid(0).length
    last += grid(0)(0)
    current += 0
    for (i <- 1 until m) {
      current += 0
      last += (last(i - 1) + grid(0)(i))
    }
    for (i <- 1 until n) {
      for (j <- 0 until m) {
        if (j == 0) {
          current(j) = last(j) + grid(i)(j)
        } else {
          current(j) = Math.min(last(j), current(j - 1)) + grid(i)(j)
        }
      }
      last = current
    }
    last(m - 1)
  }
}
