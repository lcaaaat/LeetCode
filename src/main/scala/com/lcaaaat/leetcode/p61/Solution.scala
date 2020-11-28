package com.lcaaaat.leetcode.p61

import scala.collection.mutable.ArrayBuffer

class ListNode(var x: Int = 0, var next: ListNode = null) {
  def mkString(): String = {
    var tmp = this
    val array = ArrayBuffer[Int]()
    while (tmp != null) {
      array += tmp.x
      tmp = tmp.next
    }
    array.mkString(", ")
  }
}

object ListNode {
  def apply(array: Array[Int]): ListNode = {
    val ret = new ListNode(array(0), null)
    var tmp = ret
    for (i <- 1 until array.length) {
      tmp.next = new ListNode(array(i), null)
      tmp = tmp.next
    }
    ret
  }
}

object Solution {

  def rotateRight(head: ListNode, k: Int): ListNode = {
    if (head == null) {
      null
    } else {
      val tail = {
        var tmp = head
        while (tmp.next != null) {
          tmp = tmp.next
        }
        tmp
      }
      tail.next = head
      var pLeft = head
      var pRight = head
      for (_ <- 1 to k) {
        pRight = pRight.next
      }
      while (pRight != tail) {
        pRight = pRight.next
        pLeft = pLeft.next
      }
      val ret = pLeft.next
      pLeft.next = null
      ret
    }
  }

  def main(args: Array[String]): Unit = {
    val head = Array[Int](1, 2, 3, 4, 5)
    val rotate = rotateRight(ListNode(head), 2)
    println(rotate.mkString())
  }
}
