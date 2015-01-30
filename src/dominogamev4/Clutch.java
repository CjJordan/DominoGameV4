
package dominogamev4;

import java.util.ArrayList;


public abstract class Clutch{
    protected ClutchRep  rep;

    public void addDom(int loc, Dom dom) {
    }

    public ArrayList<Dom> getDoms() {
        return new ArrayList<>();
    }

    public Dom removeDom(int loc) {
        return new Dom(1,1);
    }

    public ClutchRep getRep() {
        return this.rep;
    }

    public void setRep(ClutchRep rep) {
        this.rep = rep;
    }
    
    
}
