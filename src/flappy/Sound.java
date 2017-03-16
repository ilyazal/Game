package flappy;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound implements Observer {
    private boolean released = false;
    private Clip clip = null;
    private FloatControl volumeC = null;
    private boolean playing = false;
    private int score;

    public Sound(File f) {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(f);
            clip = AudioSystem.getClip();
            clip.open(stream);
            clip.addLineListener(new Listener());
            volumeC = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            released = true;
        } catch(IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
            exc.printStackTrace();
            released = false;
        }
    }

    //true если звук успешно загружен, false если произошла ошибка
    public boolean isReleased() {
        return released;
    }

    //проигрывается ли звук в данный момент
    public boolean isPlaying() {
        return playing;
    }

    //Запуск
	/*
	  breakOld определяет поведение, если звук уже играется
	  Если reakOld==true, о звук будет прерван и запущен заново
	  Иначе ничего не произойдёт
	*/
    public void play(boolean breakOld) {
        if (released) {
            if (breakOld) {
                clip.stop();
                clip.setFramePosition(0);
                clip.start();
                playing = true;
            } else if (!isPlaying()) {
                clip.setFramePosition(0);
                clip.start();
                playing = true;
            }
        }
    }

    //То же самое, что и play(true)
    public void play() {
        play(true);
    }

    //Останавливает воспроизведение
    public void stop() {
        if (playing) {
            clip.stop();
        }
    }

    //Дожидается окончания проигрывания звука
    public void join() {
        if (!released) return;
        synchronized(clip) {
            try {
                while (playing) clip.wait();
            } catch (InterruptedException exc) {}
        }
    }

    //Статический метод, для удобства
    public static Sound playSound(String s) {
        File f = new File(s);
        Sound snd = new Sound(f);
        snd.play();
        return snd;
    }

    @Override
    public void update(int score) {
        if (score ==5) {
            playSound("D:\\Учёба\\2 семестр\\ООП\\курсовая\\Flappy\\sound.wav");
        }
    }

    private class Listener implements LineListener {
        public void update(LineEvent ev) {
            if (ev.getType() == LineEvent.Type.STOP) {
                playing = false;
                synchronized(clip) {
                    clip.notify();
                }
            }
        }
    }
}
