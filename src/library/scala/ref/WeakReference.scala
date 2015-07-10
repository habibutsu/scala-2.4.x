/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2006-2007, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

// $Id$

package scala.ref

/**
 *  @author Sean McDirmid
 */
class WeakReference[+T <: AnyRef](value: T, queue: ReferenceQueue[T]) extends ReferenceWrapper[T] {
  def this(value: T) = this(value, null)
  val underlying =
    if (queue == null) new java.lang.ref.WeakReference(value)
    else new java.lang.ref.WeakReference(value, queue.underlying)
}
