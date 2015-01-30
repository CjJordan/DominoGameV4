

package dominogamev4;

import java.util.ArrayList;


public class HandRep implements ClutchRep{

    protected ArrayList<Dom> hand = new ArrayList<>();
    
    public HandRep(){
    }
    @Override
    public void addDom(int loc, Dom dom) {
        hand.add(loc, dom);
    }

    @Override
    public ArrayList<Dom> getDoms() {
        return this.hand;
    }

    @Override
    public Dom removeDom(int loc) {
        return hand.remove(loc);
    }

}
