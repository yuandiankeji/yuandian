远点客户端接入aip
### 功能
建立和服务器长链接

###使用
 
 1:添加依赖
 
<dependencies>
        <dependency>
            <groupId>yuandian-client</groupId>
            <artifactId>yuandian-client</artifactId>
            <version>1.0</version>
        </dependency>

  </dependencies>
  
  proto 协议文件
  
 2 注册消息
 
 1 继承 AbstractRespHandler，添加消息协议，编写消息逻辑
 
 ##### ！ 注意
 所有的 消息协议类必须放在同一个包中。
 
 2 注册
  MessageRegister.register("com.yuandian.test");
 
 其中'com.yuandian.test'为你自己的协议位置
 
 3 最后启动 客户端
 new Thread(new NetConnection(8080, "127.0.0.1")).start();
 