import java.io.ByteArrayInputStream;

// 
// Decompiled by Procyon v0.5.36
// 

public class SuperStream extends ByteArrayInputStream
{
    public SuperStream(final byte[] buf) {
        super(buf);
    }
    
    public int read() {
        int n = super.read();
        if (n == -1) {
            this.reset();
            n = super.read();
        }
        return n;
    }
    
    public int read(final byte[] b, final int n, final int n2) {
        int i = 0;
        while (i < n2) {
            final int read = super.read(b, n + i, n2 - i);
            if (read >= 0) {
                i += read;
            }
            else {
                this.reset();
            }
        }
        return i;
    }
}
