package com.example.my2dgame.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ObjKey extends SuperObject{
    public ObjKey() {
        name = "Key";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
