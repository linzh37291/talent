
package com.lin.infrastructure.utils

import scala.beans.BeanProperty


/**
 *
 * 解决方法：
 * Run—>EditConfigurations…—>Modify options—>Add VM options—>JVM options
 * 在JVM options 内添加下面指令：
 * --add-opens java.base/java.lang=ALL-UNNAMED
 * --add-opens java.base/java.util=ALL-UNNAMED
 * --add-opens java.base/java.nio=ALL-UNNAMED
 * --add-opens java.base/sun.nio.ch=ALL-UNNAMED
 *Scala的@BeanProperty可以取代Lombok
 *
 * @author linzihao
 */
 class TestBeanCopy {
  @BeanProperty var code = ""
}

object TestBeanCopy {

  def main(args: Array[String]): Unit = {

    //
    var a = new TestBeanCopy
    a.code = "1"
    var b = new TestBeanCopy
    BeanUtils.copyProperties(a, b)
    print(b.code)
  }
}
