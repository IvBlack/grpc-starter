package org.example;


import com.generated.grpc.GreetingServiceGrpc;
import com.generated.grpc.GreetingServiceOuterClass;
import io.grpc.stub.StreamObserver;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {
@Override
public void greeting(GreetingServiceOuterClass.HelloRequest request,
                     StreamObserver<GreetingServiceOuterClass.HelloResponse> responseObserver) {

        /*
        В цикле на каждой i усыпляется на время поток,
        формируется стрим из респонсов из 10 тыс.элементов
        */

        for(int i=0; i < 10000; i++) {
                try {
                        Thread.sleep(100);
                } catch (InterruptedException ex) {
                        ex.printStackTrace();
                }

                GreetingServiceOuterClass.HelloResponse response = GreetingServiceOuterClass
                        .HelloResponse.newBuilder().setGreeting("Hallo from server!, " + request.getName())
                        .build();

                responseObserver.onNext(response);
        }

        responseObserver.onCompleted();
        }
}
