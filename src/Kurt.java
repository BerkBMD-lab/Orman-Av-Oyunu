import java.security.SecureRandom;

public class Kurt extends Hayvan {
	private String kurtSekli = """
         /\\      /\\
        /  \\____/  \\
       (   o    o   )
        \\    /\\    /
         \\   \\/   /
          \\ ____ /
           |_||_|
""";

	public Kurt(String ad, int yas) {
		super(ad, "Kurt", yas, 6, 80);
	}

	@Override
	public String sekilGoster() { return kurtSekli; }

	@Override
	public String saldir(Avci avci, SecureRandom random) {
		if (random.nextInt(100) < 50) {
			avci.canKaybet(1);
			return getHayvanAd() + " üzerine atlayıp seni ısırdı! -1 can";
		}
		return getHayvanAd() + " dişlerini gösterip hırlıyor.";
	}

	@Override
	public String sesCikar() { return getHayvanAd() + " uzun bir uluma bıraktı, ama kuyruğu sallanıyor."; }
}
