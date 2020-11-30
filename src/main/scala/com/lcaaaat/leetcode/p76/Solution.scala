package com.lcaaaat.leetcode.p76

import scala.collection.mutable

object Solution {

  def minWindow(s: String, t: String): String = {
    if (s.isEmpty || t.isEmpty) {
      ""
    } else {
      val map = mutable.Map[Char, Int]()
      val tLength = t.length
      t.foreach { ch =>
        if (map.contains(ch)) {
          map(ch) = map(ch) + 1
        } else {
          map(ch) = 1
        }
      }
      var min = s.length + 1
      var current = if (map.contains(s(0))) {
        map(s(0)) = map(s(0)) - 1
        1
      } else {
        0
      }
      var left = -1
      var right = -1
      var pLeft = 0
      var pRight = 0
      def moveLeft(): Unit = {
        val ch = s(pLeft)
        if (map.contains(ch)) {
          map(ch) = map(ch) + 1
          if (map(ch) > 0) {
            current -= 1
          }
        }
        pLeft += 1
      }
      def moveRight(): Unit = {
        pRight += 1
        if (pRight < s.length) {
          val ch = s(pRight)
          if (map.contains(ch)) {
            map(ch) = map(ch) - 1
            if (map(ch) >= 0) {
              current += 1
            }
          }
        }
      }
      while (pRight < s.length) {
        if (current == tLength) {
          if (min > pRight - pLeft + 1) {
            min = pRight - pLeft + 1
            left = pLeft
            right = pRight
          }
          moveLeft()
        } else {
          moveRight()
        }
      }
      if (left != -1) {
        s.substring(left, right + 1)
      } else {
        ""
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val s = "aa"
    val t = "b"
    println(minWindow(s, t))
  }
}
