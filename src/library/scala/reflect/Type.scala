/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2002-2006, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |                                         **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

// $Id$


package scala.reflect

import Predef.Pair

abstract class Type

case object NoPrefix extends Type
case object NoType extends Type

/** fullname */
case class NamedType(fullname: String) extends Type

/** pre # sym */
case class PrefixedType(pre: Type, sym: Symbol) extends Type

/** pre.type # sym == pre.sym */
case class SingleType(pre: Type, sym: Symbol) extends Type

/** clazz.this */
case class ThisType(clazz: Symbol) extends Type

/** clazz.super[superClazz] */
/** <code>tpe[args1, ..., argsn]</code> */
case class AppliedType(tpe: Type, args: List[Type]) extends Type

/** [a &lt;: lo &gt;: hi] */
case class TypeBounds(lo: Type, hi: Type) extends Type

/** <code>(formals1 ... formalsn) restpe</code> */
case class MethodType(formals: List[Type], restpe: Type) extends Type

/**  */
case class PolyType(typeParams: List[Symbol], typeBounds: List[(Type, Type)], resultType: Type) extends Type

/**  */
class ImplicitMethodType(formals: List[Type], restpe: Type)
extends MethodType(formals, restpe)
