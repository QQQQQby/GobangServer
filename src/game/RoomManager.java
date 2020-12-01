package game;

public class RoomManager {

    private Game[] games;

    private static RoomManager manager;

    public synchronized static RoomManager getInstance() {
        if (manager == null)
            manager = new RoomManager();
        return manager;
    }

    private RoomManager() {
        games = new Game[5];
    }


}
