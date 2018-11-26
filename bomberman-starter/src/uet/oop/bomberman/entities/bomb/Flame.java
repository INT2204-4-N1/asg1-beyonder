package uet.oop.bomberman.entities.bomb;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.graphics.Screen;

public class Flame extends Entity {

	protected Board _board;
	protected int _direction;
	private int _radius;
	protected int xOrigin, yOrigin;
	protected FlameSegment[] _flameSegments = new FlameSegment[0];

	/**
	 *
	 * @param x hoành độ bắt đầu của Flame
	 * @param y tung độ bắt đầu của Flame
	 * @param direction là hướng của Flame
	 * @param radius độ dài cực đại của Flame
	 */
	public Flame(int x, int y, int direction, int radius, Board board) {
		xOrigin = x;
		yOrigin = y;
		_x = x;
		_y = y;
		_direction = direction;
		_radius = radius;
		_board = board;
		createFlameSegments();
	}

	/**
	 * Tạo các FlameSegment, mỗi segment ứng một đơn vị độ dài
	 */
	private void createFlameSegments() {
		/**
		 * tính toán độ dài Flame, tương ứng với số lượng segment
		 */
		_flameSegments = new FlameSegment[calculatePermitedDistance()];

		/**
		 * biến last dùng để đánh dấu cho segment cuối cùng
		 */
		boolean last;

		// TODO: tạo các segment dưới đây
		int xF = (int)_x;
		int yF = (int)_y;
		for (int i = 0;i< _flameSegments.length;i++){
			switch (_direction){
				case 0 : yF--;	break;
				case 1 : xF++;	break;
				case 2 : yF++;	break;
				case 3 : xF--;	break;
			}
			if (i == _flameSegments.length -1)
				last = true;
			else
				last = false;

			_flameSegments[i] = new FlameSegment(xF,yF,_direction,last);
		}
	}

	/**
	 * Tính toán độ dài của Flame, nếu gặp vật cản là Brick/Wall, độ dài sẽ bị cắt ngắn
	 * @return
	 */
	private int calculatePermitedDistance() {
		// TODO: thực hiện tính toán độ dài của Flame
		int radius = 0;
		int xF = (int)_x;
		int yF = (int)_y;
		while (radius < _radius) {
			if (_direction == 0) yF--;    // flame đi xuống
			if (_direction == 1) xF++; // flame sang phải
			if (_direction == 2) yF++; // flame đi lên
			if (_direction == 3) xF--; // flame sang phải

			// kiểm tra xem có thực thể nào tại vị trí flame
			Entity a = _board.getEntity(xF, yF, null);
			// nếu có character nào tại vị trí flame -> cho phép flame đi xuyên qua
			if (a instanceof Character)
				radius++;
			// nếu gặp tường thì bị chặn lại, không cho đi qua + radius dừng lại tại vị trí tường
			if (!a.collide(this))
				break;
			radius ++;
		}
		return radius;

	}
	
	public FlameSegment flameSegmentAt(int x, int y) {
		for (int i = 0; i < _flameSegments.length; i++) {
			if(_flameSegments[i].getX() == x && _flameSegments[i].getY() == y)
				return _flameSegments[i];
		}
		return null;
	}

	@Override
	public void update() {}
	
	@Override
	public void render(Screen screen) {
		for (int i = 0; i < _flameSegments.length; i++) {
			_flameSegments[i].render(screen);
		}
	}

	@Override
	public boolean collide(Entity e) {
		// TODO: xử lý va chạm với Bomber, Enemy. Chú ý đối tượng này có vị trí chính là vị trí của Bomb đã nổ
		return true;
	}
}
