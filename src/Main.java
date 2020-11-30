import server.Server;

public class Main {

    public static void main(String[] args) {
        Server server = Server.getInstance();
        if (args.length > 0) server.setHostname(args[0]);
        if (args.length > 1) server.setPort(Integer.parseInt(args[1]));
        server.start();
    }
}
