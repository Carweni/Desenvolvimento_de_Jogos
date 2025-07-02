package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.Random;

public class GameScreen2 implements Screen {
    final float WORLD_WIDTH = 1928;
    final float WORLD_HEIGHT = 1080;
    private final int screenHeight, screenWidth;

    private final OrthographicCamera cam;
    private final Viewport viewport;

    private SpriteBatch batch;

    private MinerShip minerShip;
    private Laser laser;
    private float spiderX, spiderY;
    private boolean shipReleased = false;

    private Texture spiderSheet;
    private Animation<TextureRegion> spiderAnimation;
    private float animationTime;

    private TextureRegion blockRegion, bgBlockRegion;
    private final ArrayList<Block> Blocks = new ArrayList<>();

    private OrthogonalTiledMapRenderer tmr;
    private TiledMap map;

    private BitmapFont font;
    private NinePatch patch;
    private float countPositionX;

    private enum GameState {
        PLAYING, SPIDER_DESCENDING, TRANSITIONING
    }

    private GameState currentState = GameState.PLAYING;

    private ParticleEffect blockBreakEffect;

    public GameScreen2(MinerShip m) {
        // screen atributes
        this.screenHeight = com.badlogic.gdx.Gdx.graphics.getHeight();
        this.screenWidth = com.badlogic.gdx.Gdx.graphics.getWidth();
        float aspectRatio = (float) screenWidth / (float) screenHeight;

        // camera and viewport
        cam = new OrthographicCamera();
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();
        viewport = new FitViewport(WORLD_HEIGHT*aspectRatio, WORLD_HEIGHT, cam);
        viewport.apply();

        // tiled map
        map = new TmxMapLoader().load("tiledMap/mapa2.tmx");
        tmr = new OrthogonalTiledMapRenderer(map);

        this.batch = new SpriteBatch();

        AudioManager.getInstance().playMusic("Music/CaveBackgroundMusic.mp3");

        spiderSheet = new Texture(Gdx.files.internal("Spider_Moving_Fast_Down.png"));

        if(m!= null){
            minerShip = m;

            minerShip.setCollisionLayer((TiledMapTileLayer) map.getLayers().get("camada1"));
        }else{
            minerShip = new MinerShip(0, 0, (TiledMapTileLayer) map.getLayers().get("camada1"));
        }

        laser = new Laser(minerShip.getX(), minerShip.getY() + minerShip.getHeight());

        spiderX = 85;
        spiderY = screenHeight;

        blockBreakEffect = new ParticleEffect();
        blockBreakEffect.load(Gdx.files.internal("particleAtlas/Particle Park Glass.p"),
                Gdx.files.internal("particleAtlas"));

        TextureRegion[][] tmpFrames = TextureRegion.split(spiderSheet, spiderSheet.getWidth() / 5, spiderSheet.getHeight() / 3);
        TextureRegion[] spiderFrames = new TextureRegion[12];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                if (i < 2){
                    spiderFrames[index++] = tmpFrames[i][j];
                }
                else{
                    if (j < 2){
                        spiderFrames[index++] = tmpFrames[i][j];
                    }
                }

            }
        }
        spiderAnimation = new Animation<>(0.2f, spiderFrames);
        animationTime = 0;

        // Multiplexador:
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(minerShip.getMinerShipInputProcessor());
        multiplexer.addProcessor(laser.getLaserInputProcessor());
        Gdx.input.setInputProcessor(multiplexer);

        // Inicializando blocos
        int low = 10;
        int high = 100;
        Texture t;
        String tipo;
        float tempo;
        for(int i=0;i<24;i++) {
            for(int j=0;j<12;j++) {
                Random r = new Random();
                int result = r.nextInt(high-low) + low;

                if(r.nextFloat() < 0.8f) {
                    if(result<=20){
                        t = WallWorld.assetManager.get("diamondOre.png");
                        tipo = "diamante";
                        tempo=2;
                    }else if(result<=30){
                        t = WallWorld.assetManager.get("calcoOre.png");
                        tipo = "calcopirita";
                        tempo=3;
                    } else{
                        t =WallWorld.assetManager.get("stone.png");
                        tipo = "pedra";
                        tempo = 1;
                    }

                    if((j>1 && j<6) && (i==1)){
                        Block aux = new Block(504+(72*(i)), (j*72)+216, t, tipo, tempo, new ParticleEffect(blockBreakEffect));
                        Blocks.add(aux);
                    } else if((j<7) && (i==2)){
                        Block aux = new Block(504+(72*(i)), (j*72)+216, t, tipo, tempo, new ParticleEffect(blockBreakEffect));
                        Blocks.add(aux);
                    } else if((j<11) && (i==3)){
                        Block aux = new Block(504+(72*(i)), (j*72)+216, t, tipo, tempo, new ParticleEffect(blockBreakEffect));
                        Blocks.add(aux);
                    } else if(j>0 && j<10 && (i==4)){
                        Block aux = new Block(504+(72*(i)), (j*72)+216, t, tipo, tempo, new ParticleEffect(blockBreakEffect));
                        Blocks.add(aux);
                    } else if((j<8 && j>1) && (i>4 && i<7)){
                        Block aux = new Block(504+(72*(i)), (j*72)+216, t, tipo, tempo, new ParticleEffect(blockBreakEffect));
                        Blocks.add(aux);
                    } else if(j>3 && j<11 && (i>6 && i<12)){
                        Block aux = new Block(504+(72*(i)), (j*72)+216, t, tipo, tempo, new ParticleEffect(blockBreakEffect));
                        Blocks.add(aux);
                    } else if(j>4 && j<11 && (i>11 && i<15)){
                        Block aux = new Block(504+(72*(i)), (j*72)+216, t, tipo, tempo, new ParticleEffect(blockBreakEffect));
                        Blocks.add(aux);
                    } else if(j<10 && (i>14 && i<17)){
                        Block aux = new Block(504+(72*(i)), (j*72)+216, t, tipo, tempo, new ParticleEffect(blockBreakEffect));
                        Blocks.add(aux);
                    } else if(j>4 && j<9 && (i>16 && i<19)){
                        Block aux = new Block(504+(72*(i)), (j*72)+216, t, tipo, tempo, new ParticleEffect(blockBreakEffect));
                        Blocks.add(aux);
                    } else if(j<3 && (i>18 && i<21)){
                        Block aux = new Block(504+(72*(i)), (j*72)+216, t, tipo, tempo, new ParticleEffect(blockBreakEffect));
                        Blocks.add(aux);
                    } else if(j<5 && (i>20 && i<23)){
                        Block aux = new Block(504+(72*(i)), (j*72)+216, t, tipo, tempo, new ParticleEffect(blockBreakEffect));
                        Blocks.add(aux);
                    }


                    if((j==0||j==1) && (i==14)){
                        Block aux = new Block(504+(72*(i)), (j*72)+216, t, tipo, tempo, new ParticleEffect(blockBreakEffect));
                        Blocks.add(aux);
                    }
                    if((j==1) && (i==13)){
                        Block aux = new Block(504+(72*(i)), (j*72)+216, t, tipo, tempo, new ParticleEffect(blockBreakEffect));
                        Blocks.add(aux);
                    }
                    if((j==2 || j==3) && (i==7)){
                        Block aux = new Block(504+(72*(i)), (j*72)+216, t, tipo, tempo, new ParticleEffect(blockBreakEffect));
                        Blocks.add(aux);
                    }
                    if((j<3) && (i==17 || i==18)){
                        Block aux = new Block(504+(72*(i)), (j*72)+216, t, tipo, tempo, new ParticleEffect(blockBreakEffect));
                        Blocks.add(aux);
                    }
                }
            }
        }
        patch = new NinePatch((Texture)WallWorld.assetManager.get("menuBg.png"), 16, 16, 16, 16);
        font = new BitmapFont();

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        cam.update();
        batch.setProjectionMatrix(cam.combined);

        animationTime += Gdx.graphics.getDeltaTime();

        ScreenUtils.clear(1, 0, 0, 1);

        // Desenhar texturas
        batch.begin();

        tmr.setView(cam); //move o mapa de acordo com a câmera

        Texture backgroundTexture = WallWorld.assetManager.get("background2.jpg", Texture.class);
        float backgroundWidth = backgroundTexture.getWidth()-200;
        float backgroundHeight = viewport.getWorldHeight() + 200;

        float cameraLeft = cam.position.x - cam.viewportWidth / 2;

        int startX = (int) (cameraLeft / backgroundWidth);
        int endX = (int) ((cameraLeft + cam.viewportWidth) / backgroundWidth) + 1;

        for (int x = startX; x <= endX; x++) {
            batch.draw(backgroundTexture, x * backgroundWidth, 0, backgroundWidth, backgroundHeight);
        }
        batch.end();
        tmr.render(); //renderiza (desenha) o mapa
        batch.begin();
        float aspectRatio = (float) screenWidth / (float) screenHeight;

        for (Block aux : Blocks) {
            if (aux.isActive() && aux.getTipo().equals("bloco")) {  // Só desenha blocos ativos
                blockRegion = new TextureRegion(aux.getTexture(), 72 * 6, 0, 72, 72);
                batch.draw(blockRegion, aux.getX(), aux.getY(), 72, 72);
            } else if (aux.isActive()) {
                batch.draw(aux.getTexture(), aux.getX(), aux.getY(), 72, 72);
            }
            aux.update(delta, laser);  // Atualiza a colisão do bloco com o laser

            if (!aux.isActive() && !aux.isParticleFinished()) {
                aux.getParticleEffect().draw(batch, delta);
            }

            if(!aux.isActive() && aux.isInOreState()){
                if(aux.getTipo().equals("diamante")){
                    aux.makeOre(WallWorld.assetManager.get("diamond-small.png"));
                    batch.draw(aux.getTexture(), aux.getX()+(aux.getWidth()/2)-10, aux.getY()+(aux.getHeight()/2)-10, 20, 20);
                } else if(aux.getTipo().equals("calcopirita")){
                    aux.makeOre(WallWorld.assetManager.get("chalcopyrite-small.png"));
                    batch.draw(aux.getTexture(), aux.getX()+(aux.getWidth()/2)-10, aux.getY()+(aux.getHeight()/2)-10, 20, 20);
                }
            }
        }

        countPositionX = cam.position.x-(WORLD_HEIGHT*aspectRatio/2);
        patch.draw(batch, countPositionX + 20, WORLD_HEIGHT-200-10, 150, 200);
        font.draw(batch, "Recursos:", countPositionX + 50, WORLD_HEIGHT-patch.getTotalHeight()/2);
        font.getData().setScale(1.2f);
        batch.draw((Texture) WallWorld.assetManager.get("honey_small.png", Texture.class), countPositionX + 50, WORLD_HEIGHT-patch.getTotalHeight()-20, 25, 25);
        font.draw(batch, "- " + minerShip.getOreCount()[0], countPositionX + 50+30, WORLD_HEIGHT-75);
        batch.draw((Texture) WallWorld.assetManager.get("coil_small.png", Texture.class), countPositionX + 50, WORLD_HEIGHT-patch.getTotalHeight()-50, 25, 25);
        font.draw(batch, "- " + minerShip.getOreCount()[1], countPositionX + 50+30, WORLD_HEIGHT-105);
        batch.draw((Texture) WallWorld.assetManager.get("diamond-small.png", Texture.class), countPositionX + 50, WORLD_HEIGHT-patch.getTotalHeight()-80, 25, 25);
        font.draw(batch, "- " + minerShip.getOreCount()[2], countPositionX + 50+30, WORLD_HEIGHT-135);
        batch.draw((Texture) WallWorld.assetManager.get("chalcopyrite-small.png", Texture.class), countPositionX + 50, WORLD_HEIGHT-patch.getTotalHeight()-110, 25, 25);
        font.draw(batch, "- " + minerShip.getOreCount()[3], countPositionX + 50+30, WORLD_HEIGHT-165);

        if (currentState == GameState.PLAYING) {
            if (!shipReleased) {
                if (spiderY > WORLD_HEIGHT / 2.0 - 270) {
                    spiderY -= 3;
                } else {
                    batch.draw(WallWorld.assetManager.get("spiderOpen.png", Texture.class), spiderX - 20, spiderY, 400, 400);
                    shipReleased = true;
                }
            }

            if (!shipReleased) {
                TextureRegion currentFrame = spiderAnimation.getKeyFrame(animationTime, true);
                batch.draw(currentFrame, spiderX, spiderY, 400, 400);
                minerShip.setMinerShipX(spiderX + 180);
                minerShip.setMinerShipY(spiderY + 180);
            } else {
                batch.draw(WallWorld.assetManager.get("spiderOpen.png", Texture.class), spiderX - 20, spiderY, 400, 400);
                minerShip.update(batch, Blocks);
                if (minerShip.getX() > (WORLD_HEIGHT * aspectRatio) / 2.0) {
                    cam.position.set(minerShip.getX(), cam.viewportHeight / 2f, 0);
                }

                Rectangle spiderOpenHitbox = new Rectangle(spiderX - 20, spiderY, 350, 350);
                if (minerShip.getHitbox().overlaps(spiderOpenHitbox)) {
                    if (minerShip.getMinerShipInputProcessor().getNPressed()) {
                        currentState = GameState.SPIDER_DESCENDING;
                    }
                    patch.draw(batch, 45, 40, 300, 50);
                    font.draw(batch, "Pressione N para mudar de  mapa", 58, 72);
                    font.getData().setScale(1.2f);
                }
            }
        } else if (currentState == GameState.SPIDER_DESCENDING) {
            if (spiderY > -400) {
                spiderY -= 3;
            } else {
                currentState = GameState.TRANSITIONING;
            }

            TextureRegion currentFrame = spiderAnimation.getKeyFrame(animationTime, true);
            batch.draw(currentFrame, spiderX, spiderY, 400, 400);
        } else if (currentState == GameState.TRANSITIONING) {
            GameScreen newGameScreen = new GameScreen(minerShip);
            ((WallWorld) Gdx.app.getApplicationListener()).setScreen(newGameScreen);
        }


        if (laser.laserInputProcessor.isMouseClicked()) {
            if(minerShip.isFacingRight()) {
                laser.updatePosition(
                        minerShip.getX() + minerShip.getWidth() - laser.getWidth() / 2,
                        minerShip.getY() + minerShip.getHeight() / 2 - laser.getHeight() / 2
                );
                laser.update(Gdx.graphics.getDeltaTime(), true);
                laser.activate();
            }else{
                laser.updatePosition(
                        minerShip.getX() - laser.getWidth() / 2,
                        minerShip.getY() + minerShip.getHeight() / 2 - laser.getHeight() / 2);
                laser.update(Gdx.graphics.getDeltaTime(), false);
                laser.activate();
            }
        } else {
            laser.deactivate();
        }

        laser.draw(batch);

        batch.end();

        /*
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0, 1, 0, 1);

        shapeRenderer.rect(minerShip.getHitbox().x, minerShip.getHitbox().y,
                minerShip.getHitbox().width, minerShip.getHitbox().height);

        for (Block block : Blocks) {
            shapeRenderer.rect(block.getHitbox().x, block.getHitbox().y,
                    block.getHitbox().width, block.getHitbox().height);
        }

        for (WallBlock wallBlock : WallBlocks) {
            shapeRenderer.rect(wallBlock.getHitbox().x, wallBlock.getHitbox().y,
                    wallBlock.getHitbox().width, wallBlock.getHitbox().height);
        }

        shapeRenderer.rect(laser.getHitbox().x, laser.getHitbox().y,
                laser.getHitbox().width, laser.getHitbox().height);
        shapeRenderer.end();
         */
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        batch.dispose();
        spiderSheet.dispose();
        tmr.dispose();
        map.dispose();
    }
}