package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import java.util.List;

// Padrão singleton (sem Controller)
public class MinerShip extends Sprite {
    private final MinerShipInputProcessor minerShipInputProcessor;
    private boolean isFacingRight = true;
    private Rectangle hitbox;
    private TiledMapTileLayer collisionLayer;
    private int[] oreCount = new int[4];

    public MinerShip(float positionX, float positionY, TiledMapTileLayer collisionLayer) {
        super((Texture) WallWorld.assetManager.get("ship.png"));
        this.minerShipInputProcessor = new MinerShipInputProcessor();
        this.setSize(72, 48);
        this.setPosition(positionX, positionY);
        createHitbox();
        this.collisionLayer= collisionLayer;
    }

    private void createHitbox() {
        hitbox = new Rectangle(getX() + 5, getY() + 5,  getWidth() - 5, getHeight() - 5);
    }

    private void updateHitbox() {
        hitbox.setPosition(getX(), getY());
    }

    public void setMinerShipX(float minerShipX) {
        this.setX(minerShipX);
        updateHitbox();
    }

    public void setMinerShipY(float minerShipY) {
        this.setY(minerShipY);
        updateHitbox();
    }

    public int[] getOreCount() {
        return oreCount;
    }

    public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
        this.collisionLayer = collisionLayer;
    }

    public boolean isFacingRight() {
        return isFacingRight;
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    public void update(SpriteBatch batch, List<Block> blocks) {
        float nextX = getX();
        float nextY = getY();
        float nextWidth = 0;
        float nextHeight = 0;
        float sampleWidth = 0;
        float sampleHeight = 0;

        if (minerShipInputProcessor.getKeyUpPressed() ) {
            nextY += 4;
            nextHeight = getHeight();
            sampleWidth = getWidth()-5;
        }
        if (minerShipInputProcessor.getKeyDownPressed() && getY() > 36) {
            nextY -= 4;
            sampleWidth = getWidth()-5;
        }
        if (minerShipInputProcessor.getKeyRightPressed() ) {
            nextX += 4;
            isFacingRight = true;
            nextWidth = getWidth();
            sampleHeight = getHeight();
        }
        if (minerShipInputProcessor.getKeyLeftPressed() && getX() > 24) {
            nextX -= 4;
            isFacingRight = false;
            sampleHeight = getHeight();
        }

        Rectangle nextHitbox = new Rectangle(nextX + 5, nextY + 5, getWidth() - 5, getHeight() - 5);
        boolean collision = false;

        if(
                ((collisionLayer.getCell((int)(nextX+nextWidth)/collisionLayer.getTileWidth(),
                        (int)(nextY+nextHeight)/collisionLayer.getTileHeight()) != null)
                && (collisionLayer.getCell((int)(nextX+nextWidth)/collisionLayer.getTileWidth(),
                        (int)(nextY+nextHeight)/collisionLayer.getTileHeight()).getTile().getProperties().containsKey("colision")))
                || ((collisionLayer.getCell((int)(nextX+nextWidth+sampleWidth)/collisionLayer.getTileWidth(),
                        (int)(nextY+nextHeight+sampleHeight)/collisionLayer.getTileHeight()) != null)
                && (collisionLayer.getCell((int)(nextX+nextWidth+sampleWidth)/collisionLayer.getTileWidth(),
                        (int)(nextY+nextHeight+sampleHeight)/collisionLayer.getTileHeight()).getTile().getProperties().containsKey("colision")))
        ){
            collision = true;
        }

        for (Block block : blocks) {
            if (!block.isInOreState() && nextHitbox.overlaps(block.getHitbox())) {
                collision = true;
                break;
            }

            if(block.isInOreState() && nextHitbox.overlaps(block.getHitbox())){
                if(block.getTipo().equals("ouro")){
                    this.oreCount[0]++;
                    block.deactivateHitbox();
                }else if(block.getTipo().equals("minerio")){
                    this.oreCount[1]++;
                    block.deactivateHitbox();
                }else if(block.getTipo().equals("diamante")){
                    this.oreCount[2]++;
                    block.deactivateHitbox();
                }else{
                    this.oreCount[3]++;
                    block.deactivateHitbox();
                }

            }
        }

        if(nextX < 72*3){
            collision=true;
        }
        if(nextX < 72*5 ){
            if(nextY > 72*8-70 || nextY < 72*5+20){
                collision=true;
            }
        }

        if (!collision) {
            setPosition(nextX, nextY);
            updateHitbox();
        }

        if (isFacingRight) {
            batch.draw(WallWorld.assetManager.get("ship.png", Texture.class), getX(), getY(), getWidth(), getHeight());
        } else {
            batch.draw(WallWorld.assetManager.get("ship.png", Texture.class), getX() + getWidth(), getY(), -getWidth(), getHeight());
        }

    }

    public MinerShipInputProcessor getMinerShipInputProcessor() {
        return minerShipInputProcessor;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}
