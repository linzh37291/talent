package com.lin.infrastructure.config

import com.lin.infrastructure.utils.PropertyUtil
import com.mongodb.reactivestreams.client.{MongoClient, MongoClients}
import com.mongodb.{BasicDBObject, ConnectionString}
import org.bson.Document
import org.reactivestreams.{Subscriber, Subscription}
import org.springframework.context.annotation.Configuration

import java.util
import java.util.Collections


/**
 *
 * 链接地址:
 *
 * mongodb://talent:talent@localhost:27017/?authSource=talent&readPreference=primary&appname=MongoDB%20Compass%20Isolated%20Edition&ssl=false
 *
 * @author linzihao
 */
@Configuration
class ReactiveMongoDdConfig {
  val log = org.slf4j.LoggerFactory.getLogger(classOf[ReactiveMongoDdConfig])
  val mongodbDatabase: String = PropertyUtil.getProperty("spring.data.mongodb.database");
  val mongodbUsername: String = PropertyUtil.getProperty("spring.data.mongodb.username");
  val mongodbPassword: String = PropertyUtil.getProperty("spring.data.mongodb.password");


  def delCmd(client: MongoClient): Unit = {
    var dropResult = new Subscriber[Document] {
      override def onSubscribe(s: Subscription): Unit = {
        log.info("删除原有的用户！！！！！！！：")
        s.request(1000)
      }

      override def onNext(t: Document): Unit = {}

      override def onError(t: Throwable): Unit = {
        throw t;
      }

      override def onComplete(): Unit = {
      }
    }
    var dropUserCommand = new BasicDBObject("dropUser", mongodbUsername);
    var mongodb = client.getDatabase(mongodbDatabase);
    mongodb.runCommand(dropUserCommand).subscribe(dropResult)
  }

  def createCmd(client: MongoClient): Unit = {
    log.info("执行创建用户命令！！！！！！！！！！！")
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
    var mongodb = client.getDatabase(mongodbDatabase);
    var createUserCommand = new BasicDBObject("createUser", mongodbUsername).append("pwd", mongodbPassword)
      .append("roles", Collections.singletonList(new BasicDBObject("role", "dbOwner").append("db", mongodbDatabase)));
    mongodb.runCommand(createUserCommand).subscribe(createResult)

  }

  {
    //mongodb://talent:*****@localhost:27017/?authSource=talent&readPreference=primary&appname=MongoDB%20Compass%20Isolated%20Edition&ssl=false
    log.info("mongodbDatabase---" + mongodbDatabase)
    log.info("mongodbUsername---" + mongodbUsername)
    log.info("mongodbPassword---" + mongodbPassword)
    //    var credential = MongoCredential.createCredential(mongodbUsername, mongodbDatabase, mongodbPassword.toCharArray());
    //    var mongoCredentialList = new util.ArrayList[MongoCredential]
    //    mongoCredentialList.add(credential);
    //创建连接
    var config = new ConnectionString("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass%20Isolated%20Edition&ssl=false")
    var client = MongoClients.create(config);
    //操作数据库db
    var mongoDB = client.getDatabase(mongodbDatabase);
    var getUsersInfoCommand = new BasicDBObject("usersInfo",
      new BasicDBObject("user", mongodbUsername).append("db", mongodbDatabase));
    var result = mongoDB.runCommand(getUsersInfoCommand);
    var query = new Subscriber[Document]() {
      override def onNext(t: Document) = {
        if (!t.get("users").asInstanceOf[util.ArrayList[Object]].isEmpty) {
          log.info("查詢到已存在的用戶！！！！！！！：" + t)
        } else {
          createCmd(client)
        }
      }

      override def onSubscribe(s: Subscription): Unit = {
        log.info("执行查询用户命令！！！！！！")
        s.request(1000)
      }

      override def onError(t: Throwable): Unit = {
        throw t;
      }

      override def onComplete(): Unit = {

      }
    };
    result.subscribe(query)

  }


}
