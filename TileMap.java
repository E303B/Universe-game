public class TileMap {
    public Tile[][] tiles;
    public TileMap(int width, int height){
        tiles=new Tile[width][height];
        for (int x=0;x<width;x++) {
            for (int y=0;y<height;y++) {
                tiles[x][y]=new Tile();
            }
        }
    }
}
