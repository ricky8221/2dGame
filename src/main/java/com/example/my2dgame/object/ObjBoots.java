package com.example.my2dgame.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ObjBoots extends SuperObject{
    public ObjBoots() {
        name = "Boots";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/boots.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
