/* NSC -- new Scala compiler
 * Copyright 2005-2006 LAMP/EPFL
 * @author  Martin Odersky
 */
// $Id$

package scala.tools.nsc.symtab

trait InfoTransformers requires SymbolTable {

  abstract class InfoTransformer {
    var prev: InfoTransformer = this
    var next: InfoTransformer = this

    val pid: Phase#Id
    val changesBaseClasses: Boolean
    def transform(sym: Symbol, tpe: Type): Type

    def insert(that: InfoTransformer): unit = {
      assert(this.pid != that.pid)
      if (that.pid < this.pid) {
        prev insert that
      } else if (next.pid <= that.pid && next.pid != NoPhase.id) {
        next insert that
      } else {
        that.next = next
        that.prev = this
        next.prev = that
        this.next = that
      }
    }

    def nextFrom(from: Phase#Id): InfoTransformer =
      if (from == this.pid) this
      else if (from < this.pid)
        if (prev.pid < from) this
        else prev.nextFrom(from);
      else if (next.pid == NoPhase.id) next
      else next.nextFrom(from)
  }
}

