package com.lcaaaat.leetcode.p10

object Solution {
  def isMatch(s: String, p: String): Boolean = {
    val sLength = s.length + 1
    val pLength = p.length + 1
    val ss = " " + s
    val pp = " " + p
    val f = Array.ofDim[Boolean](sLength, pLength)
    (0 until sLength).foreach { i => (0 until pLength).foreach { j => f(i)(j) = false }}
    f(0)(0) = true
    (2 to p.length).foreach { j =>
      f(0)(j) = f(0)(j) || (f(0)(j - 2) && pp(j) == '*')
    }
    (1 to s.length).foreach { i =>
      (1 to p.length).foreach { j =>
        f(i)(j) = f(i)(j) ||
          (f(i - 1)(j - 1) && ss(i) == pp(j)) ||
          (f(i - 1)(j - 1) && pp(j) == '.')
        if (j > 1) {
          f(i)(j) = f(i)(j) ||
            (f(i - 1)(j - 1) && (pp(j) == '*' && (ss(i) == pp(j - 1) || pp(j - 1) == '.'))) ||
            (f(i - 1)(j) && pp(j) == '*' && pp(j - 1) == '.') ||
            (f(i)(j - 1) && pp(j) == '*')
        }
        if (j > 2) {
          f(i)(j) = f(i)(j) || (f(i)(j - 2) && pp(j) == '*')
        }
      }
    }
    f(s.length)(p.length)
  }

  def main(args: Array[String]): Unit = {
    assert(!isMatch("aa", "a"))
    assert(isMatch("aa", "a*"))
    assert(isMatch("ab", ".*"))
    assert(isMatch("aab", "c*a*b"))
    assert(!isMatch("mississippi", "mis*is*p*."))
    assert(isMatch("aaa", "ab*a*c*a"))
    assert(isMatch("", "c*c*"))
    assert(!isMatch("a", ".*..a*"))
//    println(isMatch("aaa", "ab*a*c*a"))
  }
}