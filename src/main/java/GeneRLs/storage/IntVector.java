package storage;

public class IntVector {
    public long x,y;
    public IntVector(){
        this.x = 0;
        this.y = 0;
    }

    public IntVector(long x, long y){
        this.x = x;
        this.y = y;
    }

    public IntVector(storage.IntVector intVector) {
        this.x = intVector.x;
        this.y = intVector.y;
    }


    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    @Override
    public String toString(){
        return "[" + this.x + " " + this.y + "]";
    }

    public IntVector add(IntVector vector){
        this.x += vector.x;
        this.y += vector.y;
        return this;
    }
}
