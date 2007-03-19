object Test extends Application {

def test() {

  abstract class A[T] {
    def myVal: T
  }

  class B[T1](value: T1) extends A[T1] {
    def myVal: T1 = value
  }

  Console.println(new B[int](23).myVal)
}
}
