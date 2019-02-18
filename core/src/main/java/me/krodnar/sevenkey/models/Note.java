package me.krodnar.sevenkey.models;

import java.util.Random;

public enum Note {

	//region notes
	C1M(0, "C", Octave.CM),
	CS1M(1, "C#", Octave.CM),
	D1M(2, "D", Octave.CM),
	DS1M(3, "D#", Octave.CM),
	E1M(4, "E", Octave.CM),
	F1M(5, "F", Octave.CM),
	FS1M(6, "F#", Octave.CM),
	G1M(7, "G", Octave.CM),
	GS1M(8, "G#", Octave.CM),
	A1M(9, "A", Octave.CM),
	AS1M(10, "A#", Octave.CM),
	B1M(11, "B", Octave.CM),

	C0(12, "C", Octave.C0),
	CS0(13, "C#", Octave.C0),
	D0(14, "D", Octave.C0),
	DS0(15, "D#", Octave.C0),
	E0(16, "E", Octave.C0),
	F0(17, "F", Octave.C0),
	FS0(18, "F#", Octave.C0),
	G0(19, "G", Octave.C0),
	GS0(20, "G#", Octave.C0),
	A0(21, "A", Octave.C0),
	AS0(22, "A#", Octave.C0),
	B0(23, "B", Octave.C0),

	C1(24, "C", Octave.C1),
	CS1(25, "C#", Octave.C1),
	D1(26, "D", Octave.C1),
	DS1(27, "D#", Octave.C1),
	E1(28, "E", Octave.C1),
	F1(29, "F", Octave.C1),
	FS1(30, "F#", Octave.C1),
	G1(31, "G", Octave.C1),
	GS1(32, "G#", Octave.C1),
	A1(33, "A", Octave.C1),
	AS1(34, "A#", Octave.C1),
	B1(35, "B", Octave.C1),

	C2(36, "C", Octave.C2),
	CS2(37, "C#", Octave.C2),
	D2(38, "D", Octave.C2),
	DS2(39, "D#", Octave.C2),
	E2(40, "E", Octave.C2),
	F2(41, "F", Octave.C2),
	FS2(42, "F#", Octave.C2),
	G2(43, "G", Octave.C2),
	GS2(44, "G#", Octave.C2),
	A2(45, "A", Octave.C2),
	AS2(46, "A#", Octave.C2),
	B2(47, "B", Octave.C2),

	C3(48, "C", Octave.C3),
	CS3(49, "C#", Octave.C3),
	D3(50, "D", Octave.C3),
	DS3(51, "D#", Octave.C3),
	E3(52, "E", Octave.C3),
	F3(53, "F", Octave.C3),
	FS3(54, "F#", Octave.C3),
	G3(55, "G", Octave.C3),
	GS3(56, "G#", Octave.C3),
	A3(57, "A", Octave.C3),
	AS3(58, "A#", Octave.C3),
	B3(59, "B", Octave.C3),

	C4(60, "C", Octave.C4),
	CS4(61, "C#", Octave.C4),
	D4(62, "D", Octave.C4),
	DS4(63, "D#", Octave.C4),
	E4(64, "E", Octave.C4),
	F4(65, "F", Octave.C4),
	FS4(66, "F#", Octave.C4),
	G4(67, "G", Octave.C4),
	GS4(68, "G#", Octave.C4),
	A4(69, "A", Octave.C4),
	AS4(70, "A#", Octave.C4),
	B4(71, "B", Octave.C4),

	C5(72, "C", Octave.C5),
	CS5(73, "C#", Octave.C5),
	D5(74, "D", Octave.C5),
	DS5(75, "D#", Octave.C5),
	E5(76, "E", Octave.C5),
	F5(77, "F", Octave.C5),
	FS5(78, "F#", Octave.C5),
	G5(79, "G", Octave.C5),
	GS5(80, "G#", Octave.C5),
	A5(81, "A", Octave.C5),
	AS5(82, "A#", Octave.C5),
	B5(83, "B", Octave.C5),

	C6(84, "C", Octave.C6),
	CS6(85, "C#", Octave.C6),
	D6(86, "D", Octave.C6),
	DS6(87, "D#", Octave.C6),
	E6(88, "E", Octave.C6),
	F6(89, "F", Octave.C6),
	FS6(90, "F#", Octave.C6),
	G6(91, "G", Octave.C6),
	GS6(92, "G#", Octave.C6),
	A6(93, "A", Octave.C6),
	AS6(94, "A#", Octave.C6),
	B6(95, "B", Octave.C6),

	C7(96, "C", Octave.C7),
	CS7(97, "C#", Octave.C7),
	D7(98, "D", Octave.C7),
	DS7(99, "D#", Octave.C7),
	E7(100, "E", Octave.C7),
	F7(101, "F", Octave.C7),
	FS7(102, "F#", Octave.C7),
	G7(103, "G", Octave.C7),
	GS7(104, "G#", Octave.C7),
	A7(105, "A", Octave.C7),
	AS7(106, "A#", Octave.C7),
	B7(107, "B", Octave.C7),

	C8(108, "C", Octave.C8),
	CS8(109, "C#", Octave.C8),
	D8(110, "D", Octave.C8),
	DS8(111, "D#", Octave.C8),
	E8(112, "E", Octave.C8),
	F8(113, "F", Octave.C8),
	FS8(114, "F#", Octave.C8),
	G8(115, "G", Octave.C8),
	GS8(116, "G#", Octave.C8),
	A8(117, "A", Octave.C8),
	AS8(118, "A#", Octave.C8),
	B8(119, "B", Octave.C8),

	C9(120, "C", Octave.C9),
	CS9(121, "C#", Octave.C9),
	D9(122, "D", Octave.C9),
	DS9(123, "D#", Octave.C9),
	E9(124, "E", Octave.C9),
	F9(125, "F", Octave.C9),
	FS9(126, "F#", Octave.C9),
	G9(127, "G", Octave.C9),
	GS9(128, "G#", Octave.C9),
	A9(129, "A", Octave.C9),
	AS9(130, "A#", Octave.C9),
	B9(131, "B", Octave.C9);
	//endregion

	private int index;
	private String notation;
	private Octave octave;

	private static final Random RANDOM = new Random();

	Note(int index, String notation, Octave octave) {
		this.index = index;
		this.notation = notation;
		this.octave = octave;
	}

	public static Note getRandom() {
		return values()[RANDOM.nextInt(values().length)];
	}

	public static Note getRandomInRange(int minIndex, int maxIndex) {
		if (minIndex < 0 || maxIndex > 132) {
			throw new IllegalArgumentException("Indexes should be in proper range.");
		}

		return values()[RANDOM.nextInt(maxIndex - minIndex + 1) + minIndex];
	}

	public static Note getRandomInRange(Octave octave) {
		return getRandomInRange(octave.getStartIndex(), octave.getEndIndex());
	}

	public static Note getRandomInRange(Octave minOctave, Octave maxOctave) {
		return minOctave == maxOctave ?
				getRandomInRange(minOctave) :
				getRandomInRange(minOctave.getStartIndex(), maxOctave.getEndIndex());
	}

	public static Note getByIndex(int index) {
		return values()[index];
	}

	public int getIndex() {
		return index;
	}

	public String getNotation() {
		return notation;
	}

	public Octave getOctave() {
		return octave;
	}

	public NotePosition getPosition() {
		return NotePosition.values()[index % 12];
	}

	public String getNaming() {
		return notation + octave.getIndex();
	}

	@Override
	public String toString() {
		return getNaming();
	}
}
