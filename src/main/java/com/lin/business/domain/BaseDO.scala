package com.lin.business.domain

/**
 * Domain负责业务逻辑，
 * 调用Repository对象来执行数据库操作。Domain没有数据库操作，
 * 具体的数据库操作是通过调用Repository对象完成的。
 *
 * 领域对象是具有属性和行为的对象，是有状态的，数据和行为都是可以重用的。
 *
 * 在很多Java项目中，Service类是无状态的，而且一般是单例，业务逻辑直接写到Service类里面，这种方式本质上是面向过程的编程方式，丢失了面向对象的所有好处，重用性极差。
 *
 * 应用“贫血模型”会把属于对象的数据和行为分离，领域对象不再是一个整体，破坏了领域模型。
 *
 * @Author linzihao
 */
abstract class BaseDO {

}
