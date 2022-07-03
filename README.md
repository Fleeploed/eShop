# Mobilní aplikace eShop
Mobilní aplikace obsahuje možnost přidávat zboží do kategorií, registrovat uživatele a poskytovat údaje o stavu zboží a další funkce. Aplikace je zpracována v prostředí Android Studio a Visual Studio Code s pomocí jazyků Java, JavaScript a MySQL.

## Obsah
1. [Analýza požadavků](#Analýza-požadavků)
    1. [Funkční požadavky](#Funkční-požadavky)
    2. [Nefunkční požadavky](#Nefunkční-požadavky)
    3. [Použité technologie](#Použité-technologie)
2. [Návrh databáze](#Návrh-databáze)
3. [Uživatelské rozhraní](#Uživatelské-rozhraní)

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


