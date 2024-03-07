package src;
public class WorldState {
    private String roomMonkeyIn;
    private String roomBoxIn;
    private String roomBananasIn;
    private String monkeyHeight;
    private boolean monkeyHasBananas;

    public static final String ROOM_A = "A";
    public static final String ROOM_B = "B";
    public static final String ROOM_C = "C";

    public String getRoomMonkeyIn(){
        return this.roomMonkeyIn;
    }

    public boolean isMonkeyAt(String room) {
        return this.roomMonkeyIn.equalsIgnoreCase(room);
    }

    public void setInitialState(String monkey, String box, String banana){
        this.roomMonkeyIn = monkey;
        this.roomBoxIn = box;
        this.roomBananasIn = banana;
    }

}
