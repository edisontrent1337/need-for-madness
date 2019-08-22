import java.io.IOException;

//
// Decompiled by Procyon v0.5.36
//

public class ModSlayer {
	static final String VERSION = "1.0";
	static final String COPYRIGHT = "";
	static final int EFF_VOL_SLIDE = 1;
	static final int EFF_PORT_DOWN = 2;
	static final int EFF_PORT_UP = 4;
	static final int EFF_VIBRATO = 8;
	static final int EFF_ARPEGGIO = 16;
	static final int EFF_PORT_TO = 32;
	static final int EFF_TREMOLO = 64;
	static final int EFF_RETRIG = 128;
	static final int MIX_BUF_SIZE = 2048;
	static final int DEF_TEMPO_NTSC = 6;
	static final int DEF_TEMPO_PAL = 6;
	static final int DEF_BPM_NTSC = 125;
	static final int DEF_BPM_PAL = 145;
	static final int MIDCRATE = 8448;
	static final int MAX_SAMPLES = 100;
	static final int MAX_TRACKS = 32;
	static final int S3M_MAGIC1 = 4122;
	static final int S3M_MAGIC2;
	static final int S3M_INSTR2;
	static final int[] normal_vol_adj;
	static final int[] loud_vol_adj;
	static final int[] sintable;
	static final int[] period_set;
	static final int[] period_set_step;
	int def_tempo;
	int def_bpm;
	byte[] vol_table;
	int[] vol_adj;
	int vol_shift;
	Mod mod;
	int order_pos;
	int tempo;
	int tempo_wait;
	int bpm;
	int row;
	int break_row;
	int bpm_samples;
	int pattofs;
	byte[] patt;
	int numtracks;
	ModTrackInfo[] tracks;
	int mixspeed;
	boolean mod_done;
	public boolean bit16;
	public int samplingrate;
	public int oversample;
	public int gain;
	public int nloops;
	public boolean loud;
	static final byte[] sunfmt;
	private static final int ERROR_SHIFT = 12;
	private static final int ERROR_MASK = 4095;
	private static final long ratediv = 22748294283264L;

	final void beattrack(final ModTrackInfo modTrackInfo) {
		if (modTrackInfo.period_low_limit == 0) {
			modTrackInfo.period_low_limit = 1;
		}
		if ((modTrackInfo.effect & 0x1) != 0x0) {
			modTrackInfo.volume += modTrackInfo.vol_slide;
			if (modTrackInfo.volume < 0) {
				modTrackInfo.volume = 0;
			}
			if (modTrackInfo.volume > 64) {
				modTrackInfo.volume = 64;
			}
		}
		if ((modTrackInfo.effect & 0x2) != 0x0) {
			if ((modTrackInfo.period += modTrackInfo.port_down) > modTrackInfo.period_high_limit) {
				modTrackInfo.period = modTrackInfo.period_high_limit;
			}
			modTrackInfo.pitch = modTrackInfo.finetune_rate / modTrackInfo.period;
		}
		if ((modTrackInfo.effect & 0x4) != 0x0) {
			if ((modTrackInfo.period -= modTrackInfo.port_up) < modTrackInfo.period_low_limit) {
				if (this.mod.s3m) {
					modTrackInfo.period = modTrackInfo.period_high_limit;
				} else {
					modTrackInfo.period = modTrackInfo.period_low_limit;
				}
			}
			modTrackInfo.pitch = modTrackInfo.finetune_rate / modTrackInfo.period;
		}
		if ((modTrackInfo.effect & 0x20) != 0x0) {
			if (modTrackInfo.portto < modTrackInfo.period) {
				if ((modTrackInfo.period += modTrackInfo.port_inc) > modTrackInfo.portto) {
					modTrackInfo.period = modTrackInfo.portto;
				}
			} else if (modTrackInfo.portto > modTrackInfo.period && (modTrackInfo.period -= modTrackInfo.port_inc) < modTrackInfo.portto) {
				modTrackInfo.period = modTrackInfo.portto;
			}
			modTrackInfo.pitch = modTrackInfo.finetune_rate / modTrackInfo.period;
		}
		if ((modTrackInfo.effect & 0x8) != 0x0) {
			modTrackInfo.vibpos += modTrackInfo.vib_rate << 2;
			int n = ModSlayer.sintable[modTrackInfo.vibpos >> 2 & 0x1F] * modTrackInfo.vib_depth >> 7;
			if ((modTrackInfo.vibpos & 0x80) != 0x0) {
				n = -n;
			}
			int n2 = n + modTrackInfo.period;
			if (n2 < modTrackInfo.period_low_limit) {
				n2 = modTrackInfo.period_low_limit;
			}
			if (n2 > modTrackInfo.period_high_limit) {
				n2 = modTrackInfo.period_high_limit;
			}
			modTrackInfo.pitch = modTrackInfo.finetune_rate / n2;
		}
		if ((modTrackInfo.effect & 0x10) != 0x0) {
			modTrackInfo.pitch = modTrackInfo.finetune_rate / modTrackInfo.arp[modTrackInfo.arpindex];
			++modTrackInfo.arpindex;
			if (modTrackInfo.arpindex >= 3) {
				modTrackInfo.arpindex = 0;
			}
		}
	}

	final void mixtrack_16_mono(final ModTrackInfo modTrackInfo, final int[] array, int n, int i) {
		final byte[] samples = modTrackInfo.samples;
		int position = modTrackInfo.position;
		final int n2 = this.vol_adj[modTrackInfo.volume] * this.gain >> this.vol_shift + 8;
		int error = modTrackInfo.error;
		final int n3 = modTrackInfo.pitch & 0xFFF;
		final int n4 = modTrackInfo.pitch >> 12;
		if (modTrackInfo.replen < 3) {
			final int length = modTrackInfo.length;
			if (position >= length) {
				return;
			}
			final int n5 = n + i;
			if (modTrackInfo.pitch < 4096) {
				while (position < length && n < n5) {
					final int n6 = n++;
					array[n6] += (samples[position] * (4096 - error) + samples[position + 1] * error) * n2 >> 12;
					final int n7;
					position += n4 + ((n7 = error + n3) >> 12);
					error = (n7 & 0xFFF);
				}
			} else {
				while (position < length && n < n5) {
					final int n8 = n++;
					array[n8] += samples[position] * n2;
					final int n9;
					position += n4 + ((n9 = error + n3) >> 12);
					error = (n9 & 0xFFF);
				}
			}
			modTrackInfo.error = error;
			modTrackInfo.position = position;
		} else {
			final int n10 = modTrackInfo.replen + modTrackInfo.repeat;
			if (modTrackInfo.pitch < 4096) {
				while (i > 0) {
					if (position >= n10) {
						position -= modTrackInfo.replen;
					}
					final int n11 = n++;
					array[n11] += (samples[position] * (4096 - error) + samples[position + 1] * error) * n2 >> 12;
					final int n12;
					position += n4 + ((n12 = error + n3) >> 12);
					error = (n12 & 0xFFF);
					--i;
				}
			} else {
				while (i > 0) {
					if (position >= n10) {
						position -= modTrackInfo.replen;
					}
					final int n13 = n++;
					array[n13] += samples[position] * n2;
					final int n14;
					position += n4 + ((n14 = error + n3) >> 12);
					error = (n14 & 0xFFF);
					--i;
				}
			}
			modTrackInfo.error = error;
			modTrackInfo.position = position;
		}
	}

	ModSlayer(final Mod mod, final int samplingrate, final int gain, final int def_bpm) {
		this.mod_done = false;
		this.nloops = 1;
		this.loud = false;
		this.samplingrate = samplingrate;
		this.gain = gain;
		this.oversample = 1;
		this.mod = mod;
		this.def_tempo = 6;
		this.def_bpm = def_bpm;
	}

	final void make_vol_table8() {
		this.vol_table = new byte[16640];
		int n = 0;
		do {
			this.vol_table[n] = (byte) (this.vol_adj[n >> 8] * (byte) n >> 8 + this.vol_shift);
		} while (++n < 16640);
	}

	static {
		S3M_MAGIC2 = Mod.FOURCC("SCRM");
		S3M_INSTR2 = Mod.FOURCC("SCRS");
		normal_vol_adj = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 63};
		loud_vol_adj = new int[]{0, 0, 1, 2, 2, 3, 3, 4, 5, 6, 7, 8, 9, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 47, 48, 49, 50, 51, 52, 53, 53, 54, 55, 55, 56, 56, 57, 57, 58, 58, 59, 59, 60, 60, 61, 61, 61, 62, 62, 62, 63, 63, 63, 63, 63, 63};
		sintable = new int[]{0, 25, 50, 74, 98, 120, 142, 162, 180, 197, 212, 225, 236, 244, 250, 254, 255, 254, 250, 244, 236, 225, 212, 197, 180, 162, 142, 120, 98, 74, 50, 25};
		period_set = new int[]{1712, 1616, 1525, 1440, 1359, 1283, 1211, 1143, 1078, 1018, 961, 907, 856, 808, 763, 720, 679, 641, 605, 571, 539, 509, 480, 453, 428, 404, 381, 360, 340, 321, 303, 286, 270, 254, 240, 227, 214, 202, 191, 180, 170, 160, 151, 143, 135, 127, 120, 113, 107, 101, 95, 90, 85, 80, 76, 71, 67, 64, 60, 57, 53, 50, 48, 45, 42, 40, 38, 36, 34, 32, 30, 28, 27, 25, 24, 22, 21, 20, 19, 18, 17, 16, 15, 14};
		period_set_step = new int[]{1664, 1570, 1482, 1399, 1321, 1247, 1177, 1110, 1048, 989, 934, 881, 832, 785, 741, 699, 660, 623, 588, 555, 524, 494, 466, 440, 416, 392, 370, 350, 330, 312, 294, 278, 262, 247, 233, 220, 208, 196, 185, 175, 165, 155, 147, 139, 131, 123, 116, 110, 104, 98, 92, 87, 82, 78, 73, 69, 65, 62, 58, 55, 51, 49, 46, 43, 41, 39, 37, 35, 33, 31, 29, 27, 26, 24, 23, 21, 20, 19, 18, 17, 16, 15, 14, 14};
		sunfmt = new byte[]{46, 115, 110, 100, 0, 0, 0, 24, 127, 127, 127, 127, 0, 0, 0, 1, 0, 0, 31, 76, 0, 0, 0, 1, 0, 0, 0, 0};
	}

	final void updatetracks() {
		this.tempo_wait = this.tempo;
		if (this.row >= 64) {
			if (this.order_pos >= this.mod.song_length_patterns) {
				this.order_pos = 0;
				--this.nloops;
				if (this.nloops == 0) {
					this.mod_done = true;
				}
			}
			this.row = this.break_row;
			this.break_row = 0;
			if (this.mod.positions[this.order_pos] == (byte) 255) {
				this.order_pos = 0;
				this.row = 0;
			}
			this.patt = this.mod.patterns[this.mod.positions[this.order_pos]];
			this.pattofs = this.row * 4 * this.numtracks;
			++this.order_pos;
		}
		++this.row;
		for (int i = 0; i < this.numtracks; ++i) {
			this.pattofs = this.getMusicTrack(this.tracks[i], this.patt, this.pattofs);
		}
	}

	public byte[] turnToBytes() throws IOException {
		this.bit16 = true;
		this.startplaying(this.loud);
		final int[] array = new int[this.mixspeed];
		final int[] array2 = new int[this.mixspeed];
		final int[] array3 = new int[3500000];
		int n = 0;
		while (!this.mod_done) {
			if (--this.tempo_wait > 0) {
				for (int i = 0; i < this.numtracks; ++i) {
					this.beattrack(this.tracks[i]);
				}
			} else {
				this.updatetracks();
			}
			System.arraycopy(array2, 0, array, 0, this.bpm_samples);
			for (int j = 0; j < this.numtracks; ++j) {
				this.mixtrack_16_mono(this.tracks[j], array, 0, this.bpm_samples);
			}
			int bpm_samples = this.bpm_samples;
			if (this.oversample > 1) {
				int n2 = 0;
				bpm_samples = this.bpm_samples / this.oversample;
				if (this.oversample == 2) {
					for (int k = 0; k < bpm_samples; ++k) {
						array[k] = array[n2] + array[n2 + 1] >> 1;
						n2 += 2;
					}
				} else {
					for (int l = 0; l < bpm_samples; ++l) {
						int n3 = array[n2++];
						for (int n4 = 1; n4 < this.oversample; ++n4) {
							n3 += array[n2++];
						}
						array[l] = n3 / this.oversample;
					}
				}
			}
			for (int n5 = 0; n5 < bpm_samples; ++n5) {
				if (n < 3500000) {
					array3[n] = array[n5];
					++n;
				}
			}
		}
		for (int n6 = 2; n6 < n; ++n6) {
			array3[n6] = (array3[n6] + array3[n6 - 2]) / 2;
		}
		for (int n7 = 57; n7 < n; ++n7) {
			array3[n7] = (array3[n7] + array3[n7] + array3[n7 - 50]) / 3;
		}
		final byte[] array4 = new byte[n];
		for (int n8 = 0; n8 < n; ++n8) {
			array4[n8] = UlawUtils.linear2ulawclip(array3[n8]);
		}
		return array4;
	}

	final int getMusicTrack(final ModTrackInfo modTrackInfo, final byte[] array, int n) {
		final int n2 = array[n] & 0xF0;
		final int portto = (array[n++] & 0xF) << 8 | (array[n++] & 0xFF);
		final int n3 = array[n] & 0xF;
		int n4 = n2 | (array[n++] & 0xF0) >> 4;
		int oldsampofs = array[n++];
		modTrackInfo.effect = 0;
		if (n4 != 0) {
			--n4;
			final ModInstrument modInstrument = this.mod.insts[n4];
			modTrackInfo.volume = modInstrument.volume;
			modTrackInfo.length = modInstrument.sample_length;
			modTrackInfo.repeat = modInstrument.repeat_point;
			modTrackInfo.replen = modInstrument.repeat_length;
			modTrackInfo.finetune_rate = modInstrument.finetune_rate;
			modTrackInfo.samples = modInstrument.samples;
			modTrackInfo.period_low_limit = modInstrument.period_low_limit;
			modTrackInfo.period_high_limit = modInstrument.period_high_limit;
		}
		if (portto != 0) {
			modTrackInfo.portto = portto;
			if (n3 != 3 && n3 != 5) {
				final int n5 = portto;
				modTrackInfo.period = n5;
				modTrackInfo.start_period = n5;
				modTrackInfo.pitch = modTrackInfo.finetune_rate / portto;
				modTrackInfo.position = 0;
			}
		}
		Label_0911:
		{
			if (n3 != 0 || oldsampofs != 0) {
				switch (n3) {
					case 0: {
						int n6 = 12;
						while (true) {
							while (modTrackInfo.period < ModSlayer.period_set[n6]) {
								if (++n6 >= 48) {
									modTrackInfo.arp[0] = ModSlayer.period_set[n6];
									modTrackInfo.arp[1] = ModSlayer.period_set[n6 + (oldsampofs & 0xF)];
									modTrackInfo.arp[2] = ModSlayer.period_set[n6 + ((oldsampofs & 0xF0) >> 4)];
									modTrackInfo.arpindex = 0;
									modTrackInfo.effect |= 0x10;
									break Label_0911;
								}
							}
						}
					}
					case 1: {
						modTrackInfo.effect |= 0x4;
						if (oldsampofs != 0) {
							modTrackInfo.port_up = oldsampofs;
						}
						break;
					}
					case 2: {
						modTrackInfo.effect |= 0x2;
						if (oldsampofs != 0) {
							modTrackInfo.port_down = oldsampofs;
						}
						break;
					}
					case 3: {
						if (oldsampofs != 0) {
							modTrackInfo.port_inc = (oldsampofs & 0xFF);
						}
						modTrackInfo.effect |= 0x20;
						break;
					}
					case 4: {
						if ((oldsampofs & 0xF) != 0x0) {
							modTrackInfo.vib_depth = (oldsampofs & 0xF);
						}
						if ((oldsampofs & 0xF0) != 0x0) {
							modTrackInfo.vib_rate = (oldsampofs & 0xF0) >> 4;
						}
						if (portto != 0) {
							modTrackInfo.vibpos = 0;
						}
						modTrackInfo.effect |= 0x8;
						break;
					}
					case 9: {
						if (oldsampofs == 0) {
							oldsampofs = modTrackInfo.oldsampofs;
						}
						modTrackInfo.oldsampofs = oldsampofs;
						modTrackInfo.position = (oldsampofs & 0xFF) << 8;
						break;
					}
					case 5: {
						modTrackInfo.effect |= 0x20;
					}
					case 6: {
						if (n3 == 6) {
							modTrackInfo.effect |= 0x8;
						}
					}
					case 10: {
						modTrackInfo.vol_slide = ((oldsampofs & 0xF0) >> 4) - (oldsampofs & 0xF);
						modTrackInfo.effect |= 0x1;
						break;
					}
					case 12: {
						if (oldsampofs > 64 || oldsampofs < 0) {
							modTrackInfo.volume = 64;
						} else {
							modTrackInfo.volume = oldsampofs;
						}
						break;
					}
					case 13: {
						this.break_row = ((oldsampofs & 0xF0) >> 4) * 10 + (oldsampofs & 0xF);
						this.row = 64;
						break;
					}
					case 14: {
						final int n7 = oldsampofs & 0xF0;
						final int n8 = oldsampofs & 0xF;
						switch (n7) {
							case 1: {
								modTrackInfo.period += n8;
								if (modTrackInfo.period > modTrackInfo.period_high_limit) {
									modTrackInfo.period = modTrackInfo.period_high_limit;
								}
								modTrackInfo.pitch = modTrackInfo.finetune_rate / modTrackInfo.period;
								break;
							}
							case 2: {
								modTrackInfo.period -= n8;
								if (modTrackInfo.period < modTrackInfo.period_low_limit) {
									modTrackInfo.period = modTrackInfo.period_low_limit;
								}
								modTrackInfo.pitch = modTrackInfo.finetune_rate / modTrackInfo.period;
								break;
							}
						}
						break;
					}
					case 15: {
						if (oldsampofs == 0) {
							break;
						}
						final int bpm = oldsampofs & 0xFF;
						if (bpm <= 32) {
							this.tempo = bpm;
							this.tempo_wait = bpm;
						} else {
							this.bpm = bpm;
							this.bpm_samples = this.samplingrate / (103 * bpm >> 8) * this.oversample;
						}
						break;
					}
				}
			}
		}
		return n;
	}

	final void startplaying(final boolean b) {
		this.vol_adj = (b ? ModSlayer.loud_vol_adj : ModSlayer.normal_vol_adj);
		this.mixspeed = this.samplingrate * this.oversample;
		this.order_pos = 0;
		final int def_tempo = this.def_tempo;
		this.tempo = def_tempo;
		this.tempo_wait = def_tempo;
		this.bpm = this.def_bpm;
		this.row = 64;
		this.break_row = 0;
		this.bpm_samples = this.samplingrate / (24 * this.bpm / 60) * this.oversample;
		this.numtracks = this.mod.numtracks;
		this.tracks = new ModTrackInfo[this.numtracks];
		for (int i = 0; i < this.tracks.length; ++i) {
			this.tracks[i] = new ModTrackInfo();
		}
		if (this.mod.s3m) {
			for (int j = 0; j < this.mod.insts.length; ++j) {
				final ModInstrument modInstrument = this.mod.insts[j];
				modInstrument.finetune_rate = (int) (428L * modInstrument.finetune_value << 8) / this.mixspeed;
				modInstrument.period_low_limit = 14;
				modInstrument.period_high_limit = 1712;
			}
		} else {
			for (int k = 0; k < this.mod.insts.length; ++k) {
				final ModInstrument modInstrument2 = this.mod.insts[k];
				modInstrument2.finetune_rate = (int) (22748294283264L / (this.mixspeed * (1536 - modInstrument2.finetune_value)));
				modInstrument2.period_low_limit = 113;
				modInstrument2.period_high_limit = 856;
			}
		}
		if (this.numtracks > 8) {
			this.vol_shift = 2;
		} else if (this.numtracks > 4) {
			this.vol_shift = 1;
		} else {
			this.vol_shift = 0;
		}
		if (!this.bit16) {
			this.make_vol_table8();
		}
	}
}
