package uet.oop.bomberman.level;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.*;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.item.BombItem;
import uet.oop.bomberman.entities.tile.item.FlameItem;
import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.exceptions.LoadLevelException;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileLevelLoader extends LevelLoader {

    private static char[][] _map; // khai báo biến để lưu trữ bản đồ game

    public FileLevelLoader(Board board, int level) throws LoadLevelException {
        super(board, level);
    }

    @Override
    public void loadLevel(int level) { // phương thức đọc dữ liệu từ file để tải bản đồ

        List<String> list = new ArrayList<>(); // khởi tạo một danh sách để lưu trữ dữ liệu đọc từ file
        try {
            FileReader fr = new FileReader("res\\levels\\Level" + level + ".txt");// đọc file theo đường dẫn đã cho để lấy dữ liệu bản đồ
            BufferedReader br = new BufferedReader(fr); // đọc dữ liệu từ file và lưu vào bộ đệm
            String line = br.readLine(); // đọc dòng đầu tiên từ file
            while (!line.equals("")) { // nếu dòng đọc được không rỗng thì thêm vào danh sách
                list.add(line);
                line = br.readLine(); // đọc dòng tiếp theo
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] arrays = list.get(0).trim().split(" "); // tách các phần tử của dòng đầu tiên trong danh sách để lấy thông tin về màn chơi
        _level = Integer.parseInt(arrays[0]); // gán giá trị cấp độ cho biến _level
        _height = Integer.parseInt(arrays[1]); // gán giá trị chiều cao của bản đồ cho biến _height
        _width = Integer.parseInt(arrays[2]); // gán giá trị chiều rộng của bản đồ cho biến _width
        _map = new char[_height][_width]; // khởi tạo bản đồ game với kích thước chiều cao và chiều rộng tương ứng
        for (int i = 0; i < _height; i++) { // duyệt qua từng hàng của bản đồ
            for (int j = 0; j < _width; j++) { // duyệt qua từng cột của bản đồ
                _map[i][j] = list.get(i + 1).charAt(j); // gán giá trị ký tự tương ứng từ danh sách vào bản đồ game
            }
        }
    }



    @Override
    public void createEntities() {
        // TODO: tạo các Entity của màn chơi
        // TODO: sau khi tạo xong, gọi _board.addEntity() để thêm Entity vào game

        // TODO: phần code mẫu ở dưới để hướng dẫn cách thêm các loại Entity vào game
        // TODO: hãy xóa nó khi hoàn thành chức năng load màn chơi từ tệp cấu hình
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                int pos = x + y * getWidth();
                char c = _map[y][x];
                switch (c) {
                    // Thêm grass
                    case ' ':
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                        break;
                    // Thêm Wall
                    case '#':
                        _board.addEntity(pos, new Wall(x, y, Sprite.wall));
                        break;
                    // Thêm Portal
                    case 'x':
                        _board.addEntity(pos, new LayeredEntity(x, y,
                                new Grass(x, y, Sprite.grass),
                                new Portal(x, y, _board, Sprite.portal),
                                new Brick(x, y, Sprite.brick)));
                        break;
                    // Thêm brick
                    case '*':
                        _board.addEntity(x + y * _width,
                                new LayeredEntity(x, y,
                                        new Grass(x, y, Sprite.grass),
                                        new Brick(x, y, Sprite.brick)
                                )
                        );
                        break;
                    // Thêm Bomber
                    case 'p':
                        _board.addCharacter(new Bomber(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        Screen.setOffset(0, 0);
                        _board.addEntity(x + y * _width, new Grass(x, y, Sprite.grass));
                        break;

                    // Thêm balloon
                    case '1':
                        _board.addCharacter(new Balloon(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        _board.addEntity(x + y * _width, new Grass(x, y, Sprite.grass));
                        break;
                    // Thêm oneal
                    case '2':
                        _board.addCharacter(new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                        break;
                    // Thêm doll
                    case '3':
                        _board.addCharacter(new Doll(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        _board.addEntity(x + y * _width, new Grass(x, y, Sprite.grass));
                        break;
                    case '4':
                        _board.addCharacter(new Minvo(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        _board.addEntity(x + y * _width, new Grass(x, y, Sprite.grass));
                        break;
                    case '5' :
                        _board.addCharacter(new Kondoria(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        _board.addEntity(x + y * _width, new Grass(x, y, Sprite.grass));
                        break;
                    // Thêm oneal
                    // Thêm BomItem            
                    case 'b':
                        LayeredEntity layer = new LayeredEntity(x, y,
                                new Grass(x, y, Sprite.grass),
                                new BombItem(x, y, Sprite.powerup_bombs),
                                new Brick(x, y, Sprite.brick));
                        _board.addEntity(pos, layer);
                        break;
                    // Thêm SpeedItem
                    case 's':
                        layer = new LayeredEntity(x, y,
                                new Grass(x, y, Sprite.grass),
                                new SpeedItem(x, y, Sprite.powerup_speed),
                                new Brick(x, y, Sprite.brick));
                        _board.addEntity(pos, layer);
                        break;
                    // Thêm FlameItem
                    case 'f':
                        layer = new LayeredEntity(x, y,
                                new Grass(x, y, Sprite.grass),
                                new FlameItem(x, y, Sprite.powerup_flames),
                                new Brick(x, y, Sprite.brick));
                        _board.addEntity(pos, layer);
                        break;

                    default:
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                        break;

                }
            }
        }
    }
}