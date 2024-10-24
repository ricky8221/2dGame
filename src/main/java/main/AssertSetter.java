package main;

import entity.NpcOldMan;
import object.ObjBoots;
import object.ObjChest;
import object.ObjDoor;
import object.ObjKey;

public class AssertSetter {
    GamePanel gp;
    public AssertSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

    }

    public void setNpc() {
        gp.npc[0] = new NpcOldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 21;
    }
}
