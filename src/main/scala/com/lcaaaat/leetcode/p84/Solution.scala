package com.lcaaaat.leetcode.p84

import scala.collection.mutable

object Solution {
  def largestRectangleArea(heights: Array[Int]): Int = {
    val stack = mutable.Stack[Int]()
    val left = Array.ofDim[Int](heights.length)
    val right = Array.ofDim[Int](heights.length)
    for (i <- heights.indices) {
      while (stack.nonEmpty && heights(stack.head) >= heights(i)) {
        stack.pop()
      }
      left(i) = if (stack.isEmpty) {
        -1
      } else {
        stack.head
      }
      stack.push(i)
    }
    stack.clear()
    for (i <- heights.indices.reverse) {
      while (stack.nonEmpty && heights(stack.head) >= heights(i)) {
        stack.pop()
      }
      right(i) = if (stack.isEmpty) {
        heights.length
      } else {
        stack.elems.head
      }
      stack.push(i)
    }
    var max = 0
    for (i <- heights.indices) {
      max = Math.max(max, heights(i) * (right(i) - left(i) - 1))
    }
    max
  }

  def main(args: Array[String]): Unit = {
    val heights = Array[Int](2, 1, 2)
    println(largestRectangleArea(heights))
  }
}
