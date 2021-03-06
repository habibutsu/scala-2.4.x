/* NSC -- new Scala compiler
 * Copyright 2005-2006 LAMP/EPFL
 * @author
 */
// $Id$

package scala.tools.nsc.transform

/** <p>
 *    A base class for transforms.
 *  </p>
 *  <p>
 *    A transform contains a compiler phase which applies a tree transformer.
 *  </p>
 */
abstract class InfoTransform extends Transform {
  import global.{Symbol, Type, InfoTransformer, infoTransformers}

  def transformInfo(sym: Symbol, tpe: Type): Type

  override def newPhase(prev: scala.tools.nsc.Phase): StdPhase =
    new Phase(prev)

  protected def changesBaseClasses = true

  class Phase(prev: scala.tools.nsc.Phase) extends super.Phase(prev) {
    if (infoTransformers.nextFrom(id).pid != id) {
      val infoTransformer = new InfoTransformer {
        val pid = id
        val changesBaseClasses = InfoTransform.this.changesBaseClasses
        def transform(sym: Symbol, tpe: Type): Type = transformInfo(sym, tpe)
      }
      infoTransformers.insert(infoTransformer)
    }
  }
}

