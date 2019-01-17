package com.atguigu.akka.actors

import akka.actor.{Actor, ActorRef}

class AActor(actorRef: ActorRef) extends Actor {

  val bActorRef: ActorRef = actorRef

  //100招后停手
  var count = 0

  override def receive: Receive = {
    case "start" => {
      println("AActor 出招了 , start ok")
      self ! "我打" //发给自己
    }
    case "exist" => {
      println("AActor(黄飞鸿) 好吧，我承认你是我旗鼓相当的对手！")
      context.stop(self) //停止actoref
      context.system.terminate()//退出actorsystem
    }
    case "我打" => {
      //给BActor 发出消息
      //这里需要持有BActor的引用(BActorRef)
      println("AActor(黄飞鸿) 厉害 看我佛山无影脚")
      Thread.sleep(500)
      count += 1
      if(count >=10){
        bActorRef ! "exist" //给BActor 发出消息停手
      }else{
        bActorRef ! "我打" //给BActor 发出消息
      }

    }

  }
}
