package com.lcaaaat.leetcode.p18

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object Solution {

  def addRet(ret: mutable.HashSet[List[Int]], onePair: (Int, Int, Int, Int), anotherPair: (Int, Int, Int, Int)) : Unit = {
    if (onePair._3 != anotherPair._3 && onePair._3 != anotherPair._4 &&
      onePair._4 != anotherPair._3 && onePair._4 != anotherPair._4) {
      ret += List(onePair._1, onePair._2, anotherPair._1, anotherPair._2).sorted
    }
  }

  def fourSum(nums: Array[Int], target: Int): List[List[Int]] = {
    val pairs = mutable.Map[Int, mutable.ListBuffer[(Int, Int, Int, Int)]]()
    val pairList = mutable.HashSet[Int]()
    for (i <- 0 until nums.length - 1) {
      for (j <- i + 1 until nums.length) {
        val sum = nums(i) + nums(j)
        if (!pairs.contains(sum)) {
          pairs(sum) = new ListBuffer[(Int, Int, Int, Int)]()
        }
        pairs(sum) += Tuple4(nums(i), nums(j), i, j)
        pairList += sum
      }
    }
    val ret = mutable.HashSet[List[Int]]()
    pairList.foreach { one =>
      val another = target - one
      if (another > one && pairs.contains(another)) {
        val oneList = pairs(one)
        val anotherList = pairs(another)
        oneList.foreach { onePair =>
          anotherList.foreach { anotherPair =>
            addRet(ret, onePair, anotherPair)
          }
        }
      } else if (another == one) {
        val list = pairs(one)
        for (i <- 0 until list.length - 1) {
          for (j <- i + 1 until list.length) {
            val onePair = list(i)
            val anotherPair = list(j)
            addRet(ret, onePair, anotherPair)
          }
        }
      }
    }
    ret.toList
  }

  def main(args: Array[String]): Unit = {
    val nums = Array(1, 0, -1, 0, -2, 2)
    println(fourSum(nums, 0))
  }
}
