package com.lcaaaat.leetcode.p32

object Solution {
  def longestValidParentheses(s: String): Int = {
    val length = s.length
    val f = Array.ofDim[Int](length)
    for (i <- 0 until length) {
      f(i) = 0
    }
    for (i <- 1 until length) {
      if (s(i) == ')') {
        if (s(i - 1) == '(') {
          if (i > 1) {
            f(i) = f(i - 2) + 2
          } else {
            f(i) = 2
          }
        } else {
          if (i - f(i - 1) > 0 && s(i - f(i - 1) -1) == '(') {
            if (f(i - 1) < i - 1) {
              f(i) = f(i - 1) + 2 + f(i - 1 - f(i - 1) - 1)
            } else {
              f(i) = f(i - 1) + 2
            }
          }
        }
      }
    }
    if (f.isEmpty) {
      0
    } else {
      f.max
    }
  }

  def main(args: Array[String]): Unit = {
    val s = "()(())"
    println(longestValidParentheses(s))
  }
}
