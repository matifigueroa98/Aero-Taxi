package Model.Enums;

public enum ECity {
    
    BSAS ("Buenos Aires"),
    CBA ("Córdoba"),
    STGO ("Santiago de Chile"),
    MONT ("Montevideo");
    
    private final String city;

    private ECity(String city) {
        this.city = city;
    }  
    
    public String getCity() {
        return city;
    }
}
