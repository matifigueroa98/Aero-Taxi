package Model.Enums;

public enum EAirplaneRate {
    
    BRONZE (3000),
    SILVER (4000),
    GOLD (6000);
    
    private final Integer rate;
    
    private EAirplaneRate(Integer rate) {
        this.rate = rate;
    } 

    public Integer getRate() {
        return rate;
    }
}
