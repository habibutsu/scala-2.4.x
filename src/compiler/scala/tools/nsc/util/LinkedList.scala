/* NSC -- new Scala compiler
 * Copyright 2005-2006 LAMP/EPFL
 * @author  Martin Odersky
 */
// $Id$

package scala.tools.nsc.util

class LinkedList[T] {
  var next: LinkedList[T] = null
  var elem: T = _
}
