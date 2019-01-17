package com.atguigu.akka.actors

import akka.actor.Actor

class BActor extends Actor{
  override def receive:Receive = {
    case "我打" => {
      println("BActor(乔峰) 挺猛 看我降龙十八掌")
      Thread.sleep(500)
      //通过sender() 可以获取到发现消息的actor的ref
      sender() ! "我打"
    }
    case "exist" => {
      sender() ! "exist"
      println("BActor(乔峰) 承让承认！")
      context.stop(self) //停止actoref
      context.system.terminate()//退出actorsystem
    }
  }
}
