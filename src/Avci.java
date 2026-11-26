import java.util.ArrayList;

public class Avci {
	private static final int MAX_CAN = 5;

	private String avciAdi;
	private int can = MAX_CAN;
	private int altin = 15;
	private int yem = 2;
	private boolean gucluTuzak = false;
	private ArrayList<Hayvan> ciftlik = new ArrayList<>();

	public Avci(String avciAdi) {
		this.avciAdi = avciAdi;
	}

	public void hayvanEkle(Hayvan hayvan) { ciftlik.add(hayvan); }

	public void canKaybet(int miktar) { can = Math.max(0, can - miktar); }
	public void canKazan(int miktar) { can = Math.min(MAX_CAN, can + miktar); }
	public boolean hayattaMi() { return can > 0; }

	public void altinKazan(int miktar) { altin += miktar; }
	public boolean altinHarca(int miktar) {
		if (altin >= miktar) {
			altin -= miktar;
			return true;
		}
		return false;
	}

	public void yemAl() { yem++; }
	public void yemKullan() { if (yem > 0) yem--; }

	public void gucluTuzakAl() { gucluTuzak = true; }
	public int tuzakBonusuKullan() {
		if (gucluTuzak) {
			gucluTuzak = false;
			return 2;
		}
		return 0;
	}

	public int gunlukGelirHesapla() {
		int toplam = 0;
		for (Hayvan h : ciftlik) {
			toplam += h.gunlukGelir();
		}
		return toplam;
	}

	public int skorHesapla() {
		int skor = altin;
		for (Hayvan h : ciftlik) {
			skor += h.getDeger() + h.getMutluluk() * 10;
		}
		return skor;
	}

	public void durumGoster() {
		System.out.println(avciAdi + " | Can: " + "♥".repeat(can) + "♡".repeat(MAX_CAN - can)
				+ " | Altın: " + altin + " | Yem: " + yem
				+ " | Güçlü tuzak: " + (gucluTuzak ? "var" : "yok"));
	}

	public void ciftlikGoster() {
		if (ciftlik.isEmpty()) {
			System.out.println("Çiftlikte henüz hayvan yok.");
			return;
		}
		System.out.println("Çiftlik (günlük gelir: " + gunlukGelirHesapla() + " altın):");
		for (int i = 0; i < ciftlik.size(); i++) {
			Hayvan h = ciftlik.get(i);
			System.out.println((i + 1) + ") " + h.getHayvanAd() + " (" + h.getHayvanTuru() + ", " + h.getHayvanYas()
					+ " yaşında) | Mutluluk: " + h.getMutluluk() + "/3 | Gelir: " + h.gunlukGelir());
		}
	}

	public String getAvciAdi() { return avciAdi; }
	public void setAvciAdi(String avciAdi) { this.avciAdi = avciAdi; }
	public int getCan() { return can; }
	public int getAltin() { return altin; }
	public int getYem() { return yem; }
	public int getCiftlikBuyuklugu() { return ciftlik.size(); }
	public Hayvan getHayvan(int index) { return ciftlik.get(index); }
}
