package Model.Enums;


public enum EPropulsionType {
    
    REACTION ("jet engine"), 
    PROPELLER ("propeller engine"), 
    PISTON ("piston engine");
    
    private final String engineType;

    private EPropulsionType(String engineType) {
        this.engineType = engineType;
    }

    public String getEngineType() {
        return engineType;
    }
}