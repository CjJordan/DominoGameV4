package dominogamev4;

import java.util.ArrayList;

public class Hand extends Clutch {

    protected ClutchRep rep;

    public Hand() {
        //call super constructor??
        rep = new HandRep();
    }

    @Override
    public void addDom(int loc, Dom dom) {
        rep.addDom(loc, dom);
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
}
