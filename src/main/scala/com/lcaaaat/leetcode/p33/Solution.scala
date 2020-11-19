package com.lcaaaat.leetcode.p33

object Solution {
  def searchWithAsce(nums: Array[Int], target: Int, start: Int, end: Int): Int = {
    if (start == end) {
      if (nums(start) == target) {
        start
      } else {
        -1
      }
    } else if (start + 1 == end) {
      if (nums(start) == target) {
        start
      } else if (nums(end) == target) {
        end
      } else {
        -1
      }
    } else {
      val mid = (start + end) / 2
      if (target <= nums(mid)) {
        searchWithAsce(nums, target, start, mid)
      } else {
        searchWithAsce(nums, target, mid + 1, end)
      }
    }
  }

  def searchWithoutAsce(nums: Array[Int], target: Int, start: Int, end: Int): Int = {
    if (start == end) {
      if (nums(start) == target) {
        start
      } else {
        -1
      }
    } else if (start + 1 == end) {
      if (nums(start) == target) {
        start
      } else if (nums(end) == target) {
        end
      } else {
        -1
      }
    } else {
      val mid = (start + end) / 2
      // [4, 5, 6,7,0,1,2]
      if (nums(mid) > nums(start)) {
        if (target <= nums(mid) && target >= nums(start)) {
          searchWithAsce(nums, target, start, mid)
        } else {
          searchWithoutAsce(nums, target, mid + 1, end)
        }
      } else {
        if (target <= nums(end) && target >= nums(mid + 1)) {
          searchWithAsce(nums, target, mid + 1, end)
        } else {
          searchWithoutAsce(nums, target, start, mid)
        }
      }

    }
  }

  def search(nums: Array[Int], target: Int): Int = {
    if (nums.isEmpty) {
      -1
    } else {
      if (nums(0) <= nums(nums.length - 1)) {
        searchWithAsce(nums, target, 0, nums.length - 1)
      } else {
        searchWithoutAsce(nums, target, 0, nums.length -1)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val nums = Array[Int](4,5,6,7,0,1,2)
    val target = 0
    println(search(nums, target))
  }
}
