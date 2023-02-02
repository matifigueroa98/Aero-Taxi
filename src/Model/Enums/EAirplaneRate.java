package Model.Enums;


public enum EAirplaneRate {
    
    BRONZE (3000),
    SILVER (4000),
    GOLD (6000),
    AIRPLANE (3500); // fixed rate for each passenger 
    
    private final Integer rate;
    
    private EAirplaneRate(Integer rate) {
        this.rate = rate;
    }
}
