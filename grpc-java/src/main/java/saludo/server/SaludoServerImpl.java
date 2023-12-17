package saludo.server;

import com.proto.saludo.SaludoRequest;
import com.proto.saludo.SaludoResponse;
import com.proto.saludo.SaludoServiceGrpc;
import io.grpc.stub.StreamObserver;

public class SaludoServerImpl extends SaludoServiceGrpc.SaludoServiceImplBase{

    @Override
    public void saludo(SaludoRequest request, StreamObserver<SaludoResponse> responseObserver) {
        responseObserver.onNext(SaludoResponse.newBuilder()
                .setResult("HOLA "+request.getNombre())
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void saludoMuchasVeces(SaludoRequest request, StreamObserver<SaludoResponse> responseObserver) {
        for(int i=1; i<=10 ; i++){
            responseObserver.onNext(
                    SaludoResponse.newBuilder()
                            .setResult("Hola " + request.getNombre()+ " " + i + " vez")
                            .build()
            );
        }
        responseObserver.onCompleted();
    }
}
