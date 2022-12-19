import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class MineTimer extends JLabel {
    public MineTimer(Timer timer){
        TimerTask task = new TimerTask() {
            private volatile int current = -1;
            private final Runnable refreshTimeLabel = () -> {
                int cur = current;
                int minutes = cur / 60;
                int seconds = cur % 60;
                MineTimer.this.setText(String.format("%02d:%02d", minutes, seconds));
            };

            @Override
            public void run() {
                current++;
                SwingUtilities.invokeLater(refreshTimeLabel);
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }
}
