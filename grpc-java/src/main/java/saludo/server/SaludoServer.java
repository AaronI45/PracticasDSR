package saludo.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class SaludoServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 50051;
        Server server = ServerBuilder
                .forPort(port)
                .addService(new SaludoServerImpl())
                .build();

        server.start();

        System.out.println("Servidor iniciando...");
        System.out.println("Escuchando en puerto: "+port);

        Runtime.getRuntime().addShutdownHook(new Thread(() ->{
            System.out.println("Recibiendo solicitud de apagado");
            server.shutdown();
            System.out.println("Servidor detenido");
        }));

        server.awaitTermination();
    }
}
