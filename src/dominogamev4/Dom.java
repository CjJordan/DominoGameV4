package dominogamev4;

public class Dom {

    private int left;
    private int right;

    public Dom(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public void flip() {
        int temp = this.left;
        this.left = this.right;
        this.right = temp;
    }

    public int getLeft() {
        return this.left;
    }

    public int getRight() {
        return this.right;
    }

    public boolean isBlank() {
        return (left == 0 && right == 0);
    }

    public boolean isDouble() {
        return (left == right);
    }

    public int points() {
        return this.left + this.right;
    }
}
