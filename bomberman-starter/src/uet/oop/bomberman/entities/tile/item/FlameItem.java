package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.Game.*;


public class FlameItem extends Item {

	public FlameItem(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

	@Override
	public boolean collide(Entity e) {
		// TODO: xử lý Bomber ăn Item
		if(e instanceof Bomber) {
			//((Bomber) e).addPowerup(this);
			addBombRadius(1);

			remove();
			return true;
		}
		return false;
	}

}
