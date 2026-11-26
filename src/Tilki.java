import java.security.SecureRandom;

public class Tilki extends Hayvan {
	private String tilkiSekli = """
          /\\   /\\
         /  \\_/  \\
        ( o     o )
         \\   ^   /
          > --- <
         /       \\
""";

	public Tilki(String ad, int yas) {
		super(ad, "Tilki", yas, 5, 50);
	}

	@Override
	public String sekilGoster() { return tilkiSekli; }

	@Override
	public String saldir(Avci avci, SecureRandom random) {
		if (random.nextInt(100) < 40 && avci.getYem() > 0) {
			avci.yemKullan();
			return getHayvanAd() + " çantana daldı ve bir yem çaldı! -1 yem";
		}
		return getHayvanAd() + " etrafında sinsice dolaşıyor.";
	}

	@Override
	public String sesCikar() { return getHayvanAd() + " kıvrılıp kuyruğunu burnuna sardı."; }
}
