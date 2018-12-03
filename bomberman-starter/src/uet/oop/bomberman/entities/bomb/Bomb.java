package uet.oop.bomberman.entities.bomb;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.AnimatedEntitiy;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.level.Coordinates;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Bomb extends AnimatedEntitiy {

	protected double _timeToExplode = 120; //2 seconds
	public int _timeAfter = 20;
	
	protected Board _board;
	protected Flame[] _flames;
	protected boolean _exploded = false;
	protected boolean _allowedToPassThru = true;
	
	public Bomb(int x, int y, Board board) {
		_x = x;
		_y = y;
		_board = board;
		_sprite = Sprite.bomb;
	}
	
	@Override
	public void update() {
		if(_timeToExplode > 0) 
			_timeToExplode--;
		else {
			if(!_exploded) 
				explode();
			else
				updateFlames();
			
			if(_timeAfter > 0) 
				_timeAfter--;
			else {
				soundExplosion();
				remove();

			}
		}
			
		animate();
	}
	
	@Override
	public void render(Screen screen) {
		if(_exploded) {
			_sprite =  Sprite.bomb_exploded2;
			renderFlames(screen);
		} else
			_sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, _animate, 60);
		
		int xt = (int)_x << 4;
		int yt = (int)_y << 4;
		
		screen.renderEntity(xt, yt , this);
	}
	
	public void renderFlames(Screen screen) {
		for (int i = 0; i < _flames.length; i++) {
			_flames[i].render(screen);
		}
	}
	
	public void updateFlames() {
		for (int i = 0; i < _flames.length; i++) {
			_flames[i].update();
		}
	}

    /**
     * Xử lý Bomb nổ
     */
	protected void explode() {
		_exploded = true;
		
		// TODO: xử lý khi Character đứng tại vị trí Bomb
		
		// TODO: tạo các Flame
		_allowedToPassThru = true;
		Character a = _board.getCharacterAt(_x,_y);
		if (a != null)
			a.kill();

		// TODO: tạo các Flame
		// flame gồm 4 hướng
		_flames = new Flame[4];
		for (int i = 0;i<_flames.length;i++){
			_flames[i] = new Flame((int)_x,(int)_y,i, Game.getBombRadius(),_board);

		}
	}
	
	public FlameSegment flameAt(int x, int y) {
		if(!_exploded) return null;
		
		for (int i = 0; i < _flames.length; i++) {
			if(_flames[i] == null) return null;
			FlameSegment e = _flames[i].flameSegmentAt(x, y);
			if(e != null) return e;
		}
		
		return null;
	}
	protected void soundExplosion() {
		// TODO: xử lí âm thanh
		Thread thread = new Thread(new Runnable() {
			@Override

			public void run() {
				ClassLoader classLoader = getClass().getClassLoader();
				File file = new File(classLoader.getResource("Sound/Explode.mp3" ).getFile());
				try {
					Player player = new Player(new FileInputStream(file));
					player.play();
					player.close();
				} catch (JavaLayerException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}

	@Override
	public boolean collide(Entity e) {
        // TODO: xử lý khi Bomber đi ra sau khi vừa đặt bom (_allowedToPassThru)
        // TODO: xử lý va chạm với Flame của Bomb khác
		// vừa đặt bomb,khi bomber bước ra khỏi ô có entity bomb -> không cho bước qua
		if (e instanceof Bomber){
			double diffX = e.getX() - Coordinates.tileToPixel(getX());
			double diffY = e.getY() - Coordinates.tileToPixel(getY());

			if (!(diffX >= -10 && diffX <16 && diffY >=1 && diffY <= 28)){ // nhân vật có đứng tại vị trí khác vị trí bomb ?
				_allowedToPassThru = false;
			}
			return _allowedToPassThru;
		}
		// flame tác động vào bomb
		if  (e instanceof Flame){
			_timeToExplode = 0;
			return true;
		}
		return false;

	}
}
