abstract class Foo {
  class Inner {
    def inner: int = 1;
  }
  def foo: Inner;
}
trait Bar {
  type Inner;
  def foo: Inner = foo;
}
class Test extends Foo with Bar {
  Console.println(foo.inner);
}
