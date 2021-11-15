package Comand;

public enum ComandName {
    START("/start"),
    REG("/reg"),
    HELP("/help"),
    BUTTON("BUTTON"),
    NO("NO");

    private final String comandName;

    ComandName(String comandName) {
        this.comandName=comandName;
    }
    public String getComandName(){
        return comandName;
    }
}
