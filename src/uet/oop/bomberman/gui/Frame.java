package uet.oop.bomberman.gui;

import uet.oop.bomberman.Game;
import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
	public GamePanel _gamepane;
	private JPanel _containerpane;
	private InfoPanel _infopanel;
	
	private Game _game;

	public Frame() {

		_containerpane = new JPanel(new BorderLayout());

		_gamepane = new GamePanel(this);

		_infopanel = new InfoPanel(_gamepane.getGame());

		_containerpane.add(_infopanel, BorderLayout.PAGE_START);
		_containerpane.add(_gamepane, BorderLayout.PAGE_END);
		_game = _gamepane.getGame();
		add(_containerpane);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		_game.start();
	}
	
	public void setTime(int time)// Phương thức setTime dùng để cập nhật thời gian của trò chơi
	{
		_infopanel.setTime(time);// Gọi phương thức setTime của _infopanel với tham số time
	}
	
	public void setPoints(int points)// Phương thức setPoints dùng để cập nhật điểm số của trò chơi
	{
		_infopanel.setPoints(points);// Gọi phương thức setPoints của _infopanel với tham số points
	}
}
