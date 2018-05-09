package gameOfLife;

class Cell{
    private State state;
    private int row, column;
    int pair = 0;
    int stateChanges = 0;

    public Cell(int y, int x){
        this.state = State.LIVE;
        this.row = y;
        this.column = x;
    }

    static Cell live(int row, int column){
        return new Cell(row,column);
    }

    public String getStateName() {
        return state.getRepresentation();
    }

    public void setState(State state){
        this.state = state;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void changeState(){
        switch(this.pair){
            case 0:
                if(!this.state.equals(State.DEAD)) addStateChange();
                this.state=State.DEAD;
                break;
            case 1:
                if(!this.state.equals(State.DEAD)) addStateChange();
                this.state=State.DEAD;
                break;
            case 2: // stay LIVE
                break;
            case 3:
                if(!this.state.equals(State.LIVE)) addStateChange();
                this.state=State.LIVE;
                break;
            case 4:
                if(!this.state.equals(State.DEAD)) addStateChange();
                this.state=State.DEAD;
                break;
            case 5:
                if(!this.state.equals(State.DEAD)) addStateChange();
                this.state=State.DEAD;
                break;
            case 6:
                if(!this.state.equals(State.DEAD)) addStateChange();
                this.state=State.DEAD;
                break;
            case 7:
                if(!this.state.equals(State.DEAD)) addStateChange();
                this.state=State.DEAD;
                break;
            case 8:
                if(!this.state.equals(State.DEAD)) addStateChange();
                this.state=State.DEAD;
                break;
        }
    }

    private void addStateChange(){
        stateChanges++;
    }

    public void getPair(Cell[][] cells){
        pair = 0;
        for(int i=this.row-1; i<=this.row+1; i++) {
            if(i<0) i+=1;
            else if(i>cells.length-1) continue;
            for (int j =this.column-1; j<=this.column+1; j++) {
                if(j<0) j+=1;
                else if(j>cells.length-1) continue;
                if ((cells[i][j].state).equals(State.LIVE)){
                    this.pair++;
                    if(cells[i][j] == this) this.pair-=1;
                }
            }
        }
    }
}
