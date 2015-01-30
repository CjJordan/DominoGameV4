package dominogamev4;

import java.util.ArrayList;
import java.util.Collections;

public class BoneYardRep implements ClutchRep {

    protected ArrayList<Dom> boneYardRep = new ArrayList<>();

    public BoneYardRep() {
        int pip1 = 6;
        int pip2 = 0;
        //create dominoes 0,6 through 6,6
        for (int i = 0; i <= pip1; i++) {
            for (int j = 6; j >= pip2; j--) {
                Dom domino = new Dom(i, j);
                boneYardRep.add(domino);
            }
            pip2++;
        }
        
        //Shuffle the boneyard
        Collections.shuffle(boneYardRep);
    }

    @Override
    public void addDom(int loc, Dom dom) {
        boneYardRep.add(loc, dom);
    }

    @Override
    public ArrayList<Dom> getDoms() {
        return this.boneYardRep;
    }

    @Override
    public Dom removeDom(int loc) {
        return boneYardRep.remove(loc);
    }
}
