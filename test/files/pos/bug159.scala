import compat.StringBuilder

object foo {

  // the problem seems to appear only
  // if "val _" is in the body of a case
  def cooked( ckd:StringBuilder ):Unit =
    'a' match {
      case '-' =>
        val _ = ckd.append( '_' );
      case 'v' =>
        val _ = ckd.append( '_' );
    }

}
object foo1 {
  def f():Unit = {
    1 match {
      case 2 => val _ = 1;
      case 3 => val _ = 2;
      case 4 => val _ = 2;
    }
  }
}
