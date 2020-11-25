package com.lcaaaat.leetcode.p40

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object Solution {
  def backtracking(numbers: Array[Int],
                   results: ArrayBuffer[List[Int]],
                   result: ArrayBuffer[Int],
                   ret: Int,
                   location: Int
                  ): Unit = {
    if (ret == numbers(location)) {
      result += numbers(location)
      results += result.toList
      result.remove(result.length - 1)
    } else {
      if (ret > numbers(location)) {
        result += numbers(location)
        for (i <- (location + 1) until numbers.length) {
          if (i == location + 1 || numbers(i) != numbers(i - 1)) {
            backtracking(numbers, results, result, ret - numbers(location), i)
          }
        }
        result.remove(result.length - 1)
      }
    }
  }

  def combinationSum2(candidates: Array[Int], target: Int): List[List[Int]] = {
    val numbers = candidates.sortWith( _.compareTo(_) > 0 )
    val results = ArrayBuffer[List[Int]]()
    val result = ArrayBuffer[Int]()
    for (i <- numbers.indices) {
      if (i == 0 || numbers(i) != numbers(i - 1)) {
        backtracking(numbers, results, result, target, i)
      }
    }
    results.toList
  }

  def main(args: Array[String]): Unit = {
    val numbers = Array[Int](1, 1, 3, 6, 7)
    combinationSum2(numbers, 7).foreach { arr =>
      println(arr.mkString(", "))
    }
  }
}
