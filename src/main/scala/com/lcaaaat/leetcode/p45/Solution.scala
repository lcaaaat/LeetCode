package com.lcaaaat.leetcode.p45

object Solution {
  def jump(nums: Array[Int]): Int = {
    var end = 0
    var ans = 0
    var max = 0
    for (i <- 0 until nums.length) {
      max = Math.max(nums(i) + i, max)
      if (i == end  && i != nums.length - 1) {
        ans += 1
        end = max
      }
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    val nums = Array[Int](2,3,1,1,4)
    jump(nums)
  }
}
