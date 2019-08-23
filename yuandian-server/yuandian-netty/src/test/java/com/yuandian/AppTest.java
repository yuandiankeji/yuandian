package com.yuandian;


import com.yuandian.data.rpc.NotifyMessageReq;
import com.yuandian.data.rpc.RpcWorldServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class AppTest {
    private static ManagedChannel channel;

    public static void main(String[] args) {
        channel = ManagedChannelBuilder.forAddress("127.0.0.1", 9099).usePlaintext(true).build();
        RpcWorldServiceGrpc.RpcWorldServiceBlockingStub stub = RpcWorldServiceGrpc.newBlockingStub(channel);
        NotifyMessageReq.Builder builder = NotifyMessageReq.newBuilder().setCmd("helloworld").setData("yuandian");
        stub.notify(builder.build());
    }


}
