package dominogamev4;

import java.util.ArrayList;

public interface ClutchRep{

    public void addDom(int loc, Dom dom);

    public ArrayList<Dom> getDoms();

    public Dom removeDom(int loc);
}
