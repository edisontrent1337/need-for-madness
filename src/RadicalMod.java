import java.applet.Applet;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import sun.audio.AudioPlayer;

//
// Decompiled by Procyon v0.5.36
//

public class RadicalMod {
    SuperStream stream;
    boolean playing;

    public void stop() {
        if (this.playing) {
            try {
                AudioPlayer.player.stop(this.stream);
            } catch (Exception ex) {
            }
            this.playing = false;
        }
    }

    protected void outwithit() {
        if (this.playing) {
            try {
                AudioPlayer.player.stop(this.stream);
            } catch (Exception ex) {
            }
            this.playing = false;
        }
        try {
            if (this.stream != null) {
                this.stream.close();
            }
        } catch (Exception ex2) {
        }
    }

    public RadicalMod(final String path, final int n, final int n2, final int n3, final Applet applet) {
        this.playing = false;
        final ModSlayer modSlayer = new ModSlayer(new Mod(path, applet), n2, n, n3);
        try {
            this.stream = new SuperStream(modSlayer.turnToBytes());
        } catch (Exception obj) {
            this.stream = null;
            System.out.println("Error Loading Mod: " + obj);
        }
    }

    public void resume() {
        if (!this.playing) {
            try {
                //AudioPlayer.player.start(this.stream);
            } catch (Exception ex) {
            }
            this.playing = true;
        }
    }

    public void play() {
        if (!this.playing) {
            if (this.stream != null) {
                this.stream.reset();
            }
            try {
                AudioPlayer.player.start(this.stream);
            } catch (Exception ex) {
            }
            this.playing = true;
        }
    }

    public int posit() {
        return 0;
    }
}
