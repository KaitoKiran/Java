import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{
	
	private Display display;
	private int width, height;
	public String title;
	
	private Thread thread;
	private boolean running = false;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//States ----------------------------
	private State gameState;
	private State menuState;
	private State settingsState;
	
	//input
	private KeyManager keyManager;
	
	//camera
	private GameCamera gameCamera;
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		
	}
	
	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();
		
		gameCamera = new GameCamera(0, 0);
		
		gameState = new GameState(this);
		menuState = new MenuState(this);
		settingsState = new SettingsState(this);
		State.setState(gameState);;
	}
	
	int x = 0;
	
	private void tick() {		
		
		keyManager.tick();
		
		if(State.getState() != null) {
			State.getState().tick();
		}
		
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//clear screen
		g.clearRect(0, 0, width, height);
		//Draw Here
		
		if(State.getState() != null) {
			State.getState().render(g);
		}
			
		//End Drawing
		bs.show();
		g.dispose();
	}
	
	public void run() {
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running == true) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1) {
			tick();
			render();
			ticks++;
			delta--;
			}
			
			if(timer >= 1000000000) {
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
				
			}
		}
		
		stop();
		
	}
	
	public KeyManager getKEyManager() {
		return keyManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	
	
	public synchronized void start() {
		if(running == true) {return;}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(running == false) {return;}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
