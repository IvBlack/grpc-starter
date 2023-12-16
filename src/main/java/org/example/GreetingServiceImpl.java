package org.example;

import com.generated.grpc.GreetingServiceGrpc;
import com.generated.grpc.GreetingServiceOuterClass;
import io.grpc.stub.StreamObserver;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {
@Override
public void greeting(GreetingServiceOuterClass.HelloRequest request,
        StreamObserver<GreetingServiceOuterClass.HelloResponse> responseObserver) {
        //посмотрим, что пришло от клиента
        System.out.println(request);

        //отдадим ответ клиенту
        //паттерн Builder соберет ответ сервера по цепочке, вызывая setGreeting нужное кол-во раз
        GreetingServiceOuterClass.HelloResponse response = GreetingServiceOuterClass
        .HelloResponse.newBuilder().setGreeting("Hallo from server!, " + request.getName())
        .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
        }
}
