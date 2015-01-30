package dominogamev4;

import java.util.ArrayList;

public class Board extends Clutch {

    protected ClutchRep rep;
    private int leftEnd;
    private int rightEnd;

    public Board() {
        //call super constructor??
        rep = new BoardRep();
        if (!rep.getDoms().isEmpty()) {
            leftEnd = rep.getDoms().get(0).getLeft();
            rightEnd = rep.getDoms().get(rep.getDoms().size() - 1).getRight();
        } else {
            leftEnd = -1;
            rightEnd = -1;
        }
    }

    @Override
    public void addDom(int loc, Dom dom) {
        if (loc == 0){
           rep.addDom(loc, dom); 
           Display.addDomLeft(dom);
        }else if(loc == 1){
            rep.addDom((rep.getDoms().size()), dom);
            Display.addDomRight(dom);
        }
        
    }

    @Override
    public ArrayList<Dom> getDoms() {
        return rep.getDoms();
    }

    @Override
    public Dom removeDom(int loc) {
        return rep.removeDom(loc);
    }

    @Override
    public ClutchRep getRep() {
        return this.rep;
    }

    @Override
    public void setRep(ClutchRep rep) {
        this.rep = rep;
    }

    public int getLeftEnd() {
        return  rep.getDoms().get(0).getLeft();
    }

    public int getRightEnd() {
        return rep.getDoms().get(rep.getDoms().size() - 1).getRight();
    }
    
    public void setLeftEnd(int left) {
        leftEnd = left;
    }

    public void setRightEnd(int right) {
        rightEnd = right;
    }

}
