# supportportalAPI- Kullanıcı Yönetim Sistemi
Kullanıcı tanımlama ve yetkilendirme portalı(User Management System)
#Kullanıcı tanımlama
Json formatında objemizin içerisini doldurup kayıt işlemini yapıyoruz
ResponseEntity ile 201 değerini alıyoruz ,, işlemimiz başarı ile gerçekleşmiştir.
![image](https://user-images.githubusercontent.com/45200802/190636162-2bd472d0-9ea4-42e6-be45-a7ebb35248da.png)
#User kayıt ve login TEST işlemleri
kaydetmiş olduğumuz kullanıcımızın otomatik oluşan passwordu ile giriş işlemi yapıyoruz giriş yapınca jwt token oluşturuluyor. ve giriş başarı ile gerçekleşiyor

![image](https://user-images.githubusercontent.com/45200802/191742442-fa6800ec-d50f-41ab-9dc1-6e55334551c1.png)

token bilgisinin oluştuğunu headers kısmında görebiliyoruz 
![image](https://user-images.githubusercontent.com/45200802/191743692-49b09fac-a615-4146-baa7-8f0881724cb7.png)

görülen jwt tokeni jwt.io adresinden inceleyebiliriz.. vermiş olduğumuz değerler karşımıza çıkacaktır.
aşağıdaki ekranda da görüldüğü gibi payload kısmında token oluştururken girdiğimiz değerler gözükacektir
aşağıda signature verify işlemi de test edildi ve token düzgün şekilde oluştuğu görüldü
![image](https://user-images.githubusercontent.com/45200802/191744743-bd0cc0dc-ed97-4472-913e-8ceb61d34ff4.png)
# Brute Force Attack işlemleri yapıldı ve test edildi
kullanıcı adını veya şifresini yanlış girme hakkımız 5 tanedir,fazla girdiğimiz anda LOCK işlemi yapılmakta olup
admin yetkisi ile LOCK durumunu değiştirmemiz gerekmektedir.

![image](https://user-images.githubusercontent.com/45200802/193225007-999382cf-789a-4fa9-9159-2a118b455ba2.png)

5 tane başarısız deneme takip edilir ve sonuçta aşağıdaki gibi hata mesajı dönülür.

![image](https://user-images.githubusercontent.com/45200802/193225206-03e734a1-d36c-40f5-b024-0c4f944af85f.png)









