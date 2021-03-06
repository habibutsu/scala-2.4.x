/* NSC -- new Scala compiler
 * Copyright 2005-2006 LAMP/EPFL
 * @author  Martin Odersky
 */

// $Id$

package scala.tools.nsc.backend

import scala.tools.nsc.ast._
import scala.collection.mutable.MutableList

/**
 * Simple implementation of a worklist algorithm. A processing
 * function is applied repeatedly to the first element in the
 * worklist, as long as the stack is not empty.
 *
 * The client class should mix-in this class and initialize the
 * worklist field and define the <code>processElement</code> method.
 * Then call the <code>run</code> method providing a function that
 * initializes the worklist.
 *
 * @author  Martin Odersky
 * @version 1.0
 * @see     <a href="icode/Linearizers.html" target="contentFrame">scala.tools.nsc.backend.icode.Linearizers</a>
 */
trait WorklistAlgorithm {
  type Elem
  type WList <: MutableList[Elem]

  val worklist: WList

  /**
   * Run the iterative algorithm until the worklist
   * remains empty. The initializer is run once before
   * the loop starts and should initialize the worklist.
   *
   * @param initWorklist ...
   */
  def run(initWorklist: => Unit) = {
    initWorklist

    while (!(worklist.length == 0))
      processElement(dequeue);
  }

  /**
   * Process the current element from the worklist.
   */
  def processElement(e: Elem): Unit

  /**
   * Remove and return the first element to be processed from the worklist.
   */
  def dequeue: Elem
}
