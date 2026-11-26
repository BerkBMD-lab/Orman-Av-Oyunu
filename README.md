# Orman Macerası

Java'yı öğrenme sürecinde geliştirdiğim, avcılık, çiftlik yönetimi ve 10 günlük ilerleme döngüsünü bir araya getiren konsol tabanlı metin oyunu.

> **Not:** Bu proje Java öğrenme aşamasında kendi çabamla ortaya çıkardığım bir başlangıç çalışmasıdır. Kod yapısında ve mekaniklerde zamanla iyileştirmeler yapmayı sürdürüyorum.

## Oyun Mantığı

Oyunda bir avcı olarak ormanda karşına çıkan hayvanları yakalayıp çiftliğine götürürsün:

* **Yakala ve Çiftliğe Taşı:** Önüne çıkan hayvanları yakalamaya çalışırsın; yakaladığın hayvanlar çiftliğine geçer.
* **Şansını Artır:** Hayvanlara **yem vererek** veya **tuzak kurarak** yakalama olasılığını yükseltebilirsin.
* **Pasif Günlük Gelir:** Çiftliğine getirdiğin hayvanlar sana her gün düzenli pasif gelir kazandırır.
* **10 Günlük Hazırlık ve Final:** 10 gün boyunca hayvan toplayıp gelir elde ederek güçlenir, 10. günün sonunda güçlü düşmana karşı savaşırsın.

## Başlangıç ve Çalıştırma

Oyuna başlangıç noktası **`OrmanMacerasiTest.java`** dosyasıdır.

1. Depoyu bilgisayarına indir veya klonla:
   ```bash
   git clone https://github.com/BerkBmD-lab/ilk.git
   cd ilk