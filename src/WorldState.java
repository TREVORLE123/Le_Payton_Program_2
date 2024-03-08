public class WorldState {
    private String roomMonkeyIn;
    private String roomBoxIn;
    private String roomBananasIn;
    private String monkeyHeight;
    private boolean monkeyHasBananas;

    public static final String ROOM_A = "A";
    public static final String ROOM_B = "B";
    public static final String ROOM_C = "C";
    public static final String HEIGHT_LOW = "low"; // changed to lowercase for consistency
    public static final String HEIGHT_HIGH = "HIGH"; // I assume it should be "HIGH"


    public WorldState(String roomMonkeyIn, String roomBoxIn, String roomBananasIn, String monkeyHeight, boolean monkeyHasBananas) {
        this.roomMonkeyIn = roomMonkeyIn;
        this.roomBoxIn = roomBoxIn;
        this.roomBananasIn = roomBananasIn;
        this.monkeyHeight = monkeyHeight;
        this.monkeyHasBananas = monkeyHasBananas;
    }
    
    public String getRoomMonkeyIn() {
        return this.roomMonkeyIn;
    }

    public boolean isMonkeyAt(String room) {
        return this.roomMonkeyIn.equalsIgnoreCase(room);
    }

    public String getRoomBananasIn() {
        return this.roomBananasIn;
    }

    public boolean isBananaAt(String room) {
        return this.roomBananasIn.equalsIgnoreCase(room);
    }

    public String getRoomBoxIn() {
        return this.roomBoxIn;
    }

    public boolean isBoxAt(String room) {
        return this.roomBoxIn.equalsIgnoreCase(room);
    }

    public boolean isMonkeyHeight(String height) {
        return this.monkeyHeight.equalsIgnoreCase(height);
    }

    public void setMonkeyHeight(String newHeight) {
        this.monkeyHeight = newHeight;
    }

    public String getMonkeyHeight(){
        return this.monkeyHeight;
    }

    public void setRoomMonkeyIn(String to){
        this.roomMonkeyIn = to;
    }

    public void setRoomBoxIn(String to){
        this.roomBoxIn = to;
    }

    public void setMonkeyHasBananas(boolean achieve){
        this.monkeyHasBananas = achieve;
    }

    public boolean isMonkeyHasBananas(){
        return this.monkeyHasBananas;
    }

    public void setState(String monkey, String box, String banana) {
        this.roomMonkeyIn = monkey;
        this.roomBoxIn = box;
        this.roomBananasIn = banana;
        this.monkeyHasBananas = false;
        this.monkeyHeight = HEIGHT_LOW; 
    }
}
