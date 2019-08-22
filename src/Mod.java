import java.applet.Applet;
import java.io.*;
import java.net.URL;
import java.util.zip.ZipInputStream;

//
// Decompiled by Procyon v0.5.36
//

public class Mod {

	static {
		voice_mk = FOURCC("M.K.");
		voice_mk2 = FOURCC("M!K!");
		voice_mk3 = FOURCC("M&K!");
		voice_flt4 = FOURCC("FLT4");
		voice_flt8 = FOURCC("FLT8");
		voice_28ch = FOURCC("28CH");
		voice_8chn = FOURCC("8CHN");
		voice_6chn = FOURCC("6CHN");
		voice_31_list = new int[]{Mod.voice_mk, Mod.voice_mk2, Mod.voice_mk3, Mod.voice_flt4, Mod.voice_flt8, Mod.voice_8chn, Mod.voice_6chn, Mod.voice_28ch};
	}

	String name;
	int numtracks;
	int track_shift;
	int numpatterns;
	byte[][] patterns;
	ModInstrument[] insts;
	byte[] positions;
	int song_length_patterns;
	int song_repeat_patterns;
	boolean s3m;
	static final int voice_mk;
	static final int voice_mk2;
	static final int voice_mk3;
	static final int voice_flt4;
	static final int voice_flt8;
	static final int voice_28ch;
	static final int voice_8chn;
	static final int voice_6chn;

	static final int[] voice_31_list;

	public int getNumPatterns() {
		return this.numpatterns;
	}

	public String toString() {
		return this.name + " (" + this.numtracks + " tracks, " + this.numpatterns + " patterns, " + this.insts.length + " samples)";
	}

	Mod(final String s, final Applet applet) {
		try {
			//final DataInputStream in = new DataInputStream(new URL(applet.getCodeBase(), drawShadow).openStream());
			//final ZipInputStream zipInputStream = new ZipInputStream(in);

			URL resource = this.getClass().getResource(s);
			File file = new File(resource.toURI());
			final FileInputStream in = new FileInputStream(file);
			final ZipInputStream zipInputStream = new ZipInputStream(in);
			int i = (int) zipInputStream.getNextEntry().getSize();
			final byte[] array = new byte[i];
			int off = 0;
			while (i > 0) {
				final int read = zipInputStream.read(array, off, i);
				off += read;
				i -= read;
			}
			this.loadMod(new ByteArrayInputStream(array));
			in.close();
			zipInputStream.close();
		} catch (Exception obj) {
			System.out.println("Mod: " + s + " " + obj);
		}
	}

	static final int readu16(final DataInputStream dataInputStream) throws IOException {
		return dataInputStream.readShort() & 0xFFFF;
	}

	static final String readText(final DataInputStream dataInputStream, final int len) throws IOException {
		final byte[] array = new byte[len];
		dataInputStream.readFully(array, 0, len);
		for (int i = len - 1; i >= 0; --i) {
			if (array[i] != 0) {
				return new String(array, 0, 0, i + 1);
			}
		}
		return "";
	}

	void readSequence(final DataInputStream dataInputStream) throws IOException {
		this.positions = new byte[128];
		this.song_length_patterns = readu8(dataInputStream);
		this.song_repeat_patterns = readu8(dataInputStream);
		dataInputStream.readFully(this.positions, 0, 128);
		if (this.song_repeat_patterns > this.song_length_patterns) {
			this.song_repeat_patterns = this.song_length_patterns;
		}
		this.numpatterns = 0;
		for (byte position : this.positions) {
			if (position > this.numpatterns) {
				this.numpatterns = position;
			}
		}
		++this.numpatterns;
	}

	public void loadMod(final InputStream in) throws IOException {
		final DataInputStream dataInputStream = new DataInputStream(in);
		int n = 15;
		this.numtracks = 4;
		this.name = readText(dataInputStream, 20);
		dataInputStream.mark(1068);
		dataInputStream.skip(1060L);
		final int int1 = dataInputStream.readInt();
		dataInputStream.reset();
		for (int i = 0; i < Mod.voice_31_list.length; ++i) {
			if (int1 == Mod.voice_31_list[i]) {
				n = 31;
				break;
			}
		}
		if (n == 31) {
			if (int1 == Mod.voice_8chn) {
				this.numtracks = 8;
			} else if (int1 == Mod.voice_6chn) {
				this.numtracks = 6;
			} else if (int1 == Mod.voice_28ch) {
				this.numtracks = 28;
			}
		}
		this.insts = new ModInstrument[n];
		for (int j = 0; j < n; ++j) {
			this.insts[j] = readInstrument(dataInputStream);
		}
		this.readSequence(dataInputStream);
		dataInputStream.skipBytes(4);
		this.readPatterns(dataInputStream);
		try {
			for (int k = 0; k < n; ++k) {
				readSampleData(dataInputStream, this.insts[k]);
			}
		} catch (EOFException ex) {
			System.out.println("Warning: EOF on MOD file");
		}
		dataInputStream.close();
	}

	static void readSampleData(final DataInputStream dataInputStream, final ModInstrument modInstrument) throws IOException {
		dataInputStream.readFully(modInstrument.samples, 0, modInstrument.sample_length);
		if (modInstrument.repeat_length > 3) {
			System.arraycopy(modInstrument.samples, modInstrument.repeat_point, modInstrument.samples, modInstrument.sample_length, 8);
		}
	}

	static ModInstrument readInstrument(final DataInputStream dataInputStream) throws IOException {
		final ModInstrument modInstrument = new ModInstrument();
		modInstrument.name = readText(dataInputStream, 22);
		modInstrument.sample_length = readu16(dataInputStream) << 1;
		modInstrument.samples = new byte[modInstrument.sample_length + 8];
		modInstrument.finetune_value = (byte) (readu8(dataInputStream) << 4);
		modInstrument.volume = readu8(dataInputStream);
		modInstrument.repeat_point = readu16(dataInputStream) << 1;
		modInstrument.repeat_length = readu16(dataInputStream) << 1;
		if (modInstrument.repeat_point > modInstrument.sample_length) {
			modInstrument.repeat_point = modInstrument.sample_length;
		}
		if (modInstrument.repeat_point + modInstrument.repeat_length > modInstrument.sample_length) {
			modInstrument.repeat_length = modInstrument.sample_length - modInstrument.repeat_point;
		}
		return modInstrument;
	}

	static final int FOURCC(final String s) {
		return (s.charAt(3) & '\u00ff') | (s.charAt(2) & '\u00ff') << 8 | (s.charAt(1) & '\u00ff') << 16 | (s.charAt(0) & '\u00ff') << 24;
	}

	public int getNumTracks() {
		return this.numtracks;
	}

	public String getName() {
		return this.name;
	}

	void readPatterns(final DataInputStream dataInputStream) throws IOException {
		final int len = this.numtracks * 4 * 64;
		this.patterns = new byte[this.numpatterns][];
		for (int i = 0; i < this.numpatterns; ++i) {
			dataInputStream.readFully(this.patterns[i] = new byte[len], 0, len);
		}
	}

	static final int readu8(final DataInputStream dataInputStream) throws IOException {
		return dataInputStream.readByte() & 0xFF;
	}
}
