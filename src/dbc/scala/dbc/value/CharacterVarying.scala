/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2006, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |                                         **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

// $Id:CharacterVarying.scala 6853 2006-03-20 16:58:47 +0100 (Mon, 20 Mar 2006) dubochet $


package scala.dbc.value;


/** A SQL-99 value of type character varying string. */
abstract class CharacterVarying extends Value {

  override val dataType: datatype.CharacterVarying;

  /** An SQL-99 compliant string representation of the value. */
  def sqlString: String = {
    "'" + nativeValue + "'"
  }

}

/** An object offering transformation methods (views) on the value.
  * This object must be visible in an expression to use value auto-
  * conversion. */
object CharacterVarying {

  /** A character varying string value as a native string. */
  implicit def characterVaryingToString (obj:value.CharacterVarying): String = obj.nativeValue;

}
