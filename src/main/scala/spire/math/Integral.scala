package spire.math

import spire.algebra._
import spire.macrosk.Ops

import scala.{specialized => spec}

trait Integral[@spec(Int,Long) A] extends EuclideanRing[A]
with ConvertableFrom[A] with ConvertableTo[A] with Order[A] with Signed[A] {
  def isZero(x: A) = eqv(x, zero)
  def isNonzero(x: A) = neqv(x, zero)
  def isPositive(x: A) = gt(x, zero)
  def isNegative(x: A) = lt(x, zero)
}

class IntegralOps[A](lhs:A)(implicit ev:Integral[A]) {
  def isZero() = macro Ops.unop[Boolean]
  def isNonzero() = macro Ops.unop[Boolean]
  def isPositive() = macro Ops.unop[Boolean]
  def isNegative() = macro Ops.unop[Boolean]
}

object Integral {
  implicit object IntIsIntegral extends IntIsIntegral
  implicit object LongIsIntegral extends LongIsIntegral
  implicit object BigIntIsIntegral extends BigIntIsIntegral

  @inline final def apply[A](implicit ev:Integral[A]) = ev
}

trait IntIsIntegral extends Integral[Int] with IntIsEuclideanRing
with ConvertableFromInt with ConvertableToInt with IntOrder with IntIsSigned {
  override def fromInt(n: Int): Int = n
}

trait LongIsIntegral extends Integral[Long] with LongIsEuclideanRing
with ConvertableFromLong with ConvertableToLong with LongOrder with LongIsSigned {
  override def fromInt(n: Int): Long = n
}

trait BigIntIsIntegral extends Integral[BigInt] with BigIntIsEuclideanRing
with ConvertableFromBigInt with ConvertableToBigInt with BigIntOrder with BigIntIsSigned {
  override def fromInt(n: Int): BigInt = super[ConvertableToBigInt].fromInt(n)
}
