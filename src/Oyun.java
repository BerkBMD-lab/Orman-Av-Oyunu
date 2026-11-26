import java.security.SecureRandom;
import java.util.Scanner;

public class Oyun {
	private static final String CIZGI = "---------------------------------------------------";
	private static final int GUN_SAYISI = 10;
	private static final int GUNLUK_ENERJI = 2;

	private final Scanner input = new Scanner(System.in);
	private final SecureRandom random = new SecureRandom();
	private Avci avci;

	public void baslat() {
		System.out.println(CIZGI + "\n" + CIZGI);
		System.out.println("ORMAN MACERASI: Avcı & Hayvan Bakıcısı");
		System.out.println(CIZGI);
		System.out.print("Lütfen Avcı Adınızı Giriniz: ");
		String ad = oku();
		if (ad.isEmpty()) {
			ad = "Gezgin";
		}
		avci = new Avci(ad);
		kurallariGoster();

		int gun = 0;
		do {
			gun++;
			gunuOyna(gun);
			gunSonu();
		} while (avci.hayattaMi() && gun < GUN_SAYISI);

		oyunSonu();
	}

	private void kurallariGoster() {
		System.out.println(CIZGI);
		System.out.println(avci.getAvciAdi() + ", " + GUN_SAYISI + " gün boyunca hayvan yakalayıp çiftliğinde besleyeceksin.");
		System.out.println("- Her gün " + GUNLUK_ENERJI + " keşif hakkın var (pazar ve çiftlik ücretsiz).");
		System.out.println("- Yakalamak için: zar + hayvanın güveni + tuzak bonusu >= hayvanın zorluğu.");
		System.out.println("- Yem vererek güven kazan, her hayvanın kendine özgü bir tehlikesi var.");
		System.out.println("- Çiftlikteki hayvanlar her gün altın kazandırır, mutlu hayvan daha çok kazandırır.");
		System.out.println("- Canın biterse oyun biter. Dağ tehlikeli ama efsanevi Ejderha orada!");
	}

	private void gunuOyna(int gun) {
		int enerji = GUNLUK_ENERJI;
		boolean gunBitti = false;
		System.out.println("\n" + CIZGI + "\n" + CIZGI);
		System.out.println("GÜN " + gun + "/" + GUN_SAYISI);

		while (!gunBitti && enerji > 0 && avci.hayattaMi()) {
			System.out.println("\n" + CIZGI);
			avci.durumGoster();
			System.out.println("Kalan keşif hakkı: " + enerji);
			System.out.println("1) " + Bolge.ORMAN.getAd() + " - " + Bolge.ORMAN.getAciklama());
			System.out.println("2) " + Bolge.DAG.getAd() + " - " + Bolge.DAG.getAciklama());
			System.out.println("3) Pazar   4) Çiftlik   5) Günü bitir");
			System.out.print("Seçimin: ");

			switch (oku()) {
				case "1":
					karsilasma(Bolge.ORMAN);
					enerji--;
					break;
				case "2":
					karsilasma(Bolge.DAG);
					enerji--;
					break;
				case "3":
					pazar();
					break;
				case "4":
					ciftlik();
					break;
				case "5":
					gunBitti = true;
					break;
				default:
					System.out.println("Geçersiz seçim.");
			}
		}
	}

	private void karsilasma(Bolge bolge) {
		Hayvan hayvan = bolge.hayvanUret(random);
		System.out.println("\n" + CIZGI);
		System.out.println(bolge.getAd() + "'da bir " + hayvan.getHayvanTuru() + " ile karşılaştın!");
		System.out.println(hayvan.sekilGoster());

		boolean bitti = false;
		while (!bitti && avci.hayattaMi()) {
			hayvan.hayvanBilgiGoster();
			avci.durumGoster();
			System.out.println("t) Tuzak kur (zar at)  y) Yem ver  m) Merhamet dile  k) Kaç");
			System.out.print("Hamlen: ");

			boolean turHarcandi = true;
			switch (oku().toLowerCase()) {
				case "t":
					int zar = random.nextInt(6) + 1;
					int bonus = avci.tuzakBonusuKullan();
					int toplam = zar + hayvan.getGuven() + bonus;
					System.out.println("Zar: " + zar + " + güven: " + hayvan.getGuven() + " + tuzak: " + bonus
							+ " = " + toplam + " (gereken: " + hayvan.getZorluk() + ")");
					if (toplam >= hayvan.getZorluk()) {
						System.out.println(hayvan.getHayvanAd() + " yakalandı ve çiftliğine gönderildi!");
						avci.hayvanEkle(hayvan);
						bitti = true;
					} else {
						System.out.println("Tuzak boşa gitti.");
						if (zar == 1) {
							System.out.println("Berbat atış! Ayağın tuzağa takıldı. -1 can");
							avci.canKaybet(1);
						}
					}
					break;
				case "y":
					if (avci.getYem() > 0) {
						avci.yemKullan();
						hayvan.guvenArtir();
						System.out.println(hayvan.sesCikar());
						System.out.println("Güven arttı! (" + hayvan.getGuven() + "/3)");
					} else {
						System.out.println("Yemin kalmadı! Pazardan alabilirsin.");
						turHarcandi = false;
					}
					break;
				case "m":
					if (random.nextBoolean()) {
						int hediye = 5 + random.nextInt(11);
						System.out.println(hayvan.getHayvanAd() + " merhametine karşılık verip bir hediye bıraktı: " + hediye + " altın!");
						avci.altinKazan(hediye);
					} else {
						System.out.println(hayvan.getHayvanAd() + " merhametini umursamadı ve saldırdı! -1 can");
						avci.canKaybet(1);
					}
					bitti = true;
					turHarcandi = false;
					break;
				case "k":
					System.out.println("Yavaşça geri çekilip uzaklaştın.");
					bitti = true;
					turHarcandi = false;
					break;
				default:
					System.out.println("Geçersiz hamle.");
					turHarcandi = false;
			}

			// Hayvanın özel yeteneği (polimorfizm): her tür farklı davranır
			if (turHarcandi && !bitti && avci.hayattaMi()) {
				System.out.println(hayvan.saldir(avci, random));
				if (hayvan.isKacti()) {
					bitti = true;
				}
			}
			System.out.println();
		}
		if (!avci.hayattaMi()) {
			System.out.println("Yaralarına dayanamayıp yere yığıldın...");
		}
	}

	private void pazar() {
		boolean cik = false;
		while (!cik) {
			System.out.println("\n--- PAZAR --- (Altın: " + avci.getAltin() + ")");
			System.out.println("1) Yem (10 altın)");
			System.out.println("2) Şifa iksiri: +2 can (25 altın)");
			System.out.println("3) Güçlü tuzak: sonraki atışta +2 (20 altın)");
			System.out.println("0) Çık");
			System.out.print("Seçimin: ");
			switch (oku()) {
				case "1":
					if (avci.altinHarca(10)) { avci.yemAl(); System.out.println("Bir yem aldın."); }
					else { System.out.println("Yeterli altının yok."); }
					break;
				case "2":
					if (avci.altinHarca(25)) { avci.canKazan(2); System.out.println("İksiri içtin, kendini daha iyi hissediyorsun."); }
					else { System.out.println("Yeterli altının yok."); }
					break;
				case "3":
					if (avci.altinHarca(20)) { avci.gucluTuzakAl(); System.out.println("Güçlü tuzağı çantana koydun."); }
					else { System.out.println("Yeterli altının yok."); }
					break;
				case "0":
					cik = true;
					break;
				default:
					System.out.println("Geçersiz seçim.");
			}
		}
	}

	private void ciftlik() {
		boolean cik = false;
		while (!cik) {
			System.out.println("\n--- ÇİFTLİK ---");
			avci.ciftlikGoster();
			if (avci.getCiftlikBuyuklugu() == 0) {
				return;
			}
			System.out.println("Beslemek için hayvan numarası yaz (1 yem harcar), çıkmak için 0:");
			String secim = oku();
			if ("0".equals(secim)) {
				cik = true;
				continue;
			}
			try {
				int index = Integer.parseInt(secim) - 1;
				if (index < 0 || index >= avci.getCiftlikBuyuklugu()) {
					System.out.println("Böyle bir hayvan yok.");
				} else if (avci.getYem() == 0) {
					System.out.println("Yemin kalmadı! Pazardan alabilirsin.");
				} else if (avci.getHayvan(index).getMutluluk() >= 3) {
					System.out.println("Bu hayvan zaten çok mutlu.");
				} else {
					Hayvan h = avci.getHayvan(index);
					avci.yemKullan();
					h.mutlulukArtir();
					System.out.println(h.sesCikar());
					System.out.println(h.getHayvanAd() + " mutluluğu: " + h.getMutluluk() + "/3");
				}
			} catch (NumberFormatException e) {
				System.out.println("Lütfen bir sayı gir.");
			}
		}
	}

	private void gunSonu() {
		if (!avci.hayattaMi()) {
			return;
		}
		int gelir = avci.gunlukGelirHesapla();
		System.out.println("\nGün bitti. Çiftlikten " + gelir + " altın kazandın.");
		avci.altinKazan(gelir);
	}

	private void oyunSonu() {
		System.out.println("\n" + CIZGI + "\n" + CIZGI);
		if (avci.hayattaMi()) {
			System.out.println("Macera sona erdi, sağ salim eve döndün!");
		} else {
			System.out.println("Canın bitti, macera erken sona erdi...");
		}
		avci.durumGoster();
		avci.ciftlikGoster();
		int skor = avci.skorHesapla();
		String unvan = skor >= 400 ? "Efsane Avcı" : skor >= 200 ? "Usta Bakıcı" : "Çaylak";
		System.out.println("Toplam skor: " + skor + " | Ünvan: " + unvan);
		System.out.println(CIZGI);
	}

	private String oku() {
		if (input.hasNextLine()) {
			return input.nextLine().trim();
		}
		System.out.println("\nGirdi bitti, oyun kapatılıyor.");
		System.exit(0);
		return "";
	}
}
