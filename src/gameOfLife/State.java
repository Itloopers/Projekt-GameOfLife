package gameOfLife;

public enum State{
    LIVE("x"),
    DEAD(" ");

    private String representation;

    State(String representation){
        this.representation = representation.concat(" ");
    }

    public String getRepresentation(){
        return representation;
    }
}
