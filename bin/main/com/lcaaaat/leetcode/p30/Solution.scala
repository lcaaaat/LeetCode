package com.lcaaaat.leetcode.p30

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable

object Solution {
  def recursive(
      locations: mutable.Map[Int, ArrayBuffer[String]],
      words: mutable.Map[String, Int],
      startLocation: Int,
      currentLocation: Int,
      result: mutable.Set[Int]
  ): Unit = {
    if (words.isEmpty) {
      result += startLocation
      return
    }
    if (!locations.contains(currentLocation)) {
      return
    }
    locations(currentLocation).foreach { word =>
      if (words.contains(word)) {
        words(word) = words(word) - 1
        if (words(word) == 0) {
          words.remove(word)
        }
        recursive(locations, words, startLocation, currentLocation + word.length(), result)
        if (words.contains(word)) {
          words(word) = words(word) + 1
        } else {
          words(word) = 1
        }
      }
    }
  }

  def findSubstring(s: String, words: Array[String]): List[Int] = {
    val locations = mutable.Map[Int, ArrayBuffer[String]]()
    val allWords = mutable.Map[String, Int]()
    words.foreach { word =>
      if (allWords.contains(word)) {
        allWords(word) = allWords(word) + 1
      } else {
        allWords(word) = 1
      }
      var index = s.indexOf(word)
      while (index != -1) {
        if (!locations.contains(index)) {
          locations(index) = ArrayBuffer[String]()
        }
        locations(index) += word
        index = s.indexOf(word, index + 1)
      }
    }

    val ret = mutable.Set[Int]()
    for (i <- 0 until s.length()) {
      recursive(locations, allWords, i, i, ret)
    }
    ret.toList
  }

  def main(args: Array[String]): Unit = {
    val s = "barfoothefoobarman"
    val words = Array[String]("foo", "bar")
    println(findSubstring(s, words))
  }
}
