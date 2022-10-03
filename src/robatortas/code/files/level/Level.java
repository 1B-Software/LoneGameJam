package robatortas.code.files.level;

import java.util.ArrayList;
import java.util.List;

import robatortas.code.files.InputManager;
import robatortas.code.files.entity.Blood;
import robatortas.code.files.entity.Entity;
import robatortas.code.files.entity.mob.Cat;
import robatortas.code.files.entity.mob.Mouse;
import robatortas.code.files.entity.mob.Player;
import robatortas.code.files.graphics.Screen;
import robatortas.code.files.graphics.Sprite;

public class Level {
	
	public int width, height;
	public int[] tiles;
	public List<Entity>[] entitiesInTiles;
	
	protected List<Entity> entities = new ArrayList<Entity>();
	
	public static Level level = new GameLevel("/level.png");
	
	public static InputManager input;
	
	public Player player;
	
	public Level(String path) {
		loadLevel(path);
	}
	
	public void tick() {
		//entity ticks
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			int xto = (int)e.x >> 3;
			int yto = (int)e.y >> 3;
			
			e.tick();
			
			//this removes entities
			if (e.removed) {
				entities.remove(i--);
				removeEntity(xto, yto, e);
			} else {
				int xt = (int)e.x >> 3;
				int yt = (int)e.y >> 3;
				
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
	
	Mouse mouse;
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 3;
		int y0 = yScroll >> 3;
		int x1 = (xScroll+screen.width-3) >> 3;
		int y1 = (yScroll+screen.height-11) >> 3;
		
		for(int y = y0; y <= y1; y++) {
			for(int x = x0; x <= x1; x++) {
				if (x < 0 || y < 0 || x >= width || y >= height) continue;
				if(getTile(x, y) == Tile.backStoneTile) getTile(x, y).render(x, y, screen);
			}
		}
		
		for(int i = 0; i < entities.size(); i++) if(entities.get(i) instanceof Blood) entities.get(i).render(screen);
		
		for(int y = y0; y <= y1; y++) {
			for(int x = x0; x <= x1; x++) {
				if (x < 0 || y < 0 || x >= width || y >= height) continue;
				if(getTile(x, y) != Tile.backStoneTile) getTile(x, y).render(x, y, screen);
			}
		}
		
		for(int i = 0; i < entities.size(); i++) {
			if(!(entities.get(i) instanceof Blood))entities.get(i).render(screen);
		}
	}
	
	public void add(Entity e) {
		e.init(this);
		e.removed = false;
		entities.add(e);
		insertEntity((int)e.x, (int)e.y, e);
	}
	
	public void remove(Entity e) {
		e.init(this);
		e.removed = true;
		entities.remove(e);
		insertEntity((int)e.x, (int)e.y , e);
	}

	public void insertEntity(int x, int y, Entity e) {
		if(x < 0 || x >= width || y < 0 || y >= height) return;
		entitiesInTiles[x+y*width].add(e);
	}
	
	public void insertTile(int x, int y, int color) {
		if(x < 0 || y < 0 || x >= width || y >= height) return;
		tiles[x+y*width] = color;
	}
	
	public void removeEntity(int x, int y, Entity e) {
		if(x < 0 || x >= width || y < 0 || y >= height) return;
		entitiesInTiles[x+y*width].remove(e);
	}
	
	public List<Entity> getEntityFromRadius(Entity e, int radius) {
		List<Entity> result = new ArrayList<Entity>();
		
		int ex = (int)e.x;
		int ey = (int)e.y;
		
		for(int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			int x = (int)entity.x;
			int y = (int)entity.y;
			
			// Distance x & y
			int dx = Math.abs(x-ex);
			int dy = Math.abs(y-ey);
			
			double dist = Math.sqrt(dx*dx) + Math.sqrt(dy*dy);
			if(dist <= radius) {
				result.add(entity);
			}
		}
		
		return result;
	}
	
	// Just creates a hitbox, a literal hitbox, and the input values just determine the size of it.
	public List<Entity> getEntity(int x0, int y0, int x1, int y1) {
		List<Entity> result = new ArrayList<Entity>();
		
		int xt0 = (x0 >> 3) - 1;
		int yt0 = (y0 >> 3) - 1;
		int xt1 = (x1 >> 3) + 1;
		int yt1 = (y1 >> 3) + 1;
		
		for(int y = yt0; y <= yt1; y++) {
			for(int x = xt0; x <= xt1; x++) {
				if(x < 0 || x >= width || y < 0 || y >= height) continue;
				List<Entity> entities = entitiesInTiles[x + y * this.width];
				for(int i = 0; i < entities.size(); i++) {
					Entity e = entities.get(i);
					if(e.intersects(x0, y0, x1, y1)) result.add(e);
				}
			}
		}
		
		return result;
	}
	
	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height) return  Tile.voidTile;
		if(tiles[x + y * width] == Sprite.col_stone) return Tile.stoneTile;
		if(tiles[x + y * width] == Sprite.col_wood) return Tile.woodTile;
		if(tiles[x + y * width] == Sprite.col_spike) return Tile.spikeTile;
		if(tiles[x + y * width] == Sprite.col_spikeBlood) return Tile.spikeBloodTile;
		if(tiles[x + y * width] == Sprite.col_backStone || tiles[x + y * width] == 0xffff0000) return Tile.backStoneTile;
		return Tile.voidTile;
	}
	
	public void getEntityWithLevel(int x, int y) {
//		if(x < 0 || y < 0 || x >= width || y >= height) return new Mouse(x,y);
		if(tiles[x + y * width] == 0xffff0000) new Mouse(x,y);
//		return new Entity();
	}
}