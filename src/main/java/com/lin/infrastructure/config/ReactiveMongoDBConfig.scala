package com.lin.infrastructure.config

import com.mongodb.reactivestreams.client.MongoClients
import com.mongodb.{BasicDBObject, ConnectionString, MongoCredential}
import org.bson.Document
import org.reactivestreams.{Subscriber, Subscription}
import org.springframework.context.annotation.Configuration

import java.io.FileInputStream
import java.util
import java.util.{Collections, Properties}


/**
 *
 * 链接地址:
 *
 * mongodb://talent:talent@localhost:27017/?authSource=talent&readPreference=primary&appname=MongoDB%20Compass%20Isolated%20Edition&ssl=false
 *
 * @author linzihao
 */
@Configuration
class ReactiveMongoDBConfig {

  val mongodbDatabase: String = getProperty("spring.data.mongodb.database");
  val mongodbUsername: String = getProperty("spring.data.mongodb.username");
  val mongodbPassword: String = getProperty("spring.data.mongodb.password");
  def getProperty(name: String): String = {
    val properties = new Properties()
    val path = Thread
      .currentThread()
      .getContextClassLoader
      .getResource("application.properties")
      .getPath //文件要放到resource文件夹下
    properties.load(new FileInputStream(path))

    val propVal = properties.getProperty(name)
    if (null == propVal) {
      throw new NullPointerException("read properties key={" + name + "}  is null!")
    }
    propVal
  }

  {
    //mongodb://talent:*****@localhost:27017/?authSource=talent&readPreference=primary&appname=MongoDB%20Compass%20Isolated%20Edition&ssl=false
    //    println("mongodbDatabase---"+StringUtils.hasText(mongodbDatabase))
    //    println("mongodbUsername---"+StringUtils.hasText(mongodbUsername))
    //    println("mongodbPassword---"+StringUtils.hasText(mongodbPassword))
    var credential = MongoCredential.createCredential(mongodbUsername, mongodbDatabase, mongodbPassword.toCharArray());
    var mongoCredentialList = new util.ArrayList[MongoCredential]
    mongoCredentialList.add(credential);
    //创建连接
    var config = new ConnectionString("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass%20Isolated%20Edition&ssl=false")
    var client = MongoClients.create(config);
    //创建数据库
    client.getDatabase(mongodbDatabase);
    //操作数据库db
    var mongoDB = client.getDatabase(mongodbDatabase);
    var getUsersInfoCommand = new BasicDBObject("usersInfo",
      new BasicDBObject("user", mongodbUsername).append("db", mongodbDatabase));
    var dropUserCommand = new BasicDBObject("dropUser", mongodbUsername);
    var createUserCommand = new BasicDBObject("createUser", mongodbUsername).append("pwd", mongodbPassword)
      .append("roles", Collections.singletonList(new BasicDBObject("role", "dbOwner").append("db", mongodbDatabase)));
    var result = mongoDB.runCommand(getUsersInfoCommand);
    var query = new Subscriber[Document]() {
      override def onNext(t: Document) = {
        if (!t.get("users").asInstanceOf[util.ArrayList[Object]].isEmpty) {
          println("查詢到已存在的用戶！！！！！！！：" + t)
          var dropResult = new Subscriber[Document] {
            override def onSubscribe(s: Subscription): Unit = {
              println("删除原有的用户！！！！！！！：" + t)
              s.request(1000)
            }

            override def onNext(t: Document): Unit = {}

            override def onError(t: Throwable): Unit = {
              throw t;
            }

            override def onComplete(): Unit = {
            }
          }
          mongoDB.runCommand(dropUserCommand).subscribe(dropResult)
        }
      }

      override def onSubscribe(s: Subscription): Unit = {
        println("执行查询用户命令！！！！！！")
        s.request(1000)
      }

      override def onError(t: Throwable): Unit = {
        throw t;
      }

      override def onComplete(): Unit = {
        println("执行创建用户命令！！！！！！！！！！！")
        var createResult = new Subscriber[Document] {
          override def onSubscribe(s: Subscription): Unit = {
            s.request(1000)
          }

          override def onNext(t: Document): Unit = {}

          override def onError(t: Throwable): Unit = {
            throw t;
          }

          override def onComplete(): Unit = {
            client.close();
          }
        }
        mongoDB.runCommand(createUserCommand).subscribe(createResult)
      }
    };
    result.subscribe(query)

  }


}
