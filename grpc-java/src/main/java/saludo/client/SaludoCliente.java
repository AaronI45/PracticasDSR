package saludo.client;

import com.proto.saludo.SaludoRequest;
import com.proto.saludo.SaludoResponse;
import com.proto.saludo.SaludoServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class SaludoCliente {

    public static void main(String[] args) {
        ManagedChannel ch = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        saludarUnario(ch);
        recibirSaludoStream(ch);

        System.out.println("Apagando...");
        ch.shutdown();
    }

    public static void saludarUnario(ManagedChannel ch){
        SaludoServiceGrpc.SaludoServiceBlockingStub stub = SaludoServiceGrpc.newBlockingStub(ch);
        SaludoResponse response = stub.saludo(SaludoRequest.newBuilder().setNombre("Aaron").build());

        System.out.println("SALUDO: " + response.getResult());
    }

    public static void recibirSaludoStream(ManagedChannel ch){
        SaludoServiceGrpc.SaludoServiceBlockingStub stub = SaludoServiceGrpc.newBlockingStub(ch);
        stub.saludoMuchasVeces(SaludoRequest.newBuilder().setNombre("Aaron").build())
                .forEachRemaining(response ->{
                    System.out.println(response.getResult());
                });
    }
}
