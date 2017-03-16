package flappy;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * class extends JPanel and make main window
 */
public class Renderer extends JPanel {
    private static final long serialVersionUID = 1L;
    public Flappy flappy = new Flappy(this);
    private Flappy.ForMouse formouse = flappy.new ForMouse();
    private Flappy.ForKey forkey = flappy.new ForKey();
    private static JFrame jframe = new JFrame();
    private Thread walk = new Thread(new PaintThread(this));



    /**
     * Frame size
     */
    public static final int WIDTH = 800, HEIGHT = 600;

    public Renderer() {

        jframe.setTitle("Flappy");
        jframe.add(this);
        jframe.addMouseListener(formouse);
        jframe.addKeyListener(forkey);
        ActionListener bt = new LFButton();
        JButton button = new JButton("L&F");
        button.addActionListener(bt);
        this.add(button);
        button.setFocusable(false);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(WIDTH, HEIGHT);
        jframe.setResizable(false);
        jframe.setVisible(true);
        walk.start();

    }

    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        flappy.paint(g);


    }

    public static void initMetalLookAndFeel() {
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
            SwingUtilities.updateComponentTreeUI(jframe);
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Can't use the specified look and feel on this platform.");
        } catch (Exception e) {
            System.err.println("Couldn't get specified look and feel, for some reason.");
        }
    }

    public static void initSystemLookAndFeel() {
        try {
            String systemLookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(systemLookAndFeelClassName);
            SwingUtilities.updateComponentTreeUI(jframe);
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Can't use the specified look and feel on this platform.");
        } catch (Exception e) {
            System.err.println("Couldn't get specified look and feel, for some reason.");
        }
    }




}
