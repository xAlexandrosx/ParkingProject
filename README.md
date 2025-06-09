##### Instukcja obsługi programu:
Po uruchomieniu zobaczysz w konsoli menu główne:

--- Parking System Menu ---
1. Register a car
2. Unregister a car
3. List parked cars
4. Advance time by 1 hour
5. Display parking history so far
6. Exit
Select option:

Wybierz opcję wpisując jej numer i potwierdzając Enterem.

Opcje menu:
1. Register a car
Dodaje nowy pojazd na parking.

Program poprosi o podanie typu pojazdu:
1 – Passenger
2 – Delivery
3 – Motorbike
4 – Electric

Wpisz numer rejestracyjny.

Jeśli taki pojazd już jest, dostaniesz komunikat o duplikacie.

2. Unregister a car
Usuwa pojazd z parkingu (trzeba podać jego numer rejestracyjny).

Jeśli pojazd przekroczył darmowy czas, opłata bonusowa zostanie naliczona i pokazana w liście zaparkowanych pojazdów oraz w historii parkingu po wyrejestrowaniu pojazdu.

3. List parked cars
Wyświetla wszystkie pojazdy aktualnie znajdujące się na parkingu, z informacjami o typie, rejestracji, godzinie wjazdu i naliczonych opłatach.

4. Advance time by 1 hour
Symuluje upływ godziny.

Każde przesunięcie czasu o godzinę powoduje sprawdzenie, czy któryś pojazd nie przekroczył darmowego czasu postoju.

Jeśli tak, naliczana jest dodatkowa opłata.

5. Display parking history so far
Pokazuje całą historię operacji na parkingu: wjazdy, wyjazdy, naliczone opłaty, podsumowanie dnia.

6. Exit
Kończy działanie programu.

Liczba miejsc na parkingu dla każdego typu pojazdu jest ograniczona (ustawione w kodzie, np. 2 na typ).

Jeden pojazd o danej rejestracji może być zaparkowany tylko raz.

Za parkowanie powyżej określonej liczby darmowych godzin (domyślnie 2) naliczana jest dodatkowa opłata.

Wszystkie operacje są zapisywane w historii, którą można przeglądać.

Program pilnuje, żeby nie dodać dwa razy pojazdu o tym samym numerze.

Każdy pojazd ma zapisaną godzinę wjazdu (symulacja czasu).

Każde przesunięcie czasu działa dla wszystkich pojazdów – mogą się pojawić nowe opłaty.

Wyrejestrowanie pojazdu z parkingu zapisuje wszystko w historii i podsumowuje ewentualne dodatkowe koszty.



##### Main
Uruchamia system parkingowy przez Menu. Nie posiada stanu ani logiki biznesowej.

##### Menu
Główna klasa uruchamiająca system. 
- void beginParking() – uruchamia interaktywne menu parkingowe. 
- Car handleUserChoice() - metoda obsługująca wybór rodzaju rejestrowanego pojazdu przez użytkownika, zwraca obiekt klasy Car odpowiadający wybranemu rodzajowi pojazdu.

##### Parking
służy do przechowywania obiektów i conajwyżej wypisywania jakie pojazdy są zaparkowane
- getPassengerCarSpots() – Zwraca listę samochodów osobowych zaparkowanych na parkingu.
- setPassengerCarSpots(List<Car> passengerCarSpots) – Ustawia listę samochodów osobowych na parkingu.
- getPassengerCarSpotsMax() – Zwraca maksymalną liczbę miejsc dla samochodów osobowych.
- getDeliveryCarSpots() – Zwraca listę samochodów dostawczych zaparkowanych na parkingu.
- setDeliveryCarSpots(List<Car> deliveryCarSpots) – Ustawia listę samochodów dostawczych na parkingu.
- getDeliveryCarSpotsMax() – Zwraca maksymalną liczbę miejsc dla samochodów dostawczych.
- getMotorbikeSpots() – Zwraca listę motocykli zaparkowanych na parkingu.
- setMotorbikeSpots(List<Car> motorbikeSpots) – Ustawia listę motocykli na parkingu.
- getMotorbikeSpotsMax() – Zwraca maksymalną liczbę miejsc dla motocykli.
- getElectricCarSpots() – Zwraca listę samochodów elektrycznych zaparkowanych na parkingu.
- setElectricCarSpots(List<Car> electricCarSpots) – Ustawia listę samochodów elektrycznych na parkingu.
- getElectricCarSpotsMax() – Zwraca maksymalną liczbę miejsc dla samochodów elektrycznych.
- listCars() – Wyświetla wszystkie zaparkowane pojazdy na parkingu.

##### ParkingDao
(Data access object) odpowiada za dodawanie i usuwanie pojazdów z parkingu, jest to po to aby oddzielić logikę dostępu do parkingu od reszty aplikacji. Przyjmuje jako parametr parking na którym operuje, oraz HistoryManager.

ParkingDao (interfejs):
- addCar(Car car) – Dodaje samochód dowolnego typu na parking.
- removeCar(Car car) – Usuwa samochód z parkingu (na podstawie obiektu).
- findCarByReg(Parking parking, String reg) – (statyczna) Szuka samochodu o danej rejestracji w parkingu i zwraca obiekt Car lub null.

ParkingDaoImpl (implementacja):
- ParkingDaoImpl(Parking parking, HistoryManager historyManager) – Konstruktor, przekazuje referencje do parkingu i historii.
- addCar(Car car) – Dodaje samochód na parking, sprawdza typ, zajętość miejsc, duplikaty, wyświetla błąd jeśli nie można dodać, inaczej dodaje i zapisuje do historii.
- removeCar(Car car) – Usuwa samochód z parkingu i zapisuje do historii.

##### HistoryManager
Zapisuje logi z działania aplikacji jako stringi umieszczane w ArrayList. Metoda addToHistory jest wywoływana w ParkingDao w momencie dodawania lub usuwania pojazdów z parkingu. Metoda generateEndOfDayReport wypisuje historię, oraz podsumowanie ile pojazdów obsłużono i ile overtime fee policzono
- addToHistory(Car car, String action) – Dodaje zdarzenie do historii (np. wjazd/wyjazd pojazdu, informacja o opłacie).
- getHistory() – Wyświetla wszystkie zapisane zdarzenia historii parkingu na konsoli.
- generateEndOfDayReport() – Zwraca tekstowy raport podsumowujący dzień: lista wszystkich zdarzeń, liczba zaparkowanych pojazdów i suma dodatkowych opłat (bonus fees).

##### FeeAdder
Klasa odpowiedzialna za overtime fees. Ma metodę do naliczania opłat oraz metodę do sprawdzania czy pojazd na parkingu kwalifikuje się do naliczenia mu opłaty.
- FeeAdderImpl(int feeValue, int freeHours, Parking parking) – Konstruktor, ustawia wysokość opłaty, liczbę darmowych godzin oraz referencję do parkingu.
- checkParking() – Pobiera wszystkie pojazdy z parkingu i dla każdego wywołuje addFee().
- addFee(Car car) – Dodaje opłatę bonusową dla pojazdu, jeśli przekroczono liczbę darmowych godzin (sprawdza checkFeeEligible()).
- checkFeeEligible(Car car) – Sprawdza, czy pojazd powinien dostać dodatkową opłatę (czy stoi dłużej niż dozwolone darmowe godziny).

##### Car
klasa modelowa. ma tylko podstawowe pola, gettery, settery itd. Posiada 4 klasy dziedziczące:
- Delivery
- Electric
- Motorbike
- Passenger

Klasy te nie wprowadzają dodatkowej logiki, ale ustalają dodatkową opłatę za parking w konstruktorze.

- getRegistration() – Zwraca numer rejestracyjny pojazdu.
- getTimeOfArrival() – Zwraca datę i godzinę wjazdu pojazdu.
- setTimeOfArrival(LocalDateTime timeOfArrival) – Ustawia datę i godzinę wjazdu pojazdu.
- getCarType() – Zwraca typ pojazdu.
- getBonusFee() – Zwraca aktualną naliczoną opłatę dodatkową za pojazd.
- setBonusFee(int bonusFee) – Ustawia wysokość naliczonej opłaty dodatkowej.
- equals(Object o) – Porównuje dwa pojazdy na podstawie pól: rejestracja, czas wjazdu, typ, bonusFee.
- hashCode() – Zwraca hash obiektu na podstawie pól klasy.
- toString() – Zwraca tekstowy opis pojazdu: rejestracja, typ, czas wjazdu, czas symulacji, naliczone opłaty.
- CarType – Enum opisujący typ pojazdu z kodem literowym („p”, „d”, „m”, „e”).

##### TimeSimulator
- localTime – Statyczne pole przechowujące aktualny czas symulacji (LocalDateTime).
- forwardTime() – Przesuwa czas symulacji o jedną godzinę do przodu.
- getFormattedLocalTime() – Zwraca aktualny czas symulacji jako sformatowany tekst.
- getCode() – Zwraca kod literowy danego typu pojazdu.

##### DuplicateRegistrationException – Własny wyjątek sygnalizujący próbę dodania pojazdu o już istniejącej rejestracji na parkingu.
Rzucany, gdy użytkownik próbuje zarejestrować pojazd z numerem rejestracyjnym, który już jest zajęty.