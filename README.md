# Mobilní aplikace eShop
Mobilní aplikace obsahuje možnost přidávat zboží do kategorií, registrovat uživatele a poskytovat údaje o stavu zboží a další funkce. Aplikace je zpracována v prostředí Android Studio a Visual Studio Code s pomocí jazyků Java, JavaScript a MySQL.

## Obsah
1. [Analýza požadavků](#Analýza-požadavků)
    1. [Funkční požadavky](#Funkční-požadavky)
    2. [Nefunkční požadavky](#Nefunkční-požadavky)
    3. [Použité technologie](#Použité-technologie)
2. [Návrh databáze](#Návrh-databáze)
3. [Uživatelské rozhraní](#Uživatelské-rozhraní)
4. [Implementace](#Implementace)
    1. [Vytvoření databáze](#Vytvoření-databáze)
    2. [Vytvoření skriptu](#Vytvoření-skriptu)
    3. [Konfiguraci serveru](#Konfiguraci-serveru)
    4. [Mobilní aplikace Manifest](#Mobilní-aplikace-Manifest)
    5. [Mobilní aplikace Java](#Mobilní-aplikace-Java)
    
    
##	Analýza požadavků
###	Funkční požadavky
*Vytvořit registrační formulář uživatele, který bude obsahovat uživatelské jméno, e-mail a heslo.*

*Vytvořit formulář pro autorizaci a přihlášení klienta pomocí e-mailu a hesla.*

*Vytvořit formulář pro obnovení hesla, který odešle kód na e-mail klienta.*

*Vytvořit navigační panel, který obsahuje obrázek uživatele, jméno, poštovní adresu, kategorii kosmetiky a oblečení, sledování objednávek, uživatelský účet a seznam přání.*

*Vytvořit hlavní stránku, která bude obsahovat název aplikace, nákupní košík, vyhledávací lištu produktů, navigační menu, bannery, který ukazuje, co je nového, seznam produktů a seznam nejlépe hodnocených produktů. Níže se také zobrazí poznámka o bezplatném vrácení do 120 dnů a platbě na dobírku.*

*Vytvořit produktovou sekci obsahující fotografii, název, přidání do košíku a rychlý nákup, recenze produktu.*

*Sekci nákupního košíku, která bude zobrazovat seznam přidaných produktů.*

*Sekci seznamu přání pro klienta.*

*Sekci sledování produktu, která bude zobrazovat informace o produktu, sledovací číslo, datum, směr odeslání a stav objednávky.*

*Vytvořte sekci účtu, která bude obsahovat seznam nákupů zákazníka, seznam přání, jazyk aplikace, často kladené otázky nebo FAQ, odeslání odkazu na aplikaci přátelům, hodnocení aplikace a změnu hesla.*

*Otestovat aplikaci na spotřebu paměti a využití procesoru.*

*Otestovat na selhání a chyby.*

*Otestovat na různých virtuálních a skutečných zařízeních.*

###	Nefunkční požadavky
*Vytvořit jednoduché a srozumitelné rozhraní.*

*Pro návrh aplikace použit modrý motiv.*

*Vytvořit logo aplikace „eShop“.*

*Vytvořit obrázky nových produktů pro banner na hlavní stránce.*

*Vytvořit obrázek a popis produktů.*

*Možnost lokalizace aplikace do češtiny a angličtiny.*

### Použité technologie
Použil jsem nástroj XAMPP k vytvoření databáze MySQL a Node.js k aktivaci serveru. Níže jsou uvedeny knihovny, které byly použity při programování prostředníka:
-	Express – je potřeba k přijímání a zpracovávání požadavků aplikace a odesílání odpovědí na ně.
-	Multer – slouží k nahrávání souborů.
-	Bcrypt – pro bezpečné uložení hesel v databázi.
-	JSON Web Token – slouží k šifrování a přenosu dat oprávněných uživatelů pomocí tokenů.
-	Nodemailer – slouží k zaslání potvrzovacího kódu na e-mail uživatele, pomocí kterého může obnovit heslo.

Při vytváření mobilní aplikace byly použity následující knihovny:
-	Paging je stránkovací knihovna, která pomáhá načítat a zobrazovat malé části dat najednou.
-	Glide – používá se pro správu médií a načítání obrázků.
-	Retrofit – usnadňuje získání a načtení JSON prostřednictvím webové služby založené na REST. S ním aplikace odešle HTTP požadavek.
-	GSON – knihovna od Google pro převod objektů JSON na objekty Java a naopak.

##	Návrh databáze
![image](https://user-images.githubusercontent.com/39945830/177019221-33a267b0-1de7-4f41-b6e0-96384fc4a322.png)
Na obrázku je vidět návrh databáze mobilní aplikace, která je vyrobena v programu „DataGrip“. Všechny tabulky spojují uživatele s produktem. V tabulce košíku jsou uloženy všechny produkty uživatele, které do ní naopak přidal. Historie obsahuje historii zákaznických objednávek. Položky, které zákazník označí jako oblíbené, budou uloženy v tabulce Favorite. Pokud klient chce napsat recenzi, pak budou všechny informace uloženy v tabulce Review, která má hlavní vlastnosti zpětná vazba, hodnocení a datum recenze. Tabulka objednávek obsahuje informace o objednávce (číslo objednávky, datum objednávky, stav, název a číslo bankovní karty, datum expirace karty). Ochrana uživatelských dat je jednou z nejdůležitějších součástí vývoje aplikací. Proto hesla a počet bankovních karet uživatele budou zašifrovány. A poslední tabulka dopravy obsahuje informace o doručovací adrese.

## Uživatelské rozhraní
Uživatelské rozhraní se skládá z hlavní stránky, přihlášení, registrace, vyhledávání produktů, nákupního košíku, formuláře zpětné vazby, stránky produktu atd.

### Přihlášení & Registrace & Obnovení účtu
![image](https://user-images.githubusercontent.com/39945830/177020336-a211fb90-e015-4710-9992-bbb73b57f402.png)
![image](https://user-images.githubusercontent.com/39945830/177020352-fde255b1-30d8-4cf6-8e37-6ad8b79d61d7.png)
![image](https://user-images.githubusercontent.com/39945830/177020358-8ea96999-5ccd-4703-ba37-1bfa00c7b5d7.png)

### Hlavní stránka & Navigační menu & Stránka objednávky
![image](https://user-images.githubusercontent.com/39945830/177020398-8d1f1e40-5e1c-466c-b8c3-b79abfbd75f9.png)
![image](https://user-images.githubusercontent.com/39945830/177020417-f98fcafa-56d1-43ee-bd2b-8f5e0307bf3c.png)
![image](https://user-images.githubusercontent.com/39945830/177020419-fa0ca334-16b5-4943-8ec9-29182d3545ec.png)

### Stránka účet & Seznam produktů & Stránka produktu 
![image](https://user-images.githubusercontent.com/39945830/177020504-ef6cf1a1-b100-4b62-9662-106099555c98.png)
![image](https://user-images.githubusercontent.com/39945830/177020469-eebe3747-25f9-420d-9fe7-03df924939a5.png)
![image](https://user-images.githubusercontent.com/39945830/177020473-a74e7cd9-07d7-459e-a559-e89aa181f4f4.png)
![image](https://user-images.githubusercontent.com/39945830/177020494-fcdcd414-c8d3-4234-9cbe-5cc42ec0195b.png)

### Stránka recenze & Stránka napsání recenze & Stránka košíku 
![image](https://user-images.githubusercontent.com/39945830/177020533-cad8e4be-8863-41fd-8b37-126327eabfe6.png)
![image](https://user-images.githubusercontent.com/39945830/177020541-8ea6d55a-f0e0-4d46-bc28-91b9e187414e.png)
![image](https://user-images.githubusercontent.com/39945830/177020551-47dccc86-6912-4809-89c9-f29034b92c8e.png)

### Vyhledávací stránka & Stránka přidaní produktu
![image](https://user-images.githubusercontent.com/39945830/177020564-ae9b4e2b-f9cd-402f-a905-201ca8eee4d5.png)
![image](https://user-images.githubusercontent.com/39945830/177020580-a3850af3-fa04-4202-9a4f-fde786b5ec6f.png)
![image](https://user-images.githubusercontent.com/39945830/177020573-e1617dc6-5431-41ff-9653-ba4514be09df.png)

## Implementace
### Vytvoření databáze
Databáze byla vytvořena pomocí SQL dotazu `CREATE DATABASE eshop`, což znamená vytvoření databáze s názvem eshop. 

Samotné tabulky byly vytvořeny v databázi pomocí dotazu `CREATE TABLE "název tabulky"`.

Práce se záznamy v tabulce probíhala ve skriptu. K načtení záznamů jsem použil dotaz `SELECT "název sloupce" FROM "název tabulky"`. Co se týče přidávání záznamů, byl použit dotaz `INSERT INTO "název tabulky“ VALUES („záznamy“)`.

### Vytvoření skriptu
Prvním krokem bylo vytvoření HTTP serveru. Ve skriptu `server.js` byl ke spuštění serveru použit následující kód:

```JS
const http = require('http')

const appPort = 8080
app.set('port', appPort)

http.createServer(app).listen(appPort, () =>
    console.log(`localhost:${appPort}`))
```
Metoda `http.createServer` "promění" počítač na HTTP server. Metoda app umožňuje přidávat komponenty navržené pro zpracování různých požadavků. Listen předává číslo portu, na kterém běží server. 


Skript `config.js` ukazuje, že připojení je vytvořeno pomocí hostitele, přihlašovacího jména, hesla a samotné databáze.

Uživatelský skript provádí registraci a autorizaci klienta porovnáním dvou zašifrovaných hesel z databáze a aplikace. Hesla jsou šifrována ve skriptu `encrypt.js`. V uživatelském skriptu je také generován speciální kód pro obnovu hesla. Tento kód a e-mail klienta budou odeslány skriptu `mail.js`. Dále pomocí knihovny nodemailer bude kód odeslán na e-mail klienta. V produktu je funkcí ukládání obrázků produktů do složky storage_product.


###	Konfiguraci serveru
Instalace serveru se provádí pomocí příkazu: `sudo apt install mysql-server`.

Dále je pro spuštění serveru třeba zadat příkaz `sudo service mysql start`.

Dalším krokem je instalace Node.js. K tomu je třeba přejít na příkazový řádek a zadat: `sudo apt install nodejs`.

Dalším krokem je instalace balíčků Node.js. Instalace byla provedena příkazem: `sudo npm install`.

Posledním krokem bylo spuštění skriptovacího serveru. Spuštění bylo provedeno příkazem: `sudo npm start`.

### Mobilní aplikace Manifest
Složka manifests obsahuje pouze jeden soubor `AndroidManifest.xml`. Tento soubor definuje důležité informace o aplikaci – název, verzi, ikony, jaká oprávnění aplikace používá, registruje všechny používané třídy aktivit, služby atd. Autor definoval SplashActivity jako spouštěcí aktivitu, která kontroluje aplikaci na autorizaci klienta. Pokud ne, pak aplikace otevře přihlašovací stránku, pokud ano, klient přejde na hlavní stránku. V manifestu souboru byla také nastavena oprávnění pro přístup k internetu, pro zápis a čtení souboru do paměti zařízení a do fotoaparátu zařízení

### Mobilní aplikace Java
Složka java obsahuje zdrojový kód aplikace.

První složka `adapter` slouží k práci se seznamy. Soubory zde jsou potřebné k zobrazení seznamů nákupních košíků, FAQ, objednávek, produktů, recenzí, vyhledávání, přání a hledaných výrazů. Také se v této složce používá k přidání nebo odebrání něčeho ze seznamu. Například v seznamu nákupního košíku je tlačítko přidat do seznamu přání.

Druhá složka je `model`. Tato složka je určena pro zobrazování a příjem dat ze stránek. Řekněme, že uživatel klikne na tlačítko přidat položku do košíku. Data budou přenesena z této stránky na jinou. V této složce je další složka `apiResponse`. Zde jsou data získávána ze souboru `Api` a předávána webové stránce. 

Třetí složka je `net`. Soubor API je určen k přijímání nebo odesílání dat od zprostředkovatele pomocí požadavků. Tento soubor je označen jako rozhraní a používá knihovnu `retrofit`. Ostatní soubory je potřeba k načtení dat z databáze.

Čtvrtá složka `receiver` obsahuje jeden soubor. Tento soubor je určen pro kontrolu aplikace na síťové připojení.

Pátá složka `repository` je určena k odeslání požadavků. Pro každou aktivitu je určen vlastní soubor Repository, ve kterém bude proveden požadavek.

V šesté složce `storage` jsou pouze 2 soubory. Soubor `LanguageUtils` je určen k uložení nebo načtení jazyka v obecných nastaveních. Předpokládejme, že klient v aplikaci změní jazyk z angličtiny na češtinu a nyní je nutné, aby tento jazyk byl v aplikaci konfigurován i po ukončení aplikace. Další soubor `LoginUtils` ukládá uživatelská data do místního úložiště zařízení. Děje se tak proto, aby uživatel při každém otevření aplikace nezadával svůj e-mail a heslo.

Sedmá složka `utils` obsahuje: 
-	CommunicateUtils – potřebné k tomu, aby uživatel mohl hodnotit aplikaci; 
-	Constant – zde je místní IP adresa; 
-	ImageUtils – soubor je vyžadován pro konverzi obrázků; 
-	InternetUtils – tento soubor kontroluje přístup k síti Internet; 
-	ProgressDialog – soubor pro dialogové okno s indikátorem průběhu; 
-	Slide – zobrazuje bannery v hlavní nabídce; 
-	Utils – soubor je navržen tak, aby bylo možné produkt sdílet. 
-	Validation – soubor ověří e-mail, heslo a jméno.

Osmou a nejdůležitější složkou je `view`, který obsahuje aktivitu aplikace. Hlavní činnosti jsou uvedeny níže:
-	AccountActivity – Tato aktivita otevře stránku uživatelského účtu. Jak je popsáno výše, zde jsou sekce, které pracují s použitím konstrukce skříně spínače. Zde se také provádí výběr jazyka pomocí funkce setLocale.
-	AddProductActivity – v této aktivitě je stránka přidání produktu do databáze. Po zadání všech údajů o produktu, aktivita zkontroluje, zda jsou prázdné hodnoty. Dále budou hodnoty odeslány do složky viewmodel.
-	AllClothesActivity, AllCosmeticsActivity – obě aktivity jsou si podobné, protože vypisují zboží na obrazovku. Aktivity odebere id ze souboru User, načte data pomocí názvu kategorie a id uživatele a zobrazí v layout souboru.
-	CartActivity, WishListActivity – aktivity zobrazí stránky koše a seznam přání. Aktivity kontroluje, zda je zboží v seznamu. Při načítání produktu se používá speciální indikátor.
-	HelpActivity – aktivity zobrazuje stránku FAQ. Zobrazení probíhá pomocí třídy ArrayList. Tato třída umožňuje vytvářet seznamy a je součástí knihovny Java.
-	AuthenticationActivity – otevře stránku ověřování přístupovým kódem. Aktivita zkontroluje pravost přístupového kódu a otevře časovač pro případ, že jej uživatel bude chtít odeslat znovu.
-	LoginActivity, SignUpActivity – otevřete přihlašovací a registrační stránku. Obě aktivity ověřují uživatelská data odesláním do souboru Validation.
-	DetailsActivity – aktivita, která zobrazuje všechny informace o produktu. Obrázek produktu se zobrazí pomocí knihovny Glide, která přečte odkaz na tento obrázek.
-	SearchActivity – zde můžete vyhledávat produkty podle klíčových slov. Když zadáte klíčové slovo, funkce se porovná s názvem produktu a zobrazí jej, pokud existuje nějaká shoda.

Poslední složka `viewmodel` obsahuje všechny objekty pro každou aktivitu. ViewModel jsou objekty, které poskytují data pro komponenty uživatelského rozhraní.

