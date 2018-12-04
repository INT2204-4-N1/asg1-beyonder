package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.Game.addBombRadius;
import static uet.oop.bomberman.Game.addBomberSpeed;

public class SpeedItem extends Item {

	public SpeedItem(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

	@Override
	public boolean collide(Entity e) {
		if(e instanceof Bomber) {
			//((Bomber) e).addPowerup(this);
			addBomberSpeed(0.2);

			remove();
			//return true;
		}
		return false;
		// TODO: xử lý Bomber ăn Item

	}
}
