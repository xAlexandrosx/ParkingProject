##### Lista rzeczy do zrobienia:
- Diagram UML

#### Parking
służy do przechowywania obiektów i conajwyżej wypisywania jakie pojazdy są zaparkowane

#### ParkingDao
(Data access object) odpowiada za dodawanie i usuwanie pojazdów z parkingu, jest to po to aby oddzielić logikę dostępu do parkingu od reszty aplikacji. Przyjmuje jako parametr parking na którym operuje, oraz HistoryManager.

#### HistoryManager
Zapisuje logi z działania aplikacji jako stringi umieszczane w ArrayList. Metoda addToHistory jest wywoływana w ParkingDao w momencie dodawania lub usuwania pojazdów z parkingu. Metoda generateEndOfDayReport wypisuje historię, oraz podsumowanie ile pojazdów obsłużono i ile overtime fee policzono

#### FeeAdder
Klasa odpowiedzialna za overtime fees. Ma metodę do naliczania opłat oraz metodę do sprawdzania czy pojazd na parkingu kwalifikuje się do naliczenia mu opłaty.

#### Car
klasa modelowa. ma tylko podstawowe pola, gettery, settery itd.

#### TimeSimulator
statyczne pole localtime i metoda do symulowania upływania czasu.

#### Main
klasa rozruchowa z konsolowym menu.