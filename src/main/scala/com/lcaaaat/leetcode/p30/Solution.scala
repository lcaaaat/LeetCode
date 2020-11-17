package com.lcaaaat.leetcode.p30

object Solution {
  import scala.collection.mutable.ArrayBuffer
  import scala.collection.mutable

  def findSubstring(s: String, words: Array[String]): List[Int] = {
    if (s != null && s.length > 0 && words != null && words.size > 0) {
      val wordMap = mutable.Map[String, Int]()
      words.foreach { word =>
        if (wordMap.contains(word)) {
          wordMap(word) = wordMap(word) + 1
        } else {
          wordMap(word) = 1
        }
      }

      val ret = ArrayBuffer[Int]()
      val length = words(0).length
      for (i <- 0 until length) {
        var left = i
        var right = i
        var count = 0
        val tmp = mutable.Map[String, Int]()
        while (right + length <= s.length()) {
          val word = s.substring(right, right + length);
          if (tmp.contains(word)) {
            tmp(word) = tmp(word) + 1
          } else {
            tmp(word) = 1
          }
          if (!wordMap.contains(word)) {
            wordMap(word) = 0
          }
          right += length
          count += 1
          while (tmp(word) > wordMap(word)) {
            val leftWord = s.substring(left, left + length)
            count -= 1
            tmp(leftWord) = tmp(leftWord) - 1
            left += length
          }
          if (count == words.size) {
             ret += left
          }
        }
      }
      ret.toList
    } else {
      List[Int]()
    }
  }

  def main(args: Array[String]): Unit = {
    val s = "barfoobarthefoobarman"
    val words = Array[String]("foo", "bar")
    println(findSubstring(s, words))
  }
}
