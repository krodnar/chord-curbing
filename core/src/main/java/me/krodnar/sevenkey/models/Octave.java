package me.krodnar.sevenkey.models;

public enum Octave {

	CM(-1, 0, 11),
	C0(0, 12, 23),
	C1(1, 24, 35),
	C2(2, 36, 47),
	C3(3, 48, 59),
	C4(4, 60, 71),
	C5(5, 72, 83),
	C6(6, 84, 95),
	C7(7, 96, 107),
	C8(8, 108, 119),
	C9(9, 120, 131);

	private int index;
	private int startIndex;
	private int endIndex;

	Octave(int index, int startIndex, int endIndex) {
		this.index = index;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}

	public static Octave getByIndex(int index) {
		return values()[index];
	}

	public int getIndex() {
		return index;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public String getNaming() {
		return "C" + index;
	}
}
