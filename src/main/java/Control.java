package main.java;

public class Control {
	boolean left;
	boolean right;
	boolean up;
	boolean down;
	boolean handb;
	boolean enter;
	boolean arrace;
	boolean music_muted;
	boolean sound_muted;
	Medium medium;
	int pan;
	int attack;
	int acr;
	boolean afta;
	int[] fpnt;
	int trfix;
	boolean forget;
	boolean bulistc;
	int acuracy;
	int upwait;
	boolean agressed;
	float skiplev;
	int clrnce;
	int rampp;
	int turntyp;
	int saftey;
	boolean perfection;
	float mustland;
	boolean usebounce;
	float trickprf;
	boolean zyinv;
	boolean lastl;
	boolean wlastl;
	int hold;
	int wall;
	int lwall;
	int stcnt;
	int statusque;
	int turncnt;
	int randtcnt;
	int upcnt;
	int trickfase;
	int swat;
	boolean udcomp;
	boolean lrcomp;
	boolean udbare;
	boolean lrbare;
	boolean onceu;
	boolean onced;
	boolean oncel;
	boolean oncer;
	int lrdirect;
	int uddirect;
	int lrstart;
	int udstart;
	int oxy;
	int ozy;
	int flycnt;
	boolean lrswt;
	boolean udswt;
	int actwait;
	int cntrn;

	public void preform(final Madness madness, final Geometry geometry, final CheckPoints checkPoints, final Trackers trackers) {
		this.left = false;
		this.right = false;
		this.up = false;
		this.down = false;
		this.handb = false;
		if (!madness.dest) {
			if (madness.mtouch) {
				if (this.stcnt > this.statusque) {
					this.acuracy = (7 - checkPoints.pos[madness.im]) * checkPoints.pos[0] * (6 - checkPoints.stage * 2);
					if (this.acuracy < 0) {
						this.acuracy = 0;
					}
					float n = 0.0f;
					if (checkPoints.stage == 1) {
						n = 2.0f;
					}
					if (checkPoints.stage == 2) {
						n = 1.5f;
					}
					if (checkPoints.stage == 3 && madness.im != 4) {
						n = 0.5f;
					}
					if (checkPoints.stage == 4) {
						n = 0.5f;
					}
					this.upwait = (int) ((checkPoints.pos[0] - checkPoints.pos[madness.im]) * (checkPoints.pos[0] - checkPoints.pos[madness.im]) * (checkPoints.pos[0] - checkPoints.pos[madness.im]) * n);
					if (this.upwait > 80) {
						this.upwait = 80;
					}
					if (checkPoints.stage == 1 && this.upwait < 20) {
						this.upwait = 20;
					}
					float skiplev = 0.0f;
					if (checkPoints.stage == 1 || checkPoints.stage == 2) {
						skiplev = 1.0f;
					}
					if (checkPoints.stage == 4) {
						skiplev = 0.5f;
					}
					if (checkPoints.stage == 7) {
						skiplev = 0.5f;
					}
					if (checkPoints.stage == 10) {
						skiplev = 0.5f;
					}
					if (checkPoints.pos[madness.im] - checkPoints.pos[0] >= -1) {
						this.skiplev -= (float) 0.1;
						if (this.skiplev < 0.0f) {
							this.skiplev = 0.0f;
						}
					} else {
						this.skiplev += (float) 0.2;
						if (this.skiplev > skiplev) {
							this.skiplev = skiplev;
						}
					}
					this.rampp = (int) (this.medium.random() * 4.0f - 2.0f);
					if (madness.power == 98.0f) {
						this.rampp = -1;
					}
					if (madness.power < 75.0f && this.rampp == -1) {
						this.rampp = 0;
					}
					if (madness.power < 60.0f) {
						this.rampp = 1;
					}
					if (checkPoints.stage == 6) {
						this.rampp = 2;
					}
					if (this.cntrn == 0) {
						this.agressed = false;
						this.turntyp = (int) (this.medium.random() * 4.0f);
						if ((checkPoints.stage == 3 || checkPoints.stage == 9) && madness.im == 4) {
							this.turntyp = 1;
							if (this.attack == 0) {
								this.agressed = true;
							}
						}
						if (checkPoints.pos[0] - checkPoints.pos[madness.im] < 0) {
							this.turntyp = (int) (this.medium.random() * 2.0f);
						}
						if (checkPoints.stage == 10) {
							this.turntyp = 2;
						}
						if (this.attack != 0) {
							this.turntyp = 2;
							if (checkPoints.stage == 9 || checkPoints.stage == 10 || checkPoints.stage == 11) {
								this.turntyp = (int) (this.medium.random() * 3.0f);
							}
						}
						if (checkPoints.stage == 6) {
							this.turntyp = 1;
							this.agressed = true;
						}
						if (checkPoints.stage == 7 || checkPoints.stage == 9 || checkPoints.stage == 10 || checkPoints.stage == 11) {
							this.agressed = true;
						}
						this.cntrn = 5;
					} else {
						--this.cntrn;
					}
					this.saftey = (int) ((98.0f - madness.power) / 2.0f * (this.medium.random() / 2.0f + 0.5));
					if (this.saftey > 20) {
						this.saftey = 20;
					}
					float n2 = 0.0f;
					if (checkPoints.stage == 1) {
						n2 = 0.9f;
					}
					if (checkPoints.stage == 2) {
						n2 = 0.7f;
					}
					if (checkPoints.stage == 4) {
						n2 = 0.4f;
					}
					this.mustland = n2 + (float) (this.medium.random() / 2.0f - 0.25);
					float n3 = 1.0f;
					if (checkPoints.stage == 1) {
						n3 = 5.0f;
					}
					if (checkPoints.stage == 2) {
						n3 = 2.0f;
					}
					if (checkPoints.stage == 4) {
						n3 = 1.5f;
					}
					if (madness.power > 50.0f) {
						if (checkPoints.pos[0] - checkPoints.pos[madness.im] > 0) {
							this.saftey *= (int) n3;
						} else {
							this.mustland = 0.0f;
						}
					} else {
						this.mustland -= 0.5f;
					}
					this.trickprf = (madness.power - 38.0f) / 50.0f - this.medium.random() / 2.0f;
					if (madness.power < 60.0f) {
						this.trickprf = -1.0f;
					}
					if (checkPoints.stage == 6 && this.trickprf > 0.5) {
						this.trickprf = 0.5f;
					}
					if (checkPoints.stage == 3 && madness.im == 4 && this.trickprf > 0.7) {
						this.trickprf = 0.7f;
					}
					if (checkPoints.stage == 11) {
						this.trickprf = -1.0f;
					}
					this.usebounce = this.medium.random() > madness.power / 100.0f;
					if (checkPoints.stage == 9) {
						this.usebounce = false;
					}
					this.perfection = !(this.medium.random() > madness.currentDamage / (float) madness.maxDamage[madness.carIndex]);
					if (100.0f * madness.currentDamage / madness.maxDamage[madness.carIndex] > 60.0f) {
						this.perfection = true;
					}
					if (checkPoints.stage == 6 || checkPoints.stage == 8 || checkPoints.stage == 9 || checkPoints.stage == 10 || checkPoints.stage == 11) {
						this.perfection = true;
					}
					if (checkPoints.stage == 3 && madness.im == 4) {
						this.perfection = true;
					}
					this.clrnce = 5;
					if (this.attack == 0) {
						boolean afta = true;
						if (checkPoints.stage == 3 || checkPoints.stage == 1 || checkPoints.stage == 4 || checkPoints.stage == 9) {
							afta = this.afta;
						}
						if (checkPoints.stage == 8 || checkPoints.stage == 6) {
							afta = false;
						}
						if (checkPoints.stage == 3 && madness.im == 4) {
							afta = false;
						}
						boolean b = false;
						if (checkPoints.stage == 4 || checkPoints.stage == 10) {
							b = true;
						}
						if (checkPoints.stage == 3 && madness.im == 4) {
							b = true;
						}
						int n4 = 60;
						if (checkPoints.stage == 3 || checkPoints.stage == 11) {
							n4 = 30;
						}
						if (checkPoints.stage == 5) {
							n4 = 40;
						}
						if (checkPoints.stage == 6 && this.bulistc) {
							n4 = 40;
						}
						if (checkPoints.stage == 9 && this.bulistc) {
							n4 = 30;
						}
						int acr = 0;
						do {
							if (acr != madness.im && checkPoints.clear[acr] != -1) {
								float i = geometry.xz;
								if (this.zyinv) {
									i += 180;
								}
								while (i < 0) {
									i += 360;
								}
								while (i > 180) {
									i -= 360;
								}
								int n5 = 0;
								if (checkPoints.opx[acr] - geometry.x >= 0) {
									n5 = 180;
								}
								int j;
								j = (int) (90 + n5 + Math.atan((checkPoints.opz[acr] - geometry.z) / (double) (checkPoints.opx[acr] - geometry.x)) / 0.017453292519943295);
								while (j < 0) {
									j += 360;
								}
								while (j > 180) {
									j -= 360;
								}
								float n6 = Math.abs(i - j);
								if (n6 > 180) {
									n6 = Math.abs(n6 - 360);
								}
								int n7 = 2000 * (Math.abs(checkPoints.clear[acr] - madness.clear) + 1);
								if ((checkPoints.stage == 6 || checkPoints.stage == 9) && this.bulistc) {
									n7 = 6000;
								}
								if (checkPoints.stage == 3 && madness.im == 4 && checkPoints.wasted < 2 && n7 > 4000) {
									n7 = 4000;
								}
								if (n6 < 85 + 15 * (Math.abs(checkPoints.clear[acr] - madness.clear) + 1) && this.py(geometry.x / 100, checkPoints.opx[acr] / 100, geometry.z / 100, checkPoints.opz[acr] / 100) < n7 && this.afta && madness.power > n4) {
									float n8 = (float) (35 - Math.abs(checkPoints.clear[acr] - madness.clear) * 10);
									if (n8 < 1.0f) {
										n8 = 1.0f;
									}
									float n9 = (checkPoints.pos[madness.im] + 1) * (5 - checkPoints.pos[acr]) / n8;
									if (checkPoints.stage != 11 && n9 > 0.7) {
										n9 = 0.7f;
									}
									if (acr != 0 && checkPoints.pos[0] < checkPoints.pos[madness.im]) {
										n9 = 0.0f;
									}
									if (acr != 0 && b) {
										n9 = 0.0f;
									}
									if (b && checkPoints.stage == 3 && acr == 0) {
										if (checkPoints.wasted >= 2) {
											n9 *= 0.5f;
										} else {
											n9 = 0.0f;
										}
									}
									if ((checkPoints.stage == 3 || checkPoints.stage == 9) && acr == 4) {
										n9 = 0.0f;
									}
									if (checkPoints.stage == 6) {
										n9 = 0.0f;
										if (this.bulistc && acr == 0) {
											n9 = 1.0f;
										}
									}
									if (checkPoints.stage == 8) {
										n9 = 0.0f;
									}
									if (checkPoints.stage == 9 && madness.im == 4) {
										n9 = 0.0f;
									}
									if (checkPoints.stage == 9 && this.bulistc) {
										if (acr == 0) {
											n9 = 1.0f;
										} else {
											n9 = 0.0f;
										}
									}
									if (checkPoints.stage == 9 && (checkPoints.pos[acr] == 4 || checkPoints.pos[acr] == 3)) {
										n9 = 0.0f;
									}
									if (this.medium.random() < n9) {
										this.attack = 40 * (Math.abs(checkPoints.clear[acr] - madness.clear) + 1);
										if (this.attack > 500) {
											this.attack = 500;
										}
										this.acr = acr;
										this.turntyp = (int) (1.0f + this.medium.random() * 2.0f);
									}
								}
								if (!afta || n6 <= 100 || this.py(geometry.x / 100, checkPoints.opx[acr] / 100, geometry.z / 100, checkPoints.opz[acr] / 100) >= 300 || this.medium.random() <= 0.6 - checkPoints.pos[madness.im] / 10.0f) {
									continue;
								}
								this.clrnce = 0;
								this.acuracy = 0;
							}
						} while (++acr < 5);
					}
					boolean b2 = false;
					if (checkPoints.stage == 6 || checkPoints.stage == 8 || checkPoints.stage == 11) {
						b2 = true;
					}
					if (checkPoints.stage == 9 && madness.im == 4) {
						b2 = true;
					}
					if (this.trfix != 3) {
						this.trfix = 0;
						if (100.0f * madness.currentDamage / madness.maxDamage[madness.carIndex] > 50.0f) {
							this.trfix = 1;
						}
						if (!b2 && 100.0f * madness.currentDamage / madness.maxDamage[madness.carIndex] > 80.0f) {
							this.trfix = 2;
						}
					} else {
						this.upwait = 0;
						this.acuracy = 0;
						this.skiplev = 1.0f;
						this.clrnce = 2;
					}
					if (!this.bulistc) {
						if (checkPoints.stage == 6 && madness.carIndex == 7) {
							this.bulistc = true;
						}
						if (checkPoints.stage == 9 && this.afta && (checkPoints.pos[madness.im] == 4 || checkPoints.pos[madness.im] == 3) && madness.carIndex != 9 && this.trfix != 0) {
							this.bulistc = true;
						}
					}
					this.stcnt = 0;
					this.statusque = (int) (20.0f * this.medium.random());
				} else {
					++this.stcnt;
				}
			}
			boolean b3;
			if (this.usebounce) {
				b3 = madness.wtouch;
			} else {
				b3 = madness.mtouch;
			}
			if (b3) {
				if (this.trickfase != 0) {
					this.trickfase = 0;
				}
				if (this.trfix == 2 || this.trfix == 3) {
					this.attack = 0;
				}
				if (this.attack == 0) {
					if (this.upcnt < 30) {
						this.up = true;
					}
					if (this.upcnt < 25 + this.actwait) {
						++this.upcnt;
					} else {
						this.upcnt = 0;
						this.actwait = this.upwait;
					}
					int point = madness.point;
					int n10 = 50;
					if (checkPoints.stage == 9) {
						n10 = 20;
					}
					if (!this.bulistc || this.trfix == 2 || this.trfix == 3 || this.trfix == 4 || madness.power < n10) {
						if (this.rampp == 1 && checkPoints.typ[point] <= 0) {
							int n11 = point + 1;
							if (n11 == checkPoints.n) {
								n11 = 0;
							}
							if (checkPoints.typ[n11] == -2) {
								point = n11;
							}
						}
						if (this.rampp == -1 && checkPoints.typ[point] == -2 && ++point == checkPoints.n) {
							point = 0;
						}
						if (this.medium.random() > this.skiplev) {
							int n12 = point;
							int n13 = 0;
							if (checkPoints.typ[n12] > 0) {
								int n14 = 0;
								for (int k = 0; k < checkPoints.n; ++k) {
									if (checkPoints.typ[k] > 0 && k < n12) {
										++n14;
									}
								}
								n13 = ((madness.clear != n14 + madness.nlaps * checkPoints.nsp) ? 1 : 0);
							}
							while (checkPoints.typ[n12] == 0 || checkPoints.typ[n12] == -1 || checkPoints.typ[n12] == -3 || n13 != 0) {
								point = n12;
								if (++n12 == checkPoints.n) {
									n12 = 0;
								}
								n13 = 0;
								if (checkPoints.typ[n12] > 0) {
									int n15 = 0;
									for (int l = 0; l < checkPoints.n; ++l) {
										if (checkPoints.typ[l] > 0 && l < n12) {
											++n15;
										}
									}
									n13 = ((madness.clear != n15 + madness.nlaps * checkPoints.nsp) ? 1 : 0);
								}
							}
						} else if (this.medium.random() > this.skiplev) {
							while (checkPoints.typ[point] == -1) {
								if (++point == checkPoints.n) {
									point = 0;
								}
							}
						}
						if (this.rampp == 2) {
							int n16 = point + 1;
							if (n16 == checkPoints.n) {
								n16 = 0;
							}
							if (checkPoints.typ[n16] == -2 && point != madness.point && --point < 0) {
								point += checkPoints.n;
							}
						}
						if (this.bulistc) {
							madness.nofocus = true;
						}
					} else {
						point -= 2;
						if (point < 0) {
							point += checkPoints.n;
						}
						if (checkPoints.stage == 9 && point > 76) {
							point = 76;
						}
						while (checkPoints.typ[point] == -4) {
							if (--point < 0) {
								point += checkPoints.n;
							}
						}
						madness.nofocus = true;
					}
					if (checkPoints.stage != 11 && (madness.missedCheckpoint == 0 || this.forget || this.trfix == 4 || checkPoints.stage == 10) && this.trfix != 0) {
						if (this.trfix == 2) {
							int py = -10;
							int n17 = 0;
							for (int n18 = 0; n18 < checkPoints.fn; ++n18) {
								if (this.py(geometry.x / 100, checkPoints.x[this.fpnt[n18]] / 100, geometry.z / 100, checkPoints.z[this.fpnt[n18]] / 100) < py || py == -10) {
									py = this.py(geometry.x / 100, checkPoints.x[this.fpnt[n18]] / 100, geometry.z / 100, checkPoints.z[this.fpnt[n18]] / 100);
									n17 = n18;
								}
							}
							if (checkPoints.stage == 9 && this.bulistc) {
								n17 = 1;
							}
							point = this.fpnt[n17];
							this.forget = checkPoints.special[n17];
						}
						for (int n19 = 0; n19 < checkPoints.fn; ++n19) {
							if (this.py(geometry.x / 100, checkPoints.x[this.fpnt[n19]] / 100, geometry.z / 100, checkPoints.z[this.fpnt[n19]] / 100) < 2000) {
								this.forget = false;
								this.actwait = 0;
								this.upwait = 0;
								this.turntyp = 2;
								this.randtcnt = -1;
								this.acuracy = 0;
								this.rampp = 0;
								this.trfix = 3;
							}
						}
						if (this.trfix == 3) {
							madness.nofocus = true;
						}
					}
					if (this.turncnt > this.randtcnt) {
						int n20 = 0;
						if (checkPoints.x[point] - geometry.x >= 0) {
							n20 = 180;
						}
						this.pan = (int) (90 + n20 + Math.atan((checkPoints.z[point] - geometry.z) / (double) (checkPoints.x[point] - geometry.x)) / 0.017453292519943295);
						this.turncnt = 0;
						this.randtcnt = (int) (this.acuracy * this.medium.random());
					} else {
						++this.turncnt;
					}
				} else {
					this.up = true;
					int n21 = 0;
					if (checkPoints.opx[this.acr] - geometry.x >= 0) {
						n21 = 180;
					}
					this.pan = (int) (90 + n21 + Math.atan((checkPoints.opz[this.acr] - geometry.z) / (double) (checkPoints.opx[this.acr] - geometry.x)) / 0.017453292519943295);
					--this.attack;
					if (this.attack <= 0) {
						this.attack = 0;
					}
				}
				float xz = geometry.xz;
				if (this.zyinv) {
					xz += 180;
				}
				while (xz < 0) {
					xz += 360;
				}
				while (xz > 180) {
					xz -= 360;
				}
				while (this.pan < 0) {
					this.pan += 360;
				}
				while (this.pan > 180) {
					this.pan -= 360;
				}
				if (this.wall != -1 && this.hold == 0) {
					this.clrnce = 0;
				}
				if (this.hold == 0) {
					if (Math.abs(xz - this.pan) < 180) {
						if (Math.abs(xz - this.pan) > this.clrnce) {
							if (xz < this.pan) {
								this.left = true;
								this.lastl = true;
							} else {
								this.right = true;
								this.lastl = false;
							}
							if (Math.abs(xz - this.pan) > 50 && madness.speed > madness.swits[madness.carIndex][0] && this.turntyp != 0) {
								if (this.turntyp == 1) {
									this.down = true;
								}
								if (this.turntyp == 2) {
									this.handb = true;
								}
								if (!this.agressed) {
									this.up = false;
								}
							}
						}
					} else if (Math.abs(xz - this.pan) < 360 - this.clrnce) {
						if (xz < this.pan) {
							this.right = true;
							this.lastl = false;
						} else {
							this.left = true;
							this.lastl = true;
						}
						if (Math.abs(xz - this.pan) < 310 && madness.speed > madness.swits[madness.carIndex][0] && this.turntyp != 0) {
							if (this.turntyp == 1) {
								this.down = true;
							}
							if (this.turntyp == 2) {
								this.handb = true;
							}
							if (!this.agressed) {
								this.up = false;
							}
						}
					}
				}
				if (this.wall != -1) {
					if (this.lwall != this.wall) {
						if (this.lastl) {
							this.left = true;
						} else {
							this.right = true;
						}
						this.wlastl = this.lastl;
						this.lwall = this.wall;
					} else if (this.wlastl) {
						this.left = true;
					} else {
						this.right = true;
					}
					if (trackers.dam[this.wall] != 0) {
						++this.hold;
						if (this.hold > 10) {
							this.hold = 10;
						}
					} else {
						this.hold = 1;
					}
					this.wall = -1;
				} else if (this.hold != 0) {
					--this.hold;
				}
			} else {
				if (this.trickfase == 0) {
					final int n22 = (int) ((madness.scy[0] + madness.scy[1] + madness.scy[2] + madness.scy[3]) * (geometry.y - 300) / 4000.0f);
					if (n22 > 9 && this.medium.random() > this.trickprf / 3.0f) {
						this.oxy = madness.xyRotation;
						this.ozy = madness.zyRotation;
						this.flycnt = 0;
						this.uddirect = 0;
						this.lrdirect = 0;
						this.udswt = false;
						this.lrswt = false;
						this.trickfase = 1;
						if (n22 < 16) {
							this.uddirect = -1;
							this.udstart = 0;
							this.udswt = false;
						} else if (this.medium.random() > this.medium.random() || checkPoints.stage == 8) {
							if (this.medium.random() > this.medium.random()) {
								this.uddirect = -1;
							} else {
								this.uddirect = 1;
							}
							this.udstart = (int) (10.0f * this.medium.random() * this.trickprf);
							if (this.medium.random() > 0.85) {
								this.udswt = true;
							}
							if (this.medium.random() > this.trickprf + 0.3f && checkPoints.stage != 8) {
								if (this.medium.random() > this.medium.random()) {
									this.lrdirect = -1;
								} else {
									this.lrdirect = 1;
								}
								this.lrstart = (int) (30.0f * this.medium.random());
								if (this.medium.random() > 0.75) {
									this.lrswt = true;
								}
							}
						} else {
							if (this.medium.random() > this.medium.random()) {
								this.lrdirect = -1;
							} else {
								this.lrdirect = 1;
							}
							this.lrstart = (int) (10.0f * this.medium.random() * this.trickprf);
							if (this.medium.random() > 0.75) {
								this.lrswt = true;
							}
							if (this.medium.random() > this.trickprf + 0.3f) {
								if (this.medium.random() > this.medium.random()) {
									this.uddirect = -1;
								} else {
									this.uddirect = 1;
								}
								this.udstart = (int) (30.0f * this.medium.random());
								if (this.medium.random() > 0.85) {
									this.udswt = true;
								}
							}
						}
						if (this.trfix == 3 || this.trfix == 4) {
							if (checkPoints.stage != 8) {
								if (this.lrdirect == -1 || madness.power < 60.0f) {
									this.uddirect = -1;
								}
								this.lrdirect = 0;
							} else if (this.uddirect != 0) {
								this.uddirect = 1;
								this.udswt = false;
							}
						}
					} else {
						this.trickfase = -1;
					}
					if (!this.afta) {
						this.afta = true;
					}
					if (this.trfix == 3) {
						this.trfix = 4;
						this.statusque += 30;
					}
				}
				if (this.trickfase == 1) {
					++this.flycnt;
					if (this.lrdirect != 0 && this.flycnt > this.lrstart) {
						if (this.lrswt && Math.abs(madness.xyRotation - this.oxy) > 180) {
							if (this.lrdirect == -1) {
								this.lrdirect = 1;
							} else {
								this.lrdirect = -1;
							}
							this.lrswt = false;
						}
						if (this.lrdirect == -1) {
							this.handb = true;
							this.left = true;
						} else {
							this.handb = true;
							this.right = true;
						}
					}
					if (this.uddirect != 0 && this.flycnt > this.udstart) {
						if (this.udswt && Math.abs(madness.zyRotation - this.ozy) > 180) {
							if (this.uddirect == -1) {
								this.uddirect = 1;
							} else {
								this.uddirect = -1;
							}
							this.udswt = false;
						}
						if (this.uddirect == -1) {
							this.handb = true;
							this.down = true;
						} else {
							this.handb = true;
							this.up = true;
						}
					}
					if ((madness.scy[0] + madness.scy[1] + madness.scy[2] + madness.scy[3]) * 100.0f / (geometry.y - 300) < -this.saftey) {
						this.onceu = false;
						this.onced = false;
						this.oncel = false;
						this.oncer = false;
						this.lrcomp = false;
						this.udcomp = false;
						this.udbare = false;
						this.lrbare = false;
						this.trickfase = 2;
						this.swat = 0;
					}
				}
				if (this.trickfase == 2) {
					if (this.swat == 0) {
						if (madness.dcomp != 0.0f || madness.ucomp != 0.0f) {
							this.udbare = true;
						}
						if (madness.lcomp != 0.0f || madness.rcomp != 0.0f) {
							this.lrbare = true;
						}
						this.swat = 1;
					}
					if (madness.wtouch) {
						if (this.swat == 1) {
							this.swat = 2;
						}
					} else if (this.swat == 2) {
						if (madness.capsized && this.medium.random() > this.mustland) {
							if (this.udbare) {
								this.lrbare = true;
								this.udbare = false;
							} else if (this.lrbare) {
								this.udbare = true;
								this.lrbare = false;
							}
						}
						this.swat = 3;
					}
					if (this.udbare) {
						int a;
						a = madness.zyRotation + 90;
						while (a < 0) {
							a += 360;
						}
						while (a > 180) {
							a -= 360;
						}
						final int abs = Math.abs(a);
						if (madness.lcomp - madness.rcomp < 5.0f && (this.onced || this.onceu)) {
							this.udcomp = true;
						}
						if (madness.dcomp > madness.ucomp) {
							if (madness.capsized) {
								if (this.udcomp) {
									if (abs > 90) {
										this.up = true;
									} else {
										this.down = true;
									}
								} else if (!this.onced) {
									this.down = true;
								}
							} else {
								if (this.udcomp) {
									if (this.perfection && Math.abs(abs - 90) > 30) {
										if (abs > 90) {
											this.up = true;
										} else {
											this.down = true;
										}
									}
								} else if (this.medium.random() > this.mustland) {
									this.up = true;
								}
								this.onced = true;
							}
						} else if (madness.capsized) {
							if (this.udcomp) {
								if (abs > 90) {
									this.up = true;
								} else {
									this.down = true;
								}
							} else if (!this.onceu) {
								this.up = true;
							}
						} else {
							if (this.udcomp) {
								if (this.perfection && Math.abs(abs - 90) > 30) {
									if (abs > 90) {
										this.up = true;
									} else {
										this.down = true;
									}
								}
							} else if (this.medium.random() > this.mustland) {
								this.down = true;
							}
							this.onceu = true;
						}
					}
					if (this.lrbare) {
						int a2 = madness.xyRotation + 90;
						if (this.zyinv) {
							a2 += 180;
						}
						while (a2 < 0) {
							a2 += 360;
						}
						while (a2 > 180) {
							a2 -= 360;
						}
						final int abs2 = Math.abs(a2);
						if (madness.lcomp - madness.rcomp < 10.0f && (this.oncel || this.oncer)) {
							this.lrcomp = true;
						}
						if (madness.lcomp > madness.rcomp) {
							if (madness.capsized) {
								if (this.lrcomp) {
									if (abs2 > 90) {
										this.left = true;
									} else {
										this.right = true;
									}
								} else if (!this.oncel) {
									this.left = true;
								}
							} else {
								if (this.lrcomp) {
									if (this.perfection && Math.abs(abs2 - 90) > 30) {
										if (abs2 > 90) {
											this.left = true;
										} else {
											this.right = true;
										}
									}
								} else if (this.medium.random() > this.mustland) {
									this.right = true;
								}
								this.oncel = true;
							}
						} else if (madness.capsized) {
							if (this.lrcomp) {
								if (abs2 > 90) {
									this.left = true;
								} else {
									this.right = true;
								}
							} else if (!this.oncer) {
								this.right = true;
							}
						} else {
							if (this.lrcomp) {
								if (this.perfection && Math.abs(abs2 - 90) > 30) {
									if (abs2 > 90) {
										this.left = true;
									} else {
										this.right = true;
									}
								}
							} else if (this.medium.random() > this.mustland) {
								this.left = true;
							}
							this.oncer = true;
						}
					}
				}
			}
		}
	}

	public void reset(final CheckPoints checkPoints) {
		this.pan = 0;
		this.attack = 0;
		this.acr = 0;
		this.afta = false;
		this.trfix = 0;
		this.acuracy = 0;
		this.upwait = 0;
		this.forget = false;
		this.bulistc = false;
		for (int i = 0; i < checkPoints.fn; ++i) {
			int py = -10;
			for (int j = 0; j < checkPoints.n; ++j) {
				if (this.py(checkPoints.fx[i] / 100, checkPoints.x[j] / 100, checkPoints.fz[i] / 100, checkPoints.z[j] / 100) < py || py == -10) {
					py = this.py(checkPoints.fx[i] / 100, checkPoints.x[j] / 100, checkPoints.fz[i] / 100, checkPoints.z[j] / 100);
					this.fpnt[i] = j;
				}
			}
		}
		for (int k = 0; k < checkPoints.fn; ++k) {
			final int[] fpnt = this.fpnt;
			fpnt[k] -= 4;
			if (this.fpnt[k] < 0) {
				this.fpnt[k] += checkPoints.nsp;
			}
		}
		this.resetControls();
	}

	public Control(final Medium medium) {
		this.left = false;
		this.right = false;
		this.up = false;
		this.down = false;
		this.handb = false;
		this.enter = false;
		this.arrace = false;
		this.music_muted = false;
		this.sound_muted = false;
		this.pan = 0;
		this.attack = 0;
		this.acr = 0;
		this.afta = false;
		this.fpnt = new int[5];
		this.trfix = 0;
		this.forget = false;
		this.bulistc = false;
		this.acuracy = 0;
		this.upwait = 0;
		this.agressed = false;
		this.skiplev = 1.0f;
		this.clrnce = 5;
		this.rampp = 0;
		this.turntyp = 0;
		this.saftey = 30;
		this.perfection = false;
		this.mustland = 0.5f;
		this.usebounce = false;
		this.trickprf = 0.5f;
		this.zyinv = false;
		this.lastl = false;
		this.wlastl = false;
		this.hold = 0;
		this.wall = -1;
		this.lwall = -1;
		this.stcnt = 0;
		this.statusque = 0;
		this.turncnt = 0;
		this.randtcnt = 0;
		this.upcnt = 0;
		this.trickfase = 0;
		this.swat = 0;
		this.udcomp = false;
		this.lrcomp = false;
		this.udbare = false;
		this.lrbare = false;
		this.onceu = false;
		this.onced = false;
		this.oncel = false;
		this.oncer = false;
		this.lrdirect = 0;
		this.uddirect = 0;
		this.lrstart = 0;
		this.udstart = 0;
		this.oxy = 0;
		this.ozy = 0;
		this.flycnt = 0;
		this.lrswt = false;
		this.udswt = false;
		this.actwait = 0;
		this.cntrn = 0;
		this.medium = medium;
	}

	public void resetControls() {
		this.left = false;
		this.right = false;
		this.up = false;
		this.down = false;
		this.handb = false;
		this.enter = false;
		this.arrace = false;
		this.music_muted = false;
		this.sound_muted = false;
	}

	public int py(final int n, final int n2, final int n3, final int n4) {
		return (n - n2) * (n - n2) + (n3 - n4) * (n3 - n4);
	}
}
