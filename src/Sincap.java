import java.security.SecureRandom;

public class Sincap extends Hayvan {
	private String sincapSekli = """
            (\\__/)  .~    ~. ))
            /O O  ./      .'
           {O__,   \\    {
             / .  . )    \\
             |-| '-' \\    }
            .(   _(   )_.'
           '---.~_ _ _&
""";

	public Sincap(String ad, int yas) {
		super(ad, "Sincap", yas, 4, 30);
	}

	@Override
	public String sekilGoster() { return sincapSekli; }

	@Override
	public String saldir(Avci avci, SecureRandom random) {
		if (random.nextInt(100) < 30) {
			avci.canKaybet(1);
			return getHayvanAd() + " kafana sert bir fındık fırlattı! -1 can";
		}
		return getHayvanAd() + " dalın üstünden seni süzüyor.";
	}

	@Override
	public String sesCikar() { return getHayvanAd() + " fındığı kemirirken mutlu mutlu cik cik etti."; }
}
