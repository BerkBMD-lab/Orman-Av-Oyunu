import java.security.SecureRandom;

public abstract class Hayvan implements Evcillesebilir {
	private String hayvanAd;
	private String hayvanTuru;
	private int hayvanYas;
	private int zorluk;      // Yakalamak için gereken toplam (zar + güven + tuzak)
	private int deger;       // Altın değeri
	private int guven = 0;   // 0-3, yem verdikçe artar
	private int mutluluk = 0; // 0-3, çiftlikte beslendikçe artar
	private boolean kacti = false;

	public Hayvan(String hayvanAd, String hayvanTuru, int hayvanYas, int zorluk, int deger) {
		this.hayvanAd = hayvanAd;
		this.hayvanTuru = hayvanTuru;
		this.hayvanYas = hayvanYas;
		this.zorluk = zorluk;
		this.deger = deger;
	}

	// Her tür kendi şeklini ve saldırı tarzını belirler (polimorfizm)
	public abstract String sekilGoster();
	public abstract String saldir(Avci avci, SecureRandom random);

	@Override
	public int gunlukGelir() {
		return (deger / 10) * (1 + mutluluk);
	}

	public void hayvanBilgiGoster() {
		System.out.println("Hayvan adı: " + hayvanAd);
		System.out.println("Hayvan türü: " + hayvanTuru);
		System.out.println("Hayvan yaşı: " + hayvanYas);
		System.out.println("Yakalamak için gereken: " + zorluk + " (zar + güven + tuzak)");
		System.out.println("Güven: " + guven + "/3");
	}

	public void guvenArtir() { if (guven < 3) guven++; }
	public void mutlulukArtir() { if (mutluluk < 3) mutluluk++; }

	public void setHayvanAd(String hayvanAd) { this.hayvanAd = hayvanAd; }
	public void setHayvanTuru(String hayvanTuru) { this.hayvanTuru = hayvanTuru; }
	public void setHayvanYas(int hayvanYas) { this.hayvanYas = hayvanYas; }
	public void setKacti(boolean kacti) { this.kacti = kacti; }

	public String getHayvanAd() { return hayvanAd; }
	public String getHayvanTuru() { return hayvanTuru; }
	public int getHayvanYas() { return hayvanYas; }
	public int getZorluk() { return zorluk; }
	public int getDeger() { return deger; }
	public int getGuven() { return guven; }
	public int getMutluluk() { return mutluluk; }
	public boolean isKacti() { return kacti; }
}
