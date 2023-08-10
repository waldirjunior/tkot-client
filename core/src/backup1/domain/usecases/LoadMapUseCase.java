package backup1.domain.usecases;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import backup1.shared.Singleton;

public class LoadMapUseCase {

    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tiledMapRenderer;

    public int mapWidth;
    public int mapHeight;
    public int tileWidth;
    public int tileHeight;

    public LoadMapUseCase execute(Stage stage) {
        // Carrega o mapa
        tiledMap = new TmxMapLoader().load("tkot-map/tmx.tmx");

        Array<TiledMapTileLayer> tileLayers = new Array<>();
        Array<MapGroupLayer> groupLayers = new Array<>();
        Array<MapLayer> objectLayers = new Array<>();

        separateLayers(tiledMap.getLayers(), tileLayers, groupLayers, objectLayers);

        Array<MapObjects> listObjects = new Array<>();
   /*
        // Cria o renderizador do mapa
        for (MapLayer objectLayer: objectLayers) {
            MapObjects objects = objectLayer.getObjects();
            listObjects.add(objects);
        }

        for (MapObjects objects: listObjects) {
            for (MapObject object : objects) {
                if (object instanceof TextureMapObject) {
                    TextureMapObject textureObj = (TextureMapObject) object;
                    GameObject gameObject = new GameObject(textureObj.getTextureRegion(), textureObj.getX(), textureObj.getY());
                    stage.addActor(gameObject);
                }
            }
        }

        System.out.println("tileLayers: " + tileLayers.size);


        TiledMapTileLayer tileLayer = (TiledMapTileLayer) tiledMap.getLayers().get(5);

        List<TiledMapTileSet> listTileSet = new ArrayList<TiledMapTileSet>();

        // Iterar sobre os tiles na camada
        for (int y = 0; y < tileLayer.getHeight(); y++) {
            for (int x = 0; x < tileLayer.getWidth(); x++) {
                TiledMapTileLayer.Cell cell = tileLayer.getCell(x, y);
                if (cell != null) {
                    TiledMapTile tile = cell.getTile();
                    if (tile != null) {
                        // Obter o TileSet para este Tile
                        TiledMapTileSet tileSet = tiledMap.getTileSets().getTileSet(tile.getId());
                        listTileSet.add(tileSet);
                        // Agora você pode trabalhar com o tileSet
                    }
                }
            }
        }

         */

        // Cria o renderer do mapa
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        setCenterMap();

        return this;
    }

    public void separateLayers(MapLayers layers, Array<TiledMapTileLayer> tileLayers, Array<MapGroupLayer> groupLayers, Array<MapLayer> objectLayers) {
        for (MapLayer layer : layers) {
            if (layer instanceof TiledMapTileLayer) {
                tileLayers.add((TiledMapTileLayer) layer);
            } else if (layer instanceof MapGroupLayer) {
                groupLayers.add((MapGroupLayer) layer);
                // Chama a função recursivamente para explorar subgrupos
                separateLayers(((MapGroupLayer) layer).getLayers(), tileLayers, groupLayers, objectLayers);
            } else {
                // Adiciona a layer genérica, que pode incluir objetos TMX
                objectLayers.add(layer);
            }
        }
    }

    public void renderMap(OrthographicCamera camera) {
        tiledMapRenderer.setView(camera);  // Substitua 'yourCamera' pela sua câmera
        tiledMapRenderer.render();
    }

    public void dispose() {
        // Limpa os recursos
        tiledMap.dispose();
        tiledMapRenderer.dispose();
    }

    private void setCenterMap() {

        TiledMapTileLayer firstTileLayer = null;

        for (MapLayer layer : tiledMap.getLayers()) {
            if (layer instanceof TiledMapTileLayer) {
                firstTileLayer = (TiledMapTileLayer) layer;
                break;
            }
        }

        if (firstTileLayer != null) {
            mapWidth = firstTileLayer.getWidth();
            mapHeight = firstTileLayer.getHeight();
            tileWidth = (int) firstTileLayer.getTileWidth();
            tileHeight = (int) firstTileLayer.getTileHeight();
            // ...
        }

        Singleton.getInstance().getSharedObject().mapWidth = mapWidth;
        Singleton.getInstance().getSharedObject().mapHeight = mapHeight;
        Singleton.getInstance().getSharedObject().tileWidth = tileWidth;
        Singleton.getInstance().getSharedObject().tileHeight = tileHeight;
    }

}
