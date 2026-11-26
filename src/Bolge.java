import java.security.SecureRandom;

public enum Bolge {
	ORMAN("Orman", "Sakin ve yeşil; küçük hayvanlar yaşar."),
	DAG("Dağ", "Tehlikeli ama ödülü büyük; efsanevi bir yaratık görülebilir.");

	private static final String[] ISIMLER = {"Pamuk", "Fındık", "Zıpzıp", "Karamel", "Boncuk", "Çıtır", "Gölge", "Duman"};

	private final String ad;
	private final String aciklama;

	Bolge(String ad, String aciklama) {
		this.ad = ad;
		this.aciklama = aciklama;
	}

	public String getAd() { return ad; }
	public String getAciklama() { return aciklama; }

	// Bölgeye göre rastgele hayvan üretir
	public Hayvan hayvanUret(SecureRandom random) {
		String isim = ISIMLER[random.nextInt(ISIMLER.length)];
		int yas = random.nextInt(8) + 1;
		int sans = random.nextInt(100);

		if (this == ORMAN) {
			if (sans < 40) return new Tavsan(isim, yas);
			if (sans < 80) return new Sincap(isim, yas);
			return new Tilki(isim, yas);
		}
		if (sans < 5) return new Ejderha("Alev", 300 + random.nextInt(700));
		if (sans < 50) return new Tilki(isim, yas);
		return new Kurt(isim, yas);
	}
}
