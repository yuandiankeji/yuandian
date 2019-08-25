package com.yuandian.server.core.rpc;

import com.yuandian.data.rpc.*;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RpcService extends RpcWorldServiceGrpc.RpcWorldServiceImplBase {
    private Logger logger = LoggerFactory.getLogger(RpcService.class);

    @Override
    public void query(Req request, StreamObserver<Resp> responseObserver) {
    }

    @Override
    public void notify(NotifyMessageReq request, StreamObserver<NotifyMessageResp> responseObserver) {
        String cmd = request.getCmd();
        String data = request.getData();
        logger.info("[RpcService]| cmd={},data={}", cmd, data);

    }

}
