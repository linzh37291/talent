package com.nova.felixchat.infrastructure.utils

import lombok.Data

@Data
class TestBeanCopy {

  var code = ""
  //  def setCode(code: String): Unit = {
  //    this.code = code
  //  }
  //  def getCode = code
}

/**
 *
 * @author linzihao
 */
object TestBeanCopy {

  def main(args: Array[String]): Unit = {
    var a = new TestBeanCopy
    a.code = "1"
    var b = new TestBeanCopy
    BeanUtils.copyProperties(a, b)
    print(b.code)
  }
}
