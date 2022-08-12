package robatortas.code.files.level;

import java.util.ArrayList;
import java.util.List;

import robatortas.code.files.InputManager;
import robatortas.code.files.entity.Entity;
import robatortas.code.files.graphics.Screen;
import robatortas.code.files.graphics.Sprite;

public class Level {
	
	public int width, height;
	public int[] tiles;
	public List<Entity>[] entitiesInTiles;
	public static Level level = new GameLevel("/level.png");
	
	protected List<Entity> entities = new ArrayList<Entity>();
	
	public static InputManager input;
	
	@SuppressWarnings("unchecked")
	public Level(String path) {
		loadLevel(path);

		entitiesInTiles = new ArrayList[width*height];
		for(int i = 0; i < width*height; i++) {
			entitiesInTiles[i] = new ArrayList<Entity>();
		}
	}
	
	public void tick() {
		//entity ticks
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			int xto = e.x >> 4;
			int yto = e.y >> 4;
			
			e.tick();
			
			//this removes entities
			if (e.removed) {
				entities.remove(i--);
				removeEntity(xto, yto, e);
			} else {
				int xt = e.x >> 4;
				int yt = e.y >> 4;
				
				//if the x != x or y != or x = x or y = y
				if (xto != xt || yto != yt || xto == xt || yto == yt) {
					removeEntity(xto, yto, e);
					insertEntity(xt, yt, e);
				}
			}
		}
	}
	
	protected void loadLevel(String path) {
		
	}
	
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 3;
		int y0 = yScroll >> 3;
		int x1 = (xScroll - 8) >> 3;
		int y1 = (yScroll - 8) >> 3;
		
		for(int y = y0; y < y1; y++) {
			for(int x = x0; x < x1; x++) {
				if(x < 0 || x >= width || y < 0 || y >= height) continue;
				this.getTile(x, y).render(x, y, screen);
			}
		}
		
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
	}
	
	public void add(Entity e) {
		e.init(this);
		e.removed = false;
		insertEntity(e.x << 3, e.y << 3, e);
	}
	
	public void remove(Entity e) {
		e.init(this);
		e.removed = true;
		insertEntity(e.x << 3, e.y << 3, e);
	}

	public void insertEntity(int x, int y, Entity e) {
		if(x < 0 || x > width || y < 0 || y > height) return;
		entities.add(e);
	}
	
	public void removeEntity(int x, int y, Entity e) {
		if(x < 0 || x > width || y < 0 || y > height) return;
		entities.remove(e);
	}
	
	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height) return  Tile.voidTile;
		if(tiles[x + y * width] == Sprite.col_stone) return Tile.stoneTile;
		return Tile.voidTile;
	}
}