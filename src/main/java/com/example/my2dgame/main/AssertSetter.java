package com.example.my2dgame.main;

import com.example.my2dgame.object.ObjBoots;
import com.example.my2dgame.object.ObjChest;
import com.example.my2dgame.object.ObjDoor;
import com.example.my2dgame.object.ObjKey;

public class AssertSetter {
    GamePanel gp;
    public AssertSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        //#region Key1
        gp.obj[0] = new ObjKey();
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;
        //#endregion

        //#region Key 2
        gp.obj[1] = new ObjKey();
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;
        //#endregion

        //#region Key 3
        gp.obj[2] = new ObjKey();
        gp.obj[2].worldX = 38 * gp.tileSize;
        gp.obj[2].worldY = 8 * gp.tileSize;
        //#endregion

        //#region Door 1
        gp.obj[3] = new ObjDoor();
        gp.obj[3].worldX = 10 * gp.tileSize;
        gp.obj[3].worldY = 11 * gp.tileSize;
        //#endregion

        //#region Door 2
        gp.obj[4] = new ObjDoor();
        gp.obj[4].worldX = 8 * gp.tileSize;
        gp.obj[4].worldY = 28 * gp.tileSize;
        //#endregion

        //#region Door 3
        gp.obj[5] = new ObjDoor();
        gp.obj[5].worldX = 12 * gp.tileSize;
        gp.obj[5].worldY = 22 * gp.tileSize;
        //#endregion

        //#region Chest 1
        gp.obj[6] = new ObjChest();
        gp.obj[6].worldX = 10 * gp.tileSize;
        gp.obj[6].worldY = 7 * gp.tileSize;
        //#endregion

        //#region Boots 1
        gp.obj[7] = new ObjBoots();
        gp.obj[7].worldX = 37 * gp.tileSize;
        gp.obj[7].worldY = 42 * gp.tileSize;
        //#endregion
    }
}
