import java.security.SecureRandom;

public class Ejderha extends Hayvan {
	private String ejderhaSekli = """
              __====-_  _-====__
          _--^^^#####//      \\\\#####^^^--_
       _-^##########// (    ) \\\\##########^-_
      -############//  |\\^^/|  \\\\############-
    _/############//   (@::@)   \\\\############\\_
   /#############((     \\\\//     ))#############\\
  -###############\\\\    (oo)    //###############-
""";

	public Ejderha(String ad, int yas) {
		super(ad, "Ejderha (EFSANEVİ)", yas, 8, 250);
	}

	@Override
	public String sekilGoster() { return ejderhaSekli; }

	@Override
	public String saldir(Avci avci, SecureRandom random) {
		if (random.nextInt(100) < 50) {
			avci.canKaybet(2);
			return getHayvanAd() + " alev püskürttü! -2 can";
		}
		return getHayvanAd() + " burnundan dumanlar çıkarıp seni tartıyor.";
	}

	@Override
	public int gunlukGelir() { return super.gunlukGelir() + 10; }

	@Override
	public String sesCikar() { return getHayvanAd() + " derin bir gürleme ile ısındı; yer titredi."; }
}
