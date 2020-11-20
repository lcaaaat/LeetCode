package com.lcaaaat.leetcode.p39


import scala.collection.mutable.ArrayBuffer

object Solution {
  def backtracking(numbers: Array[Int],
                   results: ArrayBuffer[List[Int]],
                   result: ArrayBuffer[Int],
                   ret: Int,
                   location: Int
                  ): Unit = {
    if (ret == 0) {
      results += result.toList
    } else {
      var newLocation = location
      while (newLocation != numbers.length && numbers(newLocation) > ret) {
        newLocation += 1
      }
      if (newLocation != numbers.length) {
        for (i <- newLocation until numbers.length) {
          result += numbers(i)
          backtracking(numbers, results, result, ret - numbers(i), i)
          result.remove(result.length - 1)
        }
      }
    }
  }

  def combinationSum(candidates: Array[Int], target: Int): List[List[Int]] = {
    val numbers = candidates.sortWith( _.compareTo(_) > 0 )
    val results = ArrayBuffer[List[Int]]()
    val result = ArrayBuffer[Int]()
    if (candidates.length >= 1) {
      backtracking(numbers, results, result, target, 0)
    }
    results.toList
  }

  def main(args: Array[String]): Unit = {
    val numbers = Array[Int](2, 3, 6, 7)
    combinationSum(numbers, 7).foreach { arr =>
      println(arr.mkString(", "))
    }
  }
}
