package com.lcaaaat.leetcode.p85

import scala.collection.mutable

object Solution {

  def maxRectangle(heights: Array[Int]): Int = {
    val stack = mutable.Stack[Int]()
    val left = Array.ofDim[Int](heights.length)
    val right = Array.ofDim[Int](heights.length)
    for (i <- heights.indices) {
      val height = heights(i)
      if (stack.isEmpty) {
        stack.push(i)
        left(i) = -1
      } else {
        while (stack.nonEmpty && heights(stack.head) >= height) {
          stack.pop()
        }
        left(i) = if (stack.nonEmpty) {
          stack.head
        } else {
          -1
        }
        stack.push(i)
      }
    }
    stack.clear()
    for (i <- heights.indices.reverse) {
      val height = heights(i)
      if (stack.isEmpty) {
        stack.push(i)
        right(i) = heights.length
      } else {
        while (stack.nonEmpty && heights(stack.head) >= height) {
          stack.pop()
        }
        right(i) = if (stack.nonEmpty) {
          stack.head
        } else {
          heights.length
        }
        stack.push(i)
      }
    }
    var max = 0
    for (i <- heights.indices) {
      max = Math.max(heights(i) * (right(i) - left(i) - 1), max)
    }
    max
  }

  def maximalRectangle(matrix: Array[Array[Char]]): Int = {
    if (matrix.isEmpty) {
      0
    } else {
      var max = 0
      val length = matrix(0).length
      val heights = Array.ofDim[Int](length)
      for (i <- heights.indices) {
        heights(i) = 0
      }
      for (i <- matrix.indices) {
        for (j <- heights.indices) {
          if (matrix(i)(j) == '1') {
            heights(j) = heights(j) + 1
          } else {
            heights(j) = 0
          }
        }
        max = Math.max(maxRectangle(heights), max)
      }
      max
    }
  }

  def main(args: Array[String]): Unit = {
    val heights = Array[Int](2, 0, 3)
    println(maxRectangle(heights))
  }
}
