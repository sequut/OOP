import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class TimerLabel extends JLabel {
    public TimerLabel(Timer timer) {
        TimerTask timerTask = new TimerTask() {
            private volatile int time = -1;

            private final Runnable refresher = () -> {
                int t = time;
                TimerLabel.this.setText(String.format("%02d:%02d", t / 60, t % 60));
            };

            @Override
            public void run() {
                time += 1;
                SwingUtilities.invokeLater(refresher);
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }
}