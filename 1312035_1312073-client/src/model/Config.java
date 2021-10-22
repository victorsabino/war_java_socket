package model;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Config {
	public static int LARG_DEFAULT = 1030;
	public static int ALT_DEFAULT = 797;
	
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Dimension screenSize = tk.getScreenSize();

	public static double sl = screenSize.getWidth(); // Largura da tela
	public static double sa = screenSize.getHeight(); // Altura da tela
	public static int x = (int) sl / 2 - Config.LARG_DEFAULT / 2; // posicionamento	// centralizado em x da tela
	public static int y = (int) sa / 2 - Config.ALT_DEFAULT / 2; // posicionamentro	// centralizado em y da tela
}
