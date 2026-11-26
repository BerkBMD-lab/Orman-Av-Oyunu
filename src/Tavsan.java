import java.security.SecureRandom;

public class Tavsan extends Hayvan {
	private String tavsanSekli = """
            ,\\
            \\\\\\,_
             \\` ,\\
        __,.-" =__)
      ."        )
   ,_/   ,    \\/\\_
   \\_|    )_\\-\\ \\_-`
       `-----` `--`
""";

	public Tavsan(String ad, int yas) {
		super(ad, "Tavşan", yas, 3, 20);
	}

	@Override
	public String sekilGoster() { return tavsanSekli; }

	@Override
	public String saldir(Avci avci, SecureRandom random) {
		if (random.nextInt(100) < 35) {
			setKacti(true);
			return getHayvanAd() + " hızla zıplayıp çalılıkların arasında kayboldu!";
		}
		return getHayvanAd() + " yerinde huzursuzca zıplıyor.";
	}

	@Override
	public String sesCikar() { return getHayvanAd() + " burnunu oynatıp havuç kokusunu içine çekti."; }
}
