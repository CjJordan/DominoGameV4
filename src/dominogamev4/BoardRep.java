package dominogamev4;

import java.util.LinkedList;
import java.util.ArrayList;

public class BoardRep implements ClutchRep {

    protected LinkedList<Dom> boardRep = new LinkedList<>();

    @Override
    public void addDom(int loc, Dom dom) {
        boardRep.add(loc, dom);
    }

    @Override
    public ArrayList<Dom> getDoms() {
        ArrayList<Dom> boneYardArray = new ArrayList<>(boardRep);
        return boneYardArray;

    }

    @Override
    public Dom removeDom(int loc) {
        return boardRep.remove(loc);
    }
    
}
